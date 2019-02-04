package com.github.allenkim.gatling.transaction.counter.action

import com.github.allenkim.gatling.transaction.counter.Predef._
import com.github.allenkim.gatling.transaction.counter.Component.TransactionComponents
import io.gatling.commons.util.ClockSingleton.nowMillis
import io.gatling.core.action.{Action, ExitableAction}
import io.gatling.core.session.{Expression, Session}
import io.gatling.core.stats.StatsEngine
import io.gatling.core.util.NameGen

case class TransactionStartAction(
                              requestName: Expression[String],
                              key:String,
                              statsEngine: StatsEngine,
                              transactionComponents: TransactionComponents,
                              next:Action) extends ExitableAction with NameGen {
  override def name: String = genName("startTransactionCount")

  override def execute(session: Session): Unit = {

    transactionStartTimes.put(key, nowMillis)

    next ! session.markAsSucceeded
  }
}
