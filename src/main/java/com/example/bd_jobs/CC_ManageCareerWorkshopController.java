package com.example.bd_jobs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CC_ManageCareerWorkshopController {

    @FXML
    private TextField WorkshopTitleTF;
    @FXML
    private TextField CapacityTF;
    @FXML
    private TextArea DescriptionTA;
    @FXML
    private DatePicker WorkshopDP;

    @FXML
    private TableView<CareerWorkshop> CareerWorkshopTV;
    @FXML
    private TableColumn<CareerWorkshop, String> WorkshopTitleTC;
    @FXML
    private TableColumn<CareerWorkshop, String> DateTC;
    @FXML
    private TableColumn<CareerWorkshop, Integer> CapacityTC;
    @FXML
    private TableColumn<CareerWorkshop, String> DescriptionTC;

    private ArrayList<CareerWorkshop> workshopList;

    @FXML
    public void initialize() {
        workshopList = new ArrayList<>();

        WorkshopTitleTC.setCellValueFactory(new PropertyValueFactory<>("title"));
        DateTC.setCellValueFactory(new PropertyValueFactory<>("date"));
        CapacityTC.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        DescriptionTC.setCellValueFactory(new PropertyValueFactory<>("description"));

        loadExistingWorkshops();
    }

    private void loadExistingWorkshops() {
        try {
            File f = new File("CareerWorkshopInfo.txt");
            if (!f.exists()) return;

            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] tokens = line.split(",", -1);
                if (tokens.length < 4) continue;

                String title = tokens[0];
                String date = tokens[1];
                int capacity = Integer.parseInt(tokens[2]);
                String description = tokens[3];

                CareerWorkshop workshop = new CareerWorkshop(title, date, capacity, description);
                workshopList.add(workshop);
                CareerWorkshopTV.getItems().add(workshop);
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void OnClickAddWorkshop(ActionEvent actionEvent) {
        String title = WorkshopTitleTF.getText().trim();
        String capacityText = CapacityTF.getText().trim();
        String description = DescriptionTA.getText().trim();
        String date = (WorkshopDP.getValue() != null) ? WorkshopDP.getValue().toString() : "";

        if (title.isBlank() || capacityText.isBlank() || description.isBlank() || date.isBlank()) {
            showAlert(Alert.AlertType.ERROR, "Adding Failed!", "Please fill in all fields before adding the workshop.");
            return;
        }

        int capacity;
        try {
            capacity = Integer.parseInt(capacityText);
            if (capacity <= 0) {
                showAlert(Alert.AlertType.ERROR, "Invalid Capacity!", "Capacity must be a positive number.");
                return;
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input!", "Capacity must be a valid number.");
            return;
        }

        CareerWorkshop workshop = new CareerWorkshop(title, date, capacity, description);
        workshopList.add(workshop);
        CareerWorkshopTV.getItems().add(workshop);

        saveWorkshopToFile(workshop);

        showAlert(Alert.AlertType.INFORMATION, "Success!", "Workshop added successfully!");
        clearFields();
    }

    private void saveWorkshopToFile(CareerWorkshop workshop) {
        try (FileWriter fw = new FileWriter("CareerWorkshopInfo.txt", true)) {
            String line = String.join(",", workshop.getTitle(), workshop.getDate(), String.valueOf(workshop.getCapacity()), workshop.getDescription());
            fw.write(line + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        WorkshopTitleTF.clear();
        CapacityTF.clear();
        DescriptionTA.clear();
        WorkshopDP.setValue(null);
    }

    private void showAlert(Alert.AlertType type, String header, String content) {
        Alert a = new Alert(type);
        a.setHeaderText(header);
        a.setContentText(content);
        a.showAndWait();
    }
}