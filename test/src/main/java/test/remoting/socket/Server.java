package test.remoting.socket;

import lombok.extern.slf4j.Slf4j;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author sunzy
 * @date 2023/8/2 10:41
 */
@Slf4j
public class Server {

    public void start(int port) {
        // 创建ServerSocket对象并绑定端口
        try (ServerSocket server = new ServerSocket(port)) {
            Socket socket;
            // 通过accept()方法监听客户端请求
            while((socket = server.accept()) != null) {
                log.info("client connect!");
                try (ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream())) {
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                    // 通过输入流读取客户端发送的消息
                    Message message = (Message)objectInputStream.readObject();
                    log.info("server receive message: " + message.getMsg());
                    message.setMsg("Hello client!!!");
                    objectOutputStream.writeObject(message);
                    objectOutputStream.flush();
                }
            }


        } catch (IOException | ClassNotFoundException e) {
            log.error("Exception!!!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start(9999);
    }
}
