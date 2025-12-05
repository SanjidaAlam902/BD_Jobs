package com.example.bd_jobs;

public class CoachingSession {
    private String date;
    private String time;
    private String name;
    private String type;
    private String status;

    public CoachingSession(String date, String time, String name, String type, String status) {
        this.date = date;
        this.time = time;
        this.name = name;
        this.type = type;
        this.status = status;
    }

    public String getDate() { return date; }
    public String getTime() { return time; }
    public String getName() { return name; }
    public String getType() { return type; }
    public String getStatus() { return status; }
}
