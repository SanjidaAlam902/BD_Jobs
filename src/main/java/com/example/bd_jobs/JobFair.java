package com.example.bd_jobs;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class JobFair {
    private final StringProperty companyName;
    private final StringProperty boothSize;
    private final StringProperty contactNumber;
    private final StringProperty status;

    public JobFair(String companyName, String boothSize, String contactNumber, String status) {
        this.companyName = new SimpleStringProperty(companyName);
        this.boothSize = new SimpleStringProperty(boothSize);
        this.contactNumber = new SimpleStringProperty(contactNumber);
        this.status = new SimpleStringProperty(status);
    }

    // Getters and setters for TableView binding
    public String getCompanyName() {
        return companyName.get();
    }

    public void setCompanyName(String companyName) {
        this.companyName.set(companyName);
    }

    public StringProperty companyNameProperty() {
        return companyName;
    }

    public String getBoothSize() {
        return boothSize.get();
    }

    public void setBoothSize(String boothSize) {
        this.boothSize.set(boothSize);
    }

    public StringProperty boothSizeProperty() {
        return boothSize;
    }

    public String getContactNumber() {
        return contactNumber.get();
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber.set(contactNumber);
    }

    public StringProperty contactNumberProperty() {
        return contactNumber;
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public StringProperty statusProperty() {
        return status;
    }
}
