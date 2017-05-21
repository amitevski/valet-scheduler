package com.mitevski.valetservice.models

import org.joda.time.DateTime

sealed trait ValetInput

case class Valet(id: String, name: String) extends ValetInput
case class Pickup(id: String, time: DateTime) extends ValetInput


