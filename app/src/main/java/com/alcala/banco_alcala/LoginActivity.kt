package com.alcala.banco_alcala

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.alcala.banco_alcala.databinding.LoginActivityBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : LoginActivityBinding
    var userInput:String = ""
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        
        var usuarioValidado:Boolean = false

        fun getUserInput(): String {
            return userInput
        }
        // inputs
        val dniRegex = "^[0-9]{8}[A-Za-z]$".toRegex()
        /* Empieza por 8 dígitos y termina por una letra */

        binding.userLogin.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                userInput = binding.userLogin.text.toString().trim()
                if (userInput.isBlank()) {
                    binding.userLogin.error = "Campo obligatorio"
                } else if (!userInput.matches(dniRegex)) {
                    binding.userLogin.error = "DNI no válido"
                }
            }
        }


        // Se comprueba que el campo no esté vacío

        binding.passwdLogin.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val userPasswd = binding.passwdLogin.text.toString().trim()
                if (userPasswd.isBlank()) {
                    binding.passwdLogin.error = "Campo obligatorio"
                }
            }
        }

        // botones
        binding.botonEntrar.setOnClickListener {
            // validacion del user
            usuarioValidado = true

            userInput = binding.userLogin.text.toString().trim()

            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("userInput", userInput)
            }
            startActivity(intent)
            finish()
        }

        binding.botonSalir.setOnClickListener{
            finish()
        }
    }



}