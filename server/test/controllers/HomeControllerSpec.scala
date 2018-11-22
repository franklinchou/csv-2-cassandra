package controllers

import org.scalatest.mockito.MockitoSugar
import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.Configuration
import play.api.test.Helpers._
import play.api.test._

class HomeControllerSpec extends PlaySpec with MockitoSugar with GuiceOneAppPerTest with Injecting {

  "HomeController GET /" should {

    "return 200" in {
      val request = FakeRequest(GET, "/")
      val config = mock[Configuration]
      val controller = new HomeController(config, stubControllerComponents())
      val health = controller.index().apply(request)
      status(health) mustBe OK
    }

  }

}
