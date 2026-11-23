package com.example.projek_marcel.History.TabLayout

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class HistoryTabAdapter(activity: FragmentActivity) :
    FragmentStateAdapter(activity) {
    // Jumlah total tab yang ada
    override fun getItemCount(): Int = 4
    // Menentukan Fragment yang ditampilkan berdasarkan posisi tab
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TabAFragment()
            1 -> TabBFragment()
            2 -> TabCFragment()
            3 -> TabDFragment()
            else -> throw IllegalStateException("Posisi tidak valid")
        }
    }
}