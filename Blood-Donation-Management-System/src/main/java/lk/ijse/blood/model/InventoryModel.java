package lk.ijse.blood.model;

import lk.ijse.blood.Util.SQLUtil;
import lk.ijse.blood.db.DbConnection;
import lk.ijse.blood.dto.InventoryDto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventoryModel {

    public static boolean addInventory(InventoryDto dto) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute( "INSERT INTO medical_inventory VALUES (?,?,?)",dto.getMedical_id(),dto.getBloodType(),dto.getDate());
    }

    public static boolean saveInventory(InventoryDto dto) throws SQLException, ClassNotFoundException {

       return SQLUtil.execute("insert into medical_inventory values (?,?,?)",dto.getMedical_id(),dto.getDate(),dto.getBloodType());
    }

    public boolean updateInventory(InventoryDto dto) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute( "UPDATE medical_inventory SET Blood_type = ?, Date = ? WHERE Med_id = ?",dto.getMedical_id(),dto.getBloodType(),dto.getDate());
    }

    public static List<InventoryDto> loadAllInventories() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM medical_inventory ");

        List<InventoryDto> inventoryList= new ArrayList<>();

        while (resultSet.next()) {
            inventoryList.add(new InventoryDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)

            ));
        }

        return inventoryList;
    }
}
