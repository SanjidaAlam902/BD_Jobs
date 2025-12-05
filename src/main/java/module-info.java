module com.example.bd_jobs {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bd_jobs to javafx.fxml;
    exports com.example.bd_jobs;
}