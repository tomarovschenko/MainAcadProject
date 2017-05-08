package mainAcadProject.dao;

import mainAcadProject.config.DBProcessor;
import mainAcadProject.entity.InstructorEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ) on 22.04.2017.
 */
public class InstructorDao {
    private InstructorEntity instructor;

    static String query = "select * from main_acad.instructor";
    static String update = "update instructor set surname=?, name=?, patronymic=?, phone=?, passport=?, email=?, working=?, remote=? where id=?";
    static String insert = "insert into instructor(id, surname, name, patronymic, phone, passport, email, working,  remote) values (?,?,?,?,?,?,?,?,?)";

    public InstructorDao(InstructorEntity instructor) {
        this.instructor = instructor;
    }

    public static List<InstructorEntity> unload() throws SQLException{
        List <InstructorEntity> instructors = new ArrayList<>();
        ResultSet resSet= DBProcessor.getConnection().createStatement().executeQuery(query);
        while (resSet.next()){
            InstructorEntity instructor = new InstructorEntity();
            instructor.setId(resSet.getString("id"));
            instructor.setSurname(resSet.getString("surname"));
            instructor.setName(resSet.getString("name"));
            instructor.setPatronymic(resSet.getString("patronymic"));
            instructor.setPhone(resSet.getString("phone"));
            instructor.setPassport(resSet.getString("passport"));
            instructor.setEmail(resSet.getString("email"));
            instructor.setWorking(resSet.getString("working"));
            instructor.setRemote(resSet.getBoolean("remote"));
            if (!instructor.isRemote()){
                instructors.add(instructor);
            }
        }
        return instructors;
    }

     public static void insert(InstructorEntity instructor) {
         try {
             PreparedStatement preparedStatement;
             preparedStatement = DBProcessor.getConnection().prepareStatement(insert);
             preparedStatement.setString(1, instructor.getId());
             preparedStatement.setString(2, instructor.getSurname());
             preparedStatement.setString(3, instructor.getName());
             preparedStatement.setString(4, instructor.getPatronymic());
             preparedStatement.setString(5, instructor.getPhone());
             preparedStatement.setString(6, instructor.getPassport());
             preparedStatement.setString(7, instructor.getEmail());
             preparedStatement.setString(8, instructor.getWorking());
             preparedStatement.setBoolean(9, instructor.isRemote());
             preparedStatement.executeUpdate();
             preparedStatement.close();
         } catch (SQLException ex) {
             ex.printStackTrace();
         }
     }

    public static void update(InstructorEntity instructor){
        try{
            PreparedStatement preparedStatement;
            preparedStatement=DBProcessor.getConnection().prepareStatement(update);
            preparedStatement.setString(9, instructor.getId());
            preparedStatement.setString(1,instructor.getSurname());
            preparedStatement.setString(2,instructor.getName());
            preparedStatement.setString(3,instructor.getPatronymic());
            preparedStatement.setString(4,instructor.getPhone());
            preparedStatement.setString(5,instructor.getPassport());
            preparedStatement.setString(6,instructor.getEmail());
            preparedStatement.setString(7,instructor.getWorking());
            preparedStatement.setBoolean(8,instructor.isRemote());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public static String fullNameInstructor(String instructor_id){
        String fullName = "";
        try {
        ResultSet resSet= DBProcessor.getConnection().createStatement().executeQuery(query+" where id= '"+instructor_id+"'");
            while (resSet.next()) {
                fullName = resSet.getString("surname")+" "+resSet.getString("name").substring(0,1)+". "+
                resSet.getString("patronymic").substring(0,1)+".";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fullName;
    }
}
