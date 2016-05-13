import java.io.PrintStream
import java.util.Random

import org.apache.spark.mllib.linalg.{DenseMatrix, DenseVector}

/**
  * Some utility classes
  */
object Utilities {

  def logger: PrintStream = System.out

  def time[T](descr: String)(f: => T): T = {
    val start = System.nanoTime
    val r     = f
    val end   = System.nanoTime
    val time  = (end - start) / 1e6
    logger.println(descr + ": = " + time + "ms")
    r
  }

  def randomDenseMatrix(rows: Int, cols: Int): DenseMatrix = {
    val rnd = new Random(100)

    DenseMatrix.rand(rows, cols, rnd)
  }

  def toDensityVector(d: DenseMatrix) = {
    val columns = d.toArray.grouped(d.numRows)
    val rows    = columns.toSeq.transpose // Skip this if you want a column-major RDD.
    val vectors = rows.map(row => new DenseVector(row.toArray))

    vectors
  }

}
