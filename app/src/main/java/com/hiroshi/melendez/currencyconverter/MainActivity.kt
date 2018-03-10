package com.hiroshi.melendez.currencyconverter



import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.support.constraint.ConstraintLayout
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.hiroshi.melendez.currencyconverter.model.Currency


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var currencyLeft : Currency = Currency("DOP", 0.020, R.drawable.dominican_republic_flag)
    private var currencyRight : Currency = Currency("USD", 1.0, R.drawable.united_states_flag)
    private var inputText : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        updateCurrency(currencyLeft, currencyRight)

        findViewById<ConstraintLayout>(R.id.currency1ConstraintLayout).setOnClickListener(this)
        findViewById<ConstraintLayout>(R.id.currency2ConstraintLayout).setOnClickListener(this)

        findViewById<ImageView>(R.id.swapIcon).setOnClickListener(this)

        findViewById<Button>(R.id.button0).setOnClickListener(this)
        findViewById<Button>(R.id.button1).setOnClickListener(this)
        findViewById<Button>(R.id.button2).setOnClickListener(this)
        findViewById<Button>(R.id.button3).setOnClickListener(this)
        findViewById<Button>(R.id.button4).setOnClickListener(this)
        findViewById<Button>(R.id.button5).setOnClickListener(this)
        findViewById<Button>(R.id.button6).setOnClickListener(this)
        findViewById<Button>(R.id.button7).setOnClickListener(this)
        findViewById<Button>(R.id.button8).setOnClickListener(this)
        findViewById<Button>(R.id.button9).setOnClickListener(this)

        findViewById<Button>(R.id.button_point).setOnClickListener(this)
        findViewById<Button>(R.id.button_delete).setOnClickListener(this)

    }

    override fun onClick(view: View) {
        var intent: Intent? = null
        when (view.id) {
            R.id.currency1ConstraintLayout, R.id.currency2ConstraintLayout -> {
                intent = Intent(this, CurrencyListView::class.java)
                intent.putExtra("viewID", view.id)
            }
            R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9 -> {
                val button : Button = findViewById(view.id)
                addNumber(button.text)
                updateResult(currencyLeft,currencyRight)
            }
            R.id.button_point -> addPoint()
            R.id.button_delete -> {
                deleteChar()
                updateResult(currencyLeft,currencyRight)
            }
            R.id.swapIcon -> {
                val temp = currencyLeft
                currencyLeft = currencyRight
                currencyRight = temp

                updateCurrency(currencyLeft,currencyRight)
                updateResult(currencyLeft,currencyRight)
            }
            else -> Log.d("MainActivity", "I do not know who is this button!")
        }
        if (intent != null) {
            startActivityForResult(intent,1)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(requestCode == 1 && resultCode == Activity.RESULT_OK){
            val name : String = data!!.extras.getString("name")
            val exchangeRate : Double = data.extras.getDouble("exchangeRate")
            val flagID : Int = data.extras.getInt("flagID")
            val viewID : Int = data.extras.getInt("viewID")
            when (viewID) {
                R.id.currency1ConstraintLayout -> currencyLeft = Currency(name,exchangeRate, flagID)
                R.id.currency2ConstraintLayout -> currencyRight = Currency(name,exchangeRate, flagID)
                else -> Log.d("MainActivity", "I do not know who is this button!")
            }

            updateCurrency(currencyLeft, currencyRight)
            updateResult(currencyLeft,currencyRight)
        }
    }

    private fun updateCurrency(currency1: Currency, currency2: Currency){
        val currency1TextView : TextView = findViewById(R.id.currency1TextView)
        currency1TextView.text = currency1.name
        val currency1ImageView : ImageView = findViewById(R.id.flag1)
        currency1ImageView.setImageResource(currency1.flagID)
        val currency2TextView : TextView = findViewById(R.id.currency2TextView)
        currency2TextView.text = currency2.name
        val currency2ImageView : ImageView = findViewById(R.id.flag2)
        currency2ImageView.setImageResource(currency2.flagID)
    }


    private fun updateResult(currencyFrom: Currency, currencyTo: Currency){
        val textView : TextView = findViewById(R.id.result_text)
        val result : Double = currencyTo.fromDollar(currencyFrom.toDollar(getNumber()))
        textView.text = getString(R.string.dollar_sign, String.format("%.2f", result))
    }

    private fun getNumber(): Double{
        val number : Double? = inputText.toDoubleOrNull()
        return number ?: 0.0
    }


    private fun addNumber(c: CharSequence){
        val inputTextView : TextView = findViewById(R.id.input_text)
        if(!(inputText == "" && "$c" == "0")) {
            inputText = "$inputText$c"
            inputTextView.text = getString(R.string.dollar_sign, inputText)
        }
    }

    private fun addPoint(){
        val inputTextView : TextView = findViewById(R.id.input_text)
        if(!inputText.contains('.', false)){
            inputText = if (inputText == "") "0." else "$inputText."
            inputTextView.text = getString(R.string.dollar_sign, inputText)
        }
    }

    private fun deleteChar(){
        val inputTextView : TextView = findViewById(R.id.input_text)
        inputText = ""
        inputTextView.text = getString(R.string._0_0)
    }


}