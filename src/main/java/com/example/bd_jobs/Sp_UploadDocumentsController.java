package com.example.bd_jobs;

import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Sp_UploadDocumentsController
{

    private File licenseFile;
    private File tinFile;
    private File idProofFile;

    private FileChooser fileChooser = new FileChooser();

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void OnClickIDProofFileChooser(ActionEvent actionEvent) {
        idProofFile = showFileChooser("Select ID Proof File");
        if (idProofFile != null) {
            System.out.println("Selected ID Proof File: " + idProofFile.getAbsolutePath());
        }
    }

    @javafx.fxml.FXML
    public void OnClickTINFileChooser(ActionEvent actionEvent) {
        tinFile = showFileChooser("Select TIN File");
        if (tinFile != null) {
            System.out.println("Selected TIN File: " + tinFile.getAbsolutePath());
        }
    }

    @javafx.fxml.FXML
    public void OnClickLicenseFileChooser(ActionEvent actionEvent) {
        licenseFile = showFileChooser("Select License File");
        if (licenseFile != null) {
            System.out.println("Selected License File: " + licenseFile.getAbsolutePath());
        }
    }

    @javafx.fxml.FXML
    public void OnClickUploadFiles(ActionEvent actionEvent) {
        if (licenseFile == null || tinFile == null || idProofFile == null) {
            System.out.println("Please select all files before uploading!");
            return;
        }

        Report doc = new Report(licenseFile, tinFile, idProofFile);
        saveDocumentsToFile(doc);

        System.out.println("Uploading files...");
        System.out.println("License: " + licenseFile.getAbsolutePath());
        System.out.println("TIN: " + tinFile.getAbsolutePath());
        System.out.println("ID Proof: " + idProofFile.getAbsolutePath());

        licenseFile = null;
        tinFile = null;
        idProofFile = null;

        System.out.println("Files uploaded successfully!");
    }

    private File showFileChooser(String title) {
        Stage stage = new Stage();
        fileChooser.setTitle(title);
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF Files", "*.pdf"),
                new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.jpeg"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        return fileChooser.showOpenDialog(stage);
    }

    private void saveDocumentsToFile(Report doc) {
        try {
            FileOutputStream fos;
            ObjectOutputStream oos;

            if (new File("UploadedDocuments.bin").exists()) {
                fos = new FileOutputStream("UploadedDocuments.bin", true);
                oos = new AppendableObjectOutputStream(fos); // Custom class to append objects
            } else {
                fos = new FileOutputStream("UploadedDocuments.bin");
                oos = new ObjectOutputStream(fos);
            }

            oos.writeObject(doc);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}