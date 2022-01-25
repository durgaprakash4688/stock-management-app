package com.smi.StockManagementApp.DowJonesIndex.service;

import com.smi.StockManagementApp.DowJonesIndex.model.DataSet;
import com.smi.StockManagementApp.DowJonesIndex.repository.DataSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class to manage the Dow Jones Stock Index Data Sets
 */
@Service
public class DataSetServiceImpl implements DataSetService{

    @Autowired
    private DataSetRepository dataSetRepository;

    @Override
    public List<DataSet> getAllDataSets() {

        return dataSetRepository.findAll();
    }

    @Override
    public void saveDataSet(List<DataSet> dataSets) {
        this.dataSetRepository.saveAll(dataSets);
    }

    @Override
    public void saveDataSet(DataSet dataSet) {
        this.dataSetRepository.save(dataSet);
    }

    @Override
    public List<DataSet> findByStockTicker(String stockTicker) {
        Optional<List<DataSet>> optionalDataSets = this.dataSetRepository.findByStockTicker(stockTicker);
        List<DataSet> datasets = null;

        if(optionalDataSets.isPresent()){
            datasets = optionalDataSets.get();
        }else{
            throw new RuntimeException("Stock Ticker not found --> "+stockTicker);
        }
        return datasets;
    }


}
