package models
import java.time

case class entries(id: Int, userId: Int, date: LocalDateTime, title: String, body: String)

case class entriesDto(date: LocalDateTime, title: String, body: String)