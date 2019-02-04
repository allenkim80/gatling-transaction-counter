package com.github.allenkim.gatling.transaction.counter.action

import com.github.allenkim.gatling.transaction.counter.Component.TransactionComponents
import io.gatling.commons.util.ClockSingleton.nowMillis
import io.gatling.core.Predef.Status
import io.gatling.core.action.{Action, ExitableAction}
import io.gatling.core.session.{Expression, Session}
import io.gatling.core.stats.StatsEngine
import io.gatling.core.stats.message.ResponseTimings
import io.gatling.core.util.NameGen

case class TransactionSendAction(requestName: Expression[String],
                                 startTime:Expression[Long],
                                 endTime:Expression[Long],
                                 result:Status,
                                 failReason:Expression[String],
                                 statsEngine: StatsEngine,
                                 transactionComponents: TransactionComponents,
                                 next:Action) extends ExitableAction with NameGen {
  override def name: String = genName("sendTransactionCount")

  override def execute(session: Session): Unit = {
    val startTime = this.startTime(session).get
    val endTime = this.endTime(session).get
    val start = if(startTime == 0) nowMillis else startTime
    val end = if(endTime == 0) nowMillis else endTime

    statsEngine.logResponse(session, requestName(session).get, ResponseTimings(start, end), result, None, Some(failReason(session).get), Nil)

    next ! session.markAsSucceeded
  }
}
