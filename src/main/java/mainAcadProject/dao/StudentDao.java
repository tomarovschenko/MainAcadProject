package mainAcadProject.dao;

import mainAcadProject.config.DBProcessor;
import mainAcadProject.entity.StudentEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ) on 05.05.2017.
 */
public class StudentDao {
    private StudentEntity student;

    static String query  = "select * from student";
    static String update = "update student set surname=?, name=?, patronymic=?, phone=?, email=?, passport=?, address=?, remote=?, manager_id=? where id=?";
    static String insert = "insert into student(id, surname, name, patronymic, phone, email, passport, address, remote, manager_id) values (?,?,?,?,?,?,?,?,?,?)";

    public StudentDao(StudentEntity student) {
        this.student = student;
    }

    public static List<StudentEntity> unload() throws SQLException{
        List <StudentEntity> students = new ArrayList<>();
        ResultSet resSet = DBProcessor.getConnection().createStatement().executeQuery(query);
        while (resSet.next()){
            StudentEntity student = new StudentEntity();
            student.setId(resSet.getString("id"));
            student.setSurname(resSet.getString("surname"));
            student.setName(resSet.getString("name"));
            student.setPatronymic(resSet.getString("patronymic"));
            student.setPhone(resSet.getString("phone"));
            student.setEmail(resSet.getString("email"));
            student.setPassport(resSet.getString("passport"));
            student.setAddress(resSet.getString("address"));
            student.setRemote(resSet.getBoolean("remote"));
            student.setManager_id(resSet.getInt("manager_id"));
            if (!student.isRemote()){
                students.add(student);
            }
        }
        return students;
    }

    public static void insert(StudentEntity student) {
        try {
            PreparedStatement preparedStatement;
            preparedStatement = DBProcessor.getConnection().prepareStatement(insert);
            preparedStatement.setString(1, student.getId());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getName());
            preparedStatement.setString(4, student.getPatronymic());
            preparedStatement.setString(5, student.getPhone());
            preparedStatement.setString(6, student.getEmail());
            preparedStatement.setString(7, student.getPassport());
            preparedStatement.setString(8, student.getAddress());
            preparedStatement.setBoolean(9, student.isRemote());
            preparedStatement.setInt(10, student.getManager_id());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public static void update(StudentEntity student) {
        try {
            PreparedStatement preparedStatement;
            preparedStatement=DBProcessor.getConnection().prepareStatement(update);
            preparedStatement.setString(10, student.getId());
            preparedStatement.setString(1, student.getSurname());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getPatronymic());
            preparedStatement.setString(4, student.getPhone());
            preparedStatement.setString(5, student.getEmail());
            preparedStatement.setString(6, student.getPassport());
            preparedStatement.setString(7, student.getAddress());
            preparedStatement.setBoolean(8, student.isRemote());
            preparedStatement.setInt(9, student.getManager_id());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public static String fullNameInstructor(String student_id){
        String fullName = "";
        try {
            ResultSet resSet= DBProcessor.getConnection().createStatement().executeQuery(query+" where id= '"+student_id+"'");
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
