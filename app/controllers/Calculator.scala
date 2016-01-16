package controllers

import play.api._
import play.api.mvc._

class Calculator extends Controller {
  // TODO: Create an action called `add`:
  //
  //  - accept two integers extracted from the URL;
  //  - add them together;
  //  - return a plain text HTTP 200 response containing the result.
  def add(a: Int, b: Int) = Action { request =>
    Ok(s"a+b=${a + b}\n")
  }

  // TODO: Create an action called `add`:
  //
  //  - accept two booleans extracted from the URL;
  //  - and them together;
  //  - return a plain text HTTP 200 response containing the result.
  def and(a: Boolean, b: Boolean) = Action { request =>
    Ok(s"a & b = ${a & b}\n")
  }

  // TODO: Create an action called `concat`:
  //
  //  - accept a rest argument extracted fro m the URL;
  //  - concatenate the URL-decoded path fragments from the argument,
  //    effectively removing slashes from the text;
  //  - return a plain text HTTP 200 response containing the result.
  //
  // TIP: Use the `urlDecode` helper method if you need to to decode the .
  def concat(args: String) = Action { request =>
    Ok(urlDecode(args).split('/').mkString(" ")+"\n")
  }

  // TODO: Create an action called `concat`:
  //
  //  - accept a list of integers extracted from the URL;
  //  - sort the list;
  //  - return a space separated plain text HTTP 200 response of the result.
  def sort(a: List[Int]) = Action { request =>
    Ok(s"sorted=${a.sorted.mkString(" ")}\n")
  }

  // TODO: Create an action called `howToAdd`:
  //
  //  - accept two integers extracted from the URL;
  //  - return a plain text HTTP 200 response containing the
  //    HTTP method and URL required to add them together.
  //
  // TIP: Use the reverse route for `add()` to construct the URL.
  def howToAdd(a: Int, b: Int) = Action { request =>
    val methodAndUri: Call = controllers.routes.Calculator.add(a,b)
    Ok(s"${methodAndUri.method} ${methodAndUri.url}\n")
  }

  private def urlDecode(str: String) =
    java.net.URLDecoder.decode(str, "UTF-8")


}