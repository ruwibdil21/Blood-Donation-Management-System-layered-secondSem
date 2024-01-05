package lk.ijse.blood.BO.Custom.Impl;

import lk.ijse.blood.BO.Custom.NeederRequestBO;
import lk.ijse.blood.DAO.Custom.NeederRequestDAO;
import lk.ijse.blood.DAO.DAOFactory;
import lk.ijse.blood.dto.BloodInventoryDto;
import lk.ijse.blood.dto.NeederRequestDto;
import lk.ijse.blood.dto.RequestDetailsDto;
import lk.ijse.blood.entity.NeederRequest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NeederRequestBOImpl implements NeederRequestBO {

    NeederRequestDAO neederRequestDAO = (NeederRequestDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.NEEDERREQUEST);
    @Override
    public List<NeederRequestDto> loadAllNeederRequest() throws SQLException, ClassNotFoundException {
        List<NeederRequest>neederRequestList = neederRequestDAO.loadAll();
        List<NeederRequestDto>neederRequestDtos = new ArrayList<>();

        for (NeederRequest neederRequest:neederRequestList){
            neederRequestDtos.add(new NeederRequestDto(neederRequest.getNeeReq_id(),neederRequest.getNeederId(),neederRequest.getDate(),neederRequest.getAmount()));

        }
        return neederRequestDtos;
    }

    @Override
    public boolean deleteNeederRequest(String id) throws SQLException, ClassNotFoundException {
        return neederRequestDAO.delete(id);
    }

    @Override
    public NeederRequestDto searchNeederRequest(String id) throws SQLException, ClassNotFoundException {
        NeederRequest neederRequest =neederRequestDAO.search(id);
        NeederRequestDto neederRequestDto = new NeederRequestDto(neederRequest.getNeeReq_id(),neederRequest.getNeederId(),neederRequest.getDate(),neederRequest.getAmount());
        return  neederRequestDto;
    }

    @Override
    public boolean saveNeederRequest(NeederRequestDto dto) throws SQLException, ClassNotFoundException {
        return neederRequestDAO.save(new NeederRequest(dto.getNeeReq_id(), dto.getNeederId(), dto.getDate(), dto.getAmount()));
    }

    @Override
    public boolean updateNeederRequwst(NeederRequestDto dto) throws SQLException, ClassNotFoundException {
        return neederRequestDAO.update(new NeederRequest(dto.getNeeReq_id(), dto.getNeederId(), dto.getDate(), dto.getAmount()));
    }

    @Override
    public String generateNeedrRequwst() throws SQLException, ClassNotFoundException {
        return neederRequestDAO.generateId();
    }

    @Override
    public boolean placeNeederRequest(NeederRequestDto neederRequestDto, BloodInventoryDto bagdto, RequestDetailsDto requestDetailsDto) throws SQLException, ClassNotFoundException {
        return neederRequestDAO.placeNeederRequest(neederRequestDto,bagdto,requestDetailsDto);
    }
}
