package controllers

import play.api._
import play.api.mvc._
import services.AuthServiceMessages

class AuthController extends Controller with ControllerHelpers {

  import services.AuthService
  import services.AuthServiceMessages._

  // TODO: Complete:
  //  - Call AuthService.login
  //     - If it's LoginSuccess, return an Ok response that sets a cookie
  //     - If it's UserNotFound or PasswordIncorrect, return a BadRequest response
  //
  // NOTE: We don't know how to create HTML yet,
  // so populate each response with a plain text message.
  def login(username: Username, password: Password) = Action { request =>
    AuthService.login(AuthServiceMessages.LoginRequest(username, password)) match {
      case res: AuthServiceMessages.LoginSuccess => Ok("Success !").withSessionCookie(res.sessionId)
      case res: PasswordIncorrect => BadRequest(s"password incorrect for $username")
      case res: UserNotFound => BadRequest(s"$username not found")
    }
  }
}
