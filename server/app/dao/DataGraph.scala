package dao

import org.janusgraph.core.{JanusGraph, JanusGraphFactory}

object DataGraph {

  val graph: JanusGraph = JanusGraphFactory.open("inmemory")

  def setUp(jg: JanusGraph): Unit = {

    var mgmt = jg.openManagement()



  }

}
