package com.example.proswim;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Arrays;
import java.util.List;

public class TutoriaisFragment extends Fragment {

    private RecyclerView recyclerView;
    private TutoriaisExercicioAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tutoriais, container, false);

        // Configurar RecyclerView
        RecyclerView exerciseRecyclerView1 = view.findViewById(R.id.exerciseRecyclerView1);
        RecyclerView exerciseRecyclerView2 = view.findViewById(R.id.exerciseRecyclerView2);
        RecyclerView exerciseRecyclerView3 = view.findViewById(R.id.exerciseRecyclerView3);

        exerciseRecyclerView1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        exerciseRecyclerView2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        exerciseRecyclerView3.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        // Adicionar o PagerSnapHelper para rolagem estilo página
        PagerSnapHelper snapHelper1 = new PagerSnapHelper();
        PagerSnapHelper snapHelper2 = new PagerSnapHelper();
        PagerSnapHelper snapHelper3 = new PagerSnapHelper();

        snapHelper1.attachToRecyclerView(exerciseRecyclerView1);
        snapHelper2.attachToRecyclerView(exerciseRecyclerView2);
        snapHelper3.attachToRecyclerView(exerciseRecyclerView3);

        // Lista de vídeos e títulos
        List<Integer> exercisevideos1 = Arrays.asList(R.raw.videoexercise1, R.raw.videoexercise2, R.raw.videoexercise3);
        List<String> exercisetitles1 = Arrays.asList("Como fazer a braçada", "Como respirar durante o nado", "Como bater perna corretamente"); //Os títulos
        List<Integer> exercisecovers1 = Arrays.asList(R.drawable.capa1, R.drawable.capa2, R.drawable.capa3); // As capas

        List<Integer> exercisevideos2 = Arrays.asList(R.raw.videoexercise4, R.raw.videoexercise5, R.raw.videoexercise6);
        List<String> exercisetitles2 = Arrays.asList("Como fazer a virada", "Como executar o 'StreamLine'", "Como melhorar a posição da cabeça"); //Os títulos
        List<Integer> exercisecovers2 = Arrays.asList(R.drawable.capa4, R.drawable.capa5, R.drawable.capa6); // As capas

        List<Integer> exercisevideos3 = Arrays.asList(R.raw.videoexercise7, R.raw.videoexercise8, R.raw.videoexercise9);
        List<String> exercisetitles3 = Arrays.asList("Aprenda os 4 tipos de nados", "Principais materiais na natação", "Como fazer a saída do bloco"); //Os títulos
        List<Integer> exercisecovers3 = Arrays.asList(R.drawable.capa7, R.drawable.capa8, R.drawable.capa9); // As capas

        // Configurar o Adapter
        TutoriaisExercicioAdapter exerciseadapter1  = new TutoriaisExercicioAdapter(exercisevideos1, exercisetitles1, exercisecovers1);
        TutoriaisExercicioAdapter exerciseadapter2  = new TutoriaisExercicioAdapter(exercisevideos2, exercisetitles2, exercisecovers2);
        TutoriaisExercicioAdapter exerciseadapter3  = new TutoriaisExercicioAdapter(exercisevideos3, exercisetitles3, exercisecovers3);

        // Adicionar Adapters aos RecyclerViews
        exerciseRecyclerView1.setAdapter(exerciseadapter1);
        exerciseRecyclerView2.setAdapter(exerciseadapter2);
        exerciseRecyclerView3.setAdapter(exerciseadapter3);

        return view;
    }
}
