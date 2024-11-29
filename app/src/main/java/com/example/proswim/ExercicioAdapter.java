package com.example.proswim;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ExercicioAdapter extends RecyclerView.Adapter<ExercicioAdapter.ExercicioViewHolder> {

    private final List<Integer> videos; // Lista de vídeos
    private final List<String> titles; // Lista de títulos

    // Construtor do Adapter
    public ExercicioAdapter(List<Integer> videos, List<String> titles) {
        this.videos = videos;
        this.titles = titles;
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
        // Configurar o VideoView e o título
        holder.videoTitle.setText(titles.get(position));
        holder.videoView.setVideoPath("android.resource://" + holder.itemView.getContext().getPackageName() + "/" + videos.get(position));
        MediaController mediaController = new MediaController(holder.itemView.getContext());
        mediaController.setAnchorView(holder.videoView);
        holder.videoView.setMediaController(mediaController);
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public static class ExercicioViewHolder extends RecyclerView.ViewHolder {
        VideoView videoView;
        TextView videoTitle;

        public ExercicioViewHolder(View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.videoview);
            videoTitle = itemView.findViewById(R.id.video_title);
        }
    }
}
