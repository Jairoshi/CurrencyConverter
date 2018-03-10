package com.hiroshi.melendez.currencyconverter.model

/**
 * Created by melendezhiroshi on 3/3/18.
 */
data class Currency(val name: String, val exchangeRate: Double,val flagID: Int){

   fun toDollar(number: Double): Double = number * exchangeRate

   fun fromDollar(number: Double): Double = number / exchangeRate

   override fun toString(): String {
      return name
   }
}