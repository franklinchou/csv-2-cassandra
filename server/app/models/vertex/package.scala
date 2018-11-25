package models

import java.lang.Float
import java.text.SimpleDateFormat
import java.util.Date

import org.apache.tinkerpop.gremlin.structure.VertexProperty


package object vertex {

  implicit def property2string(vp: VertexProperty[String]): String = {
    vp.value.toString
  }

  implicit def property2date(vp: VertexProperty[String]): Date = {
    new SimpleDateFormat("yyyy-MM-dd").parse(vp.value.toString)
  }

  implicit def property2int(vp: VertexProperty[String]): Int = {
    vp.value.toInt
  }

  implicit def property2float(vp: VertexProperty[String]): Float = {
    Float.parseFloat(vp.value.toString)
  }

  val PatientIdProperty = "patient-id"

  val ObservationDateProperty = "observation-date"

  val CompoundProperty = "compound"

  val DoseIdProperty = "dose-id"

  val DoseSizeProperty = "dose-size"

  val DoseUnitProperty = "dose-unit"

  val AdministrationMethodProperty = "administration-method"

  val IntakePhysicianProperty = "intake-physician"

  val IntakeZipCodeProperty = "intake-zip-code"

}
