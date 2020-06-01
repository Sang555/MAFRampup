//  Printable type class

object Demo2 extends App {

  //1
  trait Printable[A] {
    def format(value: A): String
  }

  //2
  object PrintableInstances {
    implicit val stringPrintable: Printable[String] = {
      new Printable[String] {
        def format(value: String): String = value
      }
    }
    implicit val intPrintable: Printable[Int] = {
      new Printable[Int] {
        def format(value: Int): String = value.toString
      }
    }
  }

  //3
  object Printable {
    def format[A](value: A)(implicit w: Printable[A]): String = w.format(value)

    def print[A](value: A)(implicit w: Printable[A]): Unit = println(format(value))
  }

  import PrintableInstances._

  case class Cat(name: String, age: Int, color: String)

  implicit val catPrintable: Printable[Cat] = new Printable[Cat] {
    override def format(value: Cat): String = {
      val name = Printable.format((value.name))
      val age = Printable.format(value.age)
      val color = Printable.format((value.color))
      name + age + color
    }
  }

  val cat = Cat("Kiki", 20, "grey")
  Printable.print(cat)
}

