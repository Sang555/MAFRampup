//writer is used to carry logs along with data..log is tied to computation
import cats.data.Writer
import cats.instances.vector._
import cats.syntax.writer._
//using this on series of computations stacks up the logs

object WriterMonad extends App {
  val a = Writer(Vector("msg1", "msg2", "msg3"), 2)
  println(a)

  //in case only log..can use tell

  println(Vector("msg1", "msg2", "msg3").tell)

  //all the log vectors are combine on own in WRiter
  type Logged[A] = Writer[Vector[String], A]

  def doOne: Logged[Int] = Writer(Vector(), 1)

  def doTwo: Logged[Int] = Writer(Vector(), 2)

  def doThree: Logged[Int] = Writer(Vector(), 3)

  val w = for {
    one <- doOne
    _ <- Vector(s"Just obtained $one").tell
    two <- doTwo
    _ <- Vector(s"Just obtained $two").tell
    three <- doThree
    _ <- Vector(s"Just obtained $three").tell
  } yield (one * two * three)

  val (messages, result) = w.run
  println("messages" + messages)
  println("result" + result)
}