    package com.example.dog_cat

    import android.content.Intent
    import android.net.Uri
    import android.os.Bundle
    import androidx.fragment.app.Fragment
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.Button
    import android.widget.TextView
    import de.hdodenhof.circleimageview.CircleImageView


    const val ARG_PARAM1 = "param1"
    const val ARG_PARAM2 = "param2"


    class Home : Fragment() {
        private var param1: String? = null
        private var param2: String? = null

        private lateinit var petNameTextView: TextView
        private lateinit var petKindTextView: TextView
        private lateinit var petBirthTextView: TextView
        private lateinit var petImageView: CircleImageView



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

            // CircleImageView 초기화
            petImageView = view.findViewById(R.id.pet_image_home)

            // Intent로부터 이미지 URI 가져오기
            val petImageUrl = activity?.intent?.getStringExtra("PET_IMAGE_URL")
            petImageUrl?.let {
                petImageView.setImageURI(Uri.parse(it)) // 이미지 URI 설정
            }

            petNameTextView = view.findViewById(R.id.pet_name_birth_home)
            petKindTextView = view.findViewById(R.id.pet_kind_home)
            petImageView = view.findViewById(R.id.pet_image_home)


            val petName = activity?.intent?.getStringExtra("PET_NAME")
            val petKind = activity?.intent?.getStringExtra("PET_KIND")
            val petBirth = activity?.intent?.getStringExtra("PET_BIRTH")
            val petGender = activity?.intent?.getStringExtra("PET_GENDER")
            val petNeutered = activity?.intent?.getStringExtra("PET_NEUTRALIZED")
            val petImageUriString = activity?.intent?.getStringExtra("PET_IMAGE_URI")
            val petweight = activity?.intent?.getStringExtra("PET_WEIGHT")


            petNameTextView.text = "$petName ($petGender) | $petBirth |   $petweight kg"
            petKindTextView.text = petKind





            val button: Button = view.findViewById(R.id.info_btn)
            button.setOnClickListener {

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