package com.example.cognitodeck.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

@Entity(
        tableName = "topic_vocabulary_relation",
        primaryKeys = {"topic_id", "vocab_id"},
        foreignKeys = {
                @ForeignKey(
                        entity = Topics.class,
                        parentColumns = "topic_id",
                        childColumns = "topic_id",
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = Vocabularies.class,
                        parentColumns = "vocab_id",
                        childColumns = "vocab_id",
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE
                )
        },
        indices = {@Index(value = "topic_id"), @Index(value = "vocab_id")}
)
public class TopicVocabularyRelation {
    @ColumnInfo(name = "topic_id")
    private int topicId;

    @ColumnInfo(name = "vocab_id")
    private int vocabId;

    public TopicVocabularyRelation(int topicId, int vocabId) {
        this.topicId = topicId;
        this.vocabId = vocabId;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public int getVocabId() {
        return vocabId;
    }

    public void setVocabId(int vocabId) {
        this.vocabId = vocabId;
    }
}
