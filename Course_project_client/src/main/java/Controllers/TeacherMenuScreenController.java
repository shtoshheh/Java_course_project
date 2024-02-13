package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Connectors.LoginConnector;
import Entities.TeacherData;
import Utils.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TeacherMenuScreenController {

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
        SceneManager.LoadScene(event,"EditTeacherDataScreen.fxml",601,423);
    }

    @FXML
    void onViewMyCoursesButtonClick(ActionEvent event) throws IOException {
        LoginConnector.SendString("my_courses");
        SceneManager.LoadScene(event,"TeacherCoursesScreen.fxml",601,423);
    }

    @FXML
    void onViewReviewsButtonClick(ActionEvent event) throws IOException {
        LoginConnector.SendString("reviews");
        SceneManager.LoadScene(event,"TeacherReviewsScreen.fxml",601,423);
    }

    @FXML
    void initialize() {
        name_field.setText(TeacherData.getName() + " " + TeacherData.getSurname());

    }

}
