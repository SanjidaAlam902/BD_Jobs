package com.example.bd_jobs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.util.ArrayList;

public class Sp_CreateSponsorshipPackageController {

    @FXML private TableView<SponsorshipPackage> CreateSponsorshipPackageTV;
    @FXML private TableColumn<SponsorshipPackage, String> NameTC;
    @FXML private TableColumn<SponsorshipPackage, String> TypeTC;
    @FXML private TableColumn<SponsorshipPackage, String> DurationTC;
    @FXML private TableColumn<SponsorshipPackage, Double> CostTC;

    @FXML private TextField NameTF;
    @FXML private TextField TypeTF;
    @FXML private TextField DurationTF;
    @FXML private TextField CostTF;
    @FXML private TextArea BenefitsTA;

    private ArrayList<SponsorshipPackage> packageList = new ArrayList<>();

    @FXML
    public void initialize() {

        NameTC.setCellValueFactory(new PropertyValueFactory<>("name"));
        TypeTC.setCellValueFactory(new PropertyValueFactory<>("type"));
        DurationTC.setCellValueFactory(new PropertyValueFactory<>("duration"));
        CostTC.setCellValueFactory(new PropertyValueFactory<>("cost"));

        loadPackagesFromTextFile();
    }

    private void loadPackagesFromTextFile() {
        File f = new File("SponsorshipPackageInfo.txt");
        if (!f.exists()) return;
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length < 5) continue;
                SponsorshipPackage sp = new SponsorshipPackage(
                        tokens[0],
                        tokens[1],
                        tokens[2],
                        Double.parseDouble(tokens[3]),
                        tokens[4]
                );
                packageList.add(sp);
                CreateSponsorshipPackageTV.getItems().add(sp);
            }
            br.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void OnClickSavePackage(ActionEvent actionEvent) {

        String name = NameTF.getText();
        String type = TypeTF.getText();
        String duration = DurationTF.getText();
        String costText = CostTF.getText();
        String benefits = BenefitsTA.getText();

        if (name.isBlank() || type.isBlank() || duration.isBlank() ||
                costText.isBlank() || benefits.isBlank()) {

            showAlert(Alert.AlertType.ERROR, "Please fill all fields.");
            return;
        }

        double cost;
        try {
            cost = Double.parseDouble(costText);
            if (cost < 0) throw new NumberFormatException();
        }
        catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid cost value.");
            return;
        }

        SponsorshipPackage sp = new SponsorshipPackage(name, type, duration, cost, benefits);
        packageList.add(sp);
        CreateSponsorshipPackageTV.getItems().add(sp);

        savePackageToTextFile(sp);

        showAlert(Alert.AlertType.INFORMATION, "Package saved successfully!");
        clearFields();
    }

    private void savePackageToTextFile(SponsorshipPackage sp) {

        try {
            FileWriter fw = new FileWriter("SponsorshipPackageInfo.txt", true);
            fw.write(
                    sp.getName() + "," +
                            sp.getType() + "," +
                            sp.getDuration() + "," +
                            sp.getCost() + "," +
                            sp.getBenefits() + "\n"
            );
            fw.close();

        }
        catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error saving package.");
        }
    }

    private void clearFields() {
        NameTF.clear();
        TypeTF.clear();
        DurationTF.clear();
        CostTF.clear();
        BenefitsTA.clear();
    }

    private void showAlert(Alert.AlertType type, String msg) {
        Alert alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}