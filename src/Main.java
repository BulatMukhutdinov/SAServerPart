import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Bulat on 30.01.2016.
 */
public class Main {
    private final static int PORT = 6666;

    public static void main(String args[]) {
        // Заглушка со средней потребляемостью в 100
        Dummy dummy = new Dummy(100d);
        Thread thread = new Thread(dummy);
        thread.start();
        // Сервер
        try {
            System.out.println(InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
           e.printStackTrace();
        }
        Server server = new Server();
        server.start(PORT);
    }

}
