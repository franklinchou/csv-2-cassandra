package models

import java.lang.Float
import java.text.SimpleDateFormat
import java.util.Date

import models.BaseObservationModel.{ObservationDate, PatientId}
import models.CompoundModel.Compound
import models.DoseModel._
import models.vertex._
import org.apache.tinkerpop.gremlin.structure.Vertex
import shapeless.{Generic, HNil}


object ObservationModel {

  final val `type` = "observation-model"

  implicit def read(s: String): ObservationModel = {

    val split = s.split(",").toSeq

    val components =
      PatientId(split(0)) ::
        ObservationDate(new SimpleDateFormat("yyyy-MM-dd").parse(split(1))) ::
        Compound.apply(split(2)) ::
        DoseId.apply(split(3).toInt) ::
        DoseSize.apply(Float.parseFloat(split(4))) ::
        DoseUnit.apply(split(5)) ::
        AdministrationMethod.apply(split(6)) ::
        HNil

    // TODO How is this different from simply using an apply method?
    Generic[ObservationModel].from(components)
  }


  implicit def vertex2model(v: Vertex): ObservationModel = {

    val patientId: String = v.property[String](PatientIdProperty)
    val observationDate: Date = v.property[String](ObservationDateProperty)
    val compound: String = v.property[String](CompoundProperty)
    val doseId: Int = v.property[String](DoseIdProperty)
    val doseSize: Float = v.property[String](DoseSizeProperty)
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

  final val `type`: String = ObservationModel.`type`

  /**
    * Observation model
    */
  val mapping: Map[String, _] = {
    Map(
      PatientIdProperty -> patientId.toString,
      CompoundProperty -> compound.toString, // Sort by the compound name as the "file type"
      ObservationDateProperty -> observationDate.value,
      DoseIdProperty -> doseId.value,
      AdministrationMethodProperty -> administrationMethod.toString,
      DoseSizeProperty -> doseSize.value,
      DoseUnitProperty -> doseUnit.toString
    )
  }

}