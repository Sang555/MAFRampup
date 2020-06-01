import cats.effect.IO
import org.http4s.Message

import scala.collection.mutable.HashMap
import io.circe.Json

trait BookDao{

  def addBook(book: Book): IO[BookId]
  def getBook(id: BookId): IO[Option[BookWithId]]
  def getBooks(): IO[List[BookWithId]]
  def updateBook(id: BookId, book: Book): IO[Either[String, Book]]
}
object BookDao {

  class BookService extends BookDao {

    //storing in memory itself instead of database
    val storage = HashMap[BookId, Book]().empty

    override def addBook(book: Book): IO[BookId] = IO {
      val bookId = BookId()
      storage.put(bookId, book)
      bookId
    }


    override def getBook(id: BookId): IO[Option[BookWithId]] = IO {
      storage.get(id).map(book => BookWithId(id.value, book.bookTitle, book.bookAuthor))
    }

    override def getBooks(): IO[List[BookWithId]] = IO {
      storage.map { case (id, book) => BookWithId(id.value, book.bookTitle, book.bookAuthor) }.toList
    }

    override def updateBook(id: BookId, book: Book): IO[Either[String, Book]] = {
      for {
        //toRight converts Option to Either by assigning left in the parantehrsis adn result in the right
        bookOpt <- getBook(id)
        _ <- IO(bookOpt.toRight("Book } not found"))

        updatedBook = storage.put(id, book)
          .toRight(s"Book with ${id.value} not found")

      } yield updatedBook
    }

  }

}