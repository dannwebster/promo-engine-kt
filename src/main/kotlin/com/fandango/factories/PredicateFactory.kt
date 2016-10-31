package com.fandango.factories

import com.fandango.model.*
import com.fandango.predicates.MovieIdPredicate
import com.fandango.predicates.TheaterIdPredicate
import com.fandango.predicates.VipPredicate

object DefaultPredicateFactory: PredicateFactory(listOf(
            MovieIdPredicate,
            VipPredicate,
            TheaterIdPredicate))

open class PredicateFactory(val predicateBuilders: List<PredicateBuilder>) {

    val typeToBuilderMap = predicateBuilders
            .map { predicateBuilder -> Pair(predicateBuilder.requiredType, predicateBuilder) }
            .toMap()

    val typeToComposition = predicateBuilders
            .map { predicateBuilder -> Pair(predicateBuilder.requiredType, predicateBuilder.composition) }
            .toMap()



    fun createCompositePredicate(ruleRecords: Iterable<RuleRecord>): Predicate = and(
                segmentPredicatesByType(ruleRecords)
                        .map { (type, records) -> Pair(typeToComposition.get(type), records) }
                        .map { (composition, records)-> Pair(composition, toPredicates(records)) }
                        .map { (composition, predicates) -> composition?.invoke(predicates)}
                        .filterNotNull()
            )

    fun toPredicates(ruleRecords: Iterable<RuleRecord>): PredicateChain = ruleRecords
                    .filter { record -> typeToBuilderMap.containsKey(record.type)}
                    .map { record -> Pair(typeToBuilderMap.get(record.type), record)}
                    .map { (builder, record) -> builder?.fromRecord(record) }
                    .filterNotNull()


    fun segmentPredicatesByType(ruleRecords: Iterable<RuleRecord>): Map<RecordType, List<RuleRecord>> = ruleRecords.groupBy { record -> record.type }





}