package com.smi.StockManagementApp.DowJonesIndex.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Validated
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

    @NotBlank
    @Column(name = "stock", nullable = false)
    private String stockTicker;

    @Valid
    @Column(name = "quarter")
    private String quarter;

    @Valid
    @Column(name = "date")
    private String date;

    @Valid
    @Column(name = "open")
    private String open;

    @Valid
    @Column(name = "high")
    private String high;

    @Valid
    @Column(name = "low")
    private String low;

    @Valid
    @Column(name = "close")
    private String close;

    @Valid
    @Column(name = "volume")
    private String volume;

    @Valid
    @Column(name = "previous_weeks_volume")
    private String previousStockVolume;

    @Valid
    @Column(name = "next_weeks_open")
    private String nextWeekOpen;

    @Valid
    @Column(name = "next_weeks_close")
    private String nextWeekClose;

    @Valid
    @Column(name = "percent_change_next_weeks_price")
    private String percentChangeNextWeekPrice;

}
