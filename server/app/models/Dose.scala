package models

case class Dose(doseId: Int,
                compound: Compound,
                size: BigDecimal,
                unit: String,
                administrationMethod: String = "Tablet") {

  val compoundName: String = this.compound.getClass.getSimpleName.replace("$", "")

}