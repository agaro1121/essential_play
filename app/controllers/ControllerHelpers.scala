package controllers

import play.api.mvc.{Request, AnyContent, Result, Cookie}

/**
  * Created by hierro on 1/21/16.
  */
trait ControllerHelpers {

  implicit class RequestCookieOps(request: Request[AnyContent]) {
    def sessionCookieId: Option[String] =
      request.cookies.get("ChatAuth").map(_.value)
  }

  implicit class ResultCookieOps(result: Result) {
    def withSessionCookie(sessionId: String) =
      result.withCookies(Cookie("ChatAuth", sessionId))
  }

}
