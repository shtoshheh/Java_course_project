package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import Connectors.LoginConnector;
import Entities.*;
import Utils.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

public class EditTeacherDataScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField email_field;

    @FXML
    private Label error_text_label;

    @FXML
    private TextField login_field;

    @FXML
    private TextField name_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private TextField phone_field;

    @FXML
    private PasswordField repeat_password_field;

    @FXML
    private TextField surname_field;

    @FXML
    void onBackButtonClick(ActionEvent event) throws IOException {
        if (changesMade()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Подтверждение");
            alert.setHeaderText("Изменения не будут сохранены. Продолжить?");
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            alert.showAndWait().ifPresent(buttonType -> {
                if (buttonType == ButtonType.YES) {
                    try {
                        SceneManager.LoadScene(event, "TeacherMenuScreen.fxml", 601, 423);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } else {
            LoginConnector.SendString("no changed");
            SceneManager.LoadScene(event, "TeacherMenuScreen.fxml", 601, 423);
        }
    }

    @FXML
    void onSaveButtonClick(ActionEvent event) throws IOException {
        if(changesMade()) {
            LoginConnector.SendString("changed");
            if(CheckAllFields()) {
                saveChanges();
                User user = UserData.toUser();
                Teacher teacher = TeacherData.toTeacher();
                LoginConnector.SendEnteredUserData(user);
                LoginConnector.SendEnteredTeacherData(teacher);
                showAlert("Данные успешно сохранены!", Alert.AlertType.INFORMATION);
                SceneManager.LoadScene(event, "TeacherMenuScreen.fxml", 601, 423);
            }
        }
    }

    @FXML
    void initialize() {
    name_field.setText(TeacherData.getName());
    surname_field.setText(TeacherData.getSurname());
    email_field.setText(TeacherData.getEmail());
    phone_field.setText(TeacherData.getPhone());
    login_field.setText(UserData.getLogin());
    password_field.setText(UserData.getPassword());
    repeat_password_field.setText(UserData.getPassword());
    }
    private void saveChanges() {
        TeacherData.setName(name_field.getText());
        TeacherData.setSurname(surname_field.getText());
        TeacherData.setEmail(email_field.getText());
        TeacherData.setPhone(phone_field.getText());
        UserData.setLogin(login_field.getText());
        UserData.setPassword(password_field.getText());

    }

    private boolean changesMade() {
        return !name_field.getText().equals(TeacherData.getName()) ||
                !surname_field.getText().equals(TeacherData.getSurname()) ||
                !email_field.getText().equals(TeacherData.getEmail()) ||                 !phone_field.getText().equals(TeacherData.getPhone()) ||
                !login_field.getText().equals(UserData.getLogin()) ||
                !password_field.getText().equals(UserData.getPassword());
    }

    private void showAlert(String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Уведомление");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean CheckAllFields(){

        if (!password_field.getText().equals(repeat_password_field.getText())) {
            error_text_label.setTextFill(Color.rgb(245, 50, 50));
            error_text_label.setText("Пароли не совпадают!");
            error_text_label.setVisible(true);
            return false;
        }
        else if (password_field.getText().length() < 6) {
            error_text_label.setTextFill(Color.rgb(245, 50, 50));
            error_text_label.setText("Пароль должен содержать не менее 6 символов!");
            error_text_label.setVisible(true);
            return false;
        }
        else if(!email_field.getText().contains("@")) {
            error_text_label.setTextFill(Color.rgb(245, 50, 50));
            error_text_label.setText("Введите корректный адрес электронной почты!");
            error_text_label.setVisible(true);
            return false;
        }
        else
            return true;

    }
}
