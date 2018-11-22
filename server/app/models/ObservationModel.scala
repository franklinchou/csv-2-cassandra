package models

import models.BaseObservationModel.{ObservationDate, PatientId}
import models.DoseModel._

case class ObservationModel(patientId: PatientId,
                            observationDate: ObservationDate,
                            compound: CompoundModel,
                            doseId: DoseId,
                            doseSize: DoseSize,
                            doseUnit: DoseUnit,
                            administrationMethod: DoseAdministrationMethod = DefaultAdministrationMethod)

  extends BaseObservationModel {

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
      doseSize.toString,
      doseUnit.toString
    )
  }

  /**
    * Flatten a model into a CSV string
    *
    * @return
    */
  def toCSVString: String = values.mkString(",")

}