package com.example.cognitodeck.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "themes")
public class Themes implements LibraryListItem{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "theme_id")
    private int themeId;

    @ColumnInfo(name = "theme_name")
    private String themeName;

    public Themes(int themeId, String themeName) {
        this.themeId = themeId;
        this.themeName = themeName;
    }

    @Ignore
    public Themes(String themeName) {
        this.themeName = themeName;
    }

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }
}
