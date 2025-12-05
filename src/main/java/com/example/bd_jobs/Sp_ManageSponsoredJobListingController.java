package com.example.bd_jobs;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Sp_ManageSponsoredJobListingController
{
    @javafx.fxml.FXML
    private TextField BudgetTF;
    @javafx.fxml.FXML
    private TableView<SponsorshipPackage> SponJobListingTV;
    @javafx.fxml.FXML
    private TableColumn<SponsorshipPackage, String> BudgetTC;
    @javafx.fxml.FXML
    private TableColumn<SponsorshipPackage, String> CategoryTC;
    @javafx.fxml.FXML
    private TextField CategoryTF;
    @javafx.fxml.FXML
    private TextField JobTitleTF;
    @javafx.fxml.FXML
    private TableColumn<SponsorshipPackage, String> TitleTC;
    @javafx.fxml.FXML
    private TableColumn<SponsorshipPackage, String> DurationTC;
    @javafx.fxml.FXML
    private TextField DurationTF;

    private ArrayList<SponsorshipPackage> jobList;

    @javafx.fxml.FXML
    public void initialize() {
        jobList = new ArrayList<>();

        TitleTC.setCellValueFactory(new PropertyValueFactory<>("jobTitle"));
        CategoryTC.setCellValueFactory(new PropertyValueFactory<>("category"));
        DurationTC.setCellValueFactory(new PropertyValueFactory<>("duration"));
        BudgetTC.setCellValueFactory(new PropertyValueFactory<>("budget"));

        loadExistingJobListings();
    }

    private void loadExistingJobListings() {
        try {
            File file = new File("SponsoredJobListings.txt");
            if (!file.exists()) return;

            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] tokens = line.split(",");

                if (tokens.length < 4) continue;

                SponsorshipPackage job = new SponsorshipPackage(
                        tokens[0],
                        tokens[1],
                        tokens[2],
                        Double.parseDouble(tokens[3])
                );

                SponJobListingTV.getItems().add(job);
                jobList.add(job);
            }

            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void OnClickSaveJobListing(ActionEvent actionEvent) {
        String title = JobTitleTF.getText().trim();
        String category = CategoryTF.getText().trim();
        String duration = DurationTF.getText().trim();
        String budgetStr = BudgetTF.getText().trim();

        if (title.isEmpty() || category.isEmpty() || duration.isEmpty() || budgetStr.isEmpty()) {
            showAlert("Error", "Please fill all fields.");
            return;
        }

        double budget;

        try {
            budget = Double.parseDouble(budgetStr);
        } catch (NumberFormatException e) {
            showAlert("Error", "Budget must be a valid number.");
            return;
        }

        SponsorshipPackage job = new SponsorshipPackage(title, category, duration, budget);
        jobList.add(job);

        saveJobToFile(job);

        showAlert("Success", "Job listing saved!");

        JobTitleTF.clear();
        CategoryTF.clear();
        DurationTF.clear();
        BudgetTF.clear();
    }

    private void saveJobToFile(SponsorshipPackage job) {
        try {
            FileWriter fw = new FileWriter("SponsoredJobListings.txt", true);
            fw.write(job.getJobTitle() + "," +
                    job.getCategory() + "," +
                    job.getDuration() + "," +
                    job.getBudget() + "\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}