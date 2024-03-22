package ca.uoit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HTTPClient {

    public static void main(String[] args) {
        Socket socket;
        BufferedReader in;
        PrintWriter out;

        if (args.length <= 1) {
            System.out.println("Usage: java HTTPClient <host> <uri> [<port>=80]");
            System.out.println("Example usage: java HTTPClient localhost index.html 8080");
            System.exit(0);
        }

        String hostname = args[0];
        String uri = args[1];
        int port = 80;
        if (args.length > 2) {
            port = Integer.parseInt(args[2]);
        }

        try {
            socket = new Socket(hostname, port);

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

            System.out.println("Request: ");
            System.out.print("GET " + uri + " HTTP/1.1\r\n");
            System.out.print("Host: " + hostname + "\r\n\r\n");
            out.print("GET " + uri + " HTTP/1.1\r\n");
            out.print("Host: " + hostname + "\r\n\r\n");
            out.flush();


            System.out.println("Response: ");
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }


            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
