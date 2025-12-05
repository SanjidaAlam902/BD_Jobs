package com.example.bd_jobs;

public class Interview {

        private String candidateName;
        private String date;
        private String time;

        public Interview() {}
        public Interview(String candidateName, String date, String time) {
            this.candidateName = candidateName; this.date = date; this.time = time;
        }

        public String getCandidateName() { return candidateName; }
        public void setCandidateName(String candidateName) { this.candidateName = candidateName; }
        public String getDate() { return date; }
        public void setDate(String date) { this.date = date; }
        public String getTime() { return time; }
        public void setTime(String time) { this.time = time; }


}
