package com.mitevski.valetservice.services

import com.mitevski.valetservice.models.{ScheduledPickup, Valet}

import scala.collection.mutable
import scala.util.{Success, Try}


object ScheduledPickupDb {

  var scheduledPickupsMock = new mutable.MutableList[ScheduledPickup]

  def scheduledPickups: Try[List[ScheduledPickup]] = Success(scheduledPickupsMock.toList)

  def saveScheduledPickup(scheduledPickup: ScheduledPickup): Try[Boolean] = {
    scheduledPickupsMock += scheduledPickup
    Success(true)
  }

}
