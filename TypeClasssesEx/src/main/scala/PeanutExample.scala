
//Bag is a functor

object FunctorExample extends App {

  //this is a functor
  case class Bag[A](value: A) {
    def map[B](f: A => B): Bag[B] = Bag(f(value))

    def apply(content: A): Bag[A] = new Bag(content)
  }

  //this is a moand
  case class Bag2[A](value: A) {
    def map[B](f: A => B): Bag2[B] = Bag2(f(value))
    def flatmap[B](f: A => Bag2[B]): Bag2[B] = f(value)
    def apply(content: A): Bag2[A] = new Bag2(content)
    def flatten = value
  }



  case class Mix(quantity: Double)

  //function
  val halfFunction = (mix: Mix) => Mix(mix.quantity / 2)

  //Functor of type Mix
  val mixBag: Bag[Mix] = Bag(Mix(1))

  val halfMixBag: Bag[Mix] = mixBag.map(mix => halfFunction(mix))
  println("using functors "+ halfMixBag)

  val mixBag2: Bag2[Mix] = Bag2(Mix(1))
  val halfFunction2 = (mix: Mix) => Bag2(Mix(mix.quantity / 2))
  val halfMixBag2: Bag2[Mix] = mixBag2.flatmap(mix => halfFunction2(mix))
  println("using monads" +halfMixBag2)
  //Verify idetity law
  val sugarBag = Bag(Mix(1))
  println(sugarBag.map(identity) == sugarBag)




}


