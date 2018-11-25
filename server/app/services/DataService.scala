package services

import com.google.inject.ImplementedBy
import models.BaseObservationModel
import org.apache.tinkerpop.gremlin.structure.Vertex


@ImplementedBy(classOf[DataService])
abstract class DataService {

  def add[M <: BaseObservationModel](m: M, modelType: String): Vertex

}
