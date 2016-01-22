package controllers

import play.api._
import play.api.mvc._

class ChatController extends Controller {

  import services.AuthService
  import services.AuthServiceMessages._

  import services.ChatService
  import services.ChatServiceMessages._

  // TODO: Complete:
  //  - Check if the user is logged in
  //     - If they are, return an Ok response containing a list of messages
  //     - If they aren't, redirect to the login page
  //
  // NOTE: We don't know how to create HTML yet,
  // so populate each response with a plain text message.
  def index = Action { request =>
    withAuthenticatedUser(request) { creds =>
      Ok(ChatService.messages.mkString("\n"))
    }
  }

  // TODO: Complete:
  //  - Check if the user is logged in
  //     - If they are, create a message from the relevant author
  //     - If they aren't, redirect to the login page
  //
  // NOTE: We don't know how to create HTML yet,
  // so populate each response with a plain text message.
  def submitMessage(text: String) = Action { request =>
    withAuthenticatedUser(request) { creds =>
      ChatService.chat(creds.username, text)
      Redirect(routes.ChatController.index)
    }
  }

  private def withAuthenticatedUser(request: Request[AnyContent])(func: Credentials => Result): Result =
    request.cookies.get("ChatAuth") match {
      case Some(sessionId) =>
        AuthService.whoami(sessionId.value) match {
          case res: Credentials => func(res)
          case res: SessionNotFound => BadRequest("Not logged in!")
        }
      case None => BadRequest("Not logged in!")
    }

}