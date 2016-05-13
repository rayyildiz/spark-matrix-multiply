/**
  * Created by rayyildiz on 18.05.17.
  */
trait SparkConfigSupport {
  protected def args: Array[String]

  def master = args.length match {
    case x: Int if x > 0 => args(0)
    case _               => "local[*]"
  }
}
