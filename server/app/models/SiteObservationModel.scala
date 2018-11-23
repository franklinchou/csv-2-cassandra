package models

import models.BaseObservationModel.{ObservationDate, PatientId}
import models.CompoundModel.Compound
import models.DoseModel._
import models.SiteModel.{IntakePhysician, ZipCode}

case class SiteObservationModel(patientId: PatientId,
                                observationDate: ObservationDate,
                                compound: Compound,
                                intakePhysician: IntakePhysician,
                                zipCode: ZipCode,
                                doseId: DoseId,
                                doseSize: DoseSize,
                                doseUnit: DoseUnit,
                                administrationMethod: AdministrationMethod = DefaultAdministrationMethod)

  extends BaseObservationModel {

  final val `type` = "site-observation"

  /**
    * Site Observation model
    */
  val values: Seq[String] =
    Seq(
      patientId.toString,
      compound.toString,
      observationDate.toString,
      doseId.toString,
      administrationMethod.toString,
      doseSize.toString,
      doseUnit.toString,
      intakePhysician.toString,
      zipCode.toString
    )

}