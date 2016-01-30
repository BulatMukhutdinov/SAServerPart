import java.io.*;
import java.net.*;
import java.util.*;

public class SocketThread extends Thread {
    protected Socket socket;

    public SocketThread(Socket clientSocket) {
        this.socket = clientSocket;
    }

    public void run() {
        try {
            System.out.println("Got a client " + getName());
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            DataInputStream in = new DataInputStream(inputStream);
            while (true) {
                in.readUTF();
                DataOutputStream out = new DataOutputStream(outputStream);
                out.writeDouble(Dummy.consumptionPercentage);
                out.flush();
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