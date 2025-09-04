package com.example.cognitodeck.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.cognitodeck.database.entity.ThemeWithTopics;
import com.example.cognitodeck.database.entity.Themes;

import java.util.List;

@Dao
public interface ThemesDao {
    @Insert
    void insert(Themes theme);

    @Update
    void update(Themes theme);

    @Delete
    void delete(Themes theme);

    @Transaction
    @Query("SELECT * FROM themes ORDER BY theme_name ASC")
    List<ThemeWithTopics> getAllThemesWithTopics();

    @Query("SELECT * FROM themes WHERE theme_id = :id")
    Themes getThemeById(int id);

    @Query("DELETE FROM themes")
    void deleteAllThemes();
}
