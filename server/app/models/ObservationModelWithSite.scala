package models

import models.BaseObservationModel.{ObservationDate, PatientId}
import models.DoseModel._
import models.SiteModel.{IntakePhysician, ZipCode}

case class ObservationModelWithSite(patientId: PatientId,
                                    observationDate: ObservationDate,
                                    compound: CompoundModel,
                                    intakePhysician: IntakePhysician,
                                    zipCode: ZipCode,
                                    doseId: DoseId,
                                    size: DoseSize,
                                    unit: DoseUnit,
                                    administrationMethod: DoseAdministrationMethod = DefaultAdministrationMethod)

  extends BaseObservationModel {

  /**
    * Flatten a model into a CSV string
    *
    * @return
    */
  def toCSVString: String = ???
}