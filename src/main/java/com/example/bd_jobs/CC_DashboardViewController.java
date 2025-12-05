package com.example.bd_jobs;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CC_DashboardViewController
{
    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void OnClickUpdateCandidateProfile(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CC_UpdateCandidateProfile.fxml"));
        Scene scene = new Scene((fxmlLoader.load()));
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void OnClickConductMockInterview(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CC_MockInterviewEvaluation.fxml"));
        Scene scene = new Scene((fxmlLoader.load()));
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void OnClickTrackCandidateProgress(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CC_TrackCandidateProgess.fxml"));
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
    public void OnClickEvaluteAndFinalReport(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CC_FinalEvaluation.fxml"));
        Scene scene = new Scene((fxmlLoader.load()));
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void OnClickScheduleCoachingSessions(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CC_ScheduleCoachingSession.fxml"));
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
    public void OnClickRecommendDevelopmentPlan(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CC_JobRecommendationPlan.fxml"));
        Scene scene = new Scene((fxmlLoader.load()));
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void OnClickProvideResumeFeedback(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CC_ResumeFeedback.fxml"));
        Scene scene = new Scene((fxmlLoader.load()));
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void OnClickManageCareerWorkshops(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CC_ManageCareerWorkshop.fxml"));
        Scene scene = new Scene((fxmlLoader.load()));
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}