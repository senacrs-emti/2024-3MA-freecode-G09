package com.example.proswim;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ExercicioAdapter extends RecyclerView.Adapter<ExercicioAdapter.ExercicioViewHolder> {

    private final List<Integer> videos; // Lista de vídeos
    private final List<String> titles; // Lista de títulos
    private final List<Integer> covers; // Lista de capas

    // Construtor do Adapter
    public ExercicioAdapter(List<Integer> videos, List<String> titles, List<Integer> covers) {
        this.videos = videos; // Atribuir a lista dos vídeos
        this.titles = titles; // Atribuir a lista dos títulos
        this.covers = covers; // Atribuir a lista de capas
    }

    @NonNull
    @Override
    public ExercicioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar o layout para cada item (card)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_video, parent, false);
        return new ExercicioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExercicioViewHolder holder, int position) {
        // Titulo
        holder.videoTitle.setText(titles.get(position));
        // Caminho do vídeo
        holder.videoView.setVideoPath("android.resource://" + holder.itemView.getContext().getPackageName() + "/" + videos.get(position));

        // Configurar MediaController
        MediaController mediaController = new MediaController(holder.itemView.getContext());
        mediaController.setAnchorView(holder.videoView);
        holder.videoView.setMediaController(mediaController);

        // Configurar capa
        holder.videoCover.setImageResource(covers.get(position));
        holder.videoCover.setVisibility(View.VISIBLE); // Mostrar a capa
        holder.videoView.setVisibility(View.GONE); // Esconder o vídeo inicialmente

        // Se o usuário clicar na capa
        holder.videoCover.setOnClickListener(v -> {
            holder.videoCover.setVisibility(View.GONE); // Esconde a capa
            holder.videoView.setVisibility(View.VISIBLE); // Mostra o VideoView
            holder.videoView.start(); // Inicia o vídeo

        });
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public static class ExercicioViewHolder extends RecyclerView.ViewHolder {
        VideoView videoView;
        TextView videoTitle;
        ImageView videoCover;

        public ExercicioViewHolder(View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.videoview);
            videoTitle = itemView.findViewById(R.id.video_title);
            videoCover = itemView.findViewById(R.id.video_cover);
        }
    }
}
