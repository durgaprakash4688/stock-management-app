package com.smi.StockManagementApp.DowJonesIndex.fixtures;

import com.smi.StockManagementApp.DowJonesIndex.model.DataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StockManagementFixtures {

    public static final String STOCK_STICKER = "AAPL";

    public static final String INVALID_STOCK_TICKER = "ABCD";

    public static final DataSet FRAME_DATA_SET_1() {
        DataSet dataSet = DataSet.builder()
                .stockTicker("ORCL")
                .quarter("1")
                .date("2022-01-28")
                .open("100")
                .high("102")
                .low("99")
                .close("100")
                .volume("20")
                .previousStockVolume("15")
                .nextWeekOpen("102")
                .nextWeekClose("105")
                .percentChangeNextWeekPrice("1")
                .build();

        return dataSet;
    }

    public static final DataSet FRAME_DATA_SET_2() {
        DataSet dataSet = DataSet.builder()
                .stockTicker("ORCL")
                .quarter("1")
                .date("2022-01-28")
                .open("100")
                .high("102")
                .low("99")
                .close("100")
                .volume("20")
                .previousStockVolume("15")
                .nextWeekOpen("102")
                .nextWeekClose("105")
                .percentChangeNextWeekPrice("1")
                .build();

        return dataSet;
    }

    public static List<DataSet> FRAME_DATA_SET_LIST(){
        List<DataSet> dataSetList = new ArrayList<>();
        dataSetList.add(FRAME_DATA_SET_1());
        dataSetList.add(FRAME_DATA_SET_2());

        return dataSetList;
    }

    public static Optional<List<DataSet>> FRAME_DATA_SET_LIST_OPTONAL(){
        List<DataSet> dataSetList = new ArrayList<>();
        dataSetList.add(FRAME_DATA_SET_1());
        dataSetList.add(FRAME_DATA_SET_2());

        return Optional.of(dataSetList);
    }
}
