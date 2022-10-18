package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import scala.collection.mutable
import models._
import java.time

@Singleton
class entriesController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {
    
    private val entries = new mutable.ListBuffer[entriesItem]()
    //not sure if im initializing LocalDateTime correctly but this is just what i found online:
    //LocalDateTime	Represents both a date and a time (yyyy-MM-dd-HH-mm-ss-ns)
    entries += entriesItem(1, 1, 2022-10-17-8-26-16-00, "fizz", "fizz2")
    entries += entriesItem(2, 2, 2022-10-18-8-26-16-00, "buzz", "buzz2")
    
    implicit val entriesJson = Json.format[entriesItem]
    implicit val entriesDtoJson = Json.format[entriesItemDto]

    def getAll(): Action[AnyContent] = Action {
        if (entries.isEmpty) {
            NoContent
        } else {
            Ok(Json.toJson(entries))
        }
    }

    def getById(itemId: Long) = Action {
        val x = entries.find(_.id == itemId)
        x match {
            case Some(item) => Ok(Json.toJson(item))
            case None => NotFound
        }
    }

    def addNewItem() = Action {
        implicit request =>
            val content = request.body
            val jsonObject = content.asJson
            val entriesItem: Option[entriesItemDto] =
                jsonObject.flatMap(
                    Json.fromJson[entriesItemDto](_).asOpt
                )
        
        entriesItem match {
            case Some(newItem) =>
                val nextId = entries.map(_.id).max + 1
                val toBeAdded = entriesItem(nextId, newItem.description, false)
                entries += toBeAdded
                Created(Json.toJson(toBeAdded))
            case None =>
                BadRequest
        }
    }

    def deleteAllDone() = Action {
        val x = entries.filter(_.isDone == true)
        if (x.isEmpty) NotFound
        x.foreach(y => entries -= y)
        Ok(Json.toJson(entries))
    }
}