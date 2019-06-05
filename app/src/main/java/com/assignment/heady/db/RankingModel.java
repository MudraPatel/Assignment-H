package com.assignment.heady.db;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity
public class RankingModel {

//    @PrimaryKey(autoGenerate = true)
//    private int id;
    @NonNull
    @PrimaryKey
    private String ranking;
    @TypeConverters(ProductConverter.class)
    @SerializedName("products")
    private List<ProductModel> productModels;

    public RankingModel( String ranking, List<ProductModel> productModels) {
//        this.id = id;
        this.ranking = ranking;
        this.productModels = productModels;
    }

    public String getRanking() {
        return ranking;
    }

    public List<ProductModel> getProductModels() {
        return productModels;
    }

//    public int getId() {
//        return id;
//    }
}
