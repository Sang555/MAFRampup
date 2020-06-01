//Using singleton Objects and companion objects
// 1. using companion object as a fctory method to create classes
// 2. understanding match control flow structure
// 3. understanding relation between class and companion object
class Movie(val name: String, val year: Int)

object Movie{
  def bestMOvieOfYear(x: Int) = {
    x match {
      case 2016 => Some(new Movie("Godzilla", 2016))
      case 2018 => Some(new Movie("contagion",2018))
      case _ => None
    }
  }
}

print(Movie.bestMOvieOfYear((2016)))

//SINGELTON OBJECT

object S1{
  def sayHello() = {
    println("Singleton method to say hello")
  }
}
S1.sayHello()

//COMPANION OBJECT

class A {
  private var sum: Int = 0
  def add(b: Int) = sum +1
  def retVal() = sum
}

object A{
  private val l1 =  Array(1)
  def increment(b: Int) = {

      val l = new A
      l.add(b)
    print(l1)
      l1 :+ l.retVal()
      print(l.retVal())
      print(l1)

  }
}

A.increment(1)