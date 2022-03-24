package tdd

case class Dollar(amount: Int) extends Money {

  def times(mul: Int): Dollar = {
    copy(amount = amount * mul)
  }

}
