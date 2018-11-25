import java.lang.Float
import java.util.Date

import models.{ObservationModel, SiteObservationModel, vertex}
import org.janusgraph.core.Cardinality

package object dao {

  val properties =
    Seq(
      (vertex.PatientIdProperty, classOf[String], Cardinality.SINGLE),
      (vertex.ObservationDateProperty, classOf[Date], Cardinality.LIST),
      (vertex.CompoundProperty, classOf[String], Cardinality.LIST),
      (vertex.DoseIdProperty, classOf[Integer], Cardinality.LIST),
      (vertex.DoseSizeProperty, classOf[Float], Cardinality.LIST),
      (vertex.DoseUnitProperty, classOf[String], Cardinality.LIST),
      (vertex.AdministrationMethodProperty, classOf[String], Cardinality.LIST)
    )

  /**
    * Model types as keys
    */
  val keys: Seq[String] =
    Seq(
      ObservationModel.`type`,
      SiteObservationModel.`type`
    )

}
