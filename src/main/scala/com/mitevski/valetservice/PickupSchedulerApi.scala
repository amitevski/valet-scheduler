package com.mitevski.valetservice

import com.mitevski.valetservice.models.{Pickup, ScheduledPickup}
import com.mitevski.valetservice.services.{PickupScheduler, ScheduledPickupDb, ValetDb}
import io.circe.Decoder
import io.circe.parser.decode
import io.circe.syntax._
import io.circe.generic.auto._
import org.http4s._
import org.http4s.circe._
import org.http4s.dsl._



object PickupSchedulerApi {
  val service = HttpService {
    case req @ POST -> Root / "api" / "v1" / "schedule_valet" =>
      // TODO: implement json decoding/encoding of case classes
//      for {
//        requestedPickup <- req.as(jsonOf[Pickup])
//        valets <- ValetDb.availableValets
//        scheduledPickups <- ScheduledPickupDb.scheduledPickups
//        scheduledPickup <- PickupScheduler.scheduleValet(valets, scheduledPickups, requestedPickup)
//        resp <- Ok(scheduledPickup.asJson)
//      } yield resp
      for {
        resp <- NotImplemented("not implemented")
      } yield resp
  }
}
