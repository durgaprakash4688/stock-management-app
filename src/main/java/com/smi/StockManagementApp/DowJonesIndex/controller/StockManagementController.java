package com.smi.StockManagementApp.DowJonesIndex.controller;

import com.smi.StockManagementApp.DowJonesIndex.model.DataSet;
import com.smi.StockManagementApp.DowJonesIndex.service.DataSetService;
import lombok.extern.java.Log;
import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@Log
public class StockManagementController {

    @Autowired
    private DataSetService dataSetService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        try {
            DataSet dataSet = new DataSet();
            model.addAttribute("dataSet", dataSet);
        } catch (Exception e) {
            model.addAttribute("message", "Unable to process the request");
            log.info(e.getMessage());
        }
        return "index";
    }

    @GetMapping("/viewAllDataSets")
    public String viewAllDataSets(Model model) {
        try {
            model.addAttribute("listDataSets", dataSetService.getAllDataSets());
        } catch (Exception e) {
            model.addAttribute("message", "Unable to process the request");
            log.info(e.getMessage());
        }

        return "viewAllDataSets";
    }

    @GetMapping("/viewUploadPage")
    public String viewUploadDataSetPage(Model model) {
        return "uploadDataSets";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model) {
        try {
            List<DataSet> dataSetList = new ArrayList<>();
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            XSSFSheet worksheet = workbook.getSheetAt(0);
            Iterator<Row> dataSetIterator = worksheet.iterator();

            while (dataSetIterator.hasNext()) {
                Row currentRow = dataSetIterator.next();
                if (currentRow.getRowNum() != 0) {
                    //Iterator<Cell> cellIterator = currentRow.iterator();
                    DataSet dataSet = DataSet.builder()
                            .stockTicker(currentRow.getCell(0).getStringCellValue())
                            .quarter(String.valueOf(currentRow.getCell(1).getNumericCellValue()))
                            .date(currentRow.getCell(2).getStringCellValue())
                            .open(String.valueOf(currentRow.getCell(3).getNumericCellValue()))
                            .high(String.valueOf(currentRow.getCell(4).getNumericCellValue()))
                            .low(String.valueOf(currentRow.getCell(5).getNumericCellValue()))
                            .close(String.valueOf(currentRow.getCell(6).getNumericCellValue()))
                            .volume(String.valueOf(currentRow.getCell(7).getNumericCellValue()))
                            .previousStockVolume(String.valueOf(currentRow.getCell(8).getNumericCellValue()))
                            .nextWeekOpen(String.valueOf(currentRow.getCell(9).getNumericCellValue()))
                            .nextWeekClose(String.valueOf(currentRow.getCell(10).getNumericCellValue()))
                            .percentChangeNextWeekPrice(String.valueOf(currentRow.getCell(11).getNumericCellValue()))
                            .build();
                    dataSetList.add(dataSet);
                }
            }
            dataSetService.saveDataSet(dataSetList);
            model.addAttribute("message", "File has been uploaded successfully");
        }catch(NotOfficeXmlFileException fe){
            model.addAttribute("message", "Please upload a valid file!!");
        }
        catch (Exception e) {
            model.addAttribute("message", "Unable to upload the file");
            log.info(e.getMessage());
        }
        return "uploadDataSets";
    }

    @PostMapping("/showfilteredDataSet")
    public String findDataSet(@ModelAttribute("stockTicker") String stockTicker, Model model) {
        try {
            model.addAttribute("listDataSets", dataSetService.findByStockTicker(stockTicker));
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        return "findDataSet";
    }

    @GetMapping("/showNewDataSet")
    public String showNewDataSet(Model model){
        DataSet dataSet = new DataSet();
        model.addAttribute("dataSet", dataSet);
        return "showNewDataSet";
    }

    @PostMapping("/saveNewDataSet")
    public String saveNewDataSet(@ModelAttribute("dataSet") DataSet dataSet, Model model){
        try{
            if(StringUtils.hasText(dataSet.getStockTicker())){
                dataSetService.saveDataSet(dataSet);
                return "redirect:/viewAllDataSets";
            }else{
                model.addAttribute("message", "Stock Ticker cannot be empty");
                return "showNewDataSet";
            }
        }catch(Exception e) {
            log.info(e.getMessage());
            model.addAttribute("message", "Please enter a valid input");
        }
        return "showNewDataSet";
    }
}
