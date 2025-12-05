package com.example.bd_jobs;

public class Candidate {


        private String name;
        private String email;
        private String status; // e.g., "Applied", "Selected", "Rejected", "On Hold"
        private String resumePath; // optional file path

        public Candidate() {}
        public Candidate(String name, String email, String status, String resumePath) {
            this.name = name; this.email = email; this.status = status; this.resumePath = resumePath;
        }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public String getResumePath() { return resumePath; }
        public void setResumePath(String resumePath) { this.resumePath = resumePath; }
    }


