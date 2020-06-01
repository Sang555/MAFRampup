//Playing around Basic scala concepts like for, forEach and collections


import scala.collection.mutable
import scala.collection.mutable
//for and foreach
var i = 0
while(i< args.length) {
  println(args(i))
  i+=1
}
args.foreach(arg => println(arg))

for(arg <- args){
  println(arg)
}


//array
val greetingsString : Array[String] = new Array[String](3)
greetingsString(0) = "This"
greetingsString(1) = "is"
greetingsString(2) = "sang"

for(i <- 0 to 2){
  print(greetingsString(i))
}

//lists

val l1 = List(1,2)
val l2 = List(3,4)
val l3 = l1 ::: l2
println("list3" + l3)
val l4 = 5 :: 6 :: l3
println("list4" + l4)
println(l4.count(a => a>2))
println(l4.drop(2))
println(l4.dropRight(2))
println(l4.exists(a => a == 2))
println(l4.foreach(print))
//returns a new list
println(l4.map(s => s +1))
println(l4.reverse)
print(l4.sortWith((a,b) => a<b))


//tuples

val pair = (1,"sang")
println(pair._1)

//immutable set

var s1= Set("a", "b")
s1 += "c"
println(s1.contains("c1"))

val s2 = mutable.Set("1", "2")
s2 += "c"
println(s2.contains("c1"))

  //map

val m1 = mutable.Map[Int, String]()
m1 += (1 -> "hi")
m1 += (2 -> "how")
print(m1)


//tuple

val t1= (1,"xxx", Console)
val num = t1._1
println(num)
