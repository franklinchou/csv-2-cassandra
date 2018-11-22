package models

import java.time.LocalDate

case class WrappedObservation(patientId: String,
                              observationDate: LocalDate,
                              dosage: Dose) extends BaseModel {


  private val values: Seq[String] = {
    Seq(
      patientId,
      dosage.compoundName,
      observationDate.toString,
      dosage.doseId.toString,
      dosage.administrationMethod,
      dosage.size.toString(),
      dosage.unit
    )
  }

  /**
    * Flatten a model into a CSV string
    *
    * @return
    */
  def toCSVString: String = values.mkString(",")
}