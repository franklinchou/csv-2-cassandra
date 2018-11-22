package models

object SiteModel {

  case class IntakePhysician(value: String) extends AnyVal {
    override def toString: String = value
  }

  /**
    * The site's zip code
    *
    * @param value 5 digit zip code
    */
  case class ZipCode(value: Int) extends AnyVal {
    override def toString: String = value.toString
  }

}
