import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;
import java.util.TreeMap;

public class H2 extends TimerTask {
    public static final String USERNAME = "root";
    public static final String PASSWORD = "root";
    public static final String DB_NAME = System.getProperty("user.dir") + "/Consumption";


    @Override
    public void run() {
        try {
            Class.forName("org.h2.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:h2:" + DB_NAME,
                    USERNAME, PASSWORD);
            Statement statement;
            statement = conn.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS consumption (id serial PRIMARY KEY, _date TIMESTAMP, consumption_percentage DOUBLE)");
            statement.execute("INSERT INTO consumption(_date, consumption_percentage) VALUES(CURRENT_TIMESTAMP(), " + Dummy.consumptionPercentage + ")");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> getValues() {
        Map<String, String> values = new TreeMap<String, String>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:h2:" + DB_NAME,
                    USERNAME, PASSWORD);
            Statement statement;
            statement = conn.createStatement();
            ResultSet result;
            result = statement.executeQuery("SELECT * FROM consumption");
            while (result.next()) {
                values.put(result.getString("_date"), result.getString("consumption_percentage"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return values;
    }
}
