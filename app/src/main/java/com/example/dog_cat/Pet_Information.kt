package com.example.dog_cat

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.icu.util.Calendar
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.github.dhaval2404.imagepicker.ImagePicker

class Pet_Information : AppCompatActivity() {

    private lateinit var startDateText: TextView
    private lateinit var startDateBtn: ImageView


    private lateinit var button_img: Button
    private lateinit var imageView: ImageView


    // ActivityResultLauncher로 이미지 선택 결과 처리
    private val imagePickerLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            val uri: Uri? = data?.data
            imageView.setImageURI(uri)  // 선택한 이미지 표시
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_information)


        val btnMale = findViewById<AppCompatButton>(R.id.btn_Male)
        val btnFemale = findViewById<AppCompatButton>(R.id.btn_Female)

        val btn_neutralize = findViewById<AppCompatButton>(R.id.btn_neutralize)
        val btn_unneutralize = findViewById<AppCompatButton>(R.id.btn_unneutralize)



        button_img = findViewById(R.id.btn_pick_img)
        imageView = findViewById(R.id.img_save)

        startDateText = findViewById(R.id.start_date_text)
        startDateBtn = findViewById(R.id.start_date_btn)

        //Calendar
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)



        // 남자 버튼 클릭 시
        btnMale.setOnClickListener {
            btnMale.setBackgroundColor(Color.BLUE)  // 선택된 효과
            btnFemale.setBackgroundColor(Color.LTGRAY)  // 비선택된 상태
        }

        // 여자 버튼 클릭 시
        btnFemale.setOnClickListener {
            btnFemale.setBackgroundColor(Color.BLUE)  // 선택된 효과
            btnMale.setBackgroundColor(Color.LTGRAY)  // 비선택된 상태
        }


        // 중성화 버튼 클릭 시
        btn_neutralize.setOnClickListener {
            btn_neutralize.setBackgroundColor(Color.BLUE)  // 선택된 효과
            btn_unneutralize.setBackgroundColor(Color.LTGRAY)  // 비선택된 상태
        }

        // 중성화 안함 버튼 클릭 시
        btn_unneutralize.setOnClickListener {
            btn_unneutralize.setBackgroundColor(Color.BLUE)  // 선택된 효과
            btn_neutralize.setBackgroundColor(Color.LTGRAY)  // 비선택된 상태
        }




        startDateBtn.setOnClickListener {
            val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                // 선택한 날짜를 TextView에 표시
                startDateText.text = "$selectedYear/${selectedMonth + 1}/$selectedDay"
            }, year, month, day)

            datePickerDialog.show() // 달력 다이얼로그 띄우기
        }
        // 이미지 선택 버튼 클릭 리스너
        button_img.setOnClickListener {
            ImagePicker.with(this)
                .crop()                    // Crop image(Optional)
                .compress(1024)             // Compress image size(Optional)
                .maxResultSize(1080, 1080)  // Final image resolution(Optional)
                .createIntent { intent -> imagePickerLauncher.launch(intent) }
        }

        // Edge-to-Edge 설정
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}