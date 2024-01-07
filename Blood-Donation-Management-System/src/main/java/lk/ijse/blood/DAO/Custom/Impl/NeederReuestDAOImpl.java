package lk.ijse.blood.DAO.Custom.Impl;

import lk.ijse.blood.BO.Custom.BloodInventoryBO;
import lk.ijse.blood.DAO.Custom.BloodInventoryDAO;
import lk.ijse.blood.DAO.Custom.NeederRequestDAO;
import lk.ijse.blood.DAO.Custom.RequestDetailsDAO;
import lk.ijse.blood.DAO.DAOFactory;
import lk.ijse.blood.Util.SQLUtil;
import lk.ijse.blood.Util.TransactionUtil;
import lk.ijse.blood.dto.BloodInventoryDto;
import lk.ijse.blood.dto.NeederRequestDto;
import lk.ijse.blood.dto.RequestDetailsDto;
import lk.ijse.blood.entity.BloodInventory;
import lk.ijse.blood.entity.NeederRequest;
import lk.ijse.blood.entity.RequestDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class NeederReuestDAOImpl implements NeederRequestDAO {

    NeederRequestDAO neederRequestDAO = (NeederRequestDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.NEEDERREQUEST);

    BloodInventoryDAO bloodInventoryDAO = (BloodInventoryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BLOODINVENTORY);
    RequestDetailsDAO requestDetailsDAO = (RequestDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.REQUESTDETAILS);
    public  boolean placeNeederRequest(NeederRequest neederRequestDto, BloodInventory bagdto, RequestDetails requestDetailsDto) throws SQLException, ClassNotFoundException {
        try {
            TransactionUtil.startTransaction();
            boolean isNeederRequestSaved = neederRequestDAO.save(neederRequestDto);
            if (isNeederRequestSaved) {
                boolean isBloodInventorySaved = bloodInventoryDAO.save(bagdto);
                if (isBloodInventorySaved) {
                    boolean isrequestDetailsSaved = requestDetailsDAO.save(requestDetailsDto);
                    if (isrequestDetailsSaved) {
                        return true;
                    }
                }
                TransactionUtil.rollBack();
                return false;
            }TransactionUtil.rollBack();
            return false;
        }finally{
            TransactionUtil.endTransaction();
        }
    }


    @Override
    public List<NeederRequest> loadAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM needer_Request");
        List<NeederRequest> neederREquestList = new ArrayList<>();

        while (resultSet.next()) {
            NeederRequest neederRequestDto = new NeederRequest(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4));
            neederREquestList.add(neederRequestDto);
        }

        return neederREquestList;    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "DELETE FROM needer_Request WHERE Request_id = ?",id);
    }

    @Override
    public NeederRequest search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet =SQLUtil.execute( "SELECT * FROM needer_Request WHERE NeeReq = ?",id);

        if (resultSet.next()) {
            return new NeederRequest(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4));

        }
        return null;
    }

    @Override
    public boolean save(NeederRequest dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "insert into needer_request values (?,?,?,?)",dto.getNeeReq_id(),dto.getNeederId(),dto.getDate(),dto.getAmount());
    }

    @Override
    public boolean update(NeederRequest dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "UPDATE needer_Request SET Needer_id = ?, Date = ?,Amount = ?, WHERE Request_id = ?",dto.getNeeReq_id(),dto.getNeederId(),dto.getDate(),dto.getAmount());
    }

    @Override
    public boolean add(NeederRequest dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
