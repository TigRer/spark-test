package com.zh.spark.core.test

import java.io.{InputStream, ObjectInputStream}
import java.net.{ServerSocket, Socket}

object Executor2 {

  def main(args: Array[String]): Unit = {


    var server = new ServerSocket(8888)
    println("服务器启动，等待客户端连接。。。")

    val client: Socket = server.accept()
    val in: InputStream = client.getInputStream
    val inObj = new ObjectInputStream(in)

    val task: SubTask = inObj.readObject().asInstanceOf[SubTask]
    println("接收到客户端[8888]发来的消息：" + task.compute)

    inObj.close()
    client.close()
    server.close()
  }

}
