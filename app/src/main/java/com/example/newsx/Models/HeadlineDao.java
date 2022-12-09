package com.example.newsx.Models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface HeadlineDao {
    @Query("SELECT * FROM Headline")
    List<Headline> getall();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Headline headline);

    @Update
    void update(Headline headline);

    @Delete
    void delete(Headline headline);
}
