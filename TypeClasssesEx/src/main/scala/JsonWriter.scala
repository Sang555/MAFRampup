// JSON Writer type class

import java.util

object Demo extends App{

  //class hierarchy
  sealed trait Json

  final case class JsObject(get: Map[String, Json]) extends Json

  final case class JsString(get: String) extends Json

  final case object JsNUll extends Json

  trait JsonWriter[A] {
    def write(data1: A): Json
  }

  case class Product(name: String, color: String, price: String)


  // instances created using implicit prefix
  implicit val stringWriter: JsonWriter[String] = {
    new JsonWriter[String] {
      def write(data1: String): Json = JsString(data1)
    }
  }
  //creating product jsonWriter
  implicit val productWriter: JsonWriter[Product] = {
    new JsonWriter[Product] {
      def write(data1: Product): Json =
        JsObject(Map(
          "name" -> JsString(data1.name),
          "color" -> JsString(data1.color),
          "price" -> JsString(data1.price)
        ))
    }
  }


  object JsonInterface {
    def toJson[A](data1: A)(implicit w: JsonWriter[A]): Json = w.write(data1)
  }

    println(JsonInterface.toJson(Product("a", "b", "1")))

}
