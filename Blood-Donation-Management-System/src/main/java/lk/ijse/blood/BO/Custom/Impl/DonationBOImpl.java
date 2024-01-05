package lk.ijse.blood.BO.Custom.Impl;

import lk.ijse.blood.BO.Custom.DonationBO;
import lk.ijse.blood.DAO.Custom.DonationDAO;
import lk.ijse.blood.DAO.DAOFactory;
import lk.ijse.blood.dto.BloodInventoryDto;
import lk.ijse.blood.dto.DonationDto;
import lk.ijse.blood.entity.BloodInventory;
import lk.ijse.blood.entity.Donation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DonationBOImpl implements DonationBO {

    DonationDAO donationDAO = (DonationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.DONATION);
    @Override
    public List<DonationDto> loadAllDonation() throws SQLException, ClassNotFoundException {
        List<Donation> donationList = donationDAO.loadAll();
        List<DonationDto> donationDtos = new ArrayList<>();

        for (Donation donation : donationList) {
            donationDtos.add(new DonationDto());
        }
        return donationDtos;    }

    @Override
    public boolean deleteDonation(String id) throws SQLException, ClassNotFoundException {
        return donationDAO.delete(id);
    }

    @Override
    public DonationDto searchDonation(String id) throws SQLException, ClassNotFoundException {
        Donation donation = donationDAO.search(id);
        DonationDto donationDto = new DonationDto(donation.getDo_id(),donation.getD_id(),donation.getDate(),donation.getBlood_type(),donation.getHemoglobin_level());
        return donationDto;
    }

    @Override
    public boolean saveDonation(DonationDto dto) throws SQLException, ClassNotFoundException {
        return  donationDAO.save(new Donation(dto.getDo_id(), dto.getD_id(), dto.getDate(), dto.getBlood_type(), dto.getHemoglobin_level()));

    }

    @Override
    public boolean updateDonation(DonationDto dto) throws SQLException, ClassNotFoundException {
        return donationDAO.update(new Donation(dto.getDo_id(),dto.getD_id(),dto.getDate(),dto.getBlood_type(),dto.getHemoglobin_level()));
    }

    @Override
    public String generateDoId() throws SQLException, ClassNotFoundException {
        return donationDAO.generateId();
    }
}
