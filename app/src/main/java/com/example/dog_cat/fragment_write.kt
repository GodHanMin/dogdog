package com.example.dog_cat


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class fragment_write : Fragment() {

    private lateinit var radioGroupOverall: RadioGroup
    private lateinit var radioGroupCondition: RadioGroup
    private lateinit var etSpecialNotes: EditText
    private lateinit var btnSubmit: Button
    private var date: String = ""
    private var onSaveListener: ((String, String) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            date = it.getString(ARG_DATE) ?: ""
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_write, container, false)

        radioGroupOverall = view.findViewById(R.id.radioGroupOverall)
        radioGroupCondition = view.findViewById(R.id.radioGroupCondition)
        etSpecialNotes = view.findViewById(R.id.etSpecialNotes)
        btnSubmit = view.findViewById(R.id.btnSubmit)

        btnSubmit.setOnClickListener {
            val overallRating = getSelectedRadioButtonText(radioGroupOverall)
            val conditionRating = getSelectedRadioButtonText(radioGroupCondition)
            val specialNotes = etSpecialNotes.text.toString()

            if (overallRating != null && conditionRating != null) {
                val diaryContent = """
                    1. 오늘 하루 평가: $overallRating
                    2. 산책 컨디션: $conditionRating
                    3. 특이사항: $specialNotes
                """.trimIndent()

                onSaveListener?.invoke(date, diaryContent)
                requireActivity().supportFragmentManager.popBackStack()
            } else {
                Toast.makeText(requireContext(), "모든 질문에 답변해주세요.", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun getSelectedRadioButtonText(radioGroup: RadioGroup): String? {
        val selectedId = radioGroup.checkedRadioButtonId
        return if (selectedId != -1) {
            view?.findViewById<RadioButton>(selectedId)?.text.toString()
        } else {
            null
        }
    }

    fun setOnSaveListener(listener: (String, String) -> Unit) {
        onSaveListener = listener
    }

    companion object {
        private const val ARG_DATE = "date"

        @JvmStatic
        fun newInstance(date: String) =
            fragment_write().apply {
                arguments = Bundle().apply {
                    putString(ARG_DATE, date)
                }
            }
    }
}