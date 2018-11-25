package services

import dao.JanusClient
import javax.inject.Inject
import models.BaseObservationModel.PatientId
import models.vertex
import org.apache.tinkerpop.gremlin.structure.Vertex

import util.ListConversions._

class DataJanusService @Inject()()() extends JanusService {

  def find(patientId: PatientId, modelType: String): List[Vertex] = {
    JanusClient.jg
      .V()
      .hasLabel(modelType)
      .has(vertex.PatientIdProperty, patientId.value)
      .toList
  }

}
