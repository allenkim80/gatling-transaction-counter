package com.github.allenkim.gatling.transaction.counter.action

import com.github.allenkim.gatling.transaction.counter.Predef._
import com.github.allenkim.gatling.transaction.counter.Component.TransactionComponents
import io.gatling.commons.stats.OK
import io.gatling.commons.util.ClockSingleton.nowMillis
import io.gatling.core.action.{Action, ExitableAction}
import io.gatling.core.session.{Expression, Session}
import io.gatling.core.stats.StatsEngine
import io.gatling.core.stats.message.ResponseTimings
import io.gatling.core.util.NameGen

case class TransactionEndAction(
                                 requestName: Expression[String],
                                 key:String,
                                 statsEngine: StatsEngine,
                                 transactionComponents: TransactionComponents,
                                 next:Action) extends ExitableAction with NameGen {
  override def name: String = genName("endTransactionCount")

  override def execute(session: Session): Unit = {
    val startTime = transactionStartTimes(key)

    statsEngine.logResponse(session, requestName(session).get, ResponseTimings(startTime, nowMillis), OK, None, Some(""), Nil)

    transactionStartTimes.remove(key)

    next ! session.markAsSucceeded
  }
}
