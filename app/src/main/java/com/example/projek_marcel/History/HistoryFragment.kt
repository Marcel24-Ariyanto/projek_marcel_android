package com.example.projek_marcel.History

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.projek_marcel.R
import com.example.projek_marcel.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {
    private var _binding: FragmentHistoryBinding? = null //
    private val binding get() = _binding!! //
    val daftarGambar = listOf(
        R.drawable.emoji_bahagia,
        R.drawable.emoji_sedih,
        R.drawable.emoji_biasa,
        R.drawable.emoji_lelah,
        R.drawable.emoji_marah
    )
    private val messageList = listOf(
        HistoryModel("Senang", "Saya dapat kue tadi sama tetangga", daftarGambar[0]),
        HistoryModel("Lelah", "Harini saya banyak sekali kegiatan", daftarGambar[3]),
        HistoryModel("Lelah", "Harini tugas saya banyak sekali", daftarGambar[3]),
        HistoryModel("Marah", "Teman kelompokku gada kerja, dan aku MARAHH!!!!", daftarGambar[4]),


    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHistoryBinding.inflate(inflater, container, false) //
        return binding.root//
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "History"

        val adapter = HistoryAdapter(requireContext(), messageList)
        binding.listViewItems.adapter = adapter
    }


}