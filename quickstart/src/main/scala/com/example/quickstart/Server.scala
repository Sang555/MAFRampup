//import BookDao.BookService
//import cats.effect.{ExitCode, IO, IOApp}
//import org.http4s.server.Router
//import org.http4s.implicits._
//import org.http4s.server.blaze._
//import cats.implicits._
//
//
//import scala.concurrent.ExecutionContext.Implicits.global
//
//object Main extends IOApp {
//
//  private val bookRepo: BookDao = new BookService
//
//  //mount the services under the / path
//  val httpRoutes = Router[IO](
//    "/" -> BookRoutes.routes(bookRepo)
//  ).orNotFound
//
//  override def run(args: List[String]): IO[ExitCode] = {
//
//    //build a blaze server
//    BlazeServerBuilder[IO](global)
//      .bindHttp(9000, "0.0.0.0")
//      .withHttpApp(httpRoutes)
//      .serve
//      .compile
//      .drain
//      .as(ExitCode.Success)
//  }
//
//}