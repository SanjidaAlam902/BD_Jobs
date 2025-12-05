package com.example.bd_jobs;



public class ReportItem {
    private String jobTitle;
    private String candidateName;
    private String status;
    private String date;

    public ReportItem() {}
    public ReportItem(String jobTitle, String candidateName, String status, String date) {
        this.jobTitle = jobTitle; this.candidateName = candidateName; this.status = status; this.date = date;
    }

    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }
    public String getCandidateName() { return candidateName; }
    public void setCandidateName(String candidateName) { this.candidateName = candidateName; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}
