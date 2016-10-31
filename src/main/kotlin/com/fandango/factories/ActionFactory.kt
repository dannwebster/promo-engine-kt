package com.fandango.factories

import com.fandango.actions.DollarsOff
import com.fandango.model.Action
import com.fandango.model.RuleRecord

class ActionFactory {

    fun createAction(ruleRecords: Iterable<RuleRecord>) =
            if (ruleRecords.count() != 1)
                throw IllegalArgumentException("should have only one createAction rule")
            else
                createAction(ruleRecords.first())

    fun createAction(ruleRecord: RuleRecord): Action = DollarsOff.fromRecord(ruleRecord)
}