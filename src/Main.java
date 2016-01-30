import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Bulat on 30.01.2016.
 */
public class Main {
    private final static int PORT = 6666;

    public static void main(String args[]) {
        // �������� �� ������� ��������������� � 100
        Dummy dummy = new Dummy(100d);
        Thread thread = new Thread(dummy);
        thread.start();
        // ������
        try {
            System.out.println(InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
           e.printStackTrace();
        }
        Server server = new Server();
        server.start(PORT);
    }

}
