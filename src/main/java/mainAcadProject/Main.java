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

    public static void main(String args[]) throws SQLException {
        Flyway flyway=new Flyway();
        flyway.setDataSource(URL, USERNAME, PASSWORD);
        flyway.migrate();

        /*
        DBProcessor db=new DBProcessor();
        Connection con=db.getConnection(URL,USERNAME,PASSWORD);
        String query="select * from main_academy.course";
        String insert ="insert into main_academy.course(course_name) values (?)";


       /* Statement statement=con.createStatement();
        ResultSet resSet=statement.executeQuery(query);

        while (resSet.next()){
            int course_id;
            String course_name;
            course_id=resSet.getInt("course_id");
            course_name=resSet.getString("course_name");
            Сourse course= new Сourse(course_id,course_name);
            System.out.println(course.toString());
        }
        PreparedStatement preparedStatement1=con.prepareStatement(insert);
        preparedStatement1.setString(1,"JKUYHFFFD");
        preparedStatement1.execute();

        PreparedStatement preparedStatement=con.prepareStatement(query);
        ResultSet resSet=preparedStatement.executeQuery();

        while (resSet.next()){
            System.out.println(resSet.getInt("course_id")+" "+ resSet.getString("course_name"));
        }


        preparedStatement.close();
        con.close();*/



    }
}
