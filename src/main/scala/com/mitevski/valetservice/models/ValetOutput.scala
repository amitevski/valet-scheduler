package com.mitevski.valetservice.models

import io.circe.generic.auto._

sealed trait ValetOutput

case class ScheduledPickup(id: String, valet: Valet, pickup: Pickup)
