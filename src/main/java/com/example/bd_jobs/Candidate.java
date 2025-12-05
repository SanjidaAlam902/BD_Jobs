package com.example.bd_jobs;

public class Candidate {
    private String name;
    private String email;
    private String contact;
    private String careerObjective;
    private String cvFileName;

    private String timeline;     // For progress tracking
    private String progressNote;

    private String feedbackDate;
    private String feedbackComment;

    private String date;
    private int communicationScore;
    private int technicalScore;
    private int problemSolvingScore;
    private int totalScore;

    private int rating;
    private String readinessStatus;
    private String finalRemarks;

    public Candidate(String name, String email, String contact, String careerObjective, String cvFileName) {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.careerObjective = careerObjective;
        this.cvFileName = cvFileName;
    }

    public Candidate(String name, String timeline, String progressNote) {
        this.name = name;
        this.timeline = timeline;
        this.progressNote = progressNote;
    }

    public Candidate(String name, String feedbackDate, String cvFileName, String feedbackComment) {
        this.name = name;
        this.feedbackDate = feedbackDate;
        this.cvFileName = cvFileName;
        this.feedbackComment = feedbackComment;
    }

    public Candidate(String name, String date, int communicationScore, int technicalScore, int problemSolvingScore) {
        this.name = name;
        this.date = date;
        this.communicationScore = communicationScore;
        this.technicalScore = technicalScore;
        this.problemSolvingScore = problemSolvingScore;
        this.totalScore = communicationScore + technicalScore + problemSolvingScore;
    }

    public Candidate(String name, int rating, String readinessStatus, String finalRemarks) {
        this.name = name;
        this.rating = rating;
        this.readinessStatus = readinessStatus;
        this.finalRemarks = finalRemarks;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getContact() { return contact; }
    public String getCareerObjective() { return careerObjective; }
    public String getCvFileName() { return cvFileName; }
    public String getTimeline() { return timeline; }
    public String getProgressNote() { return progressNote; }
    public void setName(String name) { this.name = name;}

    public void setEmail(String email) { this.email = email;}
    public void setContact(String contact) { this.contact = contact;}
    public void setCareerObjective(String careerObjective) { this.careerObjective = careerObjective;}
    public void setCvFileName(String cvFileName) { this.cvFileName = cvFileName;}
    public void setTimeline(String timeline) { this.timeline = timeline; }
    public void setProgressNote(String progressNote) { this.progressNote = progressNote; }

    public String getFeedbackDate() { return feedbackDate; }
    public String getFeedbackComment() { return feedbackComment; }
    public void setFeedbackDate(String feedbackDate) { this.feedbackDate = feedbackDate;}
    public void setFeedbackComment(String feedbackComment) { this.feedbackComment = feedbackComment;}

    public String getDate() { return date; }
    public int getCommunicationScore() { return communicationScore; }
    public int getTechnicalScore() { return technicalScore; }
    public int getProblemSolvingScore() { return problemSolvingScore; }
    public int getTotalScore() { return totalScore; }

    public int getRating() { return rating; }
    public String getRatingString() {return String.valueOf(rating);}
    public String getReadinessStatus() { return readinessStatus; }
    public String getFinalRemarks() { return finalRemarks; }
}
