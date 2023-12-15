package com.alcala.banco_alcala

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alcala.banco_alcala.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : MainActivityBinding


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainActivityBinding.inflate(layoutInflater)
        val userInput = intent.getStringExtra("userInput")
        binding.saludo.text = "Bienvenido/a $userInput"
        setContentView(binding.root)

        binding.btnSalir.setOnClickListener{
            finish()
        }
    }
}