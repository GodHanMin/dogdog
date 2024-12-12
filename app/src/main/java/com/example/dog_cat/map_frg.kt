package com.example.dog_cat

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment

class map_frg : Fragment() {

    private lateinit var contentLayout: LinearLayout

    // ActivityResultLauncher를 사용해 데이터를 받음
    private val writeMemoLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == -1) {
            val data: Intent? = result.data
            val imageUri = data?.getStringExtra("imageUri")?.let { Uri.parse(it) }
            val placeName = data?.getStringExtra("placeName")
            val placeAddress = data?.getStringExtra("placeAddress")
            val placeMemo = data?.getStringExtra("placeMemo")

            addContent(imageUri, placeName, placeAddress, placeMemo)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_map_frg, container, false)

        // 컨텐츠 레이아웃 초기화
        contentLayout = view.findViewById(R.id.content_layout)

        // 버튼 클릭 리스너 설정
        val writeButton: Button = view.findViewById(R.id.btn_write)
        writeButton.setOnClickListener {
            val intent = Intent(requireContext(), write_memo::class.java)
            writeMemoLauncher.launch(intent)
        }

        return view
    }

    private fun addContent(imageUri: Uri?, placeName: String?, placeAddress: String?, placeMemo: String?) {
        //  뷰를 동적으로 생성
        val itemView = layoutInflater.inflate(R.layout.item_memo, contentLayout, false)

        val imageView: ImageView = itemView.findViewById(R.id.thumbnail)
        val nameView: TextView = itemView.findViewById(R.id.title)
        val addressView: TextView = itemView.findViewById(R.id.address)
        val memoView: TextView = itemView.findViewById(R.id.memo)

        // 데이터 설정
        imageUri?.let { imageView.setImageURI(it) }
        nameView.text = placeName ?: "장소 이름 없음"
        addressView.text = placeAddress ?: "주소 없음"
        memoView.text = placeMemo ?: "특징 없음"

        // 레이아웃에 추가
        contentLayout.addView(itemView)
    }
}
