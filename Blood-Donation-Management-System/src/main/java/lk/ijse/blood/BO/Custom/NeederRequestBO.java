package lk.ijse.blood.BO.Custom;

import lk.ijse.blood.BO.SuperBO;
import lk.ijse.blood.dto.BloodInventoryDto;
import lk.ijse.blood.dto.NeederRequestDto;
import lk.ijse.blood.dto.RequestDetailsDto;

import java.sql.SQLException;
import java.util.List;

public interface NeederRequestBO extends SuperBO {
    List<NeederRequestDto> loadAllNeederRequest() throws SQLException, ClassNotFoundException;
    boolean deleteNeederRequest(String id) throws SQLException, ClassNotFoundException;
    NeederRequestDto searchNeederRequest(String id) throws SQLException, ClassNotFoundException ;
    boolean saveNeederRequest(NeederRequestDto dto) throws SQLException, ClassNotFoundException ;
    boolean updateNeederRequwst(NeederRequestDto dto) throws SQLException, ClassNotFoundException;
    String generateNeedrRequwst() throws SQLException,ClassNotFoundException;
    boolean placeNeederRequest(NeederRequestDto neederRequestDto, BloodInventoryDto bagdto, RequestDetailsDto requestDetailsDto) throws SQLException, ClassNotFoundException;
}

