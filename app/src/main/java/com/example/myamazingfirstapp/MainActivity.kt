package com.example.myamazingfirstapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        calculateBTN.setOnClickListener {
            validateFields()
        }

        useMetricCB.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                heightEDTX.hint = getString(R.string.hint_m)
                weightEDTX.hint = getString(R.string.hint_kilos)
            } else {
                heightEDTX.hint = getString(R.string.hint_in)
                weightEDTX.hint = getString(R.string.hint_libs)
            }

        }
    }

    private fun validateFields() {
        val hasError = heightEDTX.text.isBlank() || weightEDTX.text.isBlank()
        if (hasError) {
            if (heightEDTX.text.toString().isBlank()) {
                heightEDTX.error = "Empty height"
            }
            if (weightEDTX.text.toString().isBlank()) {
                weightEDTX.error = "Empty weight"
            }
        } else {
            val bmi = calculateBMI(
                useMetricCB.isChecked,
                heightEDTX.text.toString().toFloat(),
                weightEDTX.text.toString().toFloat()
            ).toString().substring(0, 5)
            titleTXT.text = "Your BMI is $bmi"
        }
    }

    private fun calculateBMI(useMetric: Boolean, height: Float, weight: Float): Float =
        if (useMetric) {
            weight / height.pow(2)
        } else {
            (weight * 703) / height.pow(2)
        }

}
