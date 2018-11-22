package models

import java.time.LocalDate


// See https://jto.github.io/articles/type-all-the-things/
object WrappedObservation {

  case class PatientId(value: String) extends AnyVal

  case class ObservationDate(value: LocalDate) extends AnyVal

}


case class WrappedObservation(patientId: WrappedObservation.PatientId,
                              observationDate: WrappedObservation.ObservationDate,
                              dosage: Dose) extends BaseModel {

  private val values: Seq[String] = {
    Seq(
      patientId.value,
      dosage.compoundName, // Sort by the compound name as the "file type"
      observationDate.value.toString,
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