package com.example.dog_cat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import androidx.fragment.app.Fragment

class Calender : Fragment() {

    private lateinit var calendarView: CalendarView
    private lateinit var tvSummary: TextView
    private lateinit var btnWrite: Button
    private var selectedDate: String = ""

    // 내장 메모리로 데이터를 저장하는 HashMap
    private val diaryData: HashMap<String, String> = HashMap()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calender, container, false)

        calendarView = view.findViewById(R.id.calendarView)
        tvSummary = view.findViewById(R.id.tvSummary)
        btnWrite = view.findViewById(R.id.btnWrite)

        // 캘린더 날짜 클릭 이벤트 처리
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            selectedDate = "$year-${month + 1}-$dayOfMonth"

            // 내장 메모리에서 데이터 검색
            val diary = diaryData[selectedDate]
            if (diary == null) {
                // 작성된 데이터가 없을 경우
                tvSummary.visibility = View.GONE
                btnWrite.visibility = View.VISIBLE
            } else {
                // 작성된 데이터가 있을 경우 요약 표시
                tvSummary.text = diary // 줄바꿈 문제 없이 전체 내용을 표시
                tvSummary.visibility = View.VISIBLE
                btnWrite.visibility = View.GONE
            }
        }

        // 작성하기 버튼 클릭 이벤트
        btnWrite.setOnClickListener {
            val writeFragment = fragment_write.newInstance(selectedDate)
            writeFragment.setOnSaveListener { date, content ->
                // 저장된 데이터 갱신
                diaryData[date] = content

                // 저장된 데이터 표시
                tvSummary.text = content // 전체 내용 표시
                tvSummary.visibility = View.VISIBLE
                btnWrite.visibility = View.GONE
            }
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, writeFragment)
                .addToBackStack(null)
                .commit()
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Calender().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
