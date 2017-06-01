package com.mitevski.valetservice.services

import com.mitevski.valetservice.models.{Pickup, ScheduledPickup, Valet}
import org.joda.time.{DateTime, Interval}

import scala.util.{Failure, Success, Try}
import scalaz.concurrent.Task


object PickupScheduler {

  var id = 0

  def scheduleValet(valets: List[Valet], scheduledPickups: List[ScheduledPickup], requestedPickup: Pickup): Task[ScheduledPickup] = {
    val occuppied = findOccupiedValets(scheduledPickups, requestedPickup.time)
    val available = valets.filter( valet => !occuppied.contains(valet))
    if (available.isEmpty) Task(throw new Exception("no valet available"))
    else {
      // choose valet with min(pickups)
      val valet = valetWithMinPickups(valets, scheduledPickups)
      id += 1
      Task(ScheduledPickup(id.toString, valet, requestedPickup))
    }
  }

  def findOccupiedValets(scheduledPickups: List[ScheduledPickup], time: DateTime): List[Valet] = {
    val start = time
    val end = time.plusHours(1)
    val interval = new Interval(start, end)
    scheduledPickups.filter {
      scheduled =>
        interval.contains(scheduled.pickup.time)
    }.map(_.valet)
  }

  def valetWithMinPickups(valets: List[Valet], scheduledPickups: List[ScheduledPickup]): Valet = {
    val notScheduled = notScheduledValets(valets, scheduledPickups)
    if (notScheduled.nonEmpty) notScheduled.head
    else {
      val valetCounts = scheduledPickups.groupBy(_.valet)
      valetCounts.min(Ordering.by((v: (_, List[ScheduledPickup])) => v._2.length))._1
    }
  }

  // valets without pickups today
  def notScheduledValets(valets: List[Valet], scheduledPickups: List[ScheduledPickup]): List[Valet] = {
    val scheduled = scheduledValets(scheduledPickups)
    valets.filter(valet => !scheduled.contains(valet))
  }

  // valets with pickups today
  def scheduledValets(scheduledPickups: List[ScheduledPickup]): List[Valet] = scheduledPickups.map(_.valet)
}
