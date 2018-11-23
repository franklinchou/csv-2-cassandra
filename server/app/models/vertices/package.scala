package models

import java.time.LocalDate

import org.apache.tinkerpop.gremlin.structure.VertexProperty

package object vertices {

  implicit def property2string(vp: VertexProperty[String]): String = {
    vp.value.toString
  }

  implicit def property2date(vp: VertexProperty[String]): LocalDate = {
    LocalDate.parse(vp.value.toString)
  }

  implicit def property2int(vp: VertexProperty[String]): Int = {
    vp.value.toInt
  }

  implicit def property2decimal(vp: VertexProperty[String]): BigDecimal = {
    BigDecimal(vp.value.toString)
  }

  val PatientIdProperty = "patient-id"

  val ObservationDateProperty = "observation-date"

  val CompoundProperty = "compound"

  val DoseIdProperty = "dose-id"

  val DoseSizeProperty = "dose-size"

  val DoseUnitProperty = "dose-unit"

  val AdministrationMethodProperty = "administration-method"

}
