package com.github.allenkim.gatling.transaction.counter

import com.github.allenkim.gatling.transaction.counter.protocol.TransactionProtocolBuilder
import com.github.allenkim.gatling.transaction.counter.request.TransactionRequest
import io.gatling.core.config.GatlingConfiguration
import io.gatling.core.session.Expression

import scala.collection.concurrent

object Predef {

  private[counter] var transactionStartTimes:concurrent.TrieMap[String, Long] = concurrent.TrieMap[String, Long]()

  def transactionConfig(implicit configuration: GatlingConfiguration) = TransactionProtocolBuilder(configuration)

  def transaction(requestName: Expression[String]) = {
    new TransactionRequest(requestName)
  }
}
