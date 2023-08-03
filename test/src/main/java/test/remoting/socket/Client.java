package test.remoting.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author sunzy
 * @date 2023/8/2 10:41
 */
@Slf4j
public class Client {

    public Object send(Message message, String host, int port) {
        try(Socket socket = new Socket(host, port)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            // 通过输出流向服务端发送消息
            objectOutputStream.writeObject(message);

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            return objectInputStream.readObject();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Message message = new Message();
        message.setMsg("content from client");
        Client client = new Client();
        message = (Message) client.send(message, "127.0.0.1", 9999);
        log.info("client receive message: " + message.getMsg());

    }
}
