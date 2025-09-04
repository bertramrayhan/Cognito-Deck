package com.example.cognitodeck.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cognitodeck.R;
import com.example.cognitodeck.adapter.LibraryAdapter;
import com.example.cognitodeck.database.AppDatabase;
import com.example.cognitodeck.database.dao.ThemesDao;
import com.example.cognitodeck.database.dao.TopicsDao;
import com.example.cognitodeck.database.entity.LibraryListItem;
import com.example.cognitodeck.database.entity.ThemeWithTopics;
import com.example.cognitodeck.database.entity.Themes;
import com.example.cognitodeck.database.entity.TopicDisplayItem;
import com.example.cognitodeck.database.entity.Topics;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LibraryFragment extends Fragment {

    private RecyclerView libraryRecyclerView;
    private LibraryAdapter libraryAdapter;

    public static LibraryFragment newInstance(String param1, String param2) {
        LibraryFragment fragment = new LibraryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public LibraryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_library, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        libraryRecyclerView = view.findViewById(R.id.libraryRecyclerView);
        libraryRecyclerView.setLayoutManager(new LinearLayoutManager(this.requireContext()));

        libraryAdapter = new LibraryAdapter(new ArrayList<>());
        libraryRecyclerView.setAdapter(libraryAdapter);

        AppDatabase db = AppDatabase.getDatabase(requireActivity().getApplicationContext());
        ThemesDao themesDao = db.themesDao();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            List<ThemeWithTopics> themesWithTopicsList = themesDao.getAllThemesWithTopics();

            List<LibraryListItem> finalItemsForAdapter = new ArrayList<>();
            for (ThemeWithTopics themeWithTopics : themesWithTopicsList) {
                finalItemsForAdapter.add(themeWithTopics.theme);

                Log.d("library fragment, Theme : ", themeWithTopics.theme.getThemeName());
                Log.d("library fragment, length Topic : ", String.valueOf(themeWithTopics.topics.size()));

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

            handler.post(() -> {
                libraryAdapter.setLibraryItemList(finalItemsForAdapter);
            });
        });

//        // --- BUAT DATA PALSU DI SINI ---
//        List<LibraryListItem> dummyItems = new ArrayList<>();
//
//        // --- Tema 1: JLPT ---
//        Themes theme1 = new Themes(1, "UJIAN JLPT"); // Buat objek Themes palsu
//        dummyItems.add(theme1);
//
//        // Buat beberapa topik palsu untuk tema 1
//        Topics n5 = new Topics(1, "N5", 1);
//        Topics n4 = new Topics(2, "N4", 1);
//
//        // Tambahkan ke daftar dengan logika posisi
//        dummyItems.add(new TopicDisplayItem(n5, TopicDisplayItem.PositionInGroup.FIRST));
//        dummyItems.add(new TopicDisplayItem(n4, TopicDisplayItem.PositionInGroup.LAST));
//
//
//        // --- Tema 2: Kehidupan Sehari-hari ---
//        Themes theme2 = new Themes(2, "KEHIDUPAN SEHARI-HARI"); // Buat objek Themes palsu kedua
//        dummyItems.add(theme2);
//
//        // Buat beberapa topik palsu untuk tema 2
//        Topics kuliner = new Topics(3, "Area Kuliner", 2);
//        Topics transportasi = new Topics(4, "Area Transportasi", 2);
//        Topics belanja = new Topics(5, "Area Belanja", 2);
//
//        // Tambahkan ke daftar dengan logika posisi
//        dummyItems.add(new TopicDisplayItem(kuliner, TopicDisplayItem.PositionInGroup.FIRST));
//        dummyItems.add(new TopicDisplayItem(transportasi, TopicDisplayItem.PositionInGroup.MIDDLE));
//        dummyItems.add(new TopicDisplayItem(belanja, TopicDisplayItem.PositionInGroup.LAST));
//
//
//        // --- Tema 3: Hanya Satu Topik (untuk menguji kasus 'SINGLE') ---
//        Themes theme3 = new Themes(3, "KOSAKATA KHUSUS");
//        dummyItems.add(theme3);
//
//        Topics slang = new Topics(6, "Bahasa Gaul (Slang)", 3);
//        dummyItems.add(new TopicDisplayItem(slang, TopicDisplayItem.PositionInGroup.SINGLE));
//
//
//        // Langsung berikan data palsu ke adapter
//        libraryAdapter.setLibraryItemList(dummyItems);
    }
}