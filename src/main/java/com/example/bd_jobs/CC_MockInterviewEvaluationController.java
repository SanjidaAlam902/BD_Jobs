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

public class CC_MockInterviewEvaluationController {

    @FXML
    private TextField NameTF;
    @FXML
    private TextField CommScoreTF;
    @FXML
    private TextField TechScoreTF;
    @FXML
    private TextField PSScoreTF;
    @FXML
    private DatePicker SessionDP;

    @FXML
    private TableView<Candidate> MockInterviewEvaluationTV;
    @FXML
    private TableColumn<Candidate, String> NameTC;
    @FXML
    private TableColumn<Candidate, String> DateTC;
    @FXML
    private TableColumn<Candidate, Integer> CommScoreTC;
    @FXML
    private TableColumn<Candidate, Integer> TechScoreTC;
    @FXML
    private TableColumn<Candidate, Integer> PSScoreTC;
    @FXML
    private TableColumn<Candidate, Integer> TotalScoreTC;

    private ArrayList<Candidate> interevaluationList;

    @FXML
    public void initialize() {
        interevaluationList = new ArrayList<>();

        NameTC.setCellValueFactory(new PropertyValueFactory<>("name"));
        DateTC.setCellValueFactory(new PropertyValueFactory<>("date"));
        CommScoreTC.setCellValueFactory(new PropertyValueFactory<>("communicationScore"));
        TechScoreTC.setCellValueFactory(new PropertyValueFactory<>("technicalScore"));
        PSScoreTC.setCellValueFactory(new PropertyValueFactory<>("problemSolvingScore"));
        TotalScoreTC.setCellValueFactory(new PropertyValueFactory<>("totalScore"));

        loadExistingInterview();
    }

    private void loadExistingInterview() {
        try {
            File f = new File("MockInterviewEvaluationInfo.txt");
            if (!f.exists()) return;

            Scanner sc = new Scanner(f);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] tokens = line.split(",", -1);
                if (tokens.length < 5) continue;

                Candidate inter = new Candidate(
                        tokens[0],                     // name
                        tokens[1],                     // date
                        Integer.parseInt(tokens[2]),   // commScore
                        Integer.parseInt(tokens[3]),   // techScore
                        Integer.parseInt(tokens[4])    // psScore
                );

                interevaluationList.add(inter);
                MockInterviewEvaluationTV.getItems().add(inter);
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void OnClickSaveEvaluation(ActionEvent actionEvent) {
        String name = NameTF.getText().trim();
        String commScoreText = CommScoreTF.getText().trim();
        String techScoreText = TechScoreTF.getText().trim();
        String psScoreText = PSScoreTF.getText().trim();
        String date = (SessionDP.getValue() != null) ? SessionDP.getValue().toString() : "";

        if (name.isBlank() || commScoreText.isBlank() || techScoreText.isBlank() || psScoreText.isBlank() || date.isBlank()) {
            showAlert(Alert.AlertType.ERROR, "Save Failed!", "Please fill in all fields before saving evaluation.");
            return;
        }

        int commScore, techScore, psScore;

        try {
            commScore = Integer.parseInt(commScoreText);
            techScore = Integer.parseInt(techScoreText);
            psScore = Integer.parseInt(psScoreText);

            if (commScore < 0 || techScore < 0 || psScore < 0) {
                showAlert(Alert.AlertType.ERROR, "Invalid Scores!", "Scores must be non-negative.");
                return;
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input!", "Scores must be numeric values.");
            return;
        }

        Candidate evaluation = new Candidate(name, date, commScore, techScore, psScore);

        interevaluationList.add(evaluation);
        MockInterviewEvaluationTV.getItems().add(evaluation);

        saveInterviewToFile(evaluation);

        showAlert(Alert.AlertType.INFORMATION, "Success!", "Evaluation saved successfully!");
        clearFields();
    }

    private void saveInterviewToFile(Candidate inter) {
        try (FileWriter fw = new FileWriter("MockInterviewEvaluationInfo.txt", true)) {
            String line = String.join(",",
                    inter.getName(),
                    inter.getDate(),
                    String.valueOf(inter.getCommunicationScore()),
                    String.valueOf(inter.getTechnicalScore()),
                    String.valueOf(inter.getProblemSolvingScore())
            );
            fw.write(line + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        NameTF.clear();
        CommScoreTF.clear();
        TechScoreTF.clear();
        PSScoreTF.clear();
        SessionDP.setValue(null);
    }

    private void showAlert(Alert.AlertType type, String header, String content) {
        Alert a = new Alert(type);
        a.setHeaderText(header);
        a.setContentText(content);
        a.showAndWait();
    }
}