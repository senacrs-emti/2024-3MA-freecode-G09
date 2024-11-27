package com.example.proswim.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proswim.databinding.VideoItemBinding
import com.example.proswim.model.Video
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.FrameLayout

class VideoAdapter (private val context: Context, private val listaVideos: MutableList<Video>):
    RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val itemLista = VideoItemBinding.inflate(LayoutInflater.from(context),parent, false)
        return VideoViewHolder((itemLista))
    }

    override fun getItemCount() = listaVideos.size

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        // Carrega a URL do vídeo no WebView
        val videoUrl = listaVideos[position].videoUrl
        if (!videoUrl.isNullOrEmpty()) {
            holder.video.loadUrl(videoUrl)
        }
        holder.tituloVideo.text = listaVideos[position].titulo
        configurarWebView(holder.video)
    }

    inner class VideoViewHolder(binding: VideoItemBinding): RecyclerView.ViewHolder(binding.root){
        val video = binding.webView
        val tituloVideo = binding.titulovideo
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun configurarWebView(webView: WebView) {

        // Habilita o JavaScript para permitir o carregamento correto dos vídeos
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true // Permite o armazenamento local

        // Configuração do WebChromeClient para a tela cheia
        webView.webChromeClient = object : WebChromeClient() {

            // Método chamado quando o vídeo é colocado em tela cheia
            override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
                super.onShowCustomView(view, callback)

                //Ajustar o layout para tela cheia
                if (view != null) {
                    val frameLayout = view as FrameLayout
                    val params = FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.MATCH_PARENT
                    )
                    // Ajustando o WebView para ocupar toda a tela
                    webView.layoutParams = params
                }
            }
            // Método chamado quando o vídeo sai da tela cheia
            override fun onHideCustomView() {
                super.onHideCustomView()

                // Restaura o layout original
                val params = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT
                )
                webView.layoutParams = params
            }
        }
    }
}