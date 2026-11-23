package com.example.projek_marcel.Mood

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projek_marcel.Data.entity.MoodEntity
import com.example.projek_marcel.databinding.ItemHistoryBinding
import com.example.projek_marcel.toolbar.DetailActivity

class MoodAdapter(private val moods: List<MoodEntity>
): RecyclerView.Adapter<MoodAdapter.MoodViewHolder>(){
    class MoodViewHolder(val binding: ItemHistoryBinding):
            RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoodViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoodViewHolder, position: Int) {
        val mood = moods[position]
        holder.binding.textSender.text = mood.title
        holder.binding.textMessage.text = mood.description
        holder.binding.emojiImg.setImageResource(mood.img)
        holder.binding.btnDetail.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra("MOOD_ID", mood.id)
            }
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int = moods.size
}