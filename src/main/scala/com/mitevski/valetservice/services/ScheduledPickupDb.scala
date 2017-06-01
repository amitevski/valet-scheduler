package com.mitevski.valetservice.services

import com.mitevski.valetservice.models.{ScheduledPickup, Valet}

import scala.collection.mutable
import scala.util.{Success, Try}
import scalaz.concurrent.Task


object ScheduledPickupDb {

  var scheduledPickupsMock = new mutable.MutableList[ScheduledPickup]

  def scheduledPickups: Task[List[ScheduledPickup]] = Task(scheduledPickupsMock.toList)

  def saveScheduledPickup(scheduledPickup: ScheduledPickup): Task[Boolean] = {
    scheduledPickupsMock += scheduledPickup
    Task(true)
  }

}
