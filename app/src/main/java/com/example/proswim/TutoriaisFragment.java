package com.example.proswim;

import static androidx.browser.customtabs.CustomTabsClient.getPackageName;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class TutoriaisFragment extends Fragment {

    private RecyclerView recyclerView;
    private ExerciseAdapter exerciseAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tutoriais, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Dados de exemplo
        List<Exercise> exerciseList = new ArrayList<>();
        exerciseList.add(new Exercise("Jumping Jacks", R.raw.exercise1));
        exerciseList.add(new Exercise("Push Ups", R.raw.exercise2));
        exerciseList.add(new Exercise("Plank Hold", R.raw.exercise3));

        exerciseAdapter = new ExerciseAdapter(exerciseList);
        recyclerView.setAdapter(exerciseAdapter);

        return view;
    }
}