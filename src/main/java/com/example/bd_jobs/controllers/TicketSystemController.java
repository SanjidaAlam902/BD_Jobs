package com.example.bd_jobs.controllers;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.awt.*;
import java.awt.event.ActionEvent;

public class TicketSystemController {
    @javafx.fxml.FXML
    private TableView TicketTable;
    @javafx.fxml.FXML
    private TextField searchField;
    @javafx.fxml.FXML
    private TableColumn colId;
    @javafx.fxml.FXML
    private TableColumn colTitle;

    @javafx.fxml.FXML
    public void onSearch(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void onNew(ActionEvent actionEvent) {
    }
}
