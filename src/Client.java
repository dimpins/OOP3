import java.io.IOException;
import java.net.Socket;
import Front.AuthenticationWindow;
import Front.MainWindowClient;

public class Client {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("127.0.0.1", 8080)) {
//            JFrame frame = Front.authenticationWindow();
//            frame.setVisible(true);
//            Front.mainWindowClient(frame);
              AuthenticationWindow auth = new AuthenticationWindow();
           // MainWindowClient mainClient = new MainWindowClient();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
