// understanding case classes and pattern matching
// 1. defines some class cases
// 2. call required functionality based on pattern matching

abstract class Notification
case class Email(sender: String, title: String) extends Notification
case class SMS(caller: String, message: String) extends Notification

def showNotification(notification: Notification): String = {
  notification match{
    case Email(sender, title) => {
      sender+" "+title
    }
    case SMS(number, message) => {
      number+" "+message
    }
  }
}

val sms1= SMS("778963","hello")
println(showNotification(sms1))