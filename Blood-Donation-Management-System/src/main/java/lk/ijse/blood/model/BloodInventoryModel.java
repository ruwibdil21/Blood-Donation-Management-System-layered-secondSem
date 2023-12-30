package lk.ijse.blood.model;

import lk.ijse.blood.SQLUtil;
import lk.ijse.blood.db.DbConnection;
import lk.ijse.blood.dto.BloodInventoryDto;
import lk.ijse.blood.dto.DonationDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BloodInventoryModel {

    public BloodInventoryDto searchBloodInventory(String bloodBagId) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute( "SELECT * FROM blood_inventory WHERE  BloodBag_id = ?",bloodBagId);

        if (resultSet.next()){
            return new BloodInventoryDto(
            resultSet.getString(1),
            resultSet.getString(2),
            resultSet.getString(3),
            resultSet.getString(4),
            resultSet.getString(5));

        }
        return null;
    }


    public boolean isDeleteBloodInventory(String BloodBag_id) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("DELETE FROM blood_inventory WHERE  BloodBag_id = ?",BloodBag_id);
    }


    public static boolean saveBloodInventory(BloodInventoryDto dto) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute( "insert into blood_inventory values (?,?,?,?,?)",dto.getBloodBagId(),dto.getDonation_id(),dto.getDonation_date(),dto.getEx_date(),dto.getBlood_type());
    }

    public boolean updateBloodInventory(BloodInventoryDto dto) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("UPDATE blood_inventory SET Donation_id = ?, Donation_date= ?,Ex_date = ?,Blood_type = ? ,WHERE  BloodBag_id = ?",dto.getDonation_id(),dto.getDonation_date(),dto.getEx_date(),dto.getBlood_type(),dto.getBloodBagId());
    }


    public List<BloodInventoryDto> loadAllBloodInventorys() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute( "SELECT * FROM blood_inventory ");
        List<BloodInventoryDto> bloodInventoryList = new ArrayList<>();

        while (resultSet.next()) {
            bloodInventoryList.add(new BloodInventoryDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)

            ));
        }

        return bloodInventoryList;
    }
}

