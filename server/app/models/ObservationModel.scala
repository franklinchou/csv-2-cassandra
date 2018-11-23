package models

import java.time.LocalDate

import models.BaseObservationModel.{ObservationDate, PatientId}
import models.CompoundModel.Compound
import models.DoseModel._
import models.vertices._
import models.vertices.property2string
import org.apache.tinkerpop.gremlin.structure.Vertex
import shapeless.{Generic, HNil}


object ObservationModel {

  implicit def read(s: String): ObservationModel = {

    val split = s.split(",").toSeq
    val g = Generic[ObservationModel]

    val components =
      PatientId(split(0)) ::
        ObservationDate(LocalDate.parse(split(1))) ::
        Compound.apply(split(2)) ::
        DoseId.apply(split(3).toInt) ::
        DoseSize.apply(BigDecimal(split(4))) ::
        DoseUnit.apply(split(5)) ::
        AdministrationMethod.apply(split(6)) ::
        HNil

    g.from(components)
  }


  implicit def vertex2model(v: Vertex): ObservationModel = {

    val patientId: String = v.property[String](PatientIdProperty)
    val observationDate: LocalDate = v.property[String](ObservationDateProperty)
    val compound: String = v.property[String](CompoundProperty)
    val doseId: Int = v.property[String](DoseIdProperty)
    val doseSize: BigDecimal = v.property[String](DoseSizeProperty)
    val doseUnit: String = v.property[String](DoseUnitProperty)
    val adminMethod: String = v.property[String](AdministrationMethodProperty)

    ObservationModel(
      PatientId.apply(patientId),
      ObservationDate.apply(observationDate),
      Compound.apply(compound),
      DoseId.apply(doseId),
      DoseSize.apply(doseSize),
      DoseUnit.apply(doseUnit),
      AdministrationMethod.apply(adminMethod)
    )
  }

}

case class ObservationModel(patientId: PatientId,
                            observationDate: ObservationDate,
                            compound: Compound,
                            doseId: DoseId,
                            doseSize: DoseSize,
                            doseUnit: DoseUnit,
                            administrationMethod: AdministrationMethod = DefaultAdministrationMethod)

  extends BaseObservationModel {

  final val `type`: String = "default-observation"

  /**
    * Observation model
    */
  protected val values: Seq[String] = {
    Seq(
      patientId.toString,
      compound.toString, // Sort by the compound name as the "file type"
      observationDate.toString,
      doseId.toString,
      administrationMethod.toString,
      doseSize.toString,
      doseUnit.toString
    )
  }

}