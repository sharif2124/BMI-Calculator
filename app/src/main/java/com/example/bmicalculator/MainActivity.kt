package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weighttext = findViewById<EditText>(R.id.etweght)
        val heighttext = findViewById<EditText>(R.id.etheight)
        val calculatebmi = findViewById<Button>(R.id.btncalculate)

        calculatebmi.setOnClickListener {
            val weight = weighttext.text.toString()
            val height = heighttext.text.toString()
            if (validateinput(weight,height)){

            val bmi = weight.toFloat()/((height.toFloat()/100)*(height.toFloat()/100))
            val Bmi2Degit = String.format("%.2f",bmi).toFloat()
            displayresult(Bmi2Degit)
            }


        }
    }

    private fun validateinput(weight:String?,height:String?):Boolean{
       return when{
         weight.isNullOrEmpty()->{
        Toast.makeText(this,"Weight is empty",Toast.LENGTH_LONG).show()
             return false
            }
           height.isNullOrEmpty()->{
               Toast.makeText(this,"Height is empty",Toast.LENGTH_LONG).show()
               return false
           }
           else->{
               return true
           }
        }
    }

    private fun displayresult(bmi:Float){
        val tvindex=findViewById<TextView>(R.id.tvindex)
        val tvresult=findViewById<TextView>(R.id.tvresult)
        val tvinfo = findViewById<TextView>(R.id.tvinfo)

        tvindex.text=bmi.toString()
        tvinfo.text="(Normal Range is 18.5 - 24.9)"

        var resulttext = ""
        var colour = 0

        when{
            bmi<18.0->{
                resulttext="Underweight"
                colour=R.color.under_weight
            }
            bmi in 18.50..24.99->{
                resulttext="Normal"
                colour=R.color.normal
            }
            bmi in 25.0..29.9->{
                resulttext="Overweight"
                colour=R.color.over_weight
            }
            bmi >30.0->{
                resulttext="Obesity"
                colour=R.color.obses
            }
        }
        tvresult.setTextColor(ContextCompat.getColor(this,colour))
        tvresult.text= resulttext

    }
}