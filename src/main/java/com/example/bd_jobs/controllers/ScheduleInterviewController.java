package com.example.bd_jobs.controllers;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.awt.*;
import java.awt.event.ActionEvent;

public class ScheduleInterviewController {
    @javafx.fxml.FXML
    private TableView interviewTable;
    @javafx.fxml.FXML
    private TableColumn colTime;
    @javafx.fxml.FXML
    private TextField timeField;
    @javafx.fxml.FXML
    private TableColumn colCandidate;
    @javafx.fxml.FXML
    private DatePicker interviewDate;
    @javafx.fxml.FXML
    private TableColumn colDate;
    @javafx.fxml.FXML
    private ComboBox candidateDropdown;

    @javafx.fxml.FXML
    public void onScedule(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void onBack(ActionEvent actionEvent) {
    }
}
