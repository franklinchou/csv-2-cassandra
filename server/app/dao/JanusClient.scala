package dao

import javax.inject.Singleton
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource
import org.janusgraph.core.JanusGraph
import play.api.Logger
import util.ElapsedTime.time


@Singleton
object JanusClient {

  val dataGraph: JanusGraph = DataGraph.graph

  Logger.info("Setting up in-memory graph")

  time(
    DataGraph.setUp(dataGraph),
    "Graph setup complete"
  )

  val jg: GraphTraversalSource = dataGraph.traversal()

}
