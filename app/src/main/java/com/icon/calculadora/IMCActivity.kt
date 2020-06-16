package com.icon.calculadora

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_imc.*
import kotlinx.android.synthetic.main.content_imc.*

class IMCActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //torna o EditText ReadOnly
        etResultado.setKeyListener(null);

        tietAltura.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                doCalcularIMC()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        tietPeso.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                doCalcularIMC()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }

    fun doCalcularIMC() {

        if (  !tietPeso.text!!.isNullOrBlank()  && !tietAltura.text!!.isNullOrBlank()) {
            val peso = tietPeso.text.toString().toDouble()
            val altura = tietAltura.text.toString().toDouble()
            val imc = peso / (altura * altura)

            etResultado.setText(imc.toString())

            for ( t in tabela ){
                if ( t.max >= imc && t.min <= imc ){
                    tietMin.setText(t.min.toString())
                    tietMax.setText(t.max.toString())
                    tietCategoria.setText(t.categoria)
                    break
                }
            }

        }
    }

    class ImcResultado (val min: Double,
                        val max : Double,
                        val categoria : String,
                        val cor : String){

    }

    val tabela = listOf(
         ImcResultado(0.0, 18.5, "Abaixo do Peso", "yellow"),
         ImcResultado(18.6, 24.9, "Peso Normal", "blue"),
         ImcResultado(25.0, 29.9, "Sobrepeso", "green"),
         ImcResultado(30.0, 34.9, "Obesidade Grau I", "green"),
         ImcResultado(35.0, 39.9, "Obesidade Grau II", "orange"),
         ImcResultado(40.0, 100.0, "Obesidade Grau III", "red")
    )


}
