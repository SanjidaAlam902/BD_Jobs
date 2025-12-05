package com.example.bd_jobs;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.example.bd_jobs.SponsorshipPackage;

public class Sp_ManageCompanyProfileController
{
    @javafx.fxml.FXML
    private TextField CompanyNameTF;
    @javafx.fxml.FXML
    private TextField ContactTF;
    @javafx.fxml.FXML
    private TextArea DescriptionTA;
    @javafx.fxml.FXML
    private TextField WebsiteTF;

    @javafx.fxml.FXML
    public void initialize() {
    }

    private String selectedLogoPath = null;

    private SponsorshipPackage companyProfile = new SponsorshipPackage();

    @javafx.fxml.FXML
    public void OnClickChooseLogo(ActionEvent actionEvent) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select Company Logo");
        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );

        File file = chooser.showOpenDialog(null);
        if (file != null) {
            selectedLogoPath = file.getAbsolutePath();
            showAlert("Logo Selected", "Logo chosen:\n" + selectedLogoPath);
        }
    }

    @javafx.fxml.FXML
    public void OnClickSaveCompanyProfile(ActionEvent actionEvent) {
        String companyName = CompanyNameTF.getText();
        String website = WebsiteTF.getText();
        String contact = ContactTF.getText();
        String description = DescriptionTA.getText();

        if (companyName.isEmpty() || website.isEmpty() || contact.isEmpty() || description.isEmpty() || selectedLogoPath == null) {
            showAlert("Error", "Please fill all fields and choose a logo file.");
            return;
        }

        companyProfile.setCompanyName(companyName);
        companyProfile.setWebsite(website);
        companyProfile.setContact(contact);
        companyProfile.setDescription(description);
        companyProfile.setLogoPath(selectedLogoPath);



        File f = new File("CompanyInfo.bin");
        try{
            FileOutputStream fos ;
            ObjectOutputStream oos ;
            if(f.exists()){
                fos = new FileOutputStream(f, true);
                oos = new AppendableObjectOutputStream(fos);
            }
            else{
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
            }

            oos.writeObject(companyProfile);
            oos.close();
        }
        catch (IOException e){
            //
        }

        showAlert("Success", "Company profile saved successfully!");

        CompanyNameTF.clear();
        WebsiteTF.clear();
        ContactTF.clear();
        DescriptionTA.clear();
        selectedLogoPath = null;
    }

    private void showAlert(String title, String message) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText(null);
        a.setTitle(title);
        a.setContentText(message);
        a.showAndWait();
    }
}