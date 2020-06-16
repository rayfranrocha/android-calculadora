package com.icon.calculadora

import android.app.Activity
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.View
import android.webkit.RenderProcessGoneDetail
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_calculadora.*
import kotlinx.android.synthetic.main.content_calculadora.*
import java.lang.Exception
import java.util.*

class CalculadoraActivity : AppCompatActivity() {

    //    val REQUEST_CODE_SPEECH_INPUT = 1000
    val SPEECH_REQUEST_CODE = 0

    var listaFrases = mutableListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculadora)
        setSupportActionBar(toolbar)

//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //voice!!!

        btnGravar.setOnClickListener {

            tvEscutando.visibility = View.VISIBLE
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Oi, fale alguma coisa!")

            try {
                startActivityForResult(intent, SPEECH_REQUEST_CODE)
            } catch (ex: Exception) {
                Toast.makeText(this, ex.message, Toast.LENGTH_SHORT)
            }

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        tvEscutando.visibility = View.GONE

        if (requestCode == SPEECH_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val spokenText: String? =
                data!!.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    .let { results ->
                        results[0]
                    }

            tvTexto.text = spokenText

            listaFrases.add("" + spokenText)

            var x = StringBuffer()
            for (e in listaFrases) {

                var novo = calcular(e)

                x.append(novo + "\n")
            }

            etFrases.setText(x)

        }

    }

    private fun calcular(texto: String): String {

        val re = Regex("[A-Za-z]")
        var formula = re.replace(texto, "")

        val reEspaco = Regex("[ ]")
        formula = reEspaco.replace(formula, "")

        val reApostrofe = Regex("[']")
        formula = reApostrofe.replace(formula, "")

        val reNum = Regex("[0-9]")
        var sinal = reNum.replace(formula, "")

        if (!sinal.isNullOrEmpty()) {

            val parts = formula.split(sinal)

            var resultado = 0.0

            if (parts.size==2){
                if ("+".equals(sinal)) {
                    resultado = parts[0].toDouble() + parts[1].toDouble()
                } else if ("-".equals(sinal)) {
                    resultado = parts[0].toDouble() - parts[1].toDouble()
                } else if ("*".equals(sinal)) {
                    resultado = parts[0].toDouble() * parts[1].toDouble()
                } else if ("/".equals(sinal)) {
                    resultado = parts[0].toDouble() / parts[1].toDouble()
                }

                return texto + " = " + resultado
            }

        }

        return texto

    }


}
