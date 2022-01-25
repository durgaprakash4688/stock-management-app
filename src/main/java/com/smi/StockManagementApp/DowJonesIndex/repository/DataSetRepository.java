package com.smi.StockManagementApp.DowJonesIndex.repository;

import com.smi.StockManagementApp.DowJonesIndex.model.DataSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DataSetRepository extends JpaRepository<DataSet, Long> {

    Optional<List<DataSet>> findByStockTicker(String stockTicker);
}
