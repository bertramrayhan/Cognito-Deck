package com.example.cognitodeck.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cognitodeck.R;

public class VocabularyListFragment extends Fragment {

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
}