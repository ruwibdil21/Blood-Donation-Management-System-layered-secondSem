package lk.ijse.blood.model;

import javafx.fxml.FXML;
import lk.ijse.blood.Util.SQLUtil;
import lk.ijse.blood.dto.DonationDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DonationModel {

    @FXML
    public boolean saveDonation(DonationDto dto) throws SQLException, ClassNotFoundException {

       return SQLUtil.execute( "insert into donation values (?,?,?,?,?)",dto.getDo_id(),dto.getD_id(),dto.getDate(),dto.getBlood_type(),dto.getHemoglobin_level());
    }

    public boolean isDeleteDonation(String Do_id) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute( "DELETE FROM donation WHERE Donation_id = ?",Do_id);
    }

    public DonationDto searchDonation(String DoId) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM donation WHERE Donation_id = ?");

        if (resultSet.next()){
            return new DonationDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDate(3),
                    resultSet.getString(4),
                    resultSet.getString(5));


        }
        return null;
    }

    public static List<DonationDto> loadAllDonations() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM donation ");
        List<DonationDto> donationList = new ArrayList<>();

        while (resultSet.next()) {
            donationList.add(new DonationDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDate(3),
                    resultSet.getString(4),
                    resultSet.getString(5)

            ));
        }

        return donationList;
    }
}






