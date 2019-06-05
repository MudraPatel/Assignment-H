package com.assignment.heady.db;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface ProductModelDao {

    @Query("select * from ProductModel")
    LiveData<List<ProductModel>> getAllItems();

    @Query("select * from ProductModel where id = :id")
    LiveData<ProductModel> getItembyId(int id);

    @Query("select * from ProductModel where categoriesId = :id")
    LiveData<List<ProductModel>> getItembyCategoryId(int id);

    @Query("SELECT * FROM ProductModel WHERE id IN(:ids)")
    public abstract LiveData<List<ProductModel>> findByProductIds(int[] ids);


    @Insert(onConflict = REPLACE)
    void addData(ProductModel productModel);

    @Delete
    void deleteData(ProductModel productModel);

    @Query("DELETE FROM ProductModel")
    void deleteAllData();

}
