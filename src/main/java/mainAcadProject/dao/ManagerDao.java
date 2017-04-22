package mainAcadProject.dao;

import mainAcadProject.config.DBProcessor;
import mainAcadProject.entity.CourseEntity;
import mainAcadProject.entity.ManagerEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ) on 21.04.2017.
 */
public class ManagerDao {
    private ManagerEntity manager;

    static String query="select * from main_acad.manager";
    static String update="update manager set manager_name=?, access=?, remote=? where id=?";
    static String insert ="insert into manager(manager_name, access, remote) values (?,?,?)";

    public ManagerDao(ManagerEntity manager) throws SQLException {
        this.manager = manager;
    }

    public static List<ManagerEntity> unload() throws SQLException {
        List <ManagerEntity> managers = new ArrayList<>();
        ResultSet resSet= DBProcessor.getConnection().createStatement().executeQuery(query);
        while (resSet.next()){
            ManagerEntity manager = new ManagerEntity();
            manager.setId(resSet.getInt("id"));
            manager.setName(resSet.getString("manager_name"));
            manager.setAccess(resSet.getString("access"));
            manager.setRemote(resSet.getBoolean("remote"));
            if (!manager.isRemote()) {
                managers.add(manager);
            }
        }
        return managers;
    }

    public static void update(ManagerEntity manager){
        try
        {
            PreparedStatement preparedStat;
            if (manager.getId() > 0 )
            {
                preparedStat = DBProcessor.getConnection().prepareStatement(update);
                preparedStat.setInt( 4, manager.getId() );
            }
            else
            {
                preparedStat = DBProcessor.getConnection().prepareStatement(insert);
            }

            preparedStat.setString( 1, manager.getName() );
            preparedStat.setString( 2, manager.getAccess() );
            preparedStat.setBoolean( 3, manager.isRemote() );

            preparedStat.executeUpdate();
            preparedStat.close();
        }
        catch( SQLException e )
        {
            e.printStackTrace();
        }
    }
}
