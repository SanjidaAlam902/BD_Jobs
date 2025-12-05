package com.example.bd_jobs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CC_TrackCandidateProgessController {

    @FXML
    private TextField NameTF;
    @FXML
    private TextField TmelineTF;
    @FXML
    private TextArea ProgressNoteTA;

    @FXML
    private TableView<Candidate> ProgressTV;
    @FXML
    private TableColumn<Candidate, String> NameTC;
    @FXML
    private TableColumn<Candidate, String> TimelineTC;
    @FXML
    private TableColumn<Candidate, String> NotesTC;

    private ArrayList<Candidate> progressList;

    @FXML
    public void initialize() {
        progressList = new ArrayList<>();

        NameTC.setCellValueFactory(new PropertyValueFactory<>("name"));
        TimelineTC.setCellValueFactory(new PropertyValueFactory<>("timeline"));
        NotesTC.setCellValueFactory(new PropertyValueFactory<>("progressNote"));

        loadExistingProgress();
    }

    @FXML
    public void OnClickSaveProgress(ActionEvent actionEvent) {
        String name = NameTF.getText().trim();
        String timeline = TmelineTF.getText().trim();
        String notes = ProgressNoteTA.getText().trim();

        if (name.isBlank() || timeline.isBlank() || notes.isBlank()) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Save Failed!");
            a.setContentText("Please fill in all fields before saving.");
            a.showAndWait();
            return;
        }

        Candidate progress = new Candidate(name, timeline, notes);
        progressList.add(progress);
        ProgressTV.getItems().add(progress);

        saveProgressToFile(progress);

        Alert success = new Alert(Alert.AlertType.INFORMATION);
        success.setHeaderText("Success!");
        success.setContentText("Candidate progress saved successfully!");
        success.showAndWait();

        clearFields();
    }

    private void saveProgressToFile(Candidate p) {
        try (FileWriter fw = new FileWriter("CandidateProgressInfo.txt", true)) {
            String line = String.join(",", p.getName(), p.getTimeline(), p.getProgressNote());
            fw.write(line + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        NameTF.clear();
        TmelineTF.clear();
        ProgressNoteTA.clear();
    }

    private void loadExistingProgress() {
        try {
            java.io.File f = new java.io.File("CandidateProgressInfo.txt");
            if (!f.exists()) return;

            java.util.Scanner sc = new java.util.Scanner(f);
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] tokens = line.split(",", -1);
                if (tokens.length < 3) continue;

                Candidate progress = new Candidate(
                        tokens[0], // name
                        tokens[1], // timeline
                        tokens[2]  // progressNote
                );

                progressList.add(progress);
                ProgressTV.getItems().add(progress);
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}