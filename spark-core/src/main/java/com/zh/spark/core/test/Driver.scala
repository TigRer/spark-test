package com.zh.spark.core.test

import java.io.{ObjectOutputStream, OutputStream}
import java.net.Socket

object Driver {

  def main(args: Array[String]): Unit = {

    var client1 = new Socket("localhost",9999)
    var client2 = new Socket("localhost",8888)

    val task = new Task()

    val out1: OutputStream = client1.getOutputStream
    val outObj1 = new ObjectOutputStream(out1)

    val subTask = new SubTask()
    subTask.datas = task.datas.take(2)
    subTask.logic = task.logic



    outObj1.writeObject(subTask)
    outObj1.flush()
    outObj1.close()
    client1.close()

    val out2: OutputStream = client2.getOutputStream
    val outObj2 = new ObjectOutputStream(out2)

    val subTask2 = new SubTask()
    subTask2.datas = task.datas.takeRight(2)
    subTask2.logic = task.logic



    outObj2.writeObject(subTask2)
    outObj2.flush()
    outObj2.close()
    client2.close()
    println("客户端数据发送完毕！")
  }

}
