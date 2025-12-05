package com.example.bd_jobs.controllers; // package first

import com.example.bd_jobs.ReportItem;   // imports next
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;

public class ReportsAnalyticsController { // class declaration

    @FXML private TextField jobTitleFilter;
    @FXML private DatePicker dateFrom, dateTo;
    @FXML private TableView<ReportItem> reportTable;
    @FXML private TableColumn<ReportItem, String> colJob;
    @FXML private TableColumn<ReportItem, String> colName;
    @FXML private TableColumn<ReportItem, String> colStatus;
    @FXML private TableColumn<ReportItem, String> colDate;

    private final ObservableList<ReportItem> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colJob.setCellValueFactory(new PropertyValueFactory<>("jobTitle"));
        colName.setCellValueFactory(new PropertyValueFactory<>("candidateName"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        data.addAll(
                new ReportItem("Software Engineer","Alice Ahmed","Selected","2025-11-01"),
                new ReportItem("QA Engineer","Rahim Khan","Rejected","2025-11-05"),
                new ReportItem("Data Analyst","Sadia Islam","On Hold","2025-11-09")
        );

        reportTable.setItems(data);

    }

    @FXML public void onSearch(ActionEvent e) { /* implement filter */ }

    @FXML public void onExport(ActionEvent e) {
        new Alert(Alert.AlertType.INFORMATION,"Export not implemented").showAndWait();
    }

    @FXML public void onBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/bd_jobs/Recruiter/recruiter-dashboard.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}
