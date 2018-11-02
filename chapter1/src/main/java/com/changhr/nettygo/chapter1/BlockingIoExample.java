package com.changhr.nettygo.chapter1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 阻塞I/O示例
 *
 * @author changhr
 * @create 2018-10-09 19:10
 */
public class BlockingIoExample {

    public void serve(int portNumber) throws IOException {
        // 创建一个新的 ServerSocket，用以监听指定端口上的连接请求
        ServerSocket serverSocket = new ServerSocket(portNumber);

        // 对 accept() 方法的调用将被阻塞，知道一个连接建立
        Socket clientSocket = serverSocket.accept();

        // 这些流对象都派生于该套接字的流对象
        BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));

        PrintWriter out = new PrintWriter(
                clientSocket.getOutputStream(), true);

        String request, response;

        // 处理循环开始
        while ((request = in.readLine()) != null) {

            // 如果客户端发送了Done，则退出处理循环
            if("Done".equals(request)){
                break;
            }

            // 请求被传递给服务器的处理方法
            response = processRequest(request);

            // 服务器的响应被发送给了客户端
            out.println(response);

            // 继续执行处理循环
        }
    }

    private String processRequest(String request){
        return "Processed";
    }

    public static void main(String[] args) throws IOException {
        BlockingIoExample blockingIoExample = new BlockingIoExample();
        blockingIoExample.serve(9090);
    }
}
