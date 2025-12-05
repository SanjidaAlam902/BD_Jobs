package com.example.bd_jobs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class Sp_ViewSponsorshipAnalyticsController
{
    @javafx.fxml.FXML
    private ComboBox<String> CampaignCB;
    @javafx.fxml.FXML
    private DatePicker EndDP;
    @javafx.fxml.FXML
    private TableView<Report> AnalyticsSummaryTV;
    @javafx.fxml.FXML
    private TableColumn<Report, Integer> ClicksTC;
    @javafx.fxml.FXML
    private TableColumn<Report, Integer> ImpressionsTC;
    @javafx.fxml.FXML
    private TableColumn<Report, Integer> ApplicationsTC;
    @javafx.fxml.FXML
    private DatePicker StartDP;

    private ObservableList<Report> reportList;

    @javafx.fxml.FXML
    public void initialize() {
        ImpressionsTC.setCellValueFactory(new PropertyValueFactory<>("impressions"));
        ClicksTC.setCellValueFactory(new PropertyValueFactory<>("clicks"));
        ApplicationsTC.setCellValueFactory(new PropertyValueFactory<>("applications"));

        CampaignCB.setItems(FXCollections.observableArrayList(
                "Campus Hiring 2025",
                "Internship Drive",
                "Tech Job Fair"
        ));

        reportList = FXCollections.observableArrayList();
        AnalyticsSummaryTV.setItems(reportList);
    }

    @javafx.fxml.FXML
    public void OnClickLoadAnalytics(ActionEvent actionEvent) {
        String selectedCampaign = CampaignCB.getValue();
        LocalDate startDate = StartDP.getValue();
        LocalDate endDate = EndDP.getValue();

        if (selectedCampaign == null || startDate == null || endDate == null) {
            System.out.println("Please select campaign and date range!");
            return;
        }

        reportList.clear();

        reportList.add(new Report(1200, 300, 45));
        reportList.add(new Report(800, 120, 20));
        reportList.add(new Report(1500, 450, 60));

        System.out.println("Analytics loaded for campaign: " + selectedCampaign
                + " from " + startDate + " to " + endDate);
    }
}