package com.assignment.heady.db;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface CategoriesModelDao {

    @Query("select * from CategoriesModel")
    LiveData<List<CategoriesModel>> getAllItems();

    @Query("select * from CategoriesModel where id = :id")
    LiveData<CategoriesModel> getItembyId(int id);

    @Insert(onConflict = REPLACE)
    void addData(CategoriesModel categoriesModel);

    @Delete
    void deleteData(CategoriesModel categoriesModel);

    @Query("DELETE FROM CategoriesModel")
    void deleteAllData();

}
