package models

import shapeless.HList

trait BaseSiteModel {

  val configuration: HList

}
