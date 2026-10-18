package com.example.projek_marcel
import android.widget.Toast ///
import android.util.Log ///
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projek_marcel.databinding.ActivityHalamanLoginBinding
import android.content.Intent
import androidx.appcompat.app.AlertDialog

class HalamanLogin : AppCompatActivity() {
    private lateinit var binding: ActivityHalamanLoginBinding //
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //p6
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
//        val isLogin = sharedPref.getBoolean("isLogin", false)
//        if (isLogin) {
//            startActivity(Intent(this, MainActivity::class.java))
//            finish()
//            return
//        }
        //p6
//
        binding = ActivityHalamanLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
//
//        setContentView(R.layout.activity_halaman_login) dihapus
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//
//        binding.button.setOnClickListener {
//            val user=binding.edtUsername.text.toString()
//            val pass=binding.edtPassword.text.toString()
//            Log.e("Hasil", "Username $user Password $pass")
//            Toast.makeText(this, "Username $user Password $pass ", Toast.LENGTH_LONG).show()
//            //
//            val intent = Intent(this, MainActivity::class.java)
//            intent.putExtra("username", user)
//            intent.putExtra("password", pass)
//            intent.putExtra("umur", 23)
//            startActivity(intent)
//        }


// p6

        binding.btnLogin.setOnClickListener {
            val username = binding.edtUsername.text.toString().trim()
            val password = binding.edtPassword.text.toString()
            if (username.isNotEmpty() && username == password) {
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.putString("username", username)
                editor.apply()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                AlertDialog.Builder(this)
                    .setTitle("Login Gagal")
                    .setMessage("Silahkan coba lagi")
                    .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                    .show()
            }
        }

    }
}