package com.mitevski.valetservice.services

import com.mitevski.valetservice.models.Valet

import scala.util.{Success, Try}
import scalaz.concurrent.Task


object ValetDb {

  val mockValets = List(Valet("1", "Aco"), Valet("2", "Mark"))

  def availableValets: Task[List[Valet]] = Task(mockValets) //fetch available valets from db

}
