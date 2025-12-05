package com.example.bd_jobs;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.ArrayList;

public class Sp_MakePaymentController
{
    @javafx.fxml.FXML
    private ComboBox<String> PaymentMethodCB;
    @javafx.fxml.FXML
    private TextField AmountTF;
    @javafx.fxml.FXML
    private TextField PendingPackageTF;
    @javafx.fxml.FXML
    private TextField BillingNameTF;

    private ArrayList<Payment> paymentList;

    @javafx.fxml.FXML
    public void initialize() {
        paymentList = new ArrayList<>();

        PaymentMethodCB.getItems().addAll("Bkash", "Nagad", "Rocket", "Visa Card", "MasterCard");
    }

    @javafx.fxml.FXML
    public void OnClickPayNow(ActionEvent actionEvent) {
        String packageName = PendingPackageTF.getText().trim();
        String billingName = BillingNameTF.getText().trim();
        String method = PaymentMethodCB.getValue();
        String amountText = AmountTF.getText().trim();

        if (packageName.isEmpty() || billingName.isEmpty() || method == null || amountText.isEmpty()) {
            showAlert("Error", "Please fill all fields!");
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(amountText);
        }
        catch (NumberFormatException e) {
            showAlert("Error", "Amount must be a valid number!");
            return;
        }

        Payment p = new Payment(packageName, billingName, method, amount);
        paymentList.add(p);

        savePaymentToBinary(p);

        showAlert("Success", "Payment Completed Successfully!");

        PendingPackageTF.clear();
        BillingNameTF.clear();
        AmountTF.clear();
        PaymentMethodCB.getSelectionModel().clearSelection();
    }

    private void savePaymentToBinary(Payment pay) {
        File file = new File("PaymentInfo.bin");

        try {
            FileOutputStream fos;
            ObjectOutputStream oos;

            if (file.exists()) {
                fos = new FileOutputStream(file, true);
                oos = new AppendableObjectOutputStream(fos);
            }
            else {
                fos = new FileOutputStream(file);
                oos = new ObjectOutputStream(fos);
            }

            oos.writeObject(pay);
            oos.close();

        }
        catch (IOException e) {
            //
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