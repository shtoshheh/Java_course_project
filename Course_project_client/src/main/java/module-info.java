module com.example.course_project_client {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.course_project_client to javafx.fxml;
    exports com.example.course_project_client;
    exports Controllers;
    opens Controllers to javafx.fxml;
}