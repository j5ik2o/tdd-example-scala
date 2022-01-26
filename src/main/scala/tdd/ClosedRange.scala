package tdd

final case class ClosedRange(lower: Int, upper: Int) {
  require(lower <= upper, lower + " is not before or equal to " + upper)

  def asString = s"[$lower,$upper]"

  def includes(value: Int): Boolean = lower <= value && value <= upper

  def intersects(other: ClosedRange): Boolean = lower <= other.lower && other.upper <= upper
}
