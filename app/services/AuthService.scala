package services

object AuthService {

  import services.AuthServiceMessages._

  private val passwords = Map[Username, Password](
    "alice" -> "password1",
    "bob" -> "password2",
    "charlie" -> "password3"
  )

  private var sessions = Map[SessionId, Username]()

  // TODO: Complete:
  //  - Check if the username is in `passwords`
  //     - If it is, check the password:
  //        - If it's correct, create a `session` and return a `LoginSuccess`
  //        - If it isn't, return `PasswordIncorrect`
  //     - If it isn't, return `UserNotFound`
  def login(request: LoginRequest): LoginResponse = {
    val r = passwords.get(request.username) map { pass =>
      val sessionId = generateSessionId
      sessions += (sessionId -> request.username)
      if (pass == request.password) LoginSuccess(sessionId) else PasswordIncorrect(request.username)
    }
    r.getOrElse(UserNotFound(request.username))
  }

  // TODO: Complete:
  //  - Check if the session if in `sessions`:
  //     - If it is, delete it
  //     - If it isn't, do nothing
  def logout(sessionId: SessionId): Unit = {
    sessions.get(sessionId) match {
      case Some(x) => sessions = sessions - sessionId
      case None => Unit
    }
  }

  // TODO: Complete:
  //  - Check if the session is in `sessions`:
  //     - If it is, return `Credentials`
  //     - If it isn't, return `SessionNotFound`
  def whoami(sessionId: SessionId): WhoamiResponse = {
    sessions.get(sessionId) match {
      case Some(x) => Credentials(sessionId, x)
      case None => SessionNotFound(sessionId)
    }
  }

  private def generateSessionId: String =
    java.util.UUID.randomUUID.toString
}
