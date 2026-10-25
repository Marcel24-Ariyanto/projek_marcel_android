package com.example.projek_marcel.Home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projek_marcel.MainActivity
import com.example.projek_marcel.toolbar.ToolbarActivity
import com.example.projek_marcel.databinding.FragmentHomeBinding
import com.example.projek_marcel.webview.WebViewActivity

class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater,container,false)



        return binding.root


    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnLihatDetail.setOnClickListener {
            startActivity(Intent(requireContext(), ToolbarActivity::class.java))
        }
        binding.btnTambahMood.setOnClickListener {
            startActivity(Intent(requireContext(), WebViewActivity::class.java))
        }
    }




}