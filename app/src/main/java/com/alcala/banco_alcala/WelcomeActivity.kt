package com.alcala.banco_alcala

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alcala.banco_alcala.databinding.WelcomeActivityBinding

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding : WelcomeActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WelcomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Al pulsar el botón, se inicia la actividad MainActivity
        binding.btnEntrarPrincipal.setOnClickListener {
            // definimos el intent y empezamos la activity del login
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}