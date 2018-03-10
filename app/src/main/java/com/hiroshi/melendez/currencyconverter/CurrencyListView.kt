package com.hiroshi.melendez.currencyconverter

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.hiroshi.melendez.currencyconverter.adapter.CurrencyAdapter
import com.hiroshi.melendez.currencyconverter.model.Currency
import java.util.ArrayList


class CurrencyListView : AppCompatActivity(), CurrencyAdapter.CurrencySelectableListener{

    private var buttonID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_list_view)

        if (intent.hasExtra("viewID")) {
            buttonID = intent.getIntExtra("viewID",0)
        }

       val listView = findViewById<ListView>(R.id.currency_list_view_component)

        val currencyList = ArrayList<Currency>()

        currencyList.add(Currency("USD", 1.0, R.drawable.united_states_flag))
        currencyList.add(Currency("AUD", 0.78, R.drawable.australia_flag))
        currencyList.add(Currency("CAD", 0.78, R.drawable.canada_flag))
        currencyList.add(Currency("CNY", 0.16, R.drawable.china_flag))
        currencyList.add(Currency("DOP", 0.020, R.drawable.dominican_republic_flag))
        currencyList.add(Currency("EUR", 1.23, R.drawable.european_union_flag))
        currencyList.add(Currency("HKD", 0.13, R.drawable.hong_kong_flag))
        currencyList.add(Currency("YEN", 0.0094, R.drawable.japan_flag))
        currencyList.add(Currency("MXN", 0.054, R.drawable.mexico_flag))
        currencyList.add(Currency("NZD", 0.73, R.drawable.new_zealand_flag))
        currencyList.add(Currency("NOK", 0.13, R.drawable.norway_flag))
        currencyList.add(Currency("SGD", 0.76, R.drawable.singapore_flag))
        currencyList.add(Currency("KRW", 0.00094, R.drawable.south_korea_flag))
        currencyList.add(Currency("SEK", 0.12, R.drawable.sweden_flag))
        currencyList.add(Currency("CHF", 1.05, R.drawable.switzerland_flag))
        currencyList.add(Currency("GBP", 1.39, R.drawable.united_kingdom_flag))

        val currencyAdapter = CurrencyAdapter(this, currencyList)
        listView.adapter = currencyAdapter
        listView.onItemClickListener = currencyAdapter
        currencyAdapter.setCurrencySelectableListener(this)
    }

    override fun selectedCurrency(currency: Currency) {
        val intent = Intent()
        intent.putExtra("viewID", buttonID)
        intent.putExtra("name", currency.name)
        intent.putExtra("exchangeRate", currency.exchangeRate)
        intent.putExtra("flagID", currency.flagID)
        setResult(Activity.RESULT_OK,intent)
        finish()
    }
}


