package com.assignment.heady.db;

import android.os.Build;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.room.TypeConverter;

public class CategoriesConverter {

    @TypeConverter
    public List<CategoriesModel> storedStringToCriteria(String value) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<CategoriesModel>>() {}.getType();
        return gson.fromJson(value, type);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @TypeConverter
    public String criteriaToStoredString(List<CategoriesModel> categoriesModels) {

       Gson gson = new Gson();
        return gson.toJson(categoriesModels);
    }


    @TypeConverter
    public List<RankingModel> storedStringToRanking(String value) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<RankingModel>>() {}.getType();
        return gson.fromJson(value, type);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @TypeConverter
    public String rankingToStoredString(List<RankingModel> categoriesModels) {

        Gson gson = new Gson();
        return gson.toJson(categoriesModels);
    }

}
