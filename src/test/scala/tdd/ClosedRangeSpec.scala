package tdd

import org.scalatest.freespec.AnyFreeSpec

/**
 * お題: https://gist.github.com/twada/75fb219c8cc180e9de166d8a58e877b0
 * 動画： https://dxd2021.cto-a.org/program/time-table/a-1
 */
class ClosedRangeSpec extends AnyFreeSpec {

  "下限と上限を保持できる" - {
    "[1,1]" in {
      val lower = 1
      val upper = 1
      val sut = ClosedRange(lower, upper)
      assert(lower == sut.lower)
      assert(upper == sut.upper)
    }
    "[1,2]" in {
      val lower = 1
      val upper = 2
      val sut = ClosedRange(lower, upper)
      assert(lower == sut.lower)
      assert(upper == sut.upper)
    }
  }
  "上限より下限が大きい閉区間は作れない" - {
    "[2,1]" in {
      assertThrows[IllegalArgumentException](
        ClosedRange(2, 1)
      )
    }
  }
  "閉区間は文字列表現を返す" - {
    "[1,2]" in {
      assert(ClosedRange(1, 2).asString == "[1,2]")
    }
  }
  "別の整数閉区間と等価かどうか判定できる" - {
    "範囲が完全一致の場合" - {
      "[1,2]は[1,2]と等価である" in {
        assert(ClosedRange(1, 2) == ClosedRange(1, 2))
      }
    }
    "範囲に重複がない場合" - {
      "[3,4]と[5,6]は等価ではない" in {
        assert(ClosedRange(3, 4) != ClosedRange(5, 6))
      }
      "[1,2]と[3,4]は等価ではない" in {
        assert(ClosedRange(1, 2) != ClosedRange(3, 4))
      }
    }
    "範囲の一部に重複がある場合" - {
      "[0,1]と[1,2]は等価ではない" in {
        assert(ClosedRange(0, 1) != ClosedRange(1, 2))
      }
      "[1,2]と[2,3]は等価ではない" in {
        assert(ClosedRange(1, 2) != ClosedRange(2, 3))
      }
    }
  }
  "指定した整数を含むかどうかを判定できる" - {
    "指定した値が範囲内の場合" - {
      "[1,3]は下限の1を含む" in {
        assert(ClosedRange(1, 3).includes(1))
      }
      "[1,3]は中間の2を含む" in {
        assert(ClosedRange(1, 3).includes(2))
      }
      "[1,3]は上限の2を含む" in {
        assert(ClosedRange(1, 3).includes(3))
      }
    }
    "指定した値が範囲外の場合" - {
      "[1,2]は下限を下回る0を含まない" in {
        assert(!ClosedRange(1, 2).includes(0))
      }
      "[1,2]は上限を上回る3を含まない" in {
        assert(!ClosedRange(1, 2).includes(3))
      }
    }
  }
  "別の整数閉区間が完全に含まれるかどうか判定できる" - {
    "対象の区間が範囲内の場合" - {
      "[1,3]は[1,1]を含む" in {
        assert(ClosedRange(1, 3).intersects(ClosedRange(1, 1)))
      }
      "[1,3]は[1,2]を含む" in {
        assert(ClosedRange(1, 3).intersects(ClosedRange(1, 2)))
      }
    }
    "対象の区間が範囲外の場合" - {
      "[2,3]は[0,1]を含まない" in {
        assert(!ClosedRange(2, 3).intersects(ClosedRange(0, 1)))
      }
      "[2,3]は[4,5]を含まない" in {
        assert(!ClosedRange(2, 3).intersects(ClosedRange(4, 5)))
      }
    }
    "対象の区間の一部が重複する場合" - {
      "[2,3]は[0,1]を含まない" in {
        assert(!ClosedRange(2, 3).intersects(ClosedRange(0, 2)))
      }
      "[2,3]は[3,4]を含まない" in {
        assert(!ClosedRange(2, 3).intersects(ClosedRange(3, 4)))
      }
    }
  }

}
