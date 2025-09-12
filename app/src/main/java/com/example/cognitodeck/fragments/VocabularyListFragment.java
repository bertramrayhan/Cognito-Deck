package com.example.cognitodeck.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cognitodeck.R;
import com.google.android.material.appbar.MaterialToolbar;

public class VocabularyListFragment extends Fragment {

    private int topicId;
    private MaterialToolbar toolbar;
    private NavController navController;

    public VocabularyListFragment() {
    }

    public static VocabularyListFragment newInstance(String param1, String param2) {
        VocabularyListFragment fragment = new VocabularyListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vocabulary_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getArguments() != null){
            topicId = getArguments().getInt("topicId");
        }

        toolbar = view.findViewById(R.id.toolbarVocabularyList);
        navController = Navigation.findNavController(view);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);

            actionBar.setTitle(String.valueOf(topicId));
        }

        toolbar.setNavigationOnClickListener(v -> {
            navController.navigateUp();
        });
    }
}