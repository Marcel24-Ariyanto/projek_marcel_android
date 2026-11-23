package com.example.projek_marcel.History

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projek_marcel.Data.entity.MoodEntity
import com.example.projek_marcel.History.TabLayout.HistoryActivity
import com.example.projek_marcel.Mood.AppDatabase
import com.example.projek_marcel.Mood.MoodAdapter
import com.example.projek_marcel.R
import com.example.projek_marcel.databinding.FragmentHistoryBinding
import com.google.android.material.chip.Chip
import kotlinx.coroutines.launch

class HistoryFragment : Fragment() {
    private var _binding: FragmentHistoryBinding? = null //
    private val binding get() = _binding!! //
    private lateinit var db: AppDatabase
    private lateinit var adapter: MoodAdapter
    private val moods = mutableListOf<MoodEntity>()
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
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Ganti sesuai nama file XML menu kamu di folder res/menu/
                menuInflater.inflate(R.menu.menu_history, menu)
            }


            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    // Sesuaikan ID ini dengan ID item di file XML menu kamu
                    R.id.action_riwayat -> {
                        startActivity(Intent(requireContext(), HistoryActivity::class.java))
                        true
                    }
                    R.id.action_settings -> {
                        // TODO: Isi aksi untuk Settings nanti
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

//        val adapter = HistoryAdapter(requireContext(), messageList)
//        binding.listViewItems.adapter = adapter
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
        db = AppDatabase.getInstance((requireContext()))
        adapter = MoodAdapter(moods){mood-> deleteMood(mood)}
        binding.rvProducts.layoutManager = LinearLayoutManager(requireContext())
        binding.rvProducts.adapter = adapter
        fetchMoods()

//        chip
    }
//    berfungsi untuk menghapus memorynya jika sudah pindah ke fragment lain
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun fetchMoods() {
        viewLifecycleOwner.lifecycleScope.launch {
            val result = db.moodDao().getAll()
            moods.clear()
            moods.addAll(result)
            adapter.notifyDataSetChanged()
        }
    }
    private fun deleteMood(note: MoodEntity) {
        viewLifecycleOwner.lifecycleScope.launch {
            db.moodDao().delete(note)
            fetchMoods()
        }
    }

    override fun onResume() {
        super.onResume()
        if (_binding!= null) fetchMoods()
    }


}