package com.github.allenkim.gatling.transaction.counter.Component

import com.github.allenkim.gatling.transaction.counter.protocol.TransactionProtocol
import io.gatling.core.protocol.ProtocolComponents
import io.gatling.core.session.Session


case class TransactionComponents(
                                  transactionProtocol: TransactionProtocol
                                ) extends ProtocolComponents {
  override def onStart: Option[Session => Session] = None
  override def onExit: Option[Session => Unit] = None
}
