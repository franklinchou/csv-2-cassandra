package models

import models.ObservationModel.{ObservationDate, PatientId}


case class BaseObservationModel(patientId: PatientId,
                                observationDate: ObservationDate) {

  /**
    * Flatten a model into a CSV string
    *
    * @return
    */
  def toCSVString: String = s"$patientId,$observationDate"

}