package models

import java.time.LocalDate

import models.DoseModel._

// See https://jto.github.io/articles/type-all-the-things/
object ObservationModel {

  case class PatientId(value: String) extends AnyVal {
    override def toString: String = value
  }

  case class ObservationDate(value: LocalDate) extends AnyVal {
    override def toString: String = value.toString
  }

}

case class ObservationModel(patientId: ObservationModel.PatientId,
                            observationDate: ObservationModel.ObservationDate,
                            compound: CompoundModel,
                            doseId: DoseId,
                            size: DoseSize,
                            unit: DoseUnit,
                            administrationMethod: DoseAdministrationMethod = DefaultAdministrationMethod) {

  private def compoundName = this.compound.getClass.getSimpleName.replace("$", "")

  /**
    * Expected observation model
    */
  private val values: Seq[String] = {
    Seq(
      patientId.value,
      compoundName, // Sort by the compound name as the "file type"
      observationDate.value.toString,
      doseId.toString,
      administrationMethod.toString,
      size.toString,
      unit.toString
    )
  }

  /**
    * Flatten a model into a CSV string
    *
    * @return
    */
  def toCSVString: String = values.mkString(",")

  /**
    * Convert to [[BaseObservationModel]]
    *
    * @return
    */
  def toBaseObservation: BaseObservationModel = {
    BaseObservationModel(
      patientId,
      observationDate
    )
  }

}