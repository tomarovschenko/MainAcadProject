package mainAcadProject;

import mainAcadProject.config.DBProcessor;
import mainAcadProject.entity.CourseEntity;
import org.apache.commons.lang.RandomStringUtils;
import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by ) on 23.03.2017.
 */
public class Main {
    private static final String USERNAME="root";
    private static final String PASSWORD="969899";
    private static final String URL="jdbc:mysql://localhost:3306/main_acad?useSSL=false&createDatabaseIfNotExist=true";

    public static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String args[]) {

   /* Flyway flyway=new Flyway();
        flyway.setDataSource(URL, USERNAME, PASSWORD);
        flyway.clean();
        flyway.migrate();*/

        LOG.info("Hello");



        GUIMain.runUI(args);

    }
}
