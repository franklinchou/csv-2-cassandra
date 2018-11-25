package models

import java.util.Date

import models.BaseObservationModel.{ObservationDate, PatientId}
import models.CompoundModel.Compound

// See https://jto.github.io/articles/type-all-the-things/
object BaseObservationModel {

  case class PatientId(value: String) extends AnyVal {
    override def toString: String = value
  }

  case class ObservationDate(value: Date) extends AnyVal {
    override def toString: String = value.toString
  }

}


trait BaseObservationModel {

  val `type`: String

  val patientId: PatientId

  val observationDate: ObservationDate

  val compound: Compound

  val mapping: Map[String, _]

  /**
    * Flatten a model into a CSV string
    *
    * @return
    */
  def toCSVString: String = mapping.values.mkString(",")

}