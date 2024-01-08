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
import lk.ijse.blood.bo.Custom.Impl.RequestDetailsBOImpl;
import lk.ijse.blood.bo.Custom.RequestDetailsBO;
import lk.ijse.blood.dto.RequestDetailsDto;
import lk.ijse.blood.dto.tm.RequestDetailsTm;


import java.sql.SQLException;
import java.util.List;

public class RequestDetailsFormController {
    @FXML
    private AnchorPane RequestDetails;

    @FXML
    private TableColumn<?, ?> colBloodBagId;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colNeederId;

    @FXML
    private TableView<RequestDetailsTm> tblRequestDetails;

    @FXML
    private TextField txtBloodBagId;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtNeederId;

    RequestDetailsBO requestDetailsBO = new RequestDetailsBOImpl();


    public void initialize() throws ClassNotFoundException {
        loadAllRequestDetails();
        setCellValueFactory();
    }
    private void setCellValueFactory() {
        colNeederId.setCellValueFactory(new PropertyValueFactory<>("neeReq_id"));
        colBloodBagId.setCellValueFactory(new PropertyValueFactory<>("bloodBagId"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));

    }

    public void loadAllRequestDetails() throws ClassNotFoundException {

        ObservableList<RequestDetailsTm> obList = FXCollections.observableArrayList();

        try{
            List<RequestDetailsDto> dtoList = requestDetailsBO.loadAllRequestDetails();

            for(RequestDetailsDto dto : dtoList){
                obList.add(new RequestDetailsTm(
                        dto.getNeeReq_id(),
                        dto.getBloodBagId(),
                        dto.getDescription()

                ));
            }
            tblRequestDetails.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    public void btnDeleteOnAction(ActionEvent event) throws ClassNotFoundException {
            String Needer_id = txtNeederId.getText();

            try{

                RequestDetailsDto dto =requestDetailsBO.searchRequestDetails(Needer_id);
                if(dto != null) {
                    boolean isDeleted = requestDetailsBO.deleteRequestDetails(Needer_id);
                    if (isDeleted) {
                        return;
                    }
                    new Alert(Alert.AlertType.CONFIRMATION, "RequestDetails Request Delete Succesfull!!!").show();
                    clearFields();
                }else {
                    new Alert(Alert.AlertType.ERROR, "RequestDetails Request Not Found!!!").show();
                    clearFields();
                }
            } catch (SQLException e){
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }



    @FXML
    public void btnSaveOnAction(ActionEvent event) throws ClassNotFoundException {
        String needer_id = txtNeederId.getText();
        String bloodbag_id = txtBloodBagId.getText();
        String description = txtDescription.getText();


        if (needer_id.isEmpty() || bloodbag_id.isEmpty() || description.isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Please fill out all fields").show();
        }

        var dto = new RequestDetailsDto(needer_id,bloodbag_id,description);

        try {
            boolean isSaved = requestDetailsBO.saveRequestDetails(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "RequestDetails Added Succesfull").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void clearFields() {
        txtNeederId.setText("");
        txtBloodBagId.setText("");
        txtDescription.setText("");
    }
}


