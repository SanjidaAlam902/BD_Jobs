package com.example.bd_jobs;

import javafx.beans.property.StringProperty;

public class JobPost {


    public class JobPost {

        private final StringProperty title = new SimpleStringProperty(this, "title");
        private final StringProperty description = new SimpleStringProperty(this, "description");
        private final StringProperty salary = new SimpleStringProperty(this, "salary");

        public JobPost() {}

        public JobPost(String title, String description, String salary) {
            this.title.set(title);
            this.description.set(description);
            this.salary.set(salary);
        }

        public String getTitle() { return title.get(); }
        public void setTitle(String value) { title.set(value); }
        public StringProperty titleProperty() { return title; }

        public String getDescription() { return description.get(); }
        public void setDescription(String value) { description.set(value); }
        public StringProperty descriptionProperty() { return description; }

        public String getSalary() { return salary.get(); }
        public void setSalary(String value) { salary.set(value); }
        public StringProperty salaryProperty() { return salary; }
    }


}
