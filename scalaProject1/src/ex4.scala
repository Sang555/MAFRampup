//creating customised rational number class with
// i.denominator not 0
// ii.basic operations
// iii.case of denominator 1 using auxiliary constructor
// iv.reducing rational number to simplest from eg, 66/77 to 6/7
// v.overloaded operation Eg., 3 + 2/3

class Rational(n: Int,d: Int){
  require(d!=0)
  private val g = gcd(n.abs, d.abs)
  val numerator: Int = n / g
  val denominator: Int = d / g

  //auxiliary constructor starts with this
  def this(n: Int) = this(n,1)
  override def toString = numerator.toString + "/" + denominator.toString
  def +(num2: Rational): Rational = {
    new Rational(numerator* num2.denominator + denominator* num2.numerator, denominator*num2.denominator)
  }
  def +(num2: Int): Rational = {
    new Rational(numerator + denominator* num2, denominator)
  }
  def -(num2: Rational): Rational = {
    new Rational(numerator* num2.denominator - denominator* num2.numerator, denominator*num2.denominator)
  }
  def *(num2: Rational): Rational = {
    new Rational(numerator* num2.numerator, denominator*num2.denominator)
  }
  def /(num2: Rational): Rational = {
    new Rational(numerator* num2.denominator, denominator*num2.numerator)
  }
  def lessThan(num2: Rational) = this.numerator*num2.denominator < this.denominator*num2.numerator
  def max(num2: Rational) = {
    if(this.lessThan(num2)) num2 else this
  }
  private def gcd(a: Int, b: Int): Int = {
    if(b==0) a else gcd(b, a%b)
  }
}

val x = new Rational(3,4)
print("x is "+x)
val y = new Rational(1,4)
print("y is "+y)
var z = x + y
println("sum is "+ z)
z = x - y
println("difference is "+ z)
z = x * y
println("product is "+ z)
z = x / y
println("quotient is "+ z)
z = x max y
println("max of x and y "+ z)
val p = new Rational(5)
println("Rational number 5 is "+p)
val q = new Rational(66,77)
println("66/77 in reduced form is "+ q)
z = x + 3
println("overloaded addition 3/4+3 is ",z)
