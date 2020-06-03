//this is the algebra..it only tells anout the contract of the datatype and functions
import cats.effect.IO
import cats.data.EitherT
//abstraction over composition
import scala.collection.mutable.HashMap



trait AccountDAO[Account, Long, Balance]{
  def open(no: String, name: String, accountType: AccountType): IO[Either[String, Account]]
  def getMethod(no: String): IO[Either[String,Account]]
  //  def close(account: Account): IO[Option[Account]]
  def debit(accountNo: String, amount: Long): IO[Either[String, Option[Account]]]
  def credit(accountNo: String, amount: Long): IO[Either[String, Option[Account]]]
  //  def balance(account: Account): IO[Option[Balance]]
}


object AccountDAO {

  class AccountService1 extends AccountDAO[Account, Long, Balance] {
    val storage = HashMap[String, Account]().empty

    override def open(no: String, name: String, accountType: AccountType): IO[Either[String, Account]] = IO {
      if (no == "0" || name == null) Left("Invalid account details")
      else {
        val acc = Account(no, name, Balance(0) , accountType )
        storage.put(no, acc)
        Right(acc)
      }
    }

    override def getMethod(no: String): IO[Either[String,Account]] = IO {
      storage.get(no) match {
        case None => Left("Account doesnt exits")
        case Some(value) => Right(value)
      }
      }

//    override def close(account: Account): IO[Option[Account]] = ???
//
//    override def debit(account: Account, amount: Long): IO[Option[Account]] = ???
//
    def copyAccount(acc: Account, amount: Long) = {
        val acc2 = acc.copy(balance = Balance(amount))
        storage.put(acc.no, acc2)
}

    def creditBalance(accOpt: Either[String, Account], amount: Long) = IO{
      accOpt match {
        case Left(ex) => Left(ex)
        case Right(account) => {
          Right(copyAccount(account, account.balance.amount+amount ))
        }
      }
    }
    def debitBalance(accOpt: Either[String, Account], amount: Long) = IO{
      accOpt match {
        case Left(ex) => Left(ex)
        case Right(account) => {
          if((account.balance.amount-amount) < 0)
            Left("Insufficient balance")
          else
            Right(copyAccount(account, account.balance.amount-amount ))
        }
      }
    }


    override def credit(accountno: String, amount: Long): IO[Either[String, Option[Account]]] = {
  for {
    //toRight converts Option to Either by assigning left in the parantehrsis adn result in the right
    accOpt <-getMethod(accountno)
    balanceAmount <- creditBalance(accOpt, amount)
  } yield balanceAmount
}

  override def debit(accountno: String, amount: Long): IO[Either[String, Option[Account]]] = {
    for {
      //toRight converts Option to Either by assigning left in the parantehrsis adn result in the right
      accOpt <-getMethod(accountno)
      balanceAmount <- debitBalance(accOpt, amount)
    } yield balanceAmount
//    for {
//      //toRight converts Option to Either by assigning left in the parantehrsis adn result in the right
//      accOpt <- getMethod(accountno)
//      updatedAccount = accOpt.flatMap{
//        acc =>
//          try {
//            Right(copyAccount(acc, acc.balance.amount-amount))
//          }
//          catch {
//            case ex: IllegalArgumentException => Left("insufficient balance")
//          }
//      }
//    } yield updatedAccount
}
    def transfer(accountno1: String, accountno2: String, amount: Long): IO[Either[String,Option[Account]]] = {
      for {
        //toRight converts Option to Either by assigning left in the parantehrsis adn result in the right

        _ <- credit(accountno1, amount)
        accOpt2 <- debit(accountno2, amount)
      } yield accOpt2
    }
//
//    override def balance(account: Account): IO[Option[Balance]] = ???
  }

}