package com.bustan.androidmaster.paquete

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bustan.androidmaster.R
import com.google.android.material.slider.Slider

class ImcActivity : AppCompatActivity() {
    private var alturaSeleccionada: Float = 150f // Valor inicial en cm
    private var pesoSeleccionado: Float = 60f   // Valor inicial en kg

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc)



        val tvAltura = findViewById<TextView>(R.id.tvAltura)
        val sliderAltura = findViewById<Slider>(R.id.Centimetros)
        val tvPeso = findViewById<TextView>(R.id.tvPeso)
        val sliderPeso = findViewById<Slider>(R.id.SliderPeso)
        val tvEdad = findViewById<TextView>(R.id.tvEdad)
        val sliderEdad = findViewById<Slider>(R.id.SliderEdad)
        val btnCalcular = findViewById<Button>(R.id.Calcular) // Debe ser un Button


        // Asignar valores iniciales
        sliderAltura.value = alturaSeleccionada
        tvAltura.text = "${alturaSeleccionada.toInt()} cm"

        sliderPeso.value = pesoSeleccionado
        tvPeso.text = "${pesoSeleccionado.toInt()} kg"

        // Listener para la altura
        sliderAltura.addOnChangeListener { _, value, _ ->
            alturaSeleccionada = value
            tvAltura.text = "${value.toInt()} cm"
        }

        // Listener para el peso
        sliderPeso.addOnChangeListener { _, value, _ ->
            pesoSeleccionado = value
            tvPeso.text = "${value.toInt()} kg"
        }

        // Listener para la edad
        sliderEdad.addOnChangeListener { _, value, _ ->
            tvEdad.text = "${value.toInt()} años"
        }

        // Botón calcular IMC
        btnCalcular.setOnClickListener {
            val alturaMetros = alturaSeleccionada / 100
            if (alturaMetros != 0f) {
                val imc = pesoSeleccionado / (alturaMetros * alturaMetros)
                val imcRedondeado = "%.2f".format(imc).toDouble() // Convertir a Double correctamente

                Log.d("IMCActivity", "Altura: $alturaSeleccionada cm, Peso: $pesoSeleccionado kg, IMC: $imcRedondeado")

                val intent = Intent(this, ResultadoImcActivity::class.java)
                intent.putExtra("IMC_RESULT", imcRedondeado) // Ahora sí es un Double
                startActivity(intent)
            } else {
                Log.d("IMCActivity", "La altura no puede ser 0")
            }
        }
    }
}


