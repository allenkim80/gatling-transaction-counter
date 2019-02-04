package com.github.allenkim.gatling.transaction.counter.protocol

import io.gatling.core.config.GatlingConfiguration

object TransactionProtocolBuilder {

  implicit def toTransactionProtocol(builder: TransactionProtocolBuilder): TransactionProtocol = builder.build

  def apply(implicit configuration: GatlingConfiguration): TransactionProtocolBuilder = {
    TransactionProtocolBuilder(TransactionProtocol(configuration))
  }

}

case class TransactionProtocolBuilder(transactionProtocol: TransactionProtocol) {
  def build = transactionProtocol
}
