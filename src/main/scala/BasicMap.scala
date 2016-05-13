import org.apache.spark._
import scala.util.Random

/**
  * Find even numbers of a random generated list
  */
object BasicMap extends App with SparkConfigSupport {
  // a local spark or a spark server
  val conf = new SparkConf()
    .setMaster(master)
    .setAppName("BasicMapOp")

  // Let's generate a random Integer
  val list = Seq.fill(100)(Random.nextInt(1000))

  // create a spark context

  val sc = new SparkContext(conf)

  // generate a RDD - Resillent Distirbuted Dataset
  val inputRdd = sc.parallelize(list)

  // Let's calculate , collect
  val result = inputRdd
    .filter(_ % 2 == 0)
    .collect()
    .sorted
    .mkString(",")

  println(result)
}
