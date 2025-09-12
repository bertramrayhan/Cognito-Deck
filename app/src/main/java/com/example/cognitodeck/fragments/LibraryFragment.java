package com.example.cognitodeck.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cognitodeck.MainActivity;
import com.example.cognitodeck.R;
import com.example.cognitodeck.adapter.LibraryAdapter;
import com.example.cognitodeck.data.DummyLibraryData;
import com.example.cognitodeck.database.entity.LibraryListItem;
import com.example.cognitodeck.database.entity.TopicDisplayItem;
import com.example.cognitodeck.viewModel.LibraryViewModel;
import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;
import java.util.List;

public class LibraryFragment extends Fragment implements LibraryAdapter.TopicClickListener {

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

        libraryAdapter = new LibraryAdapter(this);
        libraryRecyclerView.setAdapter(libraryAdapter);

        if(TESTING == 1){
            List<LibraryListItem> dummyItems = DummyLibraryData.getDummyLibraryItems();
            libraryAdapter.submitList(dummyItems);
        }else {
            libraryViewModel = new ViewModelProvider(this).get(LibraryViewModel.class);

            // instant get to prevent flickering
            List<LibraryListItem> currentData = libraryViewModel.getLibraryListItemLiveData().getValue();
            if (currentData != null && !currentData.isEmpty()) {
                libraryAdapter.submitList(currentData);
            }

            // observe for new data
            libraryViewModel.getLibraryListItemLiveData().observe(getViewLifecycleOwner(), libraryListItems -> {
                libraryAdapter.submitList(libraryListItems);
            });
        }
    }

    @Override
    public void onTopicClick(TopicDisplayItem topic) {
        int topicId = topic.getTopic().getTopicId();

        NavController navController = Navigation.findNavController(requireView());

        Bundle args = new Bundle();
        args.putInt("topicId", topicId);

        navController.navigate(R.id.action_navigationLibrary_to_vocabularyListFragment, args);
    }
}