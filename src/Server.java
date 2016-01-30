import java.net.*;
import java.io.*;
import java.sql.SQLException;
import java.util.List;

public class Server {

    public void start(int port) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(port); // ������� ����� ������� � ����������� ��� � �������������� ����� (����� ���� ����� ����� �� 1025 �� 65535)
        } catch (IOException e) {
            e.printStackTrace();

        }
        System.out.println("Waiting for a client...");
        while (true) {
            try {
                socket = serverSocket.accept();// ���������� ������ ����� ����������� � ������� ��������� ����� ���-�� �������� � ��������
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
            // new thread for a client
            new SocketThread(socket).start();
        }
    }
}