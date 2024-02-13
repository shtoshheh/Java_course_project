package Controllers;

import Connectors.Connector;
import Connectors.LoginConnector;
import Entities.*;
import Entities.StudentData;
import Entities.TeacherData;
import Utils.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginScreenController {

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField pass_field;

    @FXML
    private Label error_text_label;

    @FXML
    public void onLoginButtonClick(ActionEvent event) throws IOException, ClassNotFoundException {
        User user = new User(login_field.getText(), pass_field.getText());
        LoginConnector.SendString("login");
        String receivedString = (String) Connector.ois.readObject();
        System.out.println(receivedString);
        if (receivedString.equals("Ok")) {
            System.out.println(receivedString);
            LoginConnector.SendEnteredUserData(user);
            receivedString = (String) Connector.ois.readObject();
            System.out.println("Received status: " + receivedString);
            if(!receivedString.equals("Failed")){
               User receivedUser = (User) Connector.ois.readObject();
                UserData.init(receivedUser);
            }
            switch (receivedString) {
                case "a":
                    error_text_label.setTextFill(Color.GREEN);
                    error_text_label.setText("Вход выполнен успешно!");
                    error_text_label.setVisible(true);
                    SceneManager.LoadScene(event,"AdminMenuScreen.fxml",529,552);
                    break;
                case "t":
                    Teacher receivedTeacher = (Teacher)Connector.ois.readObject();
                    TeacherData.init(receivedTeacher);
                    error_text_label.setTextFill(Color.GREEN);
                    error_text_label.setText("Вход выполнен успешно!");
                    error_text_label.setVisible(true);
                    SceneManager.LoadScene(event,"TeacherMenuScreen.fxml",601,423);
                    break;
                case "s":
                    Student receivedStudent = (Student) Connector.ois.readObject();
                    StudentData.init(receivedStudent);
                    error_text_label.setTextFill(Color.GREEN);
                    error_text_label.setText("Вход выполнен успешно!");
                    error_text_label.setVisible(true);
                    SceneManager.LoadScene(event,"StudentMenuScreen.fxml",600,400);
                    break;
                case "Failed":
                    error_text_label.setTextFill(Color.RED);
                    error_text_label.setText("Вход не выполнен!");
                    error_text_label.setVisible(true);
                    break;
            }
        }
    }

    public void onRegisterButtonClick(ActionEvent event) throws IOException {
        SceneManager.LoadScene(event,"RegisterScreen.fxml",600,400);
    }

    public void onExitButtonClick() throws IOException {
        Stage stage = (Stage) error_text_label.getScene().getWindow();

        // Закрытие окна
        stage.close();
    }

}
