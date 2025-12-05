package com.example.bd_jobs;

public class CampaignFeedback {
    private String campaignName;
    private int rating;
    private String comments;

    public CampaignFeedback(String campaignName, int rating, String comments) {
        this.campaignName = campaignName;
        this.rating = rating;
        this.comments = comments;
    }

    public String getCampaignName() { return campaignName; }
    public int getRating() { return rating; }
    public String getComments() { return comments; }
}
