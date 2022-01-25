package com.smi.StockManagementApp.DowJonesIndex.repository;

import com.smi.StockManagementApp.DowJonesIndex.fixtures.StockManagementFixtures;
import com.smi.StockManagementApp.DowJonesIndex.model.DataSet;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class DataSetRepositoryTest {

    @Mock
    private DataSetRepository dataSetRepository;

    @Test
    public void getDataSets_test(){

        Mockito.when(dataSetRepository.findByStockTicker(Mockito.anyString())).thenReturn(StockManagementFixtures.FRAME_DATA_SET_LIST_OPTONAL());
        List<DataSet> dataSet = dataSetRepository.findByStockTicker("AAPL").get();
        Assertions.assertThat(dataSet).isNotNull();

    }
}