package models

import models.BaseObservationModel.{ObservationDate, PatientId}
import models.CompoundModel.Compound
import models.DoseModel._
import models.SiteModel.{IntakePhysician, ZipCode}
import models.vertex._


object SiteObservationModel {

  final val `type` = "site-observation-model"

}


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

  final val `type` = SiteObservationModel.`type`

  /**
    * Site Observation model
    */
  val mapping: Map[String, String] =
    Map(
      PatientIdProperty -> patientId.toString,
      CompoundProperty -> compound.toString,
      ObservationDateProperty -> observationDate.toString,
      DoseIdProperty -> doseId.toString,
      AdministrationMethodProperty -> administrationMethod.toString,
      DoseSizeProperty -> doseSize.toString,
      DoseUnitProperty -> doseUnit.toString,
      IntakePhysicianProperty -> intakePhysician.toString,
      IntakeZipCodeProperty -> zipCode.toString
    )

}