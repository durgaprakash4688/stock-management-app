package com.smi.StockManagementApp.DowJonesIndex.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="data_set",
        schema = "stockmanagement",
        uniqueConstraints = @UniqueConstraint(columnNames = "data_set_id")
)
public class DataSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "data_set_id", unique = true, nullable = false)
    private long dataSetId;

    @Column(name = "stock", nullable = false)
    private String stockTicker;

    @Column(name = "quarter")
    private String quarter;

    @Column(name = "date")
    private String date;

    @Column(name = "open")
    private String open;

    @Column(name = "high")
    private String high;

    @Column(name = "low")
    private String low;

    @Column(name = "close")
    private String close;

    @Column(name = "volume")
    private String volume;

    @Column(name = "previous_weeks_volume")
    private String previousStockVolume;

    @Column(name = "next_weeks_open")
    private String nextWeekOpen;

    @Column(name = "next_weeks_close")
    private String nextWeekClose;

    @Column(name = "percent_change_next_weeks_price")
    private String percentChangeNextWeekPrice;

}
