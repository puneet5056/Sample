package com.bdt

import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkConf


object SparkDemo {
  def main(args: Array[String]): Unit = {
    
  //updATE codeing
  val conf=new SparkConf();
  val spark=SparkSession
  .builder()
  .appName("Sample Spark")
  .config(conf)
  .config("spark.app.name","Spark Demo ")
  .master("local[*]")
    .getOrCreate()
  
  import spark.implicits._

    // Create a tiny Dataset of integers
    val s = Seq(10, 11, 12, 13, 14, 15)
    val ds = s.toDS()

    println("*** only one column, and it always has the same name")
    ds.columns.foreach(println(_))

    println("*** column types")
    ds.dtypes.foreach(println(_))

    println("*** schema as if it was a DataFrame")
    ds.printSchema()

    println("*** values > 12")
    ds.where($"value" > 12).show()

    // This seems to be the best way to get a range that's actually a Seq and
    // thus easy to convert to a Dataset, rather than a Range, which isn't.
    val s2 = Seq.range(1, 100)

    println("*** size of the range")
    println(s2.size)

    val tuples = Seq((1, "one", "un"), (2, "two", "deux"), (3, "three", "trois"))
    val tupleDS = tuples.toDS()

    println("*** Tuple Dataset types")
    tupleDS.dtypes.foreach(println(_))

    // the tuple columns have unfriendly names, but you can use them to query
    println("*** filter by one column and fetch another")
    tupleDS.where($"_1" > 2).select($"_2", $"_3").show()
  }
}
