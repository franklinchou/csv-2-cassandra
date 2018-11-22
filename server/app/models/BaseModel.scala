package models

import models.WrappedObservation.{ObservationDate, PatientId}


trait BaseModel {

  val patientId: PatientId

  val observationDate: ObservationDate

  /**
    * Flatten a model into a CSV string
    *
    * @return
    */
  def toCSVString: String

}