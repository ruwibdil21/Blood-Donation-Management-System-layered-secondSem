package lk.ijse.blood.BO.Custom.Impl;

import lk.ijse.blood.BO.Custom.InventoryBO;
import lk.ijse.blood.DAO.Custom.InventoryDAO;
import lk.ijse.blood.DAO.DAOFactory;
import lk.ijse.blood.dto.InventoryDto;
import lk.ijse.blood.entity.Inventory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventoryBOImpl implements InventoryBO {

    InventoryDAO inventoryDAO = (InventoryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.INVENTORY);


    @Override
    public List<InventoryDto> loadAllInventory() throws SQLException, ClassNotFoundException {
        List<Inventory>inventoryList = inventoryDAO.loadAll();
        List<InventoryDto>inventoryDtos = new ArrayList<>();

        for (Inventory inventory :inventoryList){
            inventoryDtos.add(new InventoryDto(inventory.getMedical_id(),inventory.getBloodType(),inventory.getDate()));

        }
        return inventoryDtos;
    }

    @Override
    public boolean deleteInventory(String id) throws SQLException, ClassNotFoundException {
        return inventoryDAO.delete(id);
    }

    @Override
    public InventoryDto searchInventory(String id) throws SQLException, ClassNotFoundException {
        Inventory inventory = inventoryDAO.search(id);
        InventoryDto inventoryDto = new InventoryDto(inventory.getMedical_id(),inventory.getBloodType(),inventory.getDate());
        return  inventoryDto;
    }

    @Override
    public boolean saveInventory(InventoryDto dto) throws SQLException, ClassNotFoundException {
        return inventoryDAO.save(new Inventory(dto.getMedical_id(), dto.getBloodType(), dto.getDate()));
    }

    @Override
    public boolean updateInventory(InventoryDto dto) throws SQLException, ClassNotFoundException {
        return inventoryDAO.update(new Inventory(dto.getMedical_id(), dto.getBloodType(), dto.getDate()));
    }

    @Override
    public String generateInventory() throws SQLException, ClassNotFoundException {
        return inventoryDAO.generateId();
    }
}
