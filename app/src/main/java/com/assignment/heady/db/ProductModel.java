package com.assignment.heady.db;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity
public class ProductModel {

    @PrimaryKey
    private int id;
    private int categoriesId;
    private String name;
    @SerializedName("date_added")
    private String dateAdded;
    @TypeConverters(ProductConverter.class)
    @SerializedName("variants")
    private List<VariantModel> variantModel;
    @TypeConverters(ProductConverter.class)
    @SerializedName("tax")
    private TaxModel taxModel;
    @SerializedName("view_count")
    private int viewCount;

    public ProductModel(int id,  String name, String dateAdded, List<VariantModel> variantModel, TaxModel taxModel) {
        this.id = id;
        this.name = name;
        this.dateAdded = dateAdded;
        this.variantModel = variantModel;
        this.taxModel = taxModel;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public List<VariantModel> getVariantModel() {
        return variantModel;
    }

    public TaxModel getTaxModel() {
        return taxModel;
    }


    public int getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(int categoriesId) {
        this.categoriesId = categoriesId;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }
}
