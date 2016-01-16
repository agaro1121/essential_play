package controllers

import play.api.mvc._

/**
  * Created by Hierro on 1/15/16.
  */
class HeadersNCookies extends Controller {

  def headers = Action { request =>
    val headers: Headers = request.headers
    val ucType: Option[String] = headers.get("Content-Type")
    val lcType: Option[String] = headers.get("content-type")
    val cookies: Cookies = request.cookies
    val cookie: Option[Cookie] = cookies.get("DemoCookie")
    val value: Option[String] = cookie.map(_.value)
    Ok(Seq(
      s"Headers: $headers",
      s"Content-Type: $ucType",
      s"content-type: $lcType",
      s"Cookies: $cookies",
      s"Cookie value: $value"
    ) mkString "\n")
  }

  // The HTTP method ("GET", "POST", etc):
  def method = Action {request => Ok(request.method + "\n") }
  // The URI, including path and query string:
  def uri = Action { request => Ok(request.uri + "\n") }
  // The path of the URI, without the query string:
  def path = Action { request => Ok(request.path + "\n") }
  // The query string, split into name/value pairs:
  def query = Action { request => Ok(request.queryString + "\n") }

  def ohio = Action { request => Ok("OHAI\n").
    as("text/lolspeak").
    withHeaders(
      "Cache-Control" -> "no-cache, no-store, must-revalidate", "Pragma" -> "no-cache",
      "Expires" -> "0"
    ).
    withCookies(
      Cookie(name = "DemoCookie", value = "DemoCookieValue"), Cookie(name = "OtherCookie", value = "OtherCookieValue")
    )
  }

}
