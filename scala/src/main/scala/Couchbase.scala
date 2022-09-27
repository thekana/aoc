package com.github.thekana.aoc

import com.couchbase.client.scala.env.{ClusterEnvironment, TimeoutConfig}
import com.couchbase.client.scala.{Cluster, ClusterOptions}

import scala.concurrent.duration._

object Couchbase extends App {

  // Update this to your cluster
  val username   = "Administrator"
  val password   = "password"
  val bucketName = "travel-sample"

  val env = ClusterEnvironment.builder
    // You should uncomment this if running in a production environment.
    // .securityConfig(
    //   SecurityConfig()
    //     .enableTls(true)
    //     .trustCertificate(Path.of("/path/to/cluster-root-certificate.pem"))
    // )
    .timeoutConfig(
      TimeoutConfig()
        .kvTimeout(10.seconds)
    )
    .build
    .get

  val cluster = Cluster
    .connect(
      "couchbase://localhost",
      ClusterOptions
        .create(username, password)
        .environment(env)
    )
    .get
  val bucket = cluster.bucket(bucketName)
  bucket.waitUntilReady(30.seconds).get
}
