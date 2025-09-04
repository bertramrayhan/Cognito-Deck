package com.example.cognitodeck.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "themes")
public class Themes implements LibraryListItem{
    //tipe data primitif seharusnya tidak usah diberi non null
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "theme_id")
    private int themeId;

    @NonNull
    @ColumnInfo(name = "theme_name")
    private String themeName;

    public Themes(@NonNull int themeId, @NonNull String themeName) {
        this.themeId = themeId;
        this.themeName = themeName;
    }

    @Ignore
    public Themes(@NonNull String themeName) {
        this.themeName = themeName;
    }

    @NonNull
    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(@NonNull int themeId) {
        this.themeId = themeId;
    }

    @NonNull
    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(@NonNull String themeName) {
        this.themeName = themeName;
    }
}
