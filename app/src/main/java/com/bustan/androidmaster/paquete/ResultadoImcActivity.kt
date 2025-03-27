package com.bustan.androidmaster.paquete

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bustan.androidmaster.R

class ResultadoImcActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado_imc)


        val imc = intent.getDoubleExtra("IMC_RESULT", 0.0)
        val tvResultadoImc = findViewById<TextView>(R.id.tvResultadoImc)
        val tvMensaje = findViewById<TextView>(R.id.tvMensaje)
        val btnRecalcular = findViewById<Button>(R.id.btnRecalcular)

        // Mostrar el IMC con dos decimales
        tvResultadoImc.text = "%.2f".format(imc)

        // Asignar mensaje y color según el resultado
        when {
            imc < 18.5 -> {
                tvMensaje.text = getString(R.string.bajoPeso)
                tvResultadoImc.setTextColor(Color.BLUE)
            }
            imc in 18.5..24.9 -> {
                tvMensaje.text = getString(R.string.pesoNormal)
                tvResultadoImc.setTextColor(Color.GREEN)
            }
            imc in 25.0..29.9 -> {
                tvMensaje.text = getString(R.string.pesoAlto)
                tvResultadoImc.setTextColor(Color.YELLOW)
            }
            else -> {
                tvMensaje.text = getString(R.string.pesoObesidad)
                tvResultadoImc.setTextColor(Color.RED)
            }
        }

        btnRecalcular.setOnClickListener { onBackPressedDispatcher
            val intent = Intent(this, ImcActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
