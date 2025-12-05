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

public class CC_JobRecommendationPlanController {

    @FXML
    private TextField NameTF;
    @FXML
    private TextField RolesTF;
    @FXML
    private TextField TrainingTF;

    @FXML
    private TableView<CareerWorkshop> JobRecommendationPlanTV;
    @FXML
    private TableColumn<CareerWorkshop, String> NameTC;
    @FXML
    private TableColumn<CareerWorkshop, String> RoleTC;
    @FXML
    private TableColumn<CareerWorkshop, String> TrainingTC;

    private ArrayList<CareerWorkshop> recommendationList;

    @FXML
    public void initialize() {
        recommendationList = new ArrayList<>();

        NameTC.setCellValueFactory(new PropertyValueFactory<>("candidateName"));
        RoleTC.setCellValueFactory(new PropertyValueFactory<>("recommendedRole"));
        TrainingTC.setCellValueFactory(new PropertyValueFactory<>("requiredTraining"));

        loadExistingRecommendations();
    }

    private void loadExistingRecommendations() {
        try {
            File f = new File("RecommendationInfo.txt");
            if (!f.exists()) return;

            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] tokens = line.split(",", -1); // keep empty trailing fields
                if (tokens.length < 3) continue;

                CareerWorkshop recommendation = new CareerWorkshop(
                        tokens[0],
                        tokens[1],
                        tokens[2]
                );

                recommendationList.add(recommendation);
                JobRecommendationPlanTV.getItems().add(recommendation);
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void OnClickSaveRecommendation(ActionEvent actionEvent) {
        String name = NameTF.getText().trim();
        String role = RolesTF.getText().trim();
        String training = TrainingTF.getText().trim();

        if (name.isBlank() || role.isBlank() || training.isBlank()) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Save Failed!");
            a.setContentText("Please fill all fields before saving the recommendation.");
            a.showAndWait();
            return;
        }

        CareerWorkshop recommendation = new CareerWorkshop(
                name,
                role,
                training
        );

        recommendationList.add(recommendation);
        JobRecommendationPlanTV.getItems().add(recommendation);

        saveRecommendationToFile(recommendation);

        Alert success = new Alert(Alert.AlertType.INFORMATION);
        success.setHeaderText("Success!");
        success.setContentText("Job recommendation saved successfully!");
        success.showAndWait();

        clearFields();
    }

    private void saveRecommendationToFile(CareerWorkshop c) {
        try (FileWriter fw = new FileWriter("RecommendationInfo.txt", true)) {
            String line = String.join(",",
                    c.getCandidateName(),
                    c.getRecommendedRole(),
                    c.getRequiredTraining()
            );
            fw.write(line + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        NameTF.clear();
        RolesTF.clear();
        TrainingTF.clear();
    }
}