package com.assignment.heady.db;

import android.os.Build;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.room.TypeConverter;

public class ProductConverter {

    @TypeConverter
    public List<ProductModel> storedStringToProduct(String value) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<ProductModel>>() {}.getType();
        return gson.fromJson(value, type);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @TypeConverter
    public String productToStoredString(List<ProductModel> model) {

       Gson gson = new Gson();
        return gson.toJson(model);
    }


    @TypeConverter
    public List<Integer> storedStringToChildCategories(String value) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Integer>>() {}.getType();
        return gson.fromJson(value, type);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @TypeConverter
    public String ChildCategoriesToStoredString(List<Integer> model) {

        Gson gson = new Gson();
        return gson.toJson(model);
    }


    @TypeConverter
    public List<VariantModel> storedStringToVariant(String value) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<VariantModel>>() {}.getType();
        return gson.fromJson(value, type);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @TypeConverter
    public String variantToStoredString(List<VariantModel> model) {

        Gson gson = new Gson();
        return gson.toJson(model);
    }

    @TypeConverter
    public TaxModel storedStringToTax(String value) {
        Gson gson = new Gson();
        Type type = new TypeToken<TaxModel>() {}.getType();
        return gson.fromJson(value, type);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @TypeConverter
    public String taxToStoredString(TaxModel model) {

        Gson gson = new Gson();
        return gson.toJson(model);
    }
}
