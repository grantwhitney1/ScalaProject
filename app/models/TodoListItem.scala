package models

case class TodoListItem(id: Long, description: String, isDone: Boolean)

case class TodoListItemDto(description: String)