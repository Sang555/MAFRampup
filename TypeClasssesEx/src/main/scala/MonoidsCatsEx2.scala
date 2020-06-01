// User popularity contest i.e. having most number of followers
import cats.kernel.Monoid

case class User( name: String, followers: Int) {
  def compareTo(y: User) = this.followers- followers
  def +(user2: User)(implicit  m: Monoid[User]): User = {
    m.combine(this, user2)
  }
}

object PopularityDemo extends App {
  implicit val userMonoid = new Monoid[User] {
    override def empty: User = User("xxx",0)

    override def combine(x: User, y: User): User = {
      if(x.compareTo(y) >=1 ) x else y
    }
  }

val u1 = User("a",1)
  val u2= User("b",2)

  println(u1+u2)

}