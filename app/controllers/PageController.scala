package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import scala.collection.mutable
import models._

@Singleton
class PageController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {
    def Home() = Action {
        Ok(views.html.Home())
    }
}
