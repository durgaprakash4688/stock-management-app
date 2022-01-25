package com.smi.StockManagementApp.DowJonesIndex.service;

import com.smi.StockManagementApp.DowJonesIndex.model.DataSet;

import java.util.List;

public interface DataSetService {
    List<DataSet> getAllDataSets();

    void saveDataSet(List<DataSet> dataSets);

    void saveDataSet(DataSet dataSet);

    List<DataSet> findByStockTicker(String stockTicker);
}
