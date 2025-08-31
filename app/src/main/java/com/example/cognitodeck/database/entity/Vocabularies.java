package com.example.cognitodeck.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "vocabularies")
public class Vocabularies {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "vocab_id")
    private int vocabId;

    @ColumnInfo(name = "expression")
    private String expression;

    @ColumnInfo(name = "meaning")
    private String meaning;

    @ColumnInfo(name = "language_code")
    private String languageCode;

    @ColumnInfo(name = "reading")
    private String reading;

    public Vocabularies(int vocabId, String expression, String meaning, String languageCode, String reading) {
        this.vocabId = vocabId;
        this.expression = expression;
        this.meaning = meaning;
        this.languageCode = languageCode;
        this.reading = reading;
    }

    @Ignore
    public Vocabularies(String expression, String meaning, String languageCode, String reading) {
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

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getReading() {
        return reading;
    }

    public void setReading(String reading) {
        this.reading = reading;
    }
}
