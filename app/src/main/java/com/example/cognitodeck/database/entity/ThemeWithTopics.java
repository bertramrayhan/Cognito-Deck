package com.example.cognitodeck.database.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class ThemeWithTopics {
    @Embedded
    public Themes theme;

    @Relation(
            parentColumn = "theme_id",
            entityColumn = "theme_id"
    )
    public List<Topics> topics;
}
