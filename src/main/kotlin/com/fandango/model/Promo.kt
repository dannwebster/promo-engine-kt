package com.fandango.model

data class Promo(val id: String, val predicate: Predicate, val action: Action) {
    fun canApply(cart: Cart) = predicate(cart)
    fun apply(cart: Cart) = action(cart)
}