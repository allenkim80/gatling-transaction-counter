package com.github.allenkim.gatling.transaction.counter.builder

import com.github.allenkim.gatling.transaction.counter.action.TransactionEndAction
import com.github.allenkim.gatling.transaction.counter.protocol.TransactionProtocol
import io.gatling.core.action.Action
import io.gatling.core.action.builder.ActionBuilder
import io.gatling.core.session.Expression
import io.gatling.core.structure.ScenarioContext

class TransactionEndActionBuilder(
                                 requestName: Expression[String],
                                 key: String) extends ActionBuilder{
  override def build(ctx: ScenarioContext, next: Action): Action = {
    val transactionComponents = ctx.protocolComponentsRegistry.components(TransactionProtocol.transactionProtocolKey)
    TransactionEndAction(requestName, key, ctx.coreComponents.statsEngine, transactionComponents, next)
  }

}
