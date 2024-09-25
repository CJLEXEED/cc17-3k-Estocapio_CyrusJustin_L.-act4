package com.example.tipcalculator

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get references to UI elements
        val billAmountEditText = findViewById<EditText>(R.id.billAmountEditText)
        val tipPercentageRadioGroup = findViewById<RadioGroup>(R.id.tipPercentageRadioGroup)
        val calculateButton = findViewById<Button>(R.id.calculateButton)
        val tipAmountTextView = findViewById<TextView>(R.id.tipAmountTextView)
        val totalAmountTextView = findViewById<TextView>(R.id.totalAmountTextView)

        // Set OnClickListener for the Calculate button
        calculateButton.setOnClickListener {
            // Get the bill amount from EditText and convert it to Double
            val billAmount = billAmountEditText.text.toString().toDoubleOrNull()

            // Check if the input is valid (not null and greater than 0)
            if (billAmount != null && billAmount > 0) {
                // Get the selected tip percentage from the RadioGroup
                val tipPercentage = when (tipPercentageRadioGroup.checkedRadioButtonId) {
                    R.id.tenPercentRadioButton -> 0.10
                    R.id.fifteenPercentRadioButton -> 0.15
                    R.id.twentyPercentRadioButton -> 0.20
                    else -> {
                        Toast.makeText(this, "Please select a tip percentage", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener // Exit if no tip is selected
                    }
                }

                // Calculate the tip amount and the total amount
                val tipAmount = billAmount * tipPercentage
                val totalAmount = billAmount + tipAmount

                // Display the results formatted as currency
                tipAmountTextView.text = "Tip Amount: $%.2f".format(tipAmount)
                totalAmountTextView.text = "Total Amount: $%.2f".format(totalAmount)
            } else {
                // Show an error message if the input is invalid
                billAmountEditText.error = "Please enter a valid amount"
            }
        }
    }
}
