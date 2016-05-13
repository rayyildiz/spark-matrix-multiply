/**
  * Generate some random matrix and multiply with standard methods
  */
object GenerateRandomMatrix extends App {
  import Utilities._

  val max = 10000000

  val m1 = time(s"dense matrix[5,${max}]")(randomDenseMatrix(5, max))
  val m2 = time(s"dense matrix[${max},7]")(randomDenseMatrix(max, 7))

  val result = time("M1 x M2")(m1.multiply(m2))

  println(result)
}
