package com.company.demoapplication.entity;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum EngineType implements EnumClass<String> {

    GASOLINE("G"),
    DISEL("D"),
    ELECTRIC("E");

    private final String id;

    EngineType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static EngineType fromId(String id) {
        for (EngineType at : EngineType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}