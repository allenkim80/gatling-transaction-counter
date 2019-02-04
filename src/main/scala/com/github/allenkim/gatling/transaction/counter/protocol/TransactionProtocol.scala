package com.github.allenkim.gatling.transaction.counter.protocol

import akka.actor.ActorSystem
import com.github.allenkim.gatling.transaction.counter.Component.TransactionComponents
import io.gatling.core.config.GatlingConfiguration
import io.gatling.core.protocol.{Protocol, ProtocolKey}
import io.gatling.core.{CoreComponents, protocol}


object TransactionProtocol {
  def apply(configuration: GatlingConfiguration): TransactionProtocol = TransactionProtocol()

  val transactionProtocolKey = new ProtocolKey {
    override type Protocol = TransactionProtocol
    override type Components = TransactionComponents

    override def protocolClass: Class[protocol.Protocol] =
      classOf[TransactionProtocol].asInstanceOf[Class[io.gatling.core.protocol.Protocol]]

    override def defaultProtocolValue(configuration: GatlingConfiguration): TransactionProtocol =
      TransactionProtocol(configuration)

    override def newComponents(system: ActorSystem, coreComponents: CoreComponents): TransactionProtocol => TransactionComponents = {
      transactionProtocol => TransactionComponents(transactionProtocol)
    }
  }
}

case class TransactionProtocol() extends Protocol
