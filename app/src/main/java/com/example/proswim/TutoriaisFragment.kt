package com.example.proswim

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proswim.adapter.VideoAdapter
import com.example.proswim.databinding.FragmentTutoriaisBinding
import com.example.proswim.model.Video
import com.example.proswim.model.VideoItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TutoriaisFragment : Fragment(R.layout.fragment_tutoriais) {

    private lateinit var binding: FragmentTutoriaisBinding
    private lateinit var videoAdapter: VideoAdapter
    private val videoItem = VideoItem()
    private val listaVideos: MutableList<Video> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTutoriaisBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch {
            videoItem.getVideos().collect{
                for (video in it){
                    listaVideos.add(video)
                }
            }
        }

        val recyclerViewVideos = binding.recyclerViewVideos
        recyclerViewVideos.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewVideos.setHasFixedSize(true)
        recyclerViewVideos.setRecycledViewPool(RecyclerView.RecycledViewPool())
        videoAdapter = VideoAdapter(requireContext(), listaVideos)
        recyclerViewVideos.adapter = videoAdapter

    }
}