package com.assignment.heady.db;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface RankingModelDao {

    @Query("select * from RankingModel")
    LiveData<List<RankingModel>> getAllItems();

    @Query("select * from RankingModel where ranking = :ranking")
    LiveData<RankingModel> getItembyId(String ranking);

    @Insert(onConflict = REPLACE)
    void addData(RankingModel rankingModel);

    @Delete
    void deleteData(RankingModel rankingModel);

    @Query("DELETE FROM RankingModel")
    void deleteAllData();

}
