// a semigroup is combine part of monoid
//a monoid of type A: follows closure, associative law and identity element
// ex: 1. define all possible boolean monoids
// ex: 2. deine all monoids and semigroups for sets

object MonoidDemo2 extends App {
  trait Semigroup[A] {
    def combine(x: A, y: A): A
  }

  trait Monoid[A] extends Semigroup[A] {
    def empty: A
  }

  def AssociativeLaw[A](x: A, y: A, z: A)
                       (implicit m: Monoid[A]): Boolean = {
    m.combine(x, m.combine(y, z)) == m.combine(m.combine(x, y), z)
  }

  def IdentityLaw[A](x: A)
                    (implicit m: Monoid[A]): Boolean = {
    (m.combine(x, m.empty) == x) && (m.combine(m.empty, x) == x)
  }


  //ex2
  //intersection semigroup
  implicit val setIntersectionSemiGroup: Semigroup[Set[Int]] =
  new Semigroup[Set[Int]] {
    def combine(x: Set[Int], y: Set[Int]): Set[Int] = x intersect y
  }

  //union monoid
  implicit val setUnionSemiGroup: Monoid[Set[Int]] =
    new Monoid[Set[Int]] {
      def empty: Set[Int] = Set.empty[Int]

      def combine(x: Set[Int], y: Set[Int]): Set[Int] = x union y
    }


//  val a = Set[Int](1, 2, 3)
//  val b = Set[Int](2, 4)
//  val c = Set[Int](1, 4)
//
//
//  println(AssociativeLaw[Set[Int]](a, b, c))

  //ex1
  //and
  implicit val booleanAndMonoid: Monoid[Boolean] =
  new Monoid[Boolean] {
    def combine(x: Boolean, y: Boolean) = x && y

    def empty = true
  }
  //or
  implicit val booleanOrMonoid: Monoid[Boolean] =
    new Monoid[Boolean] {
      def combine(x: Boolean, y: Boolean) = x || y

      def empty = false
    }
  //xor
  implicit val booleanXorMonoid: Monoid[Boolean] =
    new Monoid[Boolean] {
      def empty: Boolean = true

      def combine(x: Boolean, y: Boolean): Boolean =
        (!x || y) && (x || !y)
    }


}


