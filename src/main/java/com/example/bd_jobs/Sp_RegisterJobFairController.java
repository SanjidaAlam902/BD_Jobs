package com.example.bd_jobs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Sp_RegisterJobFairController
{
    @FXML private TextField CompanyNameTF;
    @FXML private TextField BoothSizeTF;
    @FXML private TextField ContactNumberTF;
    @FXML private TableColumn<JobFair, String> EventTC;
    @FXML private TableColumn<JobFair, String> StatusTC;
    @FXML private TableView<JobFair> RegisterJobFairTV;

    private ArrayList<JobFair> jobFairList;

    @FXML
    public void initialize() {
        jobFairList = new ArrayList<>();

        EventTC.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        StatusTC.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadExistingJobFair();
    }

    private void loadExistingJobFair() {
        try {
            File f = new File("JobFairInfo.txt");
            if (!f.exists()) return;
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] tokens = line.split(",");

                if (tokens.length < 4) continue;
                JobFair fair = new JobFair(
                        tokens[0],
                        tokens[1],
                        tokens[2],
                        tokens[3]
                );
                RegisterJobFairTV.getItems().add(fair);
                jobFairList.add(fair);
            }

            sc.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void OnClickRegisterJobFair(ActionEvent actionEvent) {
        String companyName = CompanyNameTF.getText().trim();
        String boothSize = BoothSizeTF.getText().trim();
        String contactNumber = ContactNumberTF.getText().trim();

        if (companyName.isEmpty() || boothSize.isEmpty() || contactNumber.isEmpty()) {
            System.out.println("All fields are required!");
            return;
        }

        JobFair jobFair = new JobFair(companyName, boothSize, contactNumber, "Pending");
        jobFairList.add(jobFair);
        RegisterJobFairTV.getItems().add(jobFair);

        saveJobFairToFile(jobFair);

        CompanyNameTF.clear();
        BoothSizeTF.clear();
        ContactNumberTF.clear();
    }

    private void saveJobFairToFile(JobFair jobFair) {
        try {
            FileWriter fw = new FileWriter("JobFairInfo.txt", true);
            fw.write(jobFair.getCompanyName() + "," +
                    jobFair.getBoothSize() + "," +
                    jobFair.getContactNumber() + "," +
                    jobFair.getStatus() + "\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}