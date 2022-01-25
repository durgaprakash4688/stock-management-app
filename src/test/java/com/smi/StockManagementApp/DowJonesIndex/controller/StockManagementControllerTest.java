package com.smi.StockManagementApp.DowJonesIndex.controller;

import com.smi.StockManagementApp.DowJonesIndex.fixtures.StockManagementFixtures;
import com.smi.StockManagementApp.DowJonesIndex.model.DataSet;
import com.smi.StockManagementApp.DowJonesIndex.service.DataSetService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(StockManagementController.class)
public class StockManagementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DataSetService dataSetService;

    @Autowired
    private StockManagementController stockManagementController;


    @Test
    public void should_return_index_view() throws Exception{
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("http://localhost:8080").flashAttr("dataSet", new DataSet()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"));
    }

    @Test
    public void should_return_view_all_data_sets_view() throws Exception{
        Mockito.when(dataSetService.getAllDataSets()).thenReturn(StockManagementFixtures.FRAME_DATA_SET_LIST());
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("http://localhost:8080/viewAllDataSets"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("viewAllDataSets"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("listDataSets"));
    }

    @Test
    public void should_return_view_upload_page_view() throws Exception{
        Mockito.when(dataSetService.getAllDataSets()).thenReturn(StockManagementFixtures.FRAME_DATA_SET_LIST());
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("http://localhost:8080/viewUploadPage"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("uploadDataSets"));
    }

    @Test
    public void should_return_upload_page_view() throws Exception{

        MockMultipartFile uploadFile = new MockMultipartFile("file", "DowJonesIndex.xlsx", "xlsx", "xlsx".getBytes());
        //Mockito.doThrow(new RuntimeException()).when(dataSetService).saveDataSet(Mockito.anyList());
        this.mockMvc
                .perform(MockMvcRequestBuilders.multipart("http://localhost:8080/upload")
                        .file(uploadFile))
                .andExpect(MockMvcResultMatchers.model().attributeExists("message"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("uploadDataSets"));;
    }

    @Test
    public void should_return_upload_page_view_failure() throws Exception{
        MockMultipartFile uploadFile = new MockMultipartFile("file", "DowJonesIndex.xlsx", "xlsx", "xlsx".getBytes());
        Mockito.doThrow(new RuntimeException()).when(dataSetService).saveDataSet(Mockito.anyList());
        this.mockMvc
                .perform(MockMvcRequestBuilders.multipart("http://localhost:8080/upload")
                        .file(uploadFile))
                .andExpect(MockMvcResultMatchers.model().attributeExists("message"))
                .andExpect(MockMvcResultMatchers.view().name("uploadDataSets"));;
    }

    @Test
    public void should_return_show_data_set_page_view() throws Exception{
        Mockito.when(dataSetService.getAllDataSets()).thenReturn(StockManagementFixtures.FRAME_DATA_SET_LIST());
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("http://localhost:8080/showfilteredDataSet")
                        .requestAttr("stockTicker", String.class))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("findDataSet"));
    }


    @Test
    public void should_return_show_new_data_set_page_view() throws Exception{
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("http://localhost:8080/showNewDataSet"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("showNewDataSet"));
    }

    @Test
    public void should_return_save_new_data_set_page_failure_view() throws Exception{
        Mockito.when(dataSetService.getAllDataSets()).thenReturn(StockManagementFixtures.FRAME_DATA_SET_LIST());
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("http://localhost:8080/saveNewDataSet"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("showNewDataSet"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("message"));
    }
}
