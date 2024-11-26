package com.example.proswim.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proswim.databinding.VideoItemBinding
import com.example.proswim.model.Video
import android.content.Context
import android.view.LayoutInflater

class VideoAdapter (private val context: Context, private val listaVideos: MutableList<Video>):
    RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val itemLista = VideoItemBinding.inflate(LayoutInflater.from(context),parent, false)
        return VideoViewHolder((itemLista))
    }

    override fun getItemCount(): = listaVideos.size

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.video.loadData(listaVideos[position].videoUrl!!,"text/html", "utf-8")
        holder.tituloVideo.text = listaVideos[position].titulo
    }

    inner class VideoViewHolder(binding: VideoItemBinding): RecyclerView.ViewHolder(binding.root){
        val video = binding.webView
        val tituloVideo = binding.titulovideo
    }
}