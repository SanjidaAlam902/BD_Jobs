package com.example.bd_jobs;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SP_DashboardViewController
{
    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void OnClickManageCompanyProfile(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Sp_ManageCompanyProfile.fxml"));
        Scene scene = new Scene((fxmlLoader.load()));
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void OnClickRegisterForJobFairEvents(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Sp_RegisterJobFair.fxml"));
        Scene scene = new Scene((fxmlLoader.load()));
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void OnClickMakeSponsorshipPayments(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Sp_MakePayment.fxml"));
        Scene scene = new Scene((fxmlLoader.load()));
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void OnClickEvaluteOutcomeAndProvideFeedback(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Sp_CampaignFeedback.fxml"));
        Scene scene = new Scene((fxmlLoader.load()));
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void OnClickSponsoredJobListings(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Sp_ManageSponsoredJobListing.fxml"));
        Scene scene = new Scene((fxmlLoader.load()));
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void OnClickSignInNavigate(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LogIn.fxml"));
        Scene scene = new Scene((fxmlLoader.load()));
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void OnClickLogOutNavigate(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LogIn.fxml"));
        Scene scene = new Scene((fxmlLoader.load()));
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void OnClickViewSponsorshipAnalytics(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Sp_ViewSponsorshipAnalytics.fxml"));
        Scene scene = new Scene((fxmlLoader.load()));
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void OnClickUploadDocuments(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Sp_UploadDocuments.fxml"));
        Scene scene = new Scene((fxmlLoader.load()));
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void OnClickCreateSponsorshipPackages(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Sp_CreateSponsorshipPackage.fxml"));
        Scene scene = new Scene((fxmlLoader.load()));
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}