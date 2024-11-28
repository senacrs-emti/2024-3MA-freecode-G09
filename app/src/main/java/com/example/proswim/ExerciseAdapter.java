package com.example.proswim;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;
import android.widget.MediaController;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {

    private List<Exercise> exerciseList;

    public ExerciseAdapter(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item, parent, false);
        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        Exercise exercise = exerciseList.get(position);

        // Atribuindo o vídeo ao VideoView
        Uri videoUri = Uri.parse("android.resource://" + holder.itemView.getContext().getPackageName() + "/" + exercise.getVideoResourceId());


        // Configuração do MediaController
        MediaController mediaController = new MediaController(holder.itemView.getContext(), null);
        mediaController.setAnchorView(holder.videoView);
        holder.videoView.setMediaController(mediaController);

        // Impedindo o vídeo de iniciar automaticamente
        holder.videoView.setOnPreparedListener(mp -> mp.pause());  // Garante que o vídeo seja pausado na inicialização

        // Exibir título ou outra informação do exercício se necessário
        holder.titleTextView.setText(exercise.getTitle());

    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }

    public static class ExerciseViewHolder extends RecyclerView.ViewHolder {
        VideoView videoView;
        TextView titleTextView;

        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.videoView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
        }
    }
}

