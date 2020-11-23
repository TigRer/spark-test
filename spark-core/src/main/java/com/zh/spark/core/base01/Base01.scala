package com.zh.spark.core.base01

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/*
  RDD的创建方式：（4种）
  1）从内存种创建
    parallelize、makeRDD
    makeRDD的底层就是parallelize:
      def makeRDD[T: ClassTag](
      seq: Seq[T],
      numSlices: Int = defaultParallelism): RDD[T] = withScope {
      parallelize(seq, numSlices)
    }
  2）外部存储创建RDD（文件）
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("spark")
    val sparkContext = new SparkContext(sparkConf)
    val fileRDD: RDD[String] = sparkContext. textFile ("
    fileRDD.collect().foreach(println)
    sparkContext.stop()
* */

object Base01 {

  def main(args: Array[String]): Unit = {

    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("spark")

    val context: SparkContext = new SparkContext(conf)

    val rdd1: RDD[Int] = context.parallelize(List(1, 2, 3, 4))

    val rdd2: RDD[Int] = context.makeRDD(List(5, 6, 7, 8))

    rdd1.collect().foreach(println)
    rdd2.collect().foreach(println)

    context.stop()
  }

}
