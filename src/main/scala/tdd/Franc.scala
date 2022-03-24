package tdd

case class Franc(amount: Int) extends Money {
  def times(mul: Int): Franc = {
    copy(amount = amount * mul)
  }
}
