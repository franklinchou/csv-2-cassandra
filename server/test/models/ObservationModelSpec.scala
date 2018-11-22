package models

import java.time.LocalDate

import models.DoseModel.{DoseId, DoseSize, DoseUnit}
import org.scalatest.FunSpec

class ObservationModelSpec extends FunSpec {

  describe("Single patient, single dosage of Experimental Compound 1") {

    val mockPatientId = ObservationModel.PatientId("mock-patient-1")

    val observation1 =
      ObservationModel(
        mockPatientId,
        ObservationModel.ObservationDate(LocalDate.of(2018, 1, 1)),
        ExperimentalCompound1Model,
        DoseId(1),
        DoseSize(BigDecimal(0.5)),
        DoseUnit("mg")
      )

    it("should convert to csv row") {
      val expected = "mock-patient-1,ExperimentalCompound1,2018-01-01,1,Tablet,0.5,mg"
      assert(observation1.toCSVString == expected)
    }

  }

}
