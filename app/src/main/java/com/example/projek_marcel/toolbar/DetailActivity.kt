package com.example.projek_marcel.toolbar

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.projek_marcel.Data.entity.MoodEntity
import com.example.projek_marcel.HalamanLogin
import com.example.projek_marcel.Mood.AppDatabase
import com.example.projek_marcel.R
import com.example.projek_marcel.databinding.ActivityDetailBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var db: AppDatabase
    private var currentMood: MoodEntity? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
binding = ActivityDetailBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Detail Mood"
//            subtitle = ""
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
//            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        db = AppDatabase.getInstance(this)

        // 1. Ambil ID dari Intent & muat data
        val moodId = intent.getIntExtra("MOOD_ID", -1)
        if (moodId != -1) {
            loadMoodDetail(moodId)
        } else {
            Toast.makeText(this, "Data tidak ditemukan", Toast.LENGTH_SHORT).show()
            finish()
        }


        binding.btnHapus.setOnClickListener {
            val moodToDelete = currentMood ?: return@setOnClickListener

            lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    db.moodDao().delete(moodToDelete)
                }

                Snackbar.make(binding.root, "Item dihapus", Snackbar.LENGTH_LONG)
                    .setAction("BATAL") {
                        // Kembalikan data ke database jika tombol BATAL ditekan
                        lifecycleScope.launch {
                            withContext(Dispatchers.IO) {
                                db.moodDao().insert(moodToDelete)
                            }
                            Toast.makeText(this@DetailActivity, "Penghapusan dibatalkan", Toast.LENGTH_SHORT).show()
                        }
                    }
                    .addCallback(object : Snackbar.Callback() {
                        override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                            // Jika Snackbar tertutup tanpa menekan BATAL, kembali ke halaman sebelumnya
                            if (event != DISMISS_EVENT_ACTION) {
                                finish()
                            }
                        }
                    })
                    .show()
            }
        }





    binding.btnHapus.setOnClickListener {
        Snackbar.make(binding.root, "Item dihapus",
            Snackbar.LENGTH_LONG)
            .setAction("BATAL"){
//                val intent = Intent(this, LoginActivity::class.java)
//                startActivity(intent)

            }.show()
    }


    }
    private fun loadMoodDetail(id: Int) {
        lifecycleScope.launch {
            val mood = withContext(Dispatchers.IO) {
                db.moodDao().getMoodById(id)
            }

            if (mood != null) {
                currentMood = mood
                binding.imageView3.setImageResource(mood.img)
                binding.textView4.text = mood.title
                binding.edtCatatan.setText(mood.description)

                val sdf = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))
                binding.textView10.text = sdf.format(Date(mood.date))
            } else {
                Toast.makeText(this@DetailActivity, "Data tidak ditemukan", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
//        val editor = sharedPref.edit()
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
//            R.id.action_search -> {
//                AlertDialog.Builder(this)
//                    .setTitle("Logout")
//                    .setMessage("Apakah Anda yakin ingin keluar?")
//                    .setPositiveButton("Ya") { dialog, _ ->
//                        editor.clear()
//                        editor.apply()
//                        startActivity(Intent(this, HalamanLogin::class.java))
//                        dialog.dismiss()
//                        finish()
//
//                    }
//                    .setNegativeButton("Batal") { dialog, _ -> dialog.dismiss() }
//                    .show()
//                true
//            }
//            R.id.action_settings -> {
//                Toast.makeText(this, "Settings Clicked", Toast.LENGTH_SHORT).show()
//                true
//            }
            else -> super.onOptionsItemSelected(item)
        }
    }



}