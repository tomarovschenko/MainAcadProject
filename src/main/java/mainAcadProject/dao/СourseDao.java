package mainAcadProject.dao;


import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import mainAcadProject.config.DBProcessor;
import mainAcadProject.entity.CourseEntity;
import sample.Course;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * Created by ) on 12.04.2017.
 */
public class СourseDao {

    private CourseEntity course;


    String query="select * from main_academy.course";
    String update="update course set course_name=?, total_hours=?, remote=? where id=?";
    String insert ="insert into course(course_name, total_hours, remote) values (?,?,?)";

    public СourseDao(CourseEntity course) throws SQLException {
        this.course = course;
    }

    public ObservableList<CourseEntity> unload() throws SQLException {
        ObservableList <CourseEntity> courses = FXCollections.observableArrayList();
        ResultSet resSet=DBProcessor.getConnection().createStatement().executeQuery(query);
        while (resSet.next()){
            CourseEntity course = new CourseEntity();
            course.setId(resSet.getInt("id"));
            course.setName(resSet.getString("course_name"));
            course.setTotal_hours(resSet.getDouble(""));
            course.setRemote(resSet.getBoolean("remote"));
            courses.add(course);
        }
        return courses;
    }

    public void update(CourseEntity course){
        try
        {
            PreparedStatement preparedStat;
            //если договор не новый
            if ( course.getId() > 0 )
            {
                preparedStat = DBProcessor.getConnection().prepareStatement(update);
                preparedStat.setInt( 4, course.getId() );
            }
            else
            {
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
