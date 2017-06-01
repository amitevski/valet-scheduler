package com.mitevski.valetservice.models

import io.circe.{Decoder, Encoder, Json}
import org.joda.time.DateTime
import io.circe.generic.auto._

sealed trait ValetInput

case class Valet(id: String, name: String) extends ValetInput

case class Pickup(id: String, time: DateTime) extends ValetInput

object Pickup {
  implicit val decodePickup: Decoder[Pickup] =
    Decoder.forProduct2("id", "time") {
      (id: String, time: String) =>
        Pickup(id, DateTime.parse(time))
    }

  implicit val DefaultJodaDateEncoder: Encoder[DateTime] = Encoder.instance[DateTime] { dateTime => Json.fromString(dateTime.toString()) }

  implicit val encodePickup: Encoder[Pickup] =
    Encoder.forProduct2("id", "time")(p =>
      (p.id, p.time)
    )
}


