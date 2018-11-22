package models

import models.DoseModel._

object DoseModel {

  case class DoseId(value: Int) extends AnyVal {
    override def toString: String = value.toString
  }

  case class DoseSize(value: BigDecimal) extends AnyVal {
    override def toString: String = value.toString()
  }

  case class DoseUnit(value: String) extends AnyVal {
    override def toString: String = value
  }

  case class DoseAdministrationMethod(value: String) extends AnyVal {
    override def toString: String = value
  }

  final val DefaultAdministrationMethod = DoseAdministrationMethod("Tablet")

}

case class DoseModel(doseId: DoseId,
                     compound: CompoundModel,
                     size: DoseSize,
                     unit: DoseUnit,
                     administrationMethod: DoseAdministrationMethod = DefaultAdministrationMethod) {

  val compoundName: String = this.compound.getClass.getSimpleName.replace("$", "")

}