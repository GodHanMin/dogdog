package com.example.dog_cat

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView


const val ARG_PARAM1 = "param1"
const val ARG_PARAM2 = "param2"


class Home : Fragment() {
    private var param1: String? = null
    private var param2: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // 전달된 데이터를 가져옴
        val petName = activity?.intent?.getStringExtra("pet_name")
        val petKind = activity?.intent?.getStringExtra("pet_kind")
        val petBirth = activity?.intent?.getStringExtra("pet_birth")
        val petGender = activity?.intent?.getStringExtra("pet_gender")
        val petNeutralization = activity?.intent?.getStringExtra("pet_neutralization")

        // UI 요소 업데이트
        val petNameBirthTextView: TextView = view.findViewById(R.id.pet_name_birth_home)
        val petKindTextView: TextView = view.findViewById(R.id.pet_kind_home)

        petNameBirthTextView.text = "$petName | $petGender | $petBirth"
        petKindTextView.text = petKind



        // 버튼을 찾고 클릭 리스너를 설정
        val button: Button = view.findViewById(R.id.info_btn)
        button.setOnClickListener {
            // AppCompatActivity를 시작하는 Intent 생성
            val intent = Intent(requireContext(), Pet_Information::class.java)
            startActivity(intent)
        }

        return view
    }

    companion object {

        fun newInstance(param1: String, param2: String) =
            Home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}