package com.fandango.model

interface RuleBuilder<T> {
    val requiredType: RecordType
    fun isRequiredType(ruleRecord: RuleRecord): Boolean = ruleRecord.type == this.requiredType
    fun fromRecord(ruleRecord: RuleRecord): T
}

interface ActionBuilder: RuleBuilder<Action>

interface PredicateBuilder: RuleBuilder<Predicate> {
    val composition: Composition
}
