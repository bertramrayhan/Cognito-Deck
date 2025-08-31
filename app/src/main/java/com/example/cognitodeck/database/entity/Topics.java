package com.example.cognitodeck.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
    tableName = "topics",
    foreignKeys = @ForeignKey(
        entity = Themes.class,
        parentColumns = "theme_id",
        childColumns = "theme_id",
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    ),
    indices = {@Index(value = "theme_id")}
)
public class Topics {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "topic_id")
    private int topicId;

    @ColumnInfo(name = "topic_name")
    private String topicName;

    @ColumnInfo(name = "theme_id")
    private int themeId;

    public Topics(int topicId, String topicName, int themeId) {
        this.topicId = topicId;
        this.topicName = topicName;
        this.themeId = themeId;
    }

    @Ignore
    public Topics(String topicName, int themeId) {
        this.topicName = topicName;
        this.themeId = themeId;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }
}