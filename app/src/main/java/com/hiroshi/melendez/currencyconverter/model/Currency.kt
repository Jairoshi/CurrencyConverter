package com.hiroshi.melendez.currencyconverter.model

/**
 * Created by melendezhiroshi on 3/3/18.
 */
data class Currency(val name: String, val exchangeRate: Double,val flagID: Int){

   fun toCurrency(currencyOut: Currency, number: Double): Double = number * exchangeRate/currencyOut.exchangeRate

   override fun toString(): String {
      return name
   }
}