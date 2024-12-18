import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try(ServerSocket server = new ServerSocket(8080)){
            System.out.println("Server listening on port " + 8080);

            while(true) {
                try (Socket socket = server.accept()) {
                    System.out.println("Client connected");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
