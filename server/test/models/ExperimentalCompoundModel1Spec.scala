package models

import java.time.LocalDate

import models.DoseModel.{DoseId, DoseSize, DoseUnit}
import org.scalatest.FunSpec

class ExperimentalCompoundModel1Spec extends FunSpec {

  describe("Experimental Compound 1") {

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

    it("should convert to a base pattern") {
      val expected = "mock-patient-1,2018-01-01"
      assert(observation1.toBaseObservation.toCSVString == expected)
    }

  }

}