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
                        .map { (type, records) -> pairRecordListWithCompositionType(type, records) }
                        .map { (composition, records)-> transformRecordsToPredicates(composition, records) }
                        .map { (composition, predicates) -> composePredicates(composition, predicates) }
                        .filterNotNull()
            )

    fun pairRecordListWithCompositionType(type: RecordType, records: Iterable<RuleRecord>) =
            Pair(typeToComposition.get(type), records)

    fun transformRecordsToPredicates(composition: Composition?, records: Iterable<RuleRecord>) =
        Pair(composition, toPredicates(records))

    fun composePredicates(composition: Composition?, predicates: PredicateChain) =
            composition?.invoke(predicates)

    fun toPredicates(ruleRecords: Iterable<RuleRecord>): PredicateChain = ruleRecords
                    .filter { record -> typeToBuilderMap.containsKey(record.type)}
                    .map { record -> Pair(typeToBuilderMap.get(record.type), record)}
                    .map { (builder, record) -> builder?.fromRecord(record) }
                    .filterNotNull()


    fun segmentPredicatesByType(ruleRecords: Iterable<RuleRecord>): Map<RecordType, List<RuleRecord>> = ruleRecords.groupBy { record -> record.type }





}