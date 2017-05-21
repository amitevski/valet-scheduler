package com.mitevski.valetservice.models

sealed trait ValetOutput

case class ScheduledPickup(id: String, valet: Valet, pickup: Pickup)
