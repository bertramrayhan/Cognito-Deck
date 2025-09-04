package com.example.cognitodeck.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.cognitodeck.database.dao.ThemesDao;
import com.example.cognitodeck.database.dao.TopicsDao;
import com.example.cognitodeck.database.entity.Themes;
import com.example.cognitodeck.database.entity.TopicVocabularyRelation;
import com.example.cognitodeck.database.entity.Topics;
import com.example.cognitodeck.database.entity.Vocabularies;

@Database(entities = {
        Themes.class,
        Topics.class,
        Vocabularies.class,
        TopicVocabularyRelation.class
}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ThemesDao themesDao();
    public abstract TopicsDao topicsDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "cognito_deck.db")
                            .createFromAsset("database/cognito_deck_db.sqlite3")
                            // .fallbackToDestructiveMigration() // Opsi jika ada perubahan versi (akan kita bahas nanti)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
