package models
import java.time

case class entries(id: Long, userId: Long, date: LocalDateTime, title: String, body: String)

case class entriesDto(date: LocalDateTime, title: String, body: String)