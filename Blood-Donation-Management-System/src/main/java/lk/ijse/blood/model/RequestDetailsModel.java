package lk.ijse.blood.model;

import lk.ijse.blood.Util.SQLUtil;
import lk.ijse.blood.dto.DonorDto;
import lk.ijse.blood.dto.RequestDetailsDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RequestDetailsModel {

    public boolean deleteRequestDetails(String neeReqId) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute( "DELETE FROM request_details WHERE NeeReqId = ?",neeReqId);

    }

    public static boolean saveRequestDetails(RequestDetailsDto dto) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("insert into request_details values (?,?,?)",dto.getNeeReq_id(),dto.getBloodBagId(),dto.getDescription());
    }


    public RequestDetailsDto searchRequestDetails(String neeReqId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM neeReq_id WHERE NeeReqId = ?",neeReqId);

        if (resultSet.next()){
            return new RequestDetailsDto(
            resultSet.getString(1),
            resultSet.getString(2),
            resultSet.getString(3));


        }
        return null;
    }

    public List<RequestDetailsDto> loadAllRequestDetails() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM requestDetails");
        List<DonorDto> donorList = new ArrayList<>();

        List<RequestDetailsDto> requestDetailsList = null;
        while (resultSet.next()) {
            requestDetailsList.add(new RequestDetailsDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            ));
        }

        return requestDetailsList;
    }
}

