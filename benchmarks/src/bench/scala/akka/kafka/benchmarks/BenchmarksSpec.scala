/*
 * Copyright (C) 2014 - 2016 Softwaremill <http://softwaremill.com>
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package akka.kafka.benchmarks

import akka.actor.ActorSystem
import akka.kafka.benchmarks.app.RunTestCommand
import akka.stream.ActorMaterializer
import akka.testkit.TestKit
import org.scalatest.{ BeforeAndAfterAll, FlatSpecLike }

import scala.language.postfixOps

class BenchmarksSpec extends TestKit(ActorSystem("AkkaKafkaBenchmarks")) with FlatSpecLike with BeforeAndAfterAll {

  val kafkaHost = "localhost:9094"

  implicit val mat = ActorMaterializer()

  it should "work" in {
    //Benchmarks.run(RunTestCommand("akka-plain-consumer", kafkaHost, 2000000))
    //Benchmarks.run(RunTestCommand("akka-plain-consumer", kafkaHost, 2000000))
    //Benchmarks.run(RunTestCommand("batched-consumer", kafkaHost, 20000))
    //Benchmarks.run(RunTestCommand("akka-batched-consumer", kafkaHost, 20000))
    //Benchmarks.run(RunTestCommand("at-most-once-consumer", kafkaHost, 50000))
    Benchmarks.run(RunTestCommand("transactions", kafkaHost, 100000))
    Benchmarks.run(RunTestCommand("akka-transactions", kafkaHost, 100000))
  }

  override protected def afterAll(): Unit = {
    shutdown(system)
    super.afterAll()
  }
}
