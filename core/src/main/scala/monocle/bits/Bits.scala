package monocle.bits

trait Bits[A] {

  def bitSize: Int

  def bitwiseAnd(a1: A, a2: A): A
  def bitwiseOr(a1: A, a2: A): A
  def bitwiseXor(a1: A, a2: A): A

  def shiftL(a: A, n: Int): A
  def shiftR(a: A, n: Int): A

  def singleBit(n: Int): A

  def updateBit(a: A, n: Int, newValue: Boolean): A = if(newValue) setBit(a, n) else clearBit(a, n)

  def setBit(a: A, n: Int): A   = bitwiseOr(a, singleBit(n))
  def clearBit(a: A, n: Int): A = bitwiseAnd(a, negate(singleBit(n)))

  def testBit(a: A, n: Int): Boolean

  def negate(a: A): A
  def signed(a: A): Boolean

}

object Bits {

  def apply[A](implicit ev: Bits[A]): Bits[A] = ev

  implicit val intInstance : Bits[Int] = new Bits[Int] {

    def bitwiseOr(a1: Int, a2: Int): Int = a1 | a2

    def singleBit(n: Int): Int = 1 << n

    val bitSize: Int = 32

    def bitwiseAnd(a1: Int, a2: Int): Int = a1 & a2

    def shiftL(a: Int, n: Int): Int = a << n

    def bitwiseXor(a1: Int, a2: Int): Int = a1 ^ a2

    def testBit(a: Int, n: Int): Boolean = bitwiseAnd(a, singleBit(n)) != 0

    def shiftR(a: Int, n: Int): Int = a >> n

    def signed(a: Int): Boolean = a.signum > 0

    def negate(a: Int): Int = ~ a
  }

}
