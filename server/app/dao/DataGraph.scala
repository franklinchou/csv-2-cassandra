package dao

import java.time.temporal.ChronoUnit

import models.vertex
import org.apache.tinkerpop.gremlin.structure.Vertex
import org.janusgraph.core.schema.{SchemaAction, SchemaStatus}
import org.janusgraph.core.{JanusGraph, JanusGraphFactory}
import org.janusgraph.graphdb.database.management.ManagementSystem

object DataGraph {

  val graph: JanusGraph = JanusGraphFactory.open("inmemory")

  def setUp(jg: JanusGraph): Unit = {

    var mgmt = jg.openManagement()

    // Associate model types as vertex labels
    keys
      .filter(m => Option(mgmt.getVertexLabel(m)).isEmpty)
      .foreach(vl => mgmt.makeVertexLabel(vl.toString).make())

    // Create model properties on vertices (for those that don't exist)
    properties
      .filter(pk => Option(mgmt.getPropertyKey(pk._1)).isEmpty)
      .foreach(pk => mgmt.makePropertyKey(pk._1).cardinality(pk._3).dataType(pk._2).make())

    mgmt.commit()

    mgmt = jg.openManagement()

    // Retrieve properties
    val patientIdProperty = mgmt.getPropertyKey(vertex.PatientIdProperty)
    //    val observationDateProperty = mgmt.getPropertyKey(vertex.ObservationDateProperty)
    //    val compoundProperty = mgmt.getPropertyKey(vertex.CompoundProperty)
    //    val doseIdProperty = mgmt.getPropertyKey(vertex.DoseIdProperty)
    //    val doseSizeProperty = mgmt.getPropertyKey(vertex.DoseSizeProperty)
    //    val doseUnitProperty = mgmt.getPropertyKey(vertex.DoseUnitProperty)
    //    val adminMethod = mgmt.getPropertyKey(vertex.AdministrationMethodProperty)

    // Retrieve models (vertices)
    //    val observationLabel = mgmt.getVertexLabel(ObservationModel.`type`)

    mgmt
      .buildIndex("index-by-patient", classOf[Vertex])
      .addKey(patientIdProperty)
      .buildCompositeIndex()

    mgmt.commit()

    val indices = Set("index-by-patient")

    // Block until indices are ready
    indices.foreach { i =>
      ManagementSystem
        .awaitGraphIndexStatus(jg, i)
        .status(SchemaStatus.REGISTERED)
        .timeout(2, ChronoUnit.MINUTES)
        .call()
    }

    mgmt = jg.openManagement()

    indices.foreach { i =>
      mgmt.updateIndex(mgmt.getGraphIndex(i), SchemaAction.REINDEX).get()
    }

    mgmt.commit()

  }

}
