package models

object CompoundModel {

  case class Compound(value: String) extends AnyVal {
    override def toString: String = value
  }

}