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
    val dataStream = mockS3Service.streamFromFile("observation-only-1000.csv")

    time(
      dataStream
        .foreach { m =>
          val model: ObservationModel = m
          janusService.add(model, ObservationModel.`type`)
        },
      "Inserted 1000 records"
    )

    it("retrieval by patient-id in less than 1/10th second") {
      val q =
        timeTrial(
          janusService.find(PatientId("25"), ObservationModel.`type`),
          1e8
        )

      assert(q.isDefined)
      assert(q.get.size == 19)
    }
  }

}
