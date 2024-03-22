package ca.uoit;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HTTPServer {

    private ServerSocket serverSocket = null;

    public HTTPServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void handleRequests() throws IOException {
        System.out.println("Listening for requests at port 8080: ");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            HTTPServerHandler handler = new HTTPServerHandler(clientSocket);
            Thread handlerThread = new Thread(handler);
            handlerThread.start();
        }
    }

    public static void main(String[] args) {
        int port = 8080;

        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        try {
            HTTPServer server = new HTTPServer(port);
            server.handleRequests();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
