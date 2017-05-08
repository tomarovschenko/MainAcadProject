package mainAcadProject.dao;

import mainAcadProject.config.DBProcessor;
import mainAcadProject.entity.ContractEntity;
import mainAcadProject.entity.LaborContractEntity;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ) on 05.05.2017.
 */
public class ContractDao {

    private ContractEntity contract;

    static String query="select * from main_acad.сontract";
    static String update="update сontract set number=?, start_date=?, end_date=?, amount=? remote=?, student_id=? where id=?";
    static String insert ="insert into сontract(number, start_date, end_date, amount, remote, student_id) values (?,?,?,?,?,?)";

    public ContractDao(ContractEntity contract) {
        this.contract = contract;
    }

    public static List<ContractEntity> unload() throws SQLException {
        List <ContractEntity> contracts =new ArrayList<>();
        ResultSet resultSet= DBProcessor.getConnection().createStatement().executeQuery(query);
        while (resultSet.next()){
            ContractEntity contract = new ContractEntity();
            contract.setId(resultSet.getInt("id"));
            contract.setNumb(resultSet.getString("number"));
            contract.setStartDate((resultSet.getDate("start_date")).toLocalDate());
            contract.setEndDate((resultSet.getDate("end_date")).toLocalDate());
            contract.setAmount(resultSet.getDouble("amount"));
            contract.setRemote(resultSet.getBoolean("remote"));
            contract.setStudent_id(resultSet.getString("student_id"));
            if (!contract.isRemote()){
                contracts.add(contract);
            }
        }
        return contracts;
    }

    public static void insert (ContractEntity contract){
        try{
            PreparedStatement preparedStat;
            preparedStat = DBProcessor.getConnection().prepareStatement(insert);
            preparedStat.setString(1,contract.getNumb());
            preparedStat.setDate(2, Date.valueOf(contract.getStartDate()));
            preparedStat.setDate(3, Date.valueOf(contract.getEndDate()));
            preparedStat.setDouble(4, contract.getAmount());
            preparedStat.setBoolean(5, contract.isRemote());
            preparedStat.setString(6, contract.getStudent_id());
            preparedStat.executeUpdate();
            preparedStat.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public static void update(ContractEntity contract){
        try{
            PreparedStatement preparedStat;
            preparedStat = DBProcessor.getConnection().prepareStatement(update);
            preparedStat.setInt(7,contract.getId());
            preparedStat.setString(1,contract.getNumb());
            preparedStat.setDate(2, Date.valueOf(contract.getStartDate()));
            preparedStat.setDate(3, Date.valueOf(contract.getEndDate()));
            preparedStat.setDouble(4, contract.getAmount());
            preparedStat.setBoolean(5, contract.isRemote());
            preparedStat.setString(6, contract.getStudent_id());
            preparedStat.executeUpdate();
            preparedStat.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
