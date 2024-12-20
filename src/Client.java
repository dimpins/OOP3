import java.io.IOException;
import java.net.Socket;
import Front.AuthenticationWindow;

public class Client {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("127.0.0.1", 8080)) {   //пытаем подключиться к серверу
            AuthenticationWindow auth = new AuthenticationWindow();        //выводим первое окно
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
