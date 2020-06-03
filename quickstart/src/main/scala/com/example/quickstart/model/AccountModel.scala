import io.circe.Decoder.Result
import io.circe.{Decoder, Encoder, HCursor, Json}
case class Balance(amount: Long= 0)
//Trait
sealed trait AccountType
case object Savings extends AccountType
case object Current extends AccountType

//like normal nested but the body the AccountType will  be empty
case class Account(no: String, name: String, balance: Balance = Balance(0), accountType: AccountType)

object AccountType{

  implicit val decodeAccountType: Decoder[AccountType] = Decoder[String].emap {
    case "savings" => Right(Savings)
    case "current"            => Right(Current)
    case other            => Left(s"Invalid mode: $other")
  }

  implicit val encodeAccountTYpe: Encoder[AccountType] = Encoder[String].contramap {
    case Savings => "savings"
    case Current           => "current"
  }

}

object Balance{

  implicit val encoderBalance: Encoder[Balance] = new Encoder[Balance]{
    override def apply(a: Balance): Json = Json.obj(
      ("amount", Json.fromLong(a.amount))
    )
  }
  implicit val decoderBalance: Decoder[Balance] = new Decoder[Balance] {
    override def apply(c: HCursor): Result[Balance] =
      for{
        amount <- c.downField("amount").as[Long]
      } yield{
        new Balance(amount)
      }
  }

}
object Account {

  implicit val encoderAccount: Encoder[Account] = new Encoder[Account]{
    override def apply(a: Account): Json = Json.obj(
      ("no", Json.fromString(a.no)),
        ("name", Json.fromString(a.name)),
      ("balance", Json.fromLong(a.balance.amount)),
      ("accountType", Json.fromString(a.accountType.toString))
    )
  }
  implicit val decoderAccount: Decoder[Account] = new Decoder[Account] {
    override def apply(c: HCursor): Result[Account] =
      for{
        no <- c.downField("no").as[String]
        name <- c.downField("name").as[String]
        balance <- c.downField("balance").as[Balance]
        accountType <- c.downField("accountType").as[AccountType]
      } yield{
        new Account(no, name, balance, accountType )
      }
  }
  //  implicit val encodeType: Encoder[AccountType] = Encoder.instance {
//    case savings @ Savings => Json.fromString(Savings.toString)
//    case current @ Current => Json.fromString(Current.toString)
//  }
//
//  implicit val decodeType: Decoder[AccountType] =
//    List[Decoder[AccountType]](
//      Decoder[Savings].widen,
//      Decoder[Current].widen
//    ).reduceLeft(_ or _)
//  implicit  val encoderAccount: Encoder[Account] = deriveEncoder[Account]
//  implicit val decoderAccount: Decoder[Account] = deriveDecoder[Account]
}