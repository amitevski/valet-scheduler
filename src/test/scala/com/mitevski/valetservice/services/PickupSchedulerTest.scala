package com.mitevski.valetservice.services

import com.mitevski.valetservice.models.{Pickup, ScheduledPickup}
import org.joda.time.DateTime
import org.scalatest.BeforeAndAfterEach

import scala.collection.mutable

class PickupSchedulerTest extends org.scalatest.FunSuite with BeforeAndAfterEach {

  override def afterEach() {
    ScheduledPickupDb.scheduledPickupsMock = new mutable.MutableList[ScheduledPickup]
  }

  test("scheduleValet should select right timeframe for pickup") {

    val requestedPickup = Pickup("1", DateTime.now())

    val scheduledPickup = (for {
      valets <- ValetDb.availableValets
      scheduledPickups <- ScheduledPickupDb.scheduledPickups
      scheduledPickup <- PickupScheduler.scheduleValet(valets, scheduledPickups, requestedPickup)
    } yield  scheduledPickup) get

    val expected = ScheduledPickup("1", ValetDb.mockValets.head, requestedPickup)
    assert(expected == scheduledPickup)
  }

  test("scheduleValet should select unused valet for second pickup") {

    val requestedPickup = Pickup("1", DateTime.now())

    val scheduledPickup = (for {
      valets <- ValetDb.availableValets
      scheduledPickups <- ScheduledPickupDb.scheduledPickups
      scheduledPickup <- PickupScheduler.scheduleValet(valets, scheduledPickups, requestedPickup)
    } yield  scheduledPickup) get

    assert(scheduledPickup.valet == ValetDb.mockValets(0))

    ScheduledPickupDb.saveScheduledPickup(scheduledPickup)

    val requestedPickup2 = Pickup("2", DateTime.now())
    val scheduledPickup2 = (for {
      valets <- ValetDb.availableValets
      scheduledPickups <- ScheduledPickupDb.scheduledPickups
      scheduledPickup <- PickupScheduler.scheduleValet(valets, scheduledPickups, requestedPickup)
    } yield  scheduledPickup) get

    assert(scheduledPickup2.valet == ValetDb.mockValets(1))
  }

}
