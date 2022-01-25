package com.smi.StockManagementApp.DowJonesIndex.service;

import com.smi.StockManagementApp.DowJonesIndex.fixtures.StockManagementFixtures;
import com.smi.StockManagementApp.DowJonesIndex.model.DataSet;
import com.smi.StockManagementApp.DowJonesIndex.repository.DataSetRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DataSetServiceImplTest {

    @InjectMocks
    private DataSetServiceImpl dataSetService;

    @Mock
    private DataSetRepository dataSetRepository;

    @Test
    public void save_allDataSet_test(){
        Mockito.when(dataSetRepository.saveAll(Mockito.anyCollection())).thenReturn(StockManagementFixtures.FRAME_DATA_SET_LIST());
        dataSetService.saveDataSet(StockManagementFixtures.FRAME_DATA_SET_LIST());
    }

    @Test
    public void findByStockTicker_test(){

        Mockito.when(dataSetRepository.findByStockTicker(Mockito.anyString())).thenReturn(StockManagementFixtures.FRAME_DATA_SET_LIST_OPTONAL());
        List<DataSet> dataSetList = dataSetService.findByStockTicker("AAPL");
        Assertions.assertThat(dataSetList.get(0).getDate()).isEqualTo(StockManagementFixtures.FRAME_DATA_SET_LIST_OPTONAL().get().get(0).getDate());
    }

    @Test
    public void get_listOfDataSets_test(){
        Mockito.when(dataSetRepository.findAll()).thenReturn(StockManagementFixtures.FRAME_DATA_SET_LIST());
        Assertions.assertThat(dataSetService.getAllDataSets().get(0)).isNotNull();
    }
}
