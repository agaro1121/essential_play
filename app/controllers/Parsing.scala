package controllers

import play.api.libs.json.JsValue
import play.api.mvc._

import scala.xml.NodeSeq

/**
  * Created by anthonygaro on 1/15/16.
  */
object Parsing extends Controller {

  def exampleAction = Action { request =>
    request.body.asXml match {
      case Some(xml) => Ok(xml.text)
      case None => BadRequest("Not XML !")
    }
  }

  def exampleAction2 = Action { request =>
    (request.body.asText map handleText) orElse
    (request.body.asJson map handleJson) orElse
    (request.body.asXml map handleXml) getOrElse
    BadRequest("You've got me stumped! ")

  }

  def handleText(data: String): Result = Ok(s"Result=$data")
  def handleJson(data: JsValue): Result = Ok(s"Result=${data.toString}")
  def handleXml(data: NodeSeq): Result = Ok(s"Result=${data.text}")

  def exampleJsonAction = Action(parse.json) { request =>
    val body: JsValue = request.body
    Ok(s"Result=$body")
  }

}
