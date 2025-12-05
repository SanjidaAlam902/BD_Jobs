package com.example.bd_jobs;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CC_FinalEvaluationController
{
    @javafx.fxml.FXML
    private TextField RatingTF;
    @javafx.fxml.FXML
    private TableView<Candidate> FinalEvaluationTV;
    @javafx.fxml.FXML
    private TableColumn<Candidate, String> StatusTC;
    @javafx.fxml.FXML
    private TextField ReadinessStatusTF;
    @javafx.fxml.FXML
    private TableColumn<Candidate, String> RatingTC;
    @javafx.fxml.FXML
    private TextArea FinalRemarkTA;
    @javafx.fxml.FXML
    private TableColumn<Candidate, String> NameTC;
    @javafx.fxml.FXML
    private TextField NameTF;
    @javafx.fxml.FXML
    private TableColumn<Candidate, String> FinalRemarkTC;

    private ArrayList<Candidate> evaluationList;

    @javafx.fxml.FXML
    public void initialize() {
        evaluationList = new ArrayList<>();

        NameTC.setCellValueFactory(new PropertyValueFactory<>("name"));
        RatingTC.setCellValueFactory(new PropertyValueFactory<>("ratingString"));
        StatusTC.setCellValueFactory(new PropertyValueFactory<>("readinessStatus"));
        FinalRemarkTC.setCellValueFactory(new PropertyValueFactory<>("finalRemarks"));

        loadExistingEvaluations();
    }

    private void loadExistingEvaluations() {
        try {
            File f = new File("EvaluationInfo.txt");
            if (!f.exists()) return;

            Scanner sc = new Scanner(f);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] tokens = line.split(",");

                if (tokens.length < 4) continue;

                Candidate eva = new Candidate(
                        tokens[0],
                        Integer.parseInt(tokens[1]),
                        tokens[2],
                        tokens[3]
                );

                FinalEvaluationTV.getItems().add(eva);
                evaluationList.add(eva);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void OnClickSubmitEvaluationReport(ActionEvent actionEvent) {
        String name = NameTF.getText();
        String ratingText = RatingTF.getText();
        String readiness = ReadinessStatusTF.getText();
        String remark = FinalRemarkTA.getText();

        if (name.isBlank() || ratingText.isBlank() || readiness.isBlank() || remark.isBlank()) {
            showError("Submission Failed!", "All fields must be filled before submitting.");
            return;
        }

        int rating;
        try {
            rating = Integer.parseInt(ratingText);
            if (rating < 1 || rating > 10) {
                showError("Invalid Rating!", "Rating must be between 1 and 10.");
                return;
            }
        } catch (NumberFormatException e) {
            showError("Invalid Input!", "Rating must be a numeric value between 1 and 10.");
            return;
        }

        Candidate c = new Candidate(name, rating, readiness, remark);

        evaluationList.add(c);
        FinalEvaluationTV.getItems().add(c);

        saveEvaluationToFile(c);

        showSuccess("Success!", "Final evaluation submitted successfully.");
        clearFields();
    }

    private void saveEvaluationToFile(Candidate c) {
        try {
            FileWriter fw = new FileWriter("EvaluationInfo.txt", true);
            fw.write(c.getName() + "," + c.getRating() + "," +
                    c.getReadinessStatus() + "," + c.getFinalRemarks() + "\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        NameTF.clear();
        RatingTF.clear();
        ReadinessStatusTF.clear();
        FinalRemarkTA.clear();
    }

    private void showError(String header, String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setHeaderText(header);
        a.setContentText(msg);
        a.showAndWait();
    }

    private void showSuccess(String header, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText(header);
        a.setContentText(msg);
        a.showAndWait();
    }
}