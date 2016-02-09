import java.io.*;
import java.net.*;
import java.util.*;

public class SocketThread extends Thread {
    protected Socket socket;
    private H2 h2;

    public SocketThread(Socket clientSocket, H2 h2) {
        this.socket = clientSocket;
        this.h2 = h2;
    }

    public void run() {
        try {
            System.out.println("Got a client " + getName());
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            ObjectInputStream in = new ObjectInputStream(inputStream);
            while (true) {
                ObjectOutputStream mapOutputStream = new ObjectOutputStream(outputStream);
                mapOutputStream.writeObject(h2.getValues());
                mapOutputStream.flush();
            }
        } catch (SocketException x) {
            System.out.println("Client " + getName() + " closed");
            System.out.println();
        } catch (EOFException x) {
            System.out.println("Waiting for the next record...");
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

}