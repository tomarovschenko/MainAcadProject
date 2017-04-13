package mainAcadProject.config;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by ) on 30.03.2017.
 */
public class DBProcessor{

    private static Connection connection;
    private static String url;
    private static String user;
    private static String pass;
    static {
        Properties properties = new Properties();
        try(InputStream stream = DBProcessor.class.getClassLoader().getResourceAsStream("db.properties")){
            properties.load(stream);
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            pass = properties.getProperty("pass");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //TODO close conn

    public static Connection getConnection() throws SQLException {
        if (connection!=null && !connection.isClosed()) {
            return connection;
        }
        connection=DriverManager.getConnection(url, user, pass);
        return connection;
    }

}
