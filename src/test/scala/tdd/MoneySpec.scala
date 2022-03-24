package tdd

import org.scalatest.funspec.AnyFunSpec

class MoneySpec extends AnyFunSpec {

  describe("Money") {
    describe("お金オブジェクトどうしで等価判定ができる") {
      it("$5 == $5") {
        assert(Dollar(5) == Dollar(5))
      }
      it("$5 != $6") {
        assert(Dollar(5) != Dollar(6))
      }
      it("5 CHF == 5 CHF") {
        assert(Franc(5) == Franc(5))
      }
      it("5 CHF != 6 CHF") {
        assert(Franc(5) != Franc(6))
      }
      it("$5 != 5 CHF") {
        assert(Dollar(5) != Franc(5))
      }
    }
    describe("お金を個数で掛けることができる") {
      it("$5 * 2 = 10") {
        val five     = Dollar(5)
        val product1 = five.times(2)
        assert(product1.amount == 10)
      }
      it("$5 * 3 = 15") {
        val five     = Dollar(5)
        val product2 = five.times(3)
        assert(product2.amount == 15)
      }
      it("5 CHF * 2 = 10 CHF") {
        val five = Franc(5)
        assert(five.times(2) == Franc(10))
      }
    }
  }

}
