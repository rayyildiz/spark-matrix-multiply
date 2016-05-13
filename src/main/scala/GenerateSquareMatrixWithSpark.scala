import org.apache.spark._
import org.apache.spark.mllib.linalg._
import org.apache.spark.mllib.linalg.distributed._
import org.apache.spark.rdd.RDD

/**
  * Generate a random square matrix and multiply them with Spark support
  */
object GenerateSquareMatrixWithSpark extends App with SparkConfigSupport {
  import Utilities._

  val max = 2048
  val m1  = time(s"generate m1[${max},${max}")(randomDenseMatrix(max, max))
  val m2  = time(s"generate m2[${max},${max}")(randomDenseMatrix(max, max))

  val conf = new SparkConf()
    .setMaster(master)
    .setAppName("SquareMatrixMultiply")

  val sc = new SparkContext(conf)

  val dv1 = time("densityVector dv1")(toDensityVector(m1))
  //val dv2 = time("densityVector dv2")(toDensityVector(m2))

  val rdd1: RDD[Vector] = sc.parallelize(dv1)
  //val rdd2:RDD[Vector] = sc.parallelize(dv2)

  val mat1 = time("mat1")(new RowMatrix(rdd1, max, max))
  //val mat2 = time("mat2")(new RowMatrix(rdd2,max,max))

  val resultRDD = time("mat1 x m2")(mat1.multiply(m2))

  println(resultRDD.rows.take(10))
}
