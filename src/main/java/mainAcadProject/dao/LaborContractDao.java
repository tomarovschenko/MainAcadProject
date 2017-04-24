package mainAcadProject.dao;

import mainAcadProject.config.DBProcessor;
import mainAcadProject.entity.LaborContractEntity;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ) on 22.04.2017.
 */
public class LaborContractDao {

    private LaborContractEntity contract;

    static String query="select * from main_acad.labor_contract";
    static String update="update labor_contract set number=?, date_start=?, date_end=?, remote=? where id=?";
    static String insert ="insert into labor_contract(number, date_start, date_end, remote, instructor_id) values (?,?,?,?,?)";

    public LaborContractDao(LaborContractEntity contract) {
        this.contract = contract;
    }

    public static List<LaborContractEntity> unload() throws SQLException{
        List <LaborContractEntity> contracts =new ArrayList<>();
        ResultSet resultSet= DBProcessor.getConnection().createStatement().executeQuery(query);
        while (resultSet.next()){
            LaborContractEntity contract = new LaborContractEntity();
            contract.setId(resultSet.getInt("id"));
            contract.setNumber(resultSet.getString("number"));
            contract.setDate_start((resultSet.getDate("date_start")).toLocalDate());
            contract.setDate_end((resultSet.getDate("date_end")).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            contract.setRemote(resultSet.getBoolean("remote"));
            if (!contract.isRemote()){
                contracts.add(contract);
            }
        }
        return contracts;
    }

    public static void update(LaborContractEntity contract){
        try{
            PreparedStatement preparedStat;
            if(contract.getId() > 0){
                preparedStat = DBProcessor.getConnection().prepareStatement(update);
                preparedStat.setInt(5,contract.getId());
            }
            else {
                preparedStat = DBProcessor.getConnection().prepareStatement(insert);
                preparedStat.setString(1,contract.getNumber());
                preparedStat.setDate(2, Date.valueOf(contract.getDate_start()));
                preparedStat.setDate(3, Date.valueOf(contract.getDate_end()));
                preparedStat.setBoolean(4, contract.isRemote());
                preparedStat.setInt(5, contract.getInstructor().getId());

                preparedStat.executeUpdate();
                preparedStat.close();
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
