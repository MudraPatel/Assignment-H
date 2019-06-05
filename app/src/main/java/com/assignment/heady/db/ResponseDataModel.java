package com.assignment.heady.db;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import androidx.room.TypeConverters;

public class ResponseDataModel {

    @TypeConverters(CategoriesConverter.class)
    @SerializedName("categories")
    private List<CategoriesModel> categoriesModel;
    @TypeConverters(CategoriesConverter.class)
    @SerializedName("rankings")
    private List<RankingModel> rankingModels;


    public ResponseDataModel(List<CategoriesModel> categoriesModel, List<RankingModel> rankingModels){
        this.categoriesModel = categoriesModel;
        this.rankingModels = rankingModels;
    }

    public List<CategoriesModel> getCategoriesModel() {
        return categoriesModel;
    }

    public List<RankingModel> getRankingModels() {
        return rankingModels;
    }
}
