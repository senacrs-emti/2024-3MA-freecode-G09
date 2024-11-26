package com.example.proswim.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class VideoItem {

    fun getVideos(): Flow<MutableList<Video>>{
        val listaVideos = mutableListOf(
            Video(
                videoUrl = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/DmWWqogr_r8?si=b7tfFQaoyBf8UNrd\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>",
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