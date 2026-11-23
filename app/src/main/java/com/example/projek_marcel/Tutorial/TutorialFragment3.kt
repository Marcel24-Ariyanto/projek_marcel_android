package com.example.projek_marcel.Tutorial

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projek_marcel.HalamanLogin
import com.example.projek_marcel.databinding.FragmentTutorial3Binding

class TutorialFragment3 : Fragment() {
    private var _binding: FragmentTutorial3Binding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTutorial3Binding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.btnNext.setOnClickListener {
            startActivity(Intent(requireContext(), HalamanLogin::class.java))
        }
    }
}