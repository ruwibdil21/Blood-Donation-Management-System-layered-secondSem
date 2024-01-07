package lk.ijse.blood.DAO.Custom;

import lk.ijse.blood.DAO.CrudDAO;
import lk.ijse.blood.dto.InventoryDto;
import lk.ijse.blood.dto.OrderDetailsDto;
import lk.ijse.blood.dto.SupplierOrdersDto;
import lk.ijse.blood.entity.Inventory;
import lk.ijse.blood.entity.OrderDetails;
import lk.ijse.blood.entity.SupplierOrders;

import java.sql.SQLException;

public interface OrderDetailsDAO extends CrudDAO<OrderDetails> {
    boolean placeOrderDetails(SupplierOrders supplierOrdersDto, Inventory inventoryDto, OrderDetails orderDetailsDto) throws SQLException, ClassNotFoundException ;
//
//     boolean saveOrderDetails(OrderDetailsDto dto) throws SQLException, ClassNotFoundException;

    }
