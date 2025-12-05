package com.example.bd_jobs;

public class SponsorshipPackage {
    private String name;
    private String type;
    private String duration;
    private double cost;
    private String benefits;

    private String companyName;
    private String website;
    private String contact;
    private String description;
    private String logoPath;

    private String jobTitle;
    private String category;
    private double budget;

    public SponsorshipPackage(String name, String type, String duration, double cost, String benefits) {
        this.name = name;
        this.type = type;
        this.duration = duration;
        this.cost = cost;
        this.benefits = benefits;
    }

    public SponsorshipPackage() {}

    public SponsorshipPackage(String jobTitle, String category, String duration, double budget) {
        this.jobTitle = jobTitle;
        this.category = category;
        this.duration = duration;
        this.budget = budget;
    }

    public String getName() { return name; }
    public String getType() { return type; }
    public String getDuration() { return duration; }
    public double getCost() { return cost; }
    public String getBenefits() { return benefits; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLogoPath() { return logoPath; }
    public void setLogoPath(String logoPath) { this.logoPath = logoPath; }

    public String getJobTitle() { return jobTitle; }
    public String getCategory() { return category; }
    public double getBudget() { return budget; }
}
