package com.mitevski.valetservice.models

import org.joda.time.DateTime
import io.circe.generic.auto._

sealed trait ValetInput

case class Valet(id: String, name: String) extends ValetInput
case class Pickup(id: String, time: DateTime) extends ValetInput


