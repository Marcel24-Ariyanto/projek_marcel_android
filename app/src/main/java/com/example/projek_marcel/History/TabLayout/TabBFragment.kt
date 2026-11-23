package com.example.projek_marcel.History.TabLayout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.projek_marcel.Data.DateFilterHelper
import com.example.projek_marcel.Data.entity.MoodEntity
import com.example.projek_marcel.Mood.AppDatabase
import com.example.projek_marcel.Mood.MoodAdapter
import com.example.projek_marcel.R
import com.example.projek_marcel.databinding.FragmentTabABinding
import com.example.projek_marcel.databinding.FragmentTabBBinding
import kotlinx.coroutines.launch


class TabBFragment : Fragment() {
    private lateinit var db: AppDatabase
    private lateinit var adapter: MoodAdapter
    private var _binding: FragmentTabBBinding? = null
    private val binding get() = _binding!!

    private val moods = mutableListOf<MoodEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTabBBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        val productAdapter = ItemHistoryAdapter(historyList) { selectedItem ->
//            Toast.makeText(
//                requireContext(),
//                "Anda memilih ${selectedItem.senderName}",
//                Toast.LENGTH_SHORT
//            ).show()
//        }
        db = AppDatabase.getInstance((requireContext()))
        adapter = MoodAdapter(moods)
        binding.rvProducts.apply {
            layoutManager = GridLayoutManager(requireContext(), 1)
        }
        binding.rvProducts.adapter = adapter

        fetchMoods()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun fetchMoods() {
        viewLifecycleOwner.lifecycleScope.launch {
            // Tinggal ganti string filter sesuai kebutuhan Tab B ("1_BULAN", "3_BULAN", dll.)
            val startTime = DateFilterHelper.getStartTimeByFilter("3_BULAN")
            val result = db.moodDao().getMoodsAfter(startTime)
            moods.clear()
            moods.addAll(result)
            adapter.notifyDataSetChanged()
        }
    }
    override fun onResume() {
        super.onResume()
        if (_binding != null) fetchMoods()
    }


}