package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Connectors.LoginConnector;
import Entities.StudentData;
import Utils.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StudentMenuScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label name_field;

    @FXML
    void onBackButtonClick(ActionEvent event) throws IOException {
        SceneManager.LoadScene(event,"LoginScreen.fxml",490,453);
    }

    @FXML
    void onEditButtonClick(ActionEvent event) throws IOException {
        LoginConnector.SendString("edit");
        SceneManager.LoadScene(event,"EditStudentDataScreen.fxml",650,421);
    }

    @FXML
    void onViewAllCoursesButtonClick(ActionEvent event) throws IOException {
        LoginConnector.SendString("info_courses");
        SceneManager.LoadScene(event,"CoursesInfoScreen.fxml",600,400);
    }

    @FXML
    void onViewMyCoursesButtonClick(ActionEvent event) throws IOException {
        LoginConnector.SendString("my_courses");
        SceneManager.LoadScene(event,"StudentCoursesScreen.fxml",600,400);
    }

    @FXML
    void onViewReviewsButtonClick(ActionEvent event) throws IOException {
        LoginConnector.SendString("reviews");
        SceneManager.LoadScene(event,"StudentReviewsScreen.fxml",600,400);
    }

    @FXML
    void initialize() {
        name_field.setText(StudentData.getName() + " " + StudentData.getSurname());

    }

}
