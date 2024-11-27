package com.example.proswim.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class VideoItem {

    fun getVideos(): Flow<MutableList<Video>>{
        val listaVideos = mutableListOf(
            Video(
                videoUrl = "https://www.youtube.com/embed/jDabVNrSiwA?si=30WIfCGQsbWgdApU",
                titulo = "21 Savage - a lot (Official Video) ft. J. Cole"
            ),
            Video(
                videoUrl = "",
                titulo = ""
            ),
            Video(
                videoUrl = "",
                titulo = ""
            ),
            Video(
                videoUrl = "",
                titulo = ""
            )
        )
        return MutableStateFlow(listaVideos)
    }
}