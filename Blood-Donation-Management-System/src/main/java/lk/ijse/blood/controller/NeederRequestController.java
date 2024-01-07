package lk.ijse.blood.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.blood.BO.Custom.DonationBO;
import lk.ijse.blood.BO.Custom.Impl.DonationBOImpl;
import lk.ijse.blood.BO.Custom.Impl.NeederBOImpl;
import lk.ijse.blood.BO.Custom.Impl.NeederRequestBOImpl;
import lk.ijse.blood.BO.Custom.NeederBO;
import lk.ijse.blood.BO.Custom.NeederRequestBO;
import lk.ijse.blood.db.DbConnection;
import lk.ijse.blood.dto.*;
import lk.ijse.blood.entity.BloodInventory;
import lk.ijse.blood.entity.NeederRequest;
import lk.ijse.blood.entity.RequestDetails;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

public class NeederRequestController {
    public DatePicker dtpDate;
    public ComboBox cmbNeederid;
    public ComboBox cmbDonationid;
    public DatePicker dtpExdate;
    public TextField txtType;
    public TextField txtBloodBagid;

    @FXML
    private AnchorPane neederRequest;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtNeeReq;

    NeederRequestBO neederRequestBO= new NeederRequestBOImpl();
    NeederBO neederBO = new NeederBOImpl();
    DonationBO donationBO = new DonationBOImpl();

    public void initialize() throws SQLException, ClassNotFoundException {
        autoGenerateRequestId();
        //autoGenerateBloodBagId();
        loadAllNeeder();
        loadAllDonation();
        dtpDate.setValue(LocalDate.now());
    }
    @FXML
    void btnSaveOnAction(ActionEvent event) throws ClassNotFoundException {
        String neeReq = txtNeeReq.getText();
        String blood_bag_id = txtBloodBagid.getText();
        String needer_id = String.valueOf(cmbNeederid.getValue());
        String date = String.valueOf(dtpDate.getValue());
        String donation_id = String.valueOf(cmbDonationid.getValue());
        String amount = txtAmount.getText();
        String type = txtType.getText();
        String exdate = String.valueOf(dtpExdate.getValue());

        boolean isNeederRequestValidated  = validateNeederRequest();
        if (!isNeederRequestValidated){return;}

        var neederrequestdto = new NeederRequest(neeReq,needer_id,date,amount);
        var bagdto = new BloodInventory(blood_bag_id, donation_id,date,exdate,type);
        var requestdto = new RequestDetails(neeReq, blood_bag_id,type);

        try {
            boolean isSaved = neederRequestBO.placeNeederRequest(neederrequestdto,bagdto,requestdto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Needer Request Added Succesfull").show();
                clearFields();
                initialize();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void clearFields() {
        txtNeeReq.setText("");
        txtBloodBagid.setText("");
        cmbNeederid.getItems().clear();
        cmbDonationid.getItems().clear();
        dtpDate.setValue(null);
        txtAmount.setText("");
        dtpExdate.setValue(null);
    }

    private void loadAllNeeder() throws ClassNotFoundException {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<NeederDto> needList = neederBO.loadAllNeeder();

            for (NeederDto neederDto  : needList) {
                obList.add(neederDto.getNeederId());
            }
            cmbNeederid.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllDonation() throws ClassNotFoundException {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<DonationDto> donList = donationBO.loadAllDonation();

            for (DonationDto donationDto : donList) {
                obList.add(donationDto.getDo_id());
            }
            cmbDonationid.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean validateNeederRequest() {
        String amount = txtAmount.getText();
        boolean isAmountValidated = Pattern.compile("/d").matcher(amount).matches();
        if (!isAmountValidated) {
            txtAmount.requestFocus();
        }
        return true;
    }

    private void autoGenerateRequestId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        ResultSet resultSet = connection.prepareStatement("SELECT Request_id FROM needer_request ORDER BY Request_id DESC LIMIT 1").executeQuery();
        boolean isExists = resultSet.next();

        if (isExists) {
            String old_id = resultSet.getString(1);
            String[] split = old_id.split("R");
            int id = Integer.parseInt(split[1]);
            id++;
            if (id < 10) {
                txtNeeReq.setText("R00" + id);
            } else if (id < 100) {
                txtNeeReq.setText("R0" + id);
            } else {
                txtNeeReq.setText("R" + id);
            }
        } else {
            txtNeeReq.setText("R001");
        }
    }

    private void autoGenerateBloodBagId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        ResultSet resultSet = connection.prepareStatement("SELECT BloodBag_id FROM blood_inventory ORDER BY BloodbBag_id DESC LIMIT 1").executeQuery();
        boolean isExists = resultSet.next();

        if (isExists) {
            String old_id = resultSet.getString(1);
            String[] split = old_id.split("B");
            int id = Integer.parseInt(split[1]);
            id++;
            if (id < 10) {
                txtBloodBagid.setText("B00" + id);
            } else if (id < 100) {
                txtBloodBagid.setText("B0" + id);
            } else {
                txtBloodBagid.setText("B" + id);
            }
        } else {
            txtBloodBagid.setText("B001");
        }
    }
}


