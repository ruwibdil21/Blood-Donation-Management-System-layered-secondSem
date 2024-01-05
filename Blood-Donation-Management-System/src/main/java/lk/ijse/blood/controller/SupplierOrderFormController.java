package lk.ijse.blood.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.blood.BO.Custom.Impl.SupplierOrderBOImpl;
import lk.ijse.blood.BO.Custom.SupplierOrderBO;
import lk.ijse.blood.dto.SupplierOrdersDto;
import lk.ijse.blood.dto.tm.SupplierOrdersTm;


import java.sql.SQLException;
import java.util.List;

public class SupplierOrderFormController {
    @FXML
    private AnchorPane SupplierOrder;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private TableView<SupplierOrdersTm> tblSupplierOrder;
    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtOrderId;

    @FXML
    private TextField txtSupplierId;

    SupplierOrderBO supplierOrderBO = new SupplierOrderBOImpl();

    public void initialize() throws ClassNotFoundException {
        loadAllSupplierOrders();
        setCellValueFactory();
    }
    private void setCellValueFactory() {
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("supOrder_id"));
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplier_id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));

    }

    public void loadAllSupplierOrders() throws ClassNotFoundException {

        ObservableList<SupplierOrdersTm> obList = FXCollections.observableArrayList();

        try{
            List<SupplierOrdersDto> dtoList = supplierOrderBO.loadAllSupplierOrders();

            for(SupplierOrdersDto dto : dtoList){
                obList.add(new SupplierOrdersTm(
                        dto.getSupOrder_id(),
                        dto.getSupplier_id(),
                        dto.getDate(),
                        dto.getAmount()

                ));
            }
            tblSupplierOrder.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    @FXML
    public void btnDeleteOnAction(ActionEvent event) throws ClassNotFoundException {
        String supplier_id = txtSupplierId.getText();

        try{
            SupplierOrdersDto dto = supplierOrderBO.searchSupplierOrders(supplier_id);
            if(dto != null) {
                boolean isDeleted = supplierOrderBO.deleteSupplierOrders(supplier_id);
                if (isDeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "SupplierOrders Delete Succesfull!!!").show();
                    clearFields();
                }
            }else {
                new Alert(Alert.AlertType.ERROR, "SupplierOrders Not Found!!!").show();
                clearFields();
            }
        } catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }



    @FXML
    public void btnSaveOnAction(ActionEvent event) throws ClassNotFoundException {
        String order_id = txtOrderId.getText();
        String supplier_id = txtSupplierId.getText();
        String date = txtDate.getText();
        String amount = txtAmount.getText();


        if (order_id.isEmpty() || supplier_id.isEmpty() || date.isEmpty() || amount.isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Please fill out all fields").show();
        }

        var dto = new SupplierOrdersDto(order_id,supplier_id,date,amount);

        try {
            boolean isSaved = supplierOrderBO.saveSupplierOrders(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "SupplierOrder Added Succesfull").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    private void clearFields() {
        txtOrderId.setText("");
        txtSupplierId.setText("");
        txtDate.setText("");
        txtAmount.setText("");

    }

    public void btnSingupOnAction(ActionEvent actionEvent) {
    }
}




