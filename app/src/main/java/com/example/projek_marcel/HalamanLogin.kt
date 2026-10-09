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
class HalamanLogin : AppCompatActivity() {
    private lateinit var binding: ActivityHalamanLoginBinding //
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

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
        binding.button.setOnClickListener {
            val user=binding.edtUsername.text.toString()
            val pass=binding.edtPassword.text.toString()
            Log.e("Hasil", "Username $user Password $pass")
            Toast.makeText(this, "Username $user Password $pass ", Toast.LENGTH_LONG).show()
            //
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("username", user)
            intent.putExtra("password", pass)
            intent.putExtra("umur", 23)
            startActivity(intent)
        }
//

    }
}