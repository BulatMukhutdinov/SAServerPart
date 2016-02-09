import java.util.Timer;

public class Main {
    public final static int PORT = 6666;
    public static final int TIME =  60*60*1000; // write to db every 60 min

    public static void main(String args[]) {
        Dummy dummy = new Dummy(100d);
        H2 h2 = new H2();
        Timer timer = new Timer();
        timer.schedule(dummy, 0, 1000);
        timer.schedule(h2, 0, TIME);
        Server server = new Server(h2);
        server.start(PORT);
    }

}
