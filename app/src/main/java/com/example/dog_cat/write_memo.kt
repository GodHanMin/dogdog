package com.example.dog_cat

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class write_memo : AppCompatActivity() {

    private lateinit var imgSave: ImageView
    private lateinit var placeNameField: EditText
    private lateinit var placeAddressField: EditText
    private lateinit var placeMemoField: EditText

    private var imageUri: Uri? = null

    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            imageUri = data?.data
            imgSave.setImageURI(imageUri)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_memo)

        // 필드 초기화
        imgSave = findViewById(R.id.img_save)
        placeNameField = findViewById(R.id.place_name)
        placeAddressField = findViewById(R.id.place_address)
        placeMemoField = findViewById(R.id.place_memo)

        // 이미지 선택 리스너
        imgSave.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK).apply { type = "image/*" }
            pickImageLauncher.launch(intent)
        }

        // 저장 버튼 클릭 시 데이터 반환
        val saveButton: Button = findViewById(R.id.btn_save)
        saveButton.setOnClickListener {
            val intent = Intent().apply {
                putExtra("imageUri", imageUri?.toString())
                putExtra("placeName", placeNameField.text.toString())
                putExtra("placeAddress", placeAddressField.text.toString())
                putExtra("placeMemo", placeMemoField.text.toString())
            }
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}
