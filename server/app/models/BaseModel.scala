package models

import java.time.LocalDate


trait BaseModel {

  val patientId: String

  val observationDate: LocalDate

  /**
    * Flatten a model into a CSV string
    *
    * @return
    */
  def toCSVString: String

}