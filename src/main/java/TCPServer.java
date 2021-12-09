import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    final static int PORT_NUMBER = 35000;

    public static void main(String[] args) throws IOException {
        System.out.println("Server is listening at port : " + PORT_NUMBER);
        ServerSocket server = new ServerSocket(PORT_NUMBER);
        while (true) {
            Socket client = server.accept(); //Wait for client connection, if no connection is obtained, wait at this step
            new Thread(new ServerThread(client)).start(); //Open a thread for each client connection
        }
        //server.close();
    }
}