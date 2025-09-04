package com.example.cognitodeck.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "vocabularies")
public class Vocabularies {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "vocab_id")
    private int vocabId;

    @NonNull
    @ColumnInfo(name = "expression")
    private String expression;

    @NonNull
    @ColumnInfo(name = "meaning")
    private String meaning;

    @NonNull
    @ColumnInfo(name = "language_code")
    private String languageCode;

    @NonNull
    @ColumnInfo(name = "reading")
    private String reading;

    public Vocabularies(int vocabId, @NonNull String expression, @NonNull String meaning, @NonNull String languageCode, @NonNull String reading) {
        this.vocabId = vocabId;
        this.expression = expression;
        this.meaning = meaning;
        this.languageCode = languageCode;
        this.reading = reading;
    }

    @Ignore
    public Vocabularies(@NonNull String expression, @NonNull String meaning, @NonNull String languageCode, @NonNull String reading) {
        this.expression = expression;
        this.meaning = meaning;
        this.languageCode = languageCode;
        this.reading = reading;
    }

    public int getVocabId() {
        return vocabId;
    }

    public void setVocabId(int vocabId) {
        this.vocabId = vocabId;
    }

    @NonNull
    public String getExpression() {
        return expression;
    }

    public void setExpression(@NonNull String expression) {
        this.expression = expression;
    }

    @NonNull
    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(@NonNull String meaning) {
        this.meaning = meaning;
    }

    @NonNull
    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(@NonNull String languageCode) {
        this.languageCode = languageCode;
    }

    @NonNull
    public String getReading() {
        return reading;
    }

    public void setReading(@NonNull String reading) {
        this.reading = reading;
    }
}
