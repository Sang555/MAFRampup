//1. show Trait of Cat
//2. Eq trait of Cat, adding customized implementations


import cats._
import cats.Show
import cats.implicits._
import cats.Eq
import cats.syntax.eq._
import cats.instances.int._
import cats.instances.string._
import cats.instances.option._



case class Cat(name: String, age: Int, color: String)



object ex1 extends App{
  implicit val catShow: Show[Cat] =
    new Show[Cat] {
      def show(cat: Cat): String =
        "Name: " + cat.name + " Age: " + cat.age + " Color: " + cat.color
    }

  implicit val catEqual: Eq[Cat] =
    Eq.instance[Cat] { (cat1,cat2) =>
      (cat1.name == cat2.name) &&
        (cat1.age == cat2.age) &&
        (cat1.color == cat2.color)
    }

  val showCat: Show[Cat] = Show.apply[Cat]
  val cat = Cat("Pinky", 3, "white")
  println(showCat.show(cat))
  val cat2 = Cat("Pixie",2,"white")

  println(cat == cat2)
  val opcat1 = Option(cat)
  val opcat2 = Option.empty[Cat]
  print(opcat1 == opcat2)
}


