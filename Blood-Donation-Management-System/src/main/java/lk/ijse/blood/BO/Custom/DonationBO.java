package lk.ijse.blood.BO.Custom;

import lk.ijse.blood.BO.SuperBO;
import lk.ijse.blood.dto.BloodInventoryDto;
import lk.ijse.blood.dto.DonationDto;

import java.sql.SQLException;
import java.util.List;

public interface DonationBO extends SuperBO {
    List<DonationDto> loadAllDonation() throws SQLException, ClassNotFoundException;
    boolean deleteDonation(String id) throws SQLException, ClassNotFoundException;
    DonationDto searchDonation(String id) throws SQLException, ClassNotFoundException ;
    boolean saveDonation(DonationDto dto) throws SQLException, ClassNotFoundException ;
    boolean updateDonation(DonationDto dto) throws SQLException, ClassNotFoundException;
    String generateDoId() throws SQLException,ClassNotFoundException;
}

