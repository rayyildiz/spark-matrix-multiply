/**
  * Generate a square matrix and multiply them with standard method ( without spark)
  */
object GenerateSquareMatrixAndMultiply extends App {
  import Utilities._

  val max = 10000

  val m1 = time(s"generate m1[${max},${max}]")(randomDenseMatrix(max, max))
  val m2 = time(s"generate m2[${max},${max}]")(randomDenseMatrix(max, max))

  val result = time("M1 x M2")(m1.multiply(m2))

  println(result)
}
