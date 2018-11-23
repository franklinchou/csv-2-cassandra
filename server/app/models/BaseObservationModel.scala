package models

import java.time.LocalDate

import models.BaseObservationModel.{ObservationDate, PatientId}
import models.CompoundModel.Compound

// See https://jto.github.io/articles/type-all-the-things/
object BaseObservationModel {

  case class PatientId(value: String) extends AnyVal {
    override def toString: String = value
  }

  case class ObservationDate(value: LocalDate) extends AnyVal {
    override def toString: String = value.toString
  }

}


trait BaseObservationModel {

  val `type`: String

  val patientId: PatientId

  val observationDate: ObservationDate

  val compound: Compound

  protected val values: Seq[String]

  /**
    * Flatten a model into a CSV string
    *
    * @return
    */
  def toCSVString: String = values.mkString(",")

}