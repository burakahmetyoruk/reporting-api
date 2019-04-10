package com.financialhouse.reporting.model.enumaration;

public enum Operation {
    DIRECT("DIRECT"),
    REFUND("REFUND"),
    THREED("3D"),
    THREEDAUTH("3DAUTH"),
    STORED("STORED");

    private String value;

    private Operation(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
