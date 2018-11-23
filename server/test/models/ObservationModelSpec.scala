package models

import java.time.LocalDate

import models.BaseObservationModel.{ObservationDate, PatientId}
import models.CompoundModel.Compound
import models.DoseModel.{DoseId, DoseSize, DoseUnit}
import models.SiteModel.{IntakePhysician, ZipCode}
import org.scalatest.FunSpec
import shapeless.LabelledGeneric
import shapeless.record._

class ObservationModelSpec extends FunSpec {

  final val mockPatientId = PatientId("mock-patient-1")

  final val mockObservationDate = ObservationDate(LocalDate.of(2018, 1, 1))

  final val mockCompound = Compound("compound-1")

  val observation1 =
    ObservationModel(
      mockPatientId,
      mockObservationDate,
      mockCompound,
      DoseId(1),
      DoseSize(BigDecimal(0.5)),
      DoseUnit("mg")
    )

  describe("Single observation") {
    it("should convert to csv row") {
      val expected = "mock-patient-1,compound-1,2018-01-01,1,Tablet,0.5,mg"
      assert(observation1.toCSVString == expected)
    }
  }

  describe("Single site-based observation") {
    val observation2 =
      SiteObservationModel(
        mockPatientId,
        mockObservationDate,
        mockCompound,
        IntakePhysician("F. Chou"),
        ZipCode(10010),
        DoseId(1),
        DoseSize(BigDecimal(0.5)),
        DoseUnit("mg")
      )

    it("should allow Generic manipulation") {
      val owsGeneric = LabelledGeneric[SiteObservationModel]
      val rec = owsGeneric.to(observation2)

      // Observation with site -> observation
      val ows2o =
        ObservationModel(
          rec('patientId),
          rec('observationDate),
          rec('compound),
          rec('doseId),
          rec('doseSize),
          rec('doseUnit),
          rec('administrationMethod)
        )

      assert(ows2o == observation1)
    }
  }


}
