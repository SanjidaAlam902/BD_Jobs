package com.example.bd_jobs;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.io.File;

public class Report {
    private IntegerProperty impressions;
    private IntegerProperty clicks;
    private IntegerProperty applications;

    private String licenseFilePath;
    private String tinFilePath;
    private String idProofFilePath;

    public Report(int impressions, int clicks, int applications) {
        this.impressions = new SimpleIntegerProperty(impressions);
        this.clicks = new SimpleIntegerProperty(clicks);
        this.applications = new SimpleIntegerProperty(applications);
    }

    public Report(File licenseFile, File tinFile, File idProofFile) {
        this.impressions = impressions;
        this.clicks = clicks;
        this.applications = applications;
        this.licenseFilePath = licenseFile.getAbsolutePath();
        this.tinFilePath = tinFile.getAbsolutePath();
        this.idProofFilePath = idProofFile.getAbsolutePath();
    }



    public int getImpressions() {
        return impressions.get();
    }

    public void setImpressions(int impressions) {
        this.impressions.set(impressions);
    }

    public IntegerProperty impressionsProperty() {
        return impressions;
    }

    public int getClicks() {
        return clicks.get();
    }

    public void setClicks(int clicks) {
        this.clicks.set(clicks);
    }

    public IntegerProperty clicksProperty() {
        return clicks;
    }

    public int getApplications() {
        return applications.get();
    }

    public void setApplications(int applications) {
        this.applications.set(applications);
    }

    public IntegerProperty applicationsProperty() {
        return applications;
    }

    public String getLicenseFilePath() { return licenseFilePath; }
    public String getTinFilePath() { return tinFilePath; }
    public String getIdProofFilePath() { return idProofFilePath; }

    @Override
    public String toString() {
        return "License: " + licenseFilePath + ", TIN: " + tinFilePath + ", ID Proof: " + idProofFilePath;
    }
}
