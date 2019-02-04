package com.github.allenkim.gatling.transaction.counter.request

import com.github.allenkim.gatling.transaction.counter.builder.{TransactionEndActionBuilder, TransactionSendActionBuilder, TransactionStartActionBuilder}
import io.gatling.commons.validation.Success
import io.gatling.core.Predef.Status
import io.gatling.core.session.Expression

class TransactionRequest(requestName: Expression[String]) {
  def sendTransaction(result: Status, failReason:Expression[String] = session => Success("")) =
    new TransactionSendActionBuilder(requestName, session => Success(0), session => Success(0), result, failReason)

  def sendTimeTransaction(startTime:Expression[Long], endTime:Expression[Long], result:Status, failReason:Expression[String] = session => Success("")) =
    new TransactionSendActionBuilder(requestName, startTime, endTime, result, failReason)

  def startTransaction(key: String) = new TransactionStartActionBuilder(requestName, key)

  def endTransaction(key: String) =
    new TransactionEndActionBuilder(requestName, key)
}
