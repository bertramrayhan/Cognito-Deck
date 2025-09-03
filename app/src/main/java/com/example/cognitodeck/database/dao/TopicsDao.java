package com.example.cognitodeck.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cognitodeck.database.entity.Topics;

import java.util.List;

@Dao
public interface TopicsDao {
    @Insert
    void insert(Topics topic);

    @Update
    void update(Topics topic);

    @Delete
    void delete(Topics topic);

    //Query annotation for custom query rather than Insert, Delete, or Update
    @Query("SELECT * FROM topics ORDER BY topic_name ASC")
    List<Topics> getAllTopics();

    @Query("SELECT * FROM topics WHERE topic_id = :id")
    Topics getTopicsById(int id);

    @Query("DELETE FROM topics")
    void deleteAllTopics();
}
