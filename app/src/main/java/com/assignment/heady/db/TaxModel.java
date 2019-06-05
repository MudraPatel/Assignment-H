package com.assignment.heady.db;

public class TaxModel {

    private String name;
    private float value;

    public TaxModel( String name, float value){
        this.name = name;
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
