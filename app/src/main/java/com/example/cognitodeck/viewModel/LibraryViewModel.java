package com.example.cognitodeck.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.cognitodeck.database.AppDatabase;
import com.example.cognitodeck.database.dao.ThemesDao;
import com.example.cognitodeck.database.entity.LibraryListItem;
import com.example.cognitodeck.database.entity.ThemeWithTopics;
import com.example.cognitodeck.database.entity.TopicDisplayItem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LibraryViewModel extends AndroidViewModel {
    private MutableLiveData<List<LibraryListItem>> libraryListItemLiveData = new MutableLiveData<>();
    private ThemesDao themesDao;
    private ExecutorService executor;

    public LibraryViewModel(@NonNull Application application) {
        super(application);
        AppDatabase db = AppDatabase.getDatabase(application);
        themesDao = db.themesDao();
        executor = Executors.newSingleThreadExecutor();

        loadLibraryData();
    }

    public LiveData<List<LibraryListItem>> getLibraryListItemLiveData() {
        return libraryListItemLiveData;
    }

    public void loadLibraryData() {
        executor.execute(() -> {
            List<ThemeWithTopics> themesWithTopicsList = themesDao.getAllThemesWithTopics();

            List<LibraryListItem> finalItemsForAdapter = new ArrayList<>();
            for (ThemeWithTopics themeWithTopics : themesWithTopicsList) {
                finalItemsForAdapter.add(themeWithTopics.theme);


                for (int index = 0; index < themeWithTopics.topics.size(); index++) {
                    TopicDisplayItem.PositionInGroup POSITION;

                    if(themeWithTopics.topics.size() == 1){
                        POSITION = TopicDisplayItem.PositionInGroup.SINGLE;
                    }else if(index == 0){
                        POSITION = TopicDisplayItem.PositionInGroup.FIRST;
                    }else if(index == themeWithTopics.topics.size() - 1){
                        POSITION = TopicDisplayItem.PositionInGroup.LAST;
                    }else {
                        POSITION = TopicDisplayItem.PositionInGroup.MIDDLE;
                    }

                    TopicDisplayItem topicDisplayItem = new TopicDisplayItem(themeWithTopics.topics.get(index), POSITION);

                    finalItemsForAdapter.add(topicDisplayItem);

                    Log.d("library fragment, Topic : ", themeWithTopics.topics.get(index).getTopicName());
                }
            }

            libraryListItemLiveData.postValue(finalItemsForAdapter);
        });
    }
}
