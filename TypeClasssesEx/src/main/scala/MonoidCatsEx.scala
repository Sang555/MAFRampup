// To calculate total expense in a year using List[Moniey]

import cats.kernel.Monoid

case class Money(rupees: Int, paise: Int)
object MonoidDemo extends App {

  implicit val MoneyMonoid = new Monoid[Money]{
    override def empty: Money = Money(0,0)

    override def combine(x: Money, y: Money): Money = {
      Money(x.rupees + y.rupees + ((x.paise + y.paise)/100), (x.paise+y.paise)%100)
    }
  }
  val expenses = List(Money(20,50),Money(100,25))
  def total(expenses: List[Money])(implicit  m: Monoid[Money]): Money = {
    expenses.foldLeft(m.empty){
      (a,b) => m.combine(a,b)
    }
  }
  println(total(expenses))
}