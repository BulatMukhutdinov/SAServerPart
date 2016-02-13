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
            ObjectOutputStream out = new ObjectOutputStream(outputStream);
            out.flush();
            ObjectInputStream in = new ObjectInputStream(inputStream);
            String request;
            while (true) {
                request = in.readUTF();
                if (request.equalsIgnoreCase("graph")) {
                    out.writeObject(h2.getValues());
                    out.flush();
                } else if (request.equalsIgnoreCase("consumption")) {
                    out.writeDouble(Dummy.consumptionPercentage);
                    out.flush();
                }
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