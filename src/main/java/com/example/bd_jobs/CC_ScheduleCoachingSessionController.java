package com.example.bd_jobs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class CC_ScheduleCoachingSessionController {

    @FXML
    private TextField NameTF;
    @FXML
    private TextField TopicTF;
    @FXML
    private TextField TimeTF;

    @FXML
    private DatePicker SessionDateDP;

    @FXML
    private ComboBox<String> TypeCB;

    @FXML
    private TableView<CoachingSession> CoachingSessionTV;
    @FXML
    private TableColumn<CoachingSession, String> DateTC;
    @FXML
    private TableColumn<CoachingSession, String> TimeTC;
    @FXML
    private TableColumn<CoachingSession, String> NameTC;
    @FXML
    private TableColumn<CoachingSession, String> TypeTC;
    @FXML
    private TableColumn<CoachingSession, String> StatusTC;

    private ArrayList<CoachingSession> sessionList;

    @FXML
    public void initialize() {
        sessionList = new ArrayList<>();

        TypeCB.getItems().addAll(
                "Interview Preparation",
                "CV Review",
                "Career Counseling",
                "Mock Interview",
                "Skill Development"
        );

        DateTC.setCellValueFactory(new PropertyValueFactory<>("date"));
        TimeTC.setCellValueFactory(new PropertyValueFactory<>("time"));
        NameTC.setCellValueFactory(new PropertyValueFactory<>("name"));
        TypeTC.setCellValueFactory(new PropertyValueFactory<>("type"));
        StatusTC.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadExistingSession();
    }

    private void loadExistingSession() {
        try {
            File f = new File("SessionInfo.txt");
            if (!f.exists()) return;

            Scanner sc = new Scanner(f);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] tokens = line.split(",");

                if (tokens.length < 5) continue;

                CoachingSession session = new CoachingSession(
                        tokens[0], // date
                        tokens[1], // time
                        tokens[2], // name
                        tokens[3], // type
                        tokens[4]  // status
                );

                CoachingSessionTV.getItems().add(session);
                sessionList.add(session);
            }

            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void OnClickConfirmSession(ActionEvent actionEvent) {

        String name = NameTF.getText();
        String type = TypeCB.getValue();
        String time = TimeTF.getText();
        String topic = TopicTF.getText(); // optional if used
        LocalDate dateValue = SessionDateDP.getValue();
        String date = (dateValue != null) ? dateValue.toString() : "";

        if (name.isBlank() || topic.isBlank() || time.isBlank() ||
                type == null || date.isBlank()) {

            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Scheduling Failed!");
            a.setContentText("Please fill in all fields before confirming the session.");
            a.showAndWait();
            return;
        }

        CoachingSession session = new CoachingSession(
                date,
                time,
                name,
                type,
                "Scheduled"
        );

        sessionList.add(session);
        CoachingSessionTV.getItems().add(session);

        saveSessionToFile(session);

        Alert success = new Alert(Alert.AlertType.INFORMATION);
        success.setHeaderText("Success!");
        success.setContentText("Coaching session scheduled successfully!");
        success.showAndWait();

        clearFields();
    }

    private void saveSessionToFile(CoachingSession session) {
        try {
            FileWriter fw = new FileWriter("SessionInfo.txt", true);
            fw.write(session.getDate() + "," + session.getTime() + "," +
                    session.getName() + "," + session.getType() + "," +
                    session.getStatus() + "\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        NameTF.clear();
        TopicTF.clear();
        TimeTF.clear();
        TypeCB.getSelectionModel().clearSelection();
        SessionDateDP.setValue(null);
    }
}