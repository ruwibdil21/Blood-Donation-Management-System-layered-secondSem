package lk.ijse.blood.model;

import lk.ijse.blood.Util.SQLUtil;
import lk.ijse.blood.Util.TransactionUtil;
import lk.ijse.blood.db.DbConnection;
import lk.ijse.blood.dto.BloodInventoryDto;
import lk.ijse.blood.dto.NeederRequestDto;
import lk.ijse.blood.dto.RequestDetailsDto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NeederRequestModel {

    public static boolean placeNeederRequest(NeederRequestDto neederRequestDto, BloodInventoryDto bagdto,RequestDetailsDto requestDetailsDto) throws SQLException, ClassNotFoundException {
       try {
           TransactionUtil.startTransaction();
            boolean isNeederRequestSaved = NeederRequestModel.saveNeederRequest(neederRequestDto);
            if (isNeederRequestSaved) {
                boolean isBloodInventorySaved = BloodInventoryModel.saveBloodInventory(bagdto);
                if (isBloodInventorySaved) {
                    boolean isrequestDetailsSaved = RequestDetailsModel.saveRequestDetails(requestDetailsDto);
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

    public boolean updateNeederRequest(NeederRequestDto dto) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute( "UPDATE needer_Request SET Needer_id = ?, Date = ?,Amount = ?, WHERE Request_id = ?",dto.getNeeReq_id(),dto.getNeederId(),dto.getDate(),dto.getAmount());
    }


    public static boolean saveNeederRequest(NeederRequestDto dto) throws SQLException, ClassNotFoundException {

       return SQLUtil.execute( "insert into needer_request values (?,?,?,?)",dto.getNeeReq_id(),dto.getNeederId(),dto.getDate(),dto.getAmount());

    }


    public NeederRequestDto searchNeeReq(String txtNeeReq) throws SQLException, ClassNotFoundException {

        ResultSet resultSet =SQLUtil.execute( "SELECT * FROM needer_Request WHERE NeeReq = ?",txtNeeReq);

        if (resultSet.next()) {
            return new NeederRequestDto(
            resultSet.getString(1),
            resultSet.getString(2),
            resultSet.getString(3),
            resultSet.getString(4));

        }
        return null;
    }



    public boolean deleteNeederRequest(String neeReq) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "DELETE FROM needer_Request WHERE Request_id = ?",neeReq);
    }

    public List<NeederRequestDto> loadAllNeederRequests() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM needer_Request");
        List<NeederRequestDto> neederREquestList = new ArrayList<>();

        while (resultSet.next()) {
            neederREquestList.add(new NeederRequestDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }

        return neederREquestList;
    }
}



