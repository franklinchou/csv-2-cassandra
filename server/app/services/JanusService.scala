package services

import dao.JanusClient
import models.{BaseObservationModel, ObservationModel, SiteObservationModel}
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal
import org.apache.tinkerpop.gremlin.structure.Vertex

abstract class JanusService extends DataService {

  type Traversal = GraphTraversal[Vertex, Vertex]

  private def addModel[M <: BaseObservationModel](m: M, t: Traversal): Vertex = {
    m.mapping.foreach(m => t.property(m._1, m._2))
    t.next()
  }

  def add[M <: BaseObservationModel](m: M, modelType: String): Vertex = {

    val traversal = JanusClient.jg.addV(modelType)

    val model = modelType match {
      case ObservationModel.`type` => m.asInstanceOf[ObservationModel]
      case SiteObservationModel.`type` => m.asInstanceOf[SiteObservationModel]
    }

    JanusClient.jg.tx.commit()
    addModel(model, traversal)
  }

}
