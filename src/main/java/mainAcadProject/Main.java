package mainAcadProject;

import org.flywaydb.core.Flyway;

import java.sql.*;


/**
 * Created by ) on 23.03.2017.
 */
public class Main {
    private static final String USERNAME="root";
    private static final String PASSWORD="969899";
    private static final String URL="jdbc:mysql://localhost:3306/main_acad?useSSL=false&createDatabaseIfNotExist=true";

    public static void main(String args[]) {
/*        Flyway flyway=new Flyway();
        flyway.setDataSource(URL, USERNAME, PASSWORD);
        flyway.clean();
        flyway.migrate();
*/
GUIMain.runUI(args);

    }
}
