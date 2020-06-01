// understanding traits
// 1. create a trait Iterator
// 2. implement different iterators like int Iterator to iterate till a limit form 0
// 3. implement char iterator to print A-Z

trait Iterator[T]{
  def hasNext: Boolean
  def next():T
}

class IntIterator(dest: Int) extends Iterator[Int] {
  private var current =0
  override def hasNext: Boolean = current < dest
  override def next(): Int = {
    if(hasNext){
      val t = current
      current += 1
      t
    } else 0
  }
}
class CharIterator(dest: Int) extends Iterator[Char] {
  private var current =0
  override def hasNext: Boolean = current < dest
  override def next(): Char = {
    if(hasNext) {
      val t = current
      current += 1
      t
    } :Char else 'A'
  }
}

val iterator = new IntIterator(5)
while (iterator.hasNext) {
  print(iterator.next())
}

val iterator2 = new CharIterator(5)
while (iterator2.hasNext) {
  print(iterator2.next())
}
