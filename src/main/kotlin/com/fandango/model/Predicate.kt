package com.fandango.model

abstract class Predicate:(Cart) -> Boolean {
    val type = this.javaClass.simpleName.replace("predicate", "", true)
}
typealias PredicateChain = Iterable<Predicate>
typealias Composition = (PredicateChain) -> Predicate

object True: Predicate() {
    override operator fun invoke(cart: Cart): Boolean = true
}

object False: Predicate() {
    override operator fun invoke(cart: Cart): Boolean = false
}


class And(val predicates: PredicateChain): Predicate() {
    override operator fun invoke(cart: Cart): Boolean = predicates.map { p -> p(cart) }.all { it }
}

class Or(val predicates: PredicateChain): Predicate() {
    override operator fun invoke(cart: Cart): Boolean = predicates.map { p -> p(cart) }.reduce { a, b ->  a || b }
}


fun and(predicates: PredicateChain): Predicate =
        if (predicates.count() == 0)
            True
        else if (predicates.count() == 1)
            predicates.first()
        else
            And(predicates)

fun or(predicates: PredicateChain): Predicate =
        if (predicates.count() == 0)
            True
        else if (predicates.count() == 1)
            predicates.first()
        else
            And(predicates)

