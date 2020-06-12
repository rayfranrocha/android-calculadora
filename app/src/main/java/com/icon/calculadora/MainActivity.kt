package com.icon.calculadora

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etValor1.setText("10")
        etValor2.setText("2")

        fabCalculadora.setOnClickListener {
            val intent = Intent(this, CalculadoraActivity::class.java)
            startActivity(intent)
        }

        fabIMC.setOnClickListener {
            val intent = Intent(this, IMCActivity::class.java)
            startActivity(intent)
        }

        btnCalcular.setOnClickListener {

            if (etValor1 != null) {

                val a: Double = etValor1!!.text.toString().toDouble()
                val b: Double = etValor2!!.text.toString().toDouble()

                var r: Double = 0.0

                if (rbSomar.isChecked) {
                    r = a + b
                } else
                    if (rbDiminuir.isChecked) {
                        r = a - b
                    } else
                        if (rbMultiplicar.isChecked) {
                            r = a * b
                        } else
                            if (rbDividir.isChecked) {
                                r = a / b
                            }

                tvResultado.text = r.toString()
            }

            // your code to perform when the user clicks on the button
            Toast.makeText(this@MainActivity, "You clicked me.", Toast.LENGTH_SHORT).show()
        }

    }

}