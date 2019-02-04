package com.github.allenkim.gatling.transaction.counter.builder

import com.github.allenkim.gatling.transaction.counter.action.TransactionSendAction
import com.github.allenkim.gatling.transaction.counter.protocol.TransactionProtocol
import io.gatling.core.Predef.Status
import io.gatling.core.action.Action
import io.gatling.core.action.builder.ActionBuilder
import io.gatling.core.session.Expression
import io.gatling.core.structure.ScenarioContext

class TransactionSendActionBuilder(
                                    requestName: Expression[String],
                                    startTime:Expression[Long],
                                    endTime:Expression[Long],
                                    result:Status,
                                    reason:Expression[String]) extends ActionBuilder {
  override def build(ctx: ScenarioContext, next: Action): Action = {
    val transactionComponents = ctx.protocolComponentsRegistry.components(TransactionProtocol.transactionProtocolKey)
    TransactionSendAction(requestName, startTime, endTime, result, reason, ctx.coreComponents.statsEngine, transactionComponents, next)
  }
}
