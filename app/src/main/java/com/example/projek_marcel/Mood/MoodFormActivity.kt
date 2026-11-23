package com.example.projek_marcel.Mood

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.projek_marcel.Data.entity.MoodEntity
import com.example.projek_marcel.R
import com.example.projek_marcel.databinding.ActivityMoodFormBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class MoodFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMoodFormBinding
    private var selectedMood: String? = null
    private lateinit var db: AppDatabase
    private val calendar =  Calendar.getInstance()
    private var selectedMoodImg: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityMoodFormBinding.inflate(layoutInflater)

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Tambah Mood"
//            subtitle = ""
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_back)
        }

            db= AppDatabase.getInstance(this)
            val moodCards = mapOf(
            binding.cardSenang to Pair("Senang", R.drawable.emoji_bahagia),
            binding.cardBiasa to Pair("Biasa", R.drawable.emoji_biasa),
            binding.cardSedih to Pair("Sedih", R.drawable.emoji_sedih),
            binding.cardMenangis to Pair("Lelah", R.drawable.emoji_lelah),
            binding.cardMarah to Pair("Marah", R.drawable.emoji_marah)
        )
            moodCards.forEach { (cardView, moodData) ->
                cardView.setOnClickListener {
                    moodCards.keys.forEach { it.isSelected = false }
                    cardView.isSelected = true
                    selectedMood = moodData.first      // Isi title otomatis (misal: "Sedih")
                    selectedMoodImg = moodData.second  // Isi ID gambar (Int)
                }
            }
            updateTanggalText() // memaggil function
            binding.layoutTanggal.setOnClickListener {
                val datePicker = DatePickerDialog(
                    this,
                    { _, year, month, dayOfMonth ->
                        calendar.set(year, month, dayOfMonth)
                        updateTanggalText()
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                )
                datePicker.show()
            }


            binding.btnSimpan.setOnClickListener {
                val mood = selectedMood
                val cerita = binding.etCerita.text.toString().trim()

                if (mood.isNullOrBlank() || cerita.isBlank()) {
                    Toast.makeText(
                        this,
                        "Isi semua kolom!",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
                binding.btnSimpan.isEnabled = false
                lifecycleScope.launch {
                    try {
                        db.moodDao().insert(
                            MoodEntity(
                                img = selectedMoodImg,
                                title = mood,
                                description = cerita,
                                date = calendar.timeInMillis
                            )
                        )
                        finish()
                    } catch (e: Exception) {
                        binding.btnSimpan.isEnabled = true
                        Toast.makeText(
                            this@MoodFormActivity,
                            "Gagal menyimpan mood",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

    private fun updateTanggalText() {
        val formatIndonesia = SimpleDateFormat("d MMMM yyyy", Locale("id", "ID"))
        binding.tvTanggal.text = formatIndonesia.format(calendar.time)
    }
}