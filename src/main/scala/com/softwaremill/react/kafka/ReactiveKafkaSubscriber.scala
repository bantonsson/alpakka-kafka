package com.softwaremill.react.kafka

import java.util.concurrent.atomic.AtomicReference

import kafka.producer.KafkaProducer
import org.reactivestreams.{Subscriber, Subscription}

private[kafka] class ReactiveKafkaSubscriber(val producer: KafkaProducer) extends Subscriber[String] {

  val active = new AtomicReference[Subscription]()

  override def onSubscribe(subscription: Subscription) {
    active.compareAndSet(null, subscription) match {
      case true => subscription.request(1)
      case false => subscription.cancel()
    }
  }

  override def onError(throwable: Throwable): Unit = {
    // TODO ?
  }

  override def onComplete() {
    // TODO ?
  }

  override def onNext(msg: String) {
    active.get().request(1)
  }

}