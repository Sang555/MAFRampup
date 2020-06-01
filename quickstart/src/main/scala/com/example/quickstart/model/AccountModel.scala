case class Balance(amount: Long= 0){
  if(amount<0)
    throw new IllegalArgumentException("Not much balance")
}
case class Account(no: String, name: String, balance: Balance = Balance(0))