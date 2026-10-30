package com.example.projek_marcel.History

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projek_marcel.R
import com.example.projek_marcel.databinding.FragmentHistoryBinding
import com.google.android.material.chip.Chip

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
//chip
        binding.chipGroupFilter.setOnCheckedStateChangeListener { group, checkedIds ->
            val selectedChipId = checkedIds.firstOrNull() // ambil id chip yang dipilih
            if (selectedChipId != null) {
                val chip = group.findViewById<Chip>(selectedChipId)
                Toast.makeText(requireContext(), "Filter: ${chip.text}",
                    Toast.LENGTH_SHORT).show()
// Lakukan logika filter di sini
            }
        }
//        chip
    }
//    berfungsi untuk menghapus memorynya jika sudah pindah ke fragment lain
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}