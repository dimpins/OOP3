import java.io.IOException;
import java.net.Socket;
import Front.AuthenticationWindow;
import Front.MainWindowClient;
import Front.MainWindowManager;
import Front.RegistationWindow;
import Front.ListAccountsWindow;

public class Client {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("127.0.0.1", 8080)) {
//            JFrame frame = Front.authenticationWindow();
//            frame.setVisible(true);
//            Front.mainWindowClient(frame);
            RegistationWindow reg = new RegistationWindow();
            AuthenticationWindow auth = new AuthenticationWindow();
            MainWindowClient mainClient = new MainWindowClient();
            MainWindowManager manager = new MainWindowManager();
            ListAccountsWindow list = new ListAccountsWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
