package controllers

import javax.inject._
import play.api._
import play.api.libs.json.Json
import play.api.mvc._


@Singleton
class HomeController @Inject()(config: Configuration,
                               cc: ControllerComponents) extends AbstractController(cc) {

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(
      Json.obj(
        "application" -> "csv-2-cassandra",
        "environment" -> config.get[String]("app.environment")
      )
    )
  }
}
