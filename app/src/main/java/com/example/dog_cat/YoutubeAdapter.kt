package com.example.dog_cat

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class YoutubeAdapter(
    private val context: Context,
    private val videoList: List<YoutubeVideo>
) : RecyclerView.Adapter<YoutubeAdapter.YoutubeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YoutubeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_youtube_video, parent, false)
        return YoutubeViewHolder(view)
    }

    override fun onBindViewHolder(holder: YoutubeViewHolder, position: Int) {
        val video = videoList[position]
        holder.title.text = video.title
        holder.thumbnail.setImageResource(video.thumbnailResId) // 썸네일 리소스 설정
        holder.itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(video.url))
            context.startActivity(intent) // 유튜브 링크 열기
        }
    }

    override fun getItemCount(): Int = videoList.size

    class YoutubeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val thumbnail: ImageView = itemView.findViewById(R.id.thumbnail)
        val title: TextView = itemView.findViewById(R.id.title)
    }
}

data class YoutubeVideo(val title: String, val thumbnailResId: Int, val url: String)
