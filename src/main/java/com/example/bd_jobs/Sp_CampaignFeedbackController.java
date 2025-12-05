package com.example.bd_jobs;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Sp_CampaignFeedbackController
{
    @javafx.fxml.FXML
    private ComboBox<String> SelectCompletedCampaignCB;
    @javafx.fxml.FXML
    private TextField RatingTF;
    @javafx.fxml.FXML
    private TextArea ImprovementCommentsTA;

    @javafx.fxml.FXML
    public void initialize() {
        SelectCompletedCampaignCB.getItems().addAll(
                "Digital Marketing Campaign",
                "Brand Awareness Campaign",
                "Social Media Ads",
                "Job Fair Outreach",
                "Campus Promotion Program"
        );
    }

    @javafx.fxml.FXML
    public void OnClickSubmitFeedback(ActionEvent actionEvent) {
        String selectedCampaign = SelectCompletedCampaignCB.getValue();
        String ratingText = RatingTF.getText();
        String comments = ImprovementCommentsTA.getText();

        if (selectedCampaign == null || ratingText.isBlank() || comments.isBlank()) {
            showAlert(Alert.AlertType.ERROR, "Please fill all fields.");
            return;
        }

        int rating;

        try {
            rating = Integer.parseInt(ratingText);
            if (rating < 1 || rating > 10) {
                showAlert(Alert.AlertType.ERROR, "Rating must be between 1 and 10.");
                return;
            }
        }
        catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Rating must be a valid number between 1 and 10.");
            return;
        }

        CampaignFeedback feedback = new CampaignFeedback(selectedCampaign, rating, comments);

        try{
            File f = new File("CampaignFeedbackInfo.txt");
            FileWriter fw = new FileWriter(f, true);
            fw.write(feedback.getCampaignName() + "," + feedback.getRating() + "," + feedback.getComments() + "\n");
            fw.close();
        }
        catch(IOException e){
            //
        }

        showAlert(Alert.AlertType.INFORMATION, "Feedback submitted successfully!");

        clearFields();
    }

    private void clearFields() {
        SelectCompletedCampaignCB.getSelectionModel().clearSelection();
        RatingTF.clear();
        ImprovementCommentsTA.clear();
    }

    private void showAlert(Alert.AlertType type, String msg) {
        Alert alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}