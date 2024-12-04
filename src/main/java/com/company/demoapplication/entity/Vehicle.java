package com.company.demoapplication.entity;

import io.jmix.core.MetadataTools;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.metamodel.datatype.DatatypeFormatter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@JmixEntity
@Table(name = "VEHICLE", indexes = {
        @Index(name = "IDX_VEHICLE_MODEL", columnList = "MODEL_ID"),
        @Index(name = "IDX_VEHICLE_STORAGE", columnList = "STORAGE_ID")
})
@Entity
public class Vehicle {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "ON_SALE", nullable = false)
    @NotNull
    private Boolean onSale = false;

    @JoinColumn(name = "MODEL_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Model model;

    @Column(name = "MOVED_DATE")
    private LocalDateTime movedDate;

    @Column(name = "BUYING_PRICE", nullable = false, precision = 19, scale = 2)
    @NotNull
    private BigDecimal buyingPrice;

    @Column(name = "SELLING_PRICE", nullable = false, precision = 19, scale = 2)
    @NotNull
    private BigDecimal sellingPrice;

    @JoinColumn(name = "STORAGE_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Storage storage;

    @Pattern(regexp = "^\\d{4}$")
    @Column(name = "MANUFACTURING_YEAR")
    private String manufacturingYear;

    @NotNull
    @Column(name = "MILEAGE", nullable = false)
    private Integer mileage;

    public Boolean getOnSale() {
        return onSale;
    }

    public void setOnSale(Boolean onSale) {
        this.onSale = onSale;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public Integer getMileage() {
        return mileage;
    }

    public String getManufacturingYear() {
        return manufacturingYear;
    }

    public void setManufacturingYear(String manufacturingYear) {
        this.manufacturingYear = manufacturingYear;
    }

    public LocalDateTime getMovedDate() {
        return movedDate;
    }

    public void setMovedDate(LocalDateTime movedDate) {
        this.movedDate = movedDate;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public BigDecimal getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(BigDecimal buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @InstanceName
    @DependsOnProperties({"model", "manufacturingYear", "mileage"})
    public String getInstanceName(MetadataTools metadataTools, DatatypeFormatter datatypeFormatter) {
        return String.format("%s %s %s",
                metadataTools.format(model),
                metadataTools.format(manufacturingYear),
                datatypeFormatter.formatInteger(mileage));
    }
}