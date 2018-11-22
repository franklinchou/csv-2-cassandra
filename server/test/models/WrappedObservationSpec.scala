package models

import java.time.LocalDate

import org.scalatest.FunSpec

class WrappedObservationSpec extends FunSpec {

  describe("Single patient, single dosage of Experimental Compound 1") {

    val mockPatientId = "mock-patient-1"

    val observation1 =
      WrappedObservation(
        mockPatientId,
        LocalDate.of(2018, 1, 1),
        Dose(1, ExperimentalCompound1, BigDecimal(0.5), "mg")
      )

    it("should") {
      val expected = "mock-patient-1,ExperimentalCompound1,2018-01-01,1,Tablet,0.5,mg"
      assert(observation1.toCSVString == expected)
    }

  }

}
