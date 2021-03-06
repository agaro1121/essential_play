package services

object ChatService {

  import ChatServiceMessages._

  private var postedMessages = Vector[Message]()

  // TODO: Complete:
  //  - Delete all messages in `postedMessages`
  def clear(): Unit = {
    postedMessages = Vector[Message]()
  }

  // TODO: Complete:
  //  - Return a list of messages in `postedMessages`
  def messages: Seq[Message] = {
    postedMessages.toSeq
  }

  // TODO: Complete:
  //  - Add a new message to `postedMessages`
  def chat(author: String, text: String): Message = {
    val newMessage = Message(author, text)
    postedMessages = postedMessages :+ newMessage
    newMessage
  }
}
