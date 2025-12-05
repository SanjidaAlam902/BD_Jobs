package com.example.bd_jobs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CC_UpdateCandidateProfileController {

    @FXML private TextField CandidateNameTF;
    @FXML private TextField CandidateEmailTF;
    @FXML private TextField CandidateContactTF;
    @FXML private TextField CandidateCareerObjectiveTF;

    @FXML private TableView<Candidate> UpdateCandidateProfileTV;
    @FXML private TableColumn<Candidate, String> CandidateNameTC;
    @FXML private TableColumn<Candidate, String> CandidateEmailTC;
    @FXML private TableColumn<Candidate, String> CandidateContactTC;
    @FXML private TableColumn<Candidate, String> CandidateCareerObjectiveTC;

    private File selectedCV;
    private ArrayList<Candidate> candidateList;

    private static final String DATA_FILE = "CandidateProfileInfo.txt";

    @FXML
    public void initialize() {
        candidateList = new ArrayList<>();

        CandidateNameTC.setCellValueFactory(new PropertyValueFactory<>("name"));
        CandidateEmailTC.setCellValueFactory(new PropertyValueFactory<>("email"));
        CandidateContactTC.setCellValueFactory(new PropertyValueFactory<>("contact"));
        CandidateCareerObjectiveTC.setCellValueFactory(new PropertyValueFactory<>("careerObjective"));

        loadExistingProfile();
    }

    private void loadExistingProfile() {
        try {
            File f = new File(DATA_FILE);
            if (!f.exists()) return;

            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;


                String[] tokens = line.split(",", -1); // -1 keeps empty trailing tokens
                if (tokens.length < 5) continue; // skip malformed lines

                Candidate cand = new Candidate(
                        tokens[0],
                        tokens[1],
                        tokens[2],
                        tokens[3],
                        tokens[4]
                );

                UpdateCandidateProfileTV.getItems().add(cand);
                candidateList.add(cand);
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void OnClickSaveProfile(ActionEvent actionEvent) {
        String name = CandidateNameTF.getText().trim();
        String email = CandidateEmailTF.getText().trim();
        String contact = CandidateContactTF.getText().trim();
        String careerObjective = CandidateCareerObjectiveTF.getText().trim();

        if (name.isBlank() || email.isBlank() || contact.isBlank() || careerObjective.isBlank() || selectedCV == null) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Profile Update Failed!");
            a.setContentText("Please fill all fields and upload a CV file.");
            a.showAndWait();
            return;
        }

        Candidate candidate = new Candidate(
                name,
                email,
                contact,
                careerObjective,
                selectedCV.getName()
        );

        candidateList.add(candidate);
        UpdateCandidateProfileTV.getItems().add(candidate);

        saveCandidateToFile(candidate);

        Alert success = new Alert(Alert.AlertType.INFORMATION);
        success.setHeaderText("Success!");
        success.setContentText("Candidate profile saved successfully.");
        success.showAndWait();

        clearFields();
    }

    @FXML
    public void OnClickFileChooser(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Select CV File");

        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF Files", "*.pdf"),
                new FileChooser.ExtensionFilter("Word Files", "*.docx"),
                new FileChooser.ExtensionFilter("All Files", ".")
        );

        Window owner = null;
        if (actionEvent != null && actionEvent.getSource() instanceof javafx.scene.Node) {
            owner = ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        }

        File file = fc.showOpenDialog(owner);
        if (file != null) {
            selectedCV = file;
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("File Selected");
            a.setContentText("Selected CV: " + file.getName());
            a.showAndWait();
        }
    }

    private void saveCandidateToFile(Candidate candidate) {
        try (FileWriter fw = new FileWriter(DATA_FILE, true)) {
            String line = String.join(",",
                    sanitizeCsvField(candidate.getName()),
                    sanitizeCsvField(candidate.getEmail()),
                    sanitizeCsvField(candidate.getContact()),
                    sanitizeCsvField(candidate.getCareerObjective()),
                    sanitizeCsvField(candidate.getCvFileName())
            );
            fw.write(line + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String sanitizeCsvField(String s) {
        if (s == null) return "";
        return s.replace("\n", " ").replace("\r", " ").replace(",", " ").trim();
    }

    private void clearFields() {
        CandidateNameTF.clear();
        CandidateEmailTF.clear();
        CandidateContactTF.clear();
        CandidateCareerObjectiveTF.clear();
        selectedCV = null;
    }
}