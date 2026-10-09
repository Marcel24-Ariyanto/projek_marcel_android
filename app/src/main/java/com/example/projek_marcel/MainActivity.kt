package com.example.projek_marcel

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.util.Log
import android.content.Intent //p4
import com.google.android.material.snackbar.Snackbar //p4
import com.google.android.material.dialog.MaterialAlertDialogBuilder //p4
import com.example.projek_marcel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val user =intent.getStringExtra("username")
        val pass = intent.getStringExtra("password")
        val umur = intent.getIntExtra("umur", 0)
        Log.e("Hasil", "$user $umur $pass")
        binding.txtUsername.text= "username: $user"
        binding.txtPassword.text = "password: $pass"


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
        binding.btnSnackbar.setOnClickListener {
            Snackbar.make(binding.root, "Ini adalah Snack Bar",
                Snackbar.LENGTH_LONG)
                .setAction("BATAL"){
//                    val intent = Intent(this, LoginActivity::class.java)
//                    startActivity(intent)

                }.show()
        }
        binding.txtLihatDetail.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }



    }
}