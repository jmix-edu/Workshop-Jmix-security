package com.company.demoapplication.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.metamodel.annotation.JmixProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "STORAGE")
@Entity
public class Storage {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @InstanceName
    @Column(name = "STORAGE_NAME", nullable = false)
    @NotNull
    private String storageName;

    @OneToMany(mappedBy = "storage")
    private List<Vehicle> vehicles;

    @Column(name = "NUMBER_OF_SLOTS", nullable = false)
    @NotNull
    private Integer numberOfSlots;

    @Transient
    @JmixProperty
    private Integer availableSlots;

    public void setNumberOfSlots(@NotNull Integer numberOfSlots) {
        this.numberOfSlots = numberOfSlots;
    }

    public @NotNull Integer getNumberOfSlots() {
        return numberOfSlots;
    }

    public Integer getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(Integer availableSlots) {
        this.availableSlots = availableSlots;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public String getStorageName() {
        return storageName;
    }

    public void setStorageName(String storageName) {
        this.storageName = storageName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @PostLoad
    public void postLoad() {
        this.availableSlots = numberOfSlots - vehicles.size();
    }
}