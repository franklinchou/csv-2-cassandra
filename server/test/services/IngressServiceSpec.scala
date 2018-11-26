package services

import models.BaseObservationModel.PatientId
import models.ObservationModel
import org.scalatest.FunSpec
import play.api.inject.guice.GuiceApplicationBuilder
import util.ElapsedTime._

class IngressServiceSpec extends FunSpec {

  describe("Mock ingress service benchmark") {

    val application = new GuiceApplicationBuilder()
    val mockS3Service = application.injector().instanceOf[IngressServiceMock]
    val janusService = application.injector().instanceOf[DataJanusService]

    def insertDataStream(ds: Iterator[String], n: Int): Unit = {
      time(
        ds.foreach { m =>
          val model: ObservationModel = m
          janusService.add(model, ObservationModel.`type`)
        },
        s"Inserted $n records"
      )
    }

    it("retrieval by patient-id in 1,000 records < 1/10th second") {
      val dataStream1 = mockS3Service.streamFromFile("observation-only-1000.csv")
      insertDataStream(dataStream1, 1000)
      val q =
        timeTrial(
          janusService.find(PatientId("25"), ObservationModel.`type`),
          1e8
        )

      assert(q.isDefined)
      assert(q.get.size == 19)
    }

    it("retrieval by patient-id in 100,000 records < 1/10th second") {
      val dataStream2 = mockS3Service.streamFromFile("observation-only-100000.csv")
      insertDataStream(dataStream2, 100000)
      val q =
        timeTrial(
          janusService.find(PatientId("25"), ObservationModel.`type`),
          1e8
        )

      assert(q.isDefined)
      assert(q.get.size > 19)
    }

  }

}
