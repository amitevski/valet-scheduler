package com.mitevski.valetservice.services

import com.mitevski.valetservice.models.Valet

import scala.util.{Success, Try}


object ValetDb {

  val mockValets = List(Valet("1", "Aco"), Valet("2", "Mark"))

  def availableValets: Try[List[Valet]] = Success(mockValets) //fetch available valets from db

}
