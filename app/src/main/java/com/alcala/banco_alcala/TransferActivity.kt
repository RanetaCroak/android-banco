package com.alcala.banco_alcala

import android.os.Bundle
import com.alcala.banco_alcala.databinding.TransferActivityBinding
import android.content.Intent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class TransferActivity : AppCompatActivity() {
        private lateinit var binding : TransferActivityBinding

        override fun onCreate(savedInstanceState: Bundle?) {
            binding = TransferActivityBinding.inflate(layoutInflater)
            super.onCreate(savedInstanceState)
            setContentView(binding.root)

            // Creación de Spinners y adaptadores de datos

            // cuentas
            val spinnerCuentas1: Spinner = binding.spinnerCuentas1
            ArrayAdapter.createFromResource(
                this, R.array.listaCuentas1, android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }.apply {
                spinnerCuentas1.adapter = this
            }

            val spinnerCuentas2: Spinner = binding.spinnerCuentas2
            ArrayAdapter.createFromResource(
                this, R.array.listaCuentas2, android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }.apply {
                spinnerCuentas2.adapter = this
            }

            // Monedas
            val spinnerMonedas: Spinner = binding.spinnerMonedas1
            ArrayAdapter.createFromResource(
                this, R.array.listaMonedas1, android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }.apply {
                spinnerMonedas.adapter = this
            }

            val textView = binding.spinnerMonedas1
            spinnerMonedas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?, view: View?, position: Int, id: Long ) {
                    textView.selectedItem.toString()
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    //
                }
            }

            val radioGroup = binding.radioGroupCuentas
            val editTextCuentaAjena = binding.editTextCuentaAjena
            var cuentaAjena = false
            radioGroup.setOnCheckedChangeListener { group, checkedId ->
                when (checkedId) {
                    R.id.rCuentaPropia -> {
                        editTextCuentaAjena.visibility = View.GONE
                        cuentaAjena = false
                        spinnerCuentas2.visibility = View.VISIBLE
                    }
                    R.id.rCuentaAjena -> {
                        editTextCuentaAjena.visibility = View.VISIBLE
                        cuentaAjena = true
                        spinnerCuentas2.visibility = View.GONE
                    }
                }
            }

            val botonEnviarDatos : Button = binding.btnEnviarDatos
            val txtIntroducirDinero : EditText = binding.txtIntroducirDinero
            val justificante : CheckBox = binding.justificante

            botonEnviarDatos.setOnClickListener{
                val cuentaOrigen = spinnerCuentas1.selectedItem.toString()
                val cuentaDestino = spinnerCuentas2.selectedItem.toString()
                val moneda = spinnerMonedas.selectedItem.toString()
                val importe = txtIntroducirDinero.text.toString()
                val numeroCuentaAjena = editTextCuentaAjena.text.toString()
                var mensaje = ""
                if (cuentaAjena){
                    if (numeroCuentaAjena.isEmpty()){
                        mensaje = "Cuenta origen: \t $cuentaOrigen \t A cuenta propia: \t $cuentaDestino \t Importe: $importe $moneda"
                    }
                } else {
                    // TODO
                    mensaje = "Cuenta origen: \t $cuentaOrigen \t A cuenta propia: \t $cuentaDestino \t Importe: $importe $moneda"
                }
                if (justificante.isChecked){
                    mensaje = "$mensaje \t Justificante: $justificante"
                } else {
                    mensaje = "$mensaje \t Justificante no solicitado"
                }
                Toast.makeText(this, "$mensaje", Toast.LENGTH_SHORT).show()
            }
        }
}