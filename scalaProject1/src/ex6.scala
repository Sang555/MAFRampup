// understanding partially applied functions, closures, singleton objects
// 1.making add a partially applied function
// 2.creating and using closures
//3. writing method to find existence of odd number
//4. understand working of singleton objects

//PAF
def sum(a:Int, b:Int, c:Int) = a+b+c
val f2=sum(1,_,3)
println(f2(2))

//closure
def addMore(more: Int) = (x: Int) => x + more
val closure1 = addMore(1)
val closure2 = addMore(10)
println(closure1(0))
println(closure2(0))

//odd number existence
def oddNumbers(nums: List[Int]) = nums.exists(_ % 2 == 1)
val l1 = List(1,2,3,4,5)

print(oddNumbers(l1))

object Greeting {
  def english = "Hi"
  def espanol = "Hola"
}

val x = Greeting
val y = x

println(x eq y)