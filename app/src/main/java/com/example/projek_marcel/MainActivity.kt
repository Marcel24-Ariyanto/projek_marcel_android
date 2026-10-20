package com.example.projek_marcel

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent //p4
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder //p4
import com.example.projek_marcel.databinding.ActivityMainBinding
import com.example.projek_marcel.toolbar.ToolbarActivity

class MainActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        val editor = sharedPref.edit()
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val username = sharedPref.getString("username", "")
        binding.txtUsername.text = "Halo "+ username + "!"


//        alert
        binding.logout.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Anda yakin ingin keluar?")

                .setNegativeButton("Tidak", null)
                .setPositiveButton("Iya") { dialog, _ ->
                    // proses hapus

                    dialog.dismiss()
                    finish()
                }
                .setCancelable(false)
                .show()
        }



//        p5
        binding.btnLihatDetail.setOnClickListener {
            startActivity(Intent(this, ToolbarActivity::class.java))
        }
//        p6
        binding.logout.setOnClickListener {

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
        }



    }
}