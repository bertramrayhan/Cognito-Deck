package com.example.cognitodeck.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Objects;

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

    @NonNull
    @ColumnInfo(name = "topic_name")
    private String topicName;

    @ColumnInfo(name = "theme_id")
    private int themeId;

    public Topics(int topicId, @NonNull String topicName, int themeId) {
        this.topicId = topicId;
        this.topicName = topicName;
        this.themeId = themeId;
    }

    @Ignore
    public Topics(@NonNull String topicName, int themeId) {
        this.topicName = topicName;
        this.themeId = themeId;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    @NonNull
    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(@NonNull String topicName) {
        this.topicName = topicName;
    }

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Topics topics = (Topics) o;
        return themeId == topics.themeId && Objects.equals(topicName, topics.topicName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topicName, themeId);
    }
}