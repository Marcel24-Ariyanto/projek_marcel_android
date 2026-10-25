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
import com.example.projek_marcel.HalamanLogin
import com.example.projek_marcel.R
import com.example.projek_marcel.databinding.ActivityToolbarBinding
import com.google.android.material.snackbar.Snackbar

class ToolbarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityToolbarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
binding = ActivityToolbarBinding.inflate(layoutInflater)
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
    binding.btnEdit.setOnClickListener {
        Snackbar.make(binding.root, "Item dihapus",
            Snackbar.LENGTH_LONG)
            .setAction("BATAL"){
//                val intent = Intent(this, LoginActivity::class.java)
//                startActivity(intent)

            }.show()
    }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        val editor = sharedPref.edit()
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            R.id.action_search -> {
                AlertDialog.Builder(this)
                    .setTitle("Logout")
                    .setMessage("Apakah Anda yakin ingin keluar?")
                    .setPositiveButton("Ya") { dialog, _ ->
                        editor.clear()
                        editor.apply()
                        startActivity(Intent(this, HalamanLogin::class.java))
                        dialog.dismiss()
                        finish()

                    }
                    .setNegativeButton("Batal") { dialog, _ -> dialog.dismiss() }
                    .show()
                true
            }
            R.id.action_settings -> {
                Toast.makeText(this, "Settings Clicked", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }


}