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
            YoutubeVideo("초보 보호자라면 꼭 봐야하는 5가지 주의사항", R.drawable.youtu_1, "https://www.youtube.com/watch?v=vUzlV1xwc38"),
            YoutubeVideo("강아지 보호자들이 나도 모르게 하는 실수", R.drawable.youtu3, "https://www.youtube.com/watch?v=dVJTmn0n7Pc"),
            YoutubeVideo("강아지 혼자 놔두고 갈떄 주의사항 4가지", R.drawable.youtu4, "https://www.youtube.com/watch?v=lYjXMLfzO4E"),
            YoutubeVideo("강아지가 행복할떄 하는 행동 9가지", R.drawable.youtu5, "https://www.youtube.com/watch?v=PsujKxMzNEs"),
            YoutubeVideo("[#티전드] 나는 몰랐던 반려견의 속마음\uD83E\uDD7A '강아지 강씨' 강형욱이 이것만은 꼭 지켜달라는 상식은? 강아지 백과사전 A~Z까지 ", R.drawable.youtu2, "https://www.youtube.com/watch?v=vUzlV1xwc38"),
        )

        adapter = YoutubeAdapter(requireContext(), videoList)
        recyclerView.adapter = adapter

        return view
    }
}
