package mainAcadProject.dao;


import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import mainAcadProject.config.DBProcessor;
import mainAcadProject.entity.CourseEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * Created by ) on 12.04.2017.
 */
public class CourseDao {

    private CourseEntity course;


    static String query="select * from main_acad.course";
    static String update="update course set course_name=?, total_hours=?, remote=? where id=?";
    static String insert ="insert into course(course_name, total_hours, remote) values (?,?,?)";

    public CourseDao(CourseEntity course) throws SQLException {
        this.course = course;
    }

    public static List<CourseEntity> unload() throws SQLException {
        List <CourseEntity> courses = new ArrayList <> ();
        ResultSet resSet=DBProcessor.getConnection().createStatement().executeQuery(query);
        while (resSet.next()){
            CourseEntity course = new CourseEntity();
            course.setId(resSet.getInt("id"));
            course.setName(resSet.getString("course_name"));
            course.setTotal_hours(resSet.getDouble("total_hours"));
            course.setRemote(resSet.getBoolean("remote"));
            if (!course.isRemote()) {
                courses.add(course);
            }
        }
        return courses;
    }

    public static void update(CourseEntity course){
        try {
            PreparedStatement preparedStat;
            //если договор не новый
            if ( course.getId() > 0 ) {
                preparedStat = DBProcessor.getConnection().prepareStatement(update);
                preparedStat.setInt( 4, course.getId() );
            }
            else {
                preparedStat = DBProcessor.getConnection().prepareStatement(insert);
            }
            preparedStat.setString( 1, course.getName() );
            preparedStat.setDouble( 2, course.getTotal_hours() );
            preparedStat.setBoolean( 3, course.isRemote() );

            preparedStat.executeUpdate();
            preparedStat.close();
        }
        catch( SQLException e )
        {
            e.printStackTrace();
        }
    }
}
