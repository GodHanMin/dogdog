package com.example.dog_cat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class youtube : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: YoutubeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_youtube, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val videoList = listOf(
            YoutubeVideo("첫 번째 영상", R.drawable.baseline_ondemand_video_24, "https://www.youtube.com/watch?v=zEtSQG_CG5Q&t=8766s"),
            YoutubeVideo("두 번째 영상", R.drawable.baseline_ondemand_video_24, "https://www.youtube.com/watch?v=0l2N2CEiniY"),
        )

        adapter = YoutubeAdapter(requireContext(), videoList)
        recyclerView.adapter = adapter

        return view
    }
}
