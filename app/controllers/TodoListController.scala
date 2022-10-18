package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import scala.collection.mutable
import models._

@Singleton
class TodoListController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {
    
    private val todoList = new mutable.ListBuffer[TodoListItem]()
    todoList += TodoListItem(1, "foo", true)
    todoList += TodoListItem(2, "bar", false)
    
    implicit val todoListJson = Json.format[TodoListItem]
    implicit val todoListDtoJson = Json.format[TodoListItemDto]

    def getAll(): Action[AnyContent] = Action {
        if (todoList.isEmpty) {
            NoContent
        } else {
            Ok(Json.toJson(todoList))
        }
    }

    def getById(itemId: Long) = Action {
        val x = todoList.find(_.id == itemId)
        x match {
            case Some(item) => Ok(Json.toJson(item))
            case None => NotFound
        }
    }

    def markAsDone(itemId: Long) = Action {
        val x = todoList.find(_.id == itemId)
        x match {
            case Some(item) => 
                val newItem = item.copy(isDone = true)
                todoList += newItem
                todoList -= item
                Ok(Json.toJson(newItem))
            case None =>
                BadRequest
        }
    }

    def addNewItem() = Action {
        implicit request =>
            val content = request.body
            val jsonObject = content.asJson
            val todoListItem: Option[TodoListItemDto] =
                jsonObject.flatMap(
                    Json.fromJson[TodoListItemDto](_).asOpt
                )
        
        todoListItem match {
            case Some(newItem) =>
                val nextId = todoList.map(_.id).max + 1
                val toBeAdded = TodoListItem(nextId, newItem.description, false)
                todoList += toBeAdded
                Created(Json.toJson(toBeAdded))
            case None =>
                BadRequest
        }
    }

    def deleteAllDone() = Action {
        val x = todoList.filter(_.isDone == true)
        if (x.isEmpty) NotFound
        x.foreach(y => todoList -= y)
        Ok(Json.toJson(todoList))
    }
}