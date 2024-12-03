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

public class MobilidadeFragment extends Fragment {

    private RecyclerView recyclerView;
    private MobilidadeExercicioAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mobilidade, container, false);

        // Configurar RecyclerView
        RecyclerView basicStretchRecyclerView1 = view.findViewById(R.id.basicStretchRecyclerView1);
        RecyclerView advancedMobilityRecyclerView2 = view.findViewById(R.id.advancedMobilityRecyclerView2);
        RecyclerView checklistRecyclerView = view.findViewById(R.id.checklistRecyclerView);

        basicStretchRecyclerView1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        advancedMobilityRecyclerView2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        checklistRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        // Adicionar o PagerSnapHelper para rolagem estilo página
        PagerSnapHelper snapHelper1 = new PagerSnapHelper();
        PagerSnapHelper snapHelper2 = new PagerSnapHelper();
        PagerSnapHelper snapHelper3 = new PagerSnapHelper();

        snapHelper1.attachToRecyclerView(basicStretchRecyclerView1);
        snapHelper2.attachToRecyclerView(advancedMobilityRecyclerView2);
        snapHelper3.attachToRecyclerView(checklistRecyclerView);

        // Lista de vídeos e títulos
        List<Integer> exercisevideos1 = Arrays.asList(R.raw.videomobilidade1, R.raw.videomobilidade2, R.raw.videomobilidade3);
        List<String> exercisetitles1 = Arrays.asList("Alongamento de Ombro com Elastico", "Rotação de Quadril", "Alongamento Posterior de Quadril"); //Os títulos
        List<Integer> exercisecovers1 = Arrays.asList(R.drawable.capa1, R.drawable.capa2, R.drawable.capa3); // As capas

        List<Integer> exercisevideos2 = Arrays.asList(R.raw.videomobilidade4, R.raw.videomobilidade5, R.raw.videomobilidade6);
        List<String> exercisetitles2 = Arrays.asList("Liberação com 'Foam Roller'", "Flexão + Rotação de Quadril", "Braçada no Elástico"); //Os títulos
        List<Integer> exercisecovers2 = Arrays.asList(R.drawable.capa4, R.drawable.capa5, R.drawable.capa6); // As capas

        // Configurar o Adapter
        MobilidadeExercicioAdapter exerciseadapter1  = new MobilidadeExercicioAdapter(exercisevideos1, exercisetitles1, exercisecovers1);
        MobilidadeExercicioAdapter exerciseadapter2  = new MobilidadeExercicioAdapter(exercisevideos2, exercisetitles2, exercisecovers2);

        // Adicionar Adapters aos RecyclerViews
        basicStretchRecyclerView1.setAdapter(exerciseadapter1);
        advancedMobilityRecyclerView2.setAdapter(exerciseadapter2);

        return view;
    }
}
