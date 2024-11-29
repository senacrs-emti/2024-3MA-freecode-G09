package com.example.proswim;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Arrays;
import java.util.List;

public class TutoriaisFragment extends Fragment {

    private RecyclerView recyclerView;
    private ExercicioAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tutoriais, container, false);

        // Configurar RecyclerView
        recyclerView = view.findViewById(R.id.exerciseRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        // Adicionar o PagerSnapHelper para rolagem de estilo "paginado"
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        // Lista de vídeos e títulos
        List<Integer> videos = Arrays.asList(R.raw.exercise1, R.raw.exercise2, R.raw.exercise3);
        List<String> titles = Arrays.asList("Exercício 1", "Exercício 2", "Exercício 3");

        // Configurar o Adapter
        adapter = new ExercicioAdapter(videos, titles);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
