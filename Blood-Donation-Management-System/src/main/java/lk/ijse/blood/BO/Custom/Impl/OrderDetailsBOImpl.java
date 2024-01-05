package lk.ijse.blood.BO.Custom.Impl;

import lk.ijse.blood.BO.Custom.OrderDetailsBO;
import lk.ijse.blood.DAO.Custom.OrderDetailsDAO;
import lk.ijse.blood.DAO.DAOFactory;
import lk.ijse.blood.dto.InventoryDto;
import lk.ijse.blood.dto.OrderDetailsDto;
import lk.ijse.blood.dto.SupplierOrdersDto;
import lk.ijse.blood.entity.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsBOImpl implements OrderDetailsBO {
    OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);
    @Override
    public List<OrderDetailsDto> loadAllOrderDetails() throws SQLException, ClassNotFoundException {
        List<OrderDetails>orderDetailsList = orderDetailsDAO.loadAll();
        List<OrderDetailsDto> orderDetailsDtos = new ArrayList<>();

        for (OrderDetails orderDetails :orderDetailsList){
            orderDetailsDtos.add(new OrderDetailsDto(orderDetails.getOrder_id(),orderDetails.getMed_id(),orderDetails.getDescription()));

        }
        return orderDetailsDtos;
    }

    @Override
    public boolean deleteOrderDetails(String id) throws SQLException, ClassNotFoundException {
        return orderDetailsDAO.delete(id);
    }

    @Override
    public OrderDetailsDto searchOrderDetails(String id) throws SQLException, ClassNotFoundException {
        OrderDetails orderDetails = orderDetailsDAO.search(id);
        OrderDetailsDto orderDetailsDto =new OrderDetailsDto(orderDetails.getOrder_id(),orderDetails.getMed_id(),orderDetails.getDescription());
        return orderDetailsDto;
    }

    @Override
    public boolean saveOrderDetails(OrderDetailsDto dto) throws SQLException, ClassNotFoundException {
        return orderDetailsDAO.save(new OrderDetails(dto.getOrder_id(), dto.getMed_id(), dto.getDescription()));
    }

    @Override
    public boolean updateOrderDetails(OrderDetailsDto dto) throws SQLException, ClassNotFoundException {
        return orderDetailsDAO.update(new OrderDetails(dto.getOrder_id(), dto.getMed_id(), dto.getDescription()));
    }

    @Override
    public String generateOrderDetails() throws SQLException, ClassNotFoundException {
        return orderDetailsDAO.generateId();
    }

    @Override
    public boolean placeOrderDetails(SupplierOrdersDto supplierOrdersDto, InventoryDto inventoryDto, OrderDetailsDto orderDetailsDto) throws SQLException, ClassNotFoundException {
        return orderDetailsDAO.placeOrderDetails(supplierOrdersDto,inventoryDto,orderDetailsDto);
    }
}
