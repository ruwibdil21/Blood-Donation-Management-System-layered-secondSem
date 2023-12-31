package lk.ijse.blood.DAO.Custom.Impl;

import lk.ijse.blood.DAO.Custom.DonationDAO;
import lk.ijse.blood.Util.SQLUtil;
import lk.ijse.blood.dto.DonationDto;
import lk.ijse.blood.entity.Donation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DonationDAOImpl implements DonationDAO {

    @Override
    public List<Donation> loadAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM donation ");
        List<Donation> donationList = new ArrayList<>();

        while (resultSet.next()) {
            Donation donationDto = new Donation(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDate(3),
                    resultSet.getString(4),
                    resultSet.getString(5));
            donationList.add(donationDto);

        }

        return donationList;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "DELETE FROM donation WHERE Donation_id = ?",id);
    }

    @Override
    public Donation search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM donation WHERE Donation_id = ?");

        if (resultSet.next()){
            return new Donation(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDate(3),
                    resultSet.getString(4),
                    resultSet.getString(5));


        }
        return null;
    }

    @Override
    public boolean save(Donation dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "insert into donation values (?,?,?,?,?)",dto.getDo_id(),dto.getD_id(),dto.getDate(),dto.getBlood_type(),dto.getHemoglobin_level());
    }

    @Override
    public boolean update(Donation dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean add(Donation dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
