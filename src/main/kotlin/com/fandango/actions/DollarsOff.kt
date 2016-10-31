package com.fandango.actions

import com.fandango.model.*
import java.math.BigDecimal

data class DollarsOff(val discount: BigDecimal): Action {
    override operator fun invoke(cart: Cart): Cart = cart.copy(value = cart.value - discount)

    companion object: ActionBuilder {
        override val requiredType = RecordType.DOLLARS_OFF
        override fun fromRecord(ruleRecord: RuleRecord): Action =
                if (!isRequiredType(ruleRecord))
                    throw IllegalArgumentException("not of required type DOLlARS_OF: ${ruleRecord}")
                else if (ruleRecord.discount == null)
                    throw IllegalArgumentException("does not include expeted value discount ${ruleRecord}")
                else
                    DollarsOff(ruleRecord.discount)
    }
}
