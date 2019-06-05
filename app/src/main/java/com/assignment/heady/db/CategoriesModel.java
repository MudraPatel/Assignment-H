package com.assignment.heady.db;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity
public class CategoriesModel {

    @PrimaryKey
    private int id;
    private String name;
    @TypeConverters(ProductConverter.class)
    @SerializedName("products")
    private List<ProductModel> products;
    @TypeConverters(ProductConverter.class)
    @SerializedName("child_categories")
    private List<Integer> childCategories;

    public CategoriesModel(int id, String name, List<ProductModel> products, List<Integer> childCategories){
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public List<ProductModel> getProducts() {
        return products;
    }

    public List<Integer> getChildCategories() {
        return childCategories;
    }
}
