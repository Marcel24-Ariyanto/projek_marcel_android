package com.example.projek_marcel.History

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.projek_marcel.databinding.ItemHistoryBinding

class HistoryAdapter (
    context: Context,
    private val histories: List<HistoryModel>
) : ArrayAdapter<HistoryModel>(context, 0, histories ){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(context), parent, false)
        val view = binding.root
        val data = histories[position]
// Muat gambar dari URL ke ImageView
        Glide.with(context)
            .load(data.emoji)
            .into(binding.emojiImg)
        binding.textSender.text = data.senderName
        binding.textMessage.text = data.HistoryText
        return view
    }
}