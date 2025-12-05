package com.example.bd_jobs.controllers;

import com.example.bd_jobs.Candidate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class ManageCandidatesController {

    @FXML private TextField searchField;
    @FXML private TableView<Candidate> candidateTable;
    @FXML private TableColumn<Candidate, String> colName;
    @FXML private TableColumn<Candidate, String> colEmail;
    @FXML private TableColumn<Candidate, String> colStatus;

    private final ObservableList<Candidate> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        data.addAll(
                new Candidate("Alice Ahmed","alice@gmail.com","Selected"),
                new Candidate("Rahim Khan","rahim@gmail.com","Applied"),
                new Candidate("Sadia Islam","sadia@gmail.com","Rejected")
        );

        candidateTable.setItems(data);
    }

    @FXML
    public void onSearch(ActionEvent e) {
        String text = searchField.getText().toLowerCase();
        if (text.isEmpty()) {
            candidateTable.setItems(data);
            return;
        }

        ObservableList<Candidate> filtered = FXCollections.observableArrayList();
        for (Candidate c : data) {
            if (c.getName().toLowerCase().contains(text)) {
                filtered.add(c);
            }
        }
        candidateTable.setItems(filtered);
    }

    @FXML
    public void onRefresh(ActionEvent e) {
        candidateTable.setItems(data);
        searchField.clear();
    }

    @FXML
    public void onAdd(ActionEvent e) {
        new Alert(Alert.AlertType.INFORMATION, "Add Candidate clicked").showAndWait();
    }

    @FXML
    public void onEdit(ActionEvent e) {
        new Alert(Alert.AlertType.INFORMATION, "Edit Candidate clicked").showAndWait();
    }

    @FXML
    public void onDelete(ActionEvent e) {
        new Alert(Alert.AlertType.INFORMATION, "Delete Candidate clicked").showAndWait();
    }

    @FXML
    public void onBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(
                "/com/example/bd_jobs/Recruiter/recruiter-dashboard.fxml")));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

}
