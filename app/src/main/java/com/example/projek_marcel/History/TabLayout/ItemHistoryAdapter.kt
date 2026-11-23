package com.example.projek_marcel.History.TabLayout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projek_marcel.History.HistoryModel
import com.example.projek_marcel.databinding.ItemHistoryBinding

class ItemHistoryAdapter(
    private val productList: List<HistoryModel>,
    private val onItemClick: (HistoryModel) -> Unit
) : RecyclerView.Adapter<ItemHistoryAdapter.ProductViewHolder>() {
    inner class ProductViewHolder(val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemHistoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ProductViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = productList[position]
        with(holder.binding) {
            emojiImg.setImageResource(item.emoji)
            textSender.text = item.senderName
            textMessage.text = item.HistoryText
            root.setOnClickListener { onItemClick(item) }
        }
    }
    override fun getItemCount(): Int = productList.size
}