package services

import models.ObservationModel
import org.scalatest.FunSpec
import play.api.inject.guice.GuiceApplicationBuilder

class IngressServiceSpec extends FunSpec {

  describe("A mock ingress service") {
    ignore("should be able to stream a file") {
      val application = new GuiceApplicationBuilder()
      val mockS3Service = application.injector().instanceOf[IngressServiceMock]
      val s = mockS3Service.streamFromFile("observation-only-1000.csv")
      val q = s.map(a => a: ObservationModel)
      // TODO Finish this test
    }
  }

}
