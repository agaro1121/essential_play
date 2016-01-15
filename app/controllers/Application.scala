package controllers

import org.joda.time.{DateTimeZone, DateTime}
import org.joda.time.format.DateTimeFormat
import play.api._
import play.api.mvc._

class Application extends Controller with TimeHelpers{

  def index = Action {
    Ok(views.html.index("Your new application is ready."))

  }

   def hello = Action {
     request =>
       Ok("Saluton Mondo")
   }

  def helloTo(name: String) = Action { request =>
    Ok(s"Hello $name")

  }

  // TODO: Return an HTTP 200 plain text response containing the time.
  //
  // Use the `localTime` and `timeToString` helper methods below.
  def time = Action {
    request =>
      Ok(timeToString(localTime))
  }


  // TODO: Read in a time zone ID (a string) and return an HTTP 200
  // plain text response containing the localized time.
  //
  // Use the `localTimeInZone` and `timeToString` helper methods below.
  def timeIn(zoneId: String) = Action {
    Ok(timeToString(localTimeInZone(zoneId).get))
  }


  // TODO: Return an HTTP 200 plain text response containing a list of
  // available time zone codes.
  //
  // Use the `zoneIds` helper method below.
  def zones = Action { request =>
    Ok(zoneIds.mkString("\n"))
  }

  def send(username: String, message: Option[String]) = Action { request =>
    Ok(s"username=$username ${(message foldLeft "message=")(_ ++ _)}")
  }

  def optionExample(arg: Option[Int]) = Action { request =>
    Ok(s"${arg}")
  }

  def listExample(arg: List[Int]) = Action { request =>
    Ok(s"${arg.mkString("\n")}")
  }

}


trait TimeHelpers {
  def localTime: DateTime =
    DateTime.now

  def localTimeInZone(zoneId: String): Option[DateTime] =
    zoneForId(zoneId) map (DateTime.now.withZone)

  def timeToString(time: DateTime): String =
    DateTimeFormat.shortTime.print(time)

  def zoneIds: List[String] = {
    import scala.collection.JavaConversions._
    DateTimeZone.getAvailableIDs.toList
  }

  def zoneForId(zoneId: String): Option[DateTimeZone] =
    try { Some(DateTimeZone.forID(zoneId)) }
    catch { case exn: IllegalArgumentException => None }
}
