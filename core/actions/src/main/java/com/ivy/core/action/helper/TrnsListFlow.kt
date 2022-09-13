package com.ivy.core.action.helper

import com.ivy.core.action.FlowAction
import com.ivy.core.action.calculate.transaction.GroupTrnsFlow
import com.ivy.core.action.transaction.TrnsFlow
import com.ivy.core.functions.transaction.TrnWhere
import com.ivy.data.transaction.TransactionsList
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import javax.inject.Inject

class TrnsListFlow @Inject constructor(
    private val trnsFlow: TrnsFlow,
    private val groupTrnsFlow: GroupTrnsFlow
) : FlowAction<TrnWhere, TransactionsList>() {

    @OptIn(FlowPreview::class)
    override fun TrnWhere.createFlow(): Flow<TransactionsList> = trnsFlow(this)
        .flatMapMerge {
            groupTrnsFlow(it)
        }
}