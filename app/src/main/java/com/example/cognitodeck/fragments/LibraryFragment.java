package com.example.cognitodeck.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
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
import com.example.cognitodeck.data.DummyLibraryData;
import com.example.cognitodeck.database.AppDatabase;
import com.example.cognitodeck.database.dao.ThemesDao;
import com.example.cognitodeck.database.entity.LibraryListItem;
import com.example.cognitodeck.database.entity.ThemeWithTopics;
import com.example.cognitodeck.database.entity.TopicDisplayItem;
import com.example.cognitodeck.viewModel.LibraryViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LibraryFragment extends Fragment {

    private static final int TESTING = 0;

    private LibraryViewModel libraryViewModel;
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

        if(TESTING == 1){
            List<LibraryListItem> dummyItems = DummyLibraryData.getDummyLibraryItems();
            libraryAdapter.setLibraryItemList(dummyItems);
        }else {
            libraryViewModel = new ViewModelProvider(this).get(LibraryViewModel.class);

            libraryViewModel.getLibraryListItemLiveData().observe(getViewLifecycleOwner(), new Observer<List<LibraryListItem>>() {

                @Override
                public void onChanged(List<LibraryListItem> libraryListItems) {
                    libraryAdapter.setLibraryItemList(libraryListItems);
                }
            });
        }


    }
}