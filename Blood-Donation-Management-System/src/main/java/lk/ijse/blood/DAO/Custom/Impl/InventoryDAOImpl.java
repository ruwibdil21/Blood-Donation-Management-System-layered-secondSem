package lk.ijse.blood.DAO.Custom.Impl;

import lk.ijse.blood.DAO.Custom.InventoryDAO;
import lk.ijse.blood.Util.SQLUtil;
import lk.ijse.blood.dto.InventoryDto;
import lk.ijse.blood.entity.Inventory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAOImpl implements InventoryDAO {

    @Override
    public List<Inventory> loadAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM medical_inventory ");

        List<Inventory> inventoryList= new ArrayList<>();

        while (resultSet.next()) {
            Inventory inventoryDto =  new Inventory(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3));
            inventoryList.add(inventoryDto);
        }

        return inventoryList;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Inventory search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Inventory dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("insert into medical_inventory values (?,?,?)",dto.getMedical_id(),dto.getDate(),dto.getBloodType());
    }

    @Override
    public boolean update(Inventory dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "UPDATE medical_inventory SET Blood_type = ?, Date = ? WHERE Med_id = ?",dto.getMedical_id(),dto.getBloodType(),dto.getDate());
    }

    @Override
    public boolean add(Inventory dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "INSERT INTO medical_inventory VALUES (?,?,?)",dto.getMedical_id(),dto.getBloodType(),dto.getDate());
    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
