package com.assignment.heady.db;

public class VariantModel {

    private int id;
    private String color;
    private int  size;
    private int price;

    public VariantModel(int id, String color, int size, int price){
        this.id  = id;
        this.color  = color;
        this.size  = size;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }

    public int getPrice() {
        return price;
    }
}
