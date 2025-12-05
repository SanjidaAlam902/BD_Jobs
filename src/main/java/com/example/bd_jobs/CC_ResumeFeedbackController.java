package com.example.bd_jobs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class CC_ResumeFeedbackController {

    @FXML
    private TextField NameTF;
    @FXML
    private TextArea CommentTA;

    @FXML
    private TableView<Candidate> ResumeFeedbackTV;
    @FXML
    private TableColumn<Candidate, String> NameTC;
    @FXML
    private TableColumn<Candidate, String> DateTC;
    @FXML
    private TableColumn<Candidate, String> FileNameTC;
    @FXML
    private TableColumn<Candidate, String> CommentTC;

    private ArrayList<Candidate> feedbackList;

    private String uploadedFileName = "Candidate_CV.pdf";

    @FXML
    public void initialize() {

        feedbackList = new ArrayList<>();

        NameTC.setCellValueFactory(new PropertyValueFactory<>("name"));
        DateTC.setCellValueFactory(new PropertyValueFactory<>("feedbackDate"));
        FileNameTC.setCellValueFactory(new PropertyValueFactory<>("cvFileName"));
        CommentTC.setCellValueFactory(new PropertyValueFactory<>("feedbackComment"));

        loadExistingResumeFeedback();
    }

    private void loadExistingResumeFeedback() {
        try {
            File f = new File("ResumeFeedbackInfo.txt"); // Use same file as saving
            if (!f.exists()) return;

            Scanner sc = new Scanner(f);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] tokens = line.split(",");

                if (tokens.length < 4) continue;

                Candidate candidate = new Candidate(
                        tokens[0], // name
                        tokens[1], // feedbackDate
                        tokens[2], // cvFileName
                        tokens[3]  // feedbackComment
                );

                ResumeFeedbackTV.getItems().add(candidate);
                feedbackList.add(candidate);
            }

            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void OnClickSaveFeedback(ActionEvent actionEvent) {

        String name = NameTF.getText();
        String comment = CommentTA.getText();

        if (name.isBlank() || comment.isBlank()) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Feedback Save Failed!");
            a.setContentText("Please fill in all fields before saving feedback.");
            a.showAndWait();
            return;
        }

        String date = LocalDate.now().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd")
        );

        Candidate candidate = new Candidate(
                name,
                date,
                uploadedFileName,
                comment
        );

        feedbackList.add(candidate);
        ResumeFeedbackTV.getItems().add(candidate);

        saveResumeFeedbackToFile(candidate);

        Alert success = new Alert(Alert.AlertType.INFORMATION);
        success.setHeaderText("Success!");
        success.setContentText("Resume feedback saved successfully!");
        success.showAndWait();

        clearFields();
    }

    private void saveResumeFeedbackToFile(Candidate c) {
        try {
            FileWriter fw = new FileWriter("ResumeFeedbackInfo.txt", true);
            fw.write(c.getName() + "," + c.getFeedbackDate() + "," +
                    c.getCvFileName() + "," + c.getFeedbackComment() + "\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        NameTF.clear();
        CommentTA.clear();
    }
}