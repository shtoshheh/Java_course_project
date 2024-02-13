package Controllers;

import Connectors.Connector;
import Connectors.LoginConnector;
import Entities.Student;
import Entities.StudentBuilderImp;
import Entities.User;
import Utils.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;

public class RegisterScreenController {

    @FXML
    private DatePicker date_field;

    @FXML
    private TextField email_field;

    @FXML
    private TextField login_field;

    @FXML
    private TextField name_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private PasswordField repeat_password_field;

    @FXML
    private TextField surname_field;

    @FXML
    private Label error_text_label;

    @FXML
    private TextField phone_field;

    @FXML
    void onBackButtonClick(ActionEvent event) throws IOException {
        SceneManager.LoadScene(event,"LoginScreen.fxml",490,453);
    }

    @FXML
    public void onRegisterButtonClick(ActionEvent event) throws IOException, ClassNotFoundException {
        if(CheckAllFields()) {
            error_text_label.setTextFill(Color.GREEN);
            error_text_label.setText("Всё ок!");
            RegisterOperation();
            SceneManager.LoadScene(event,"StudentMenuScreen.fxml",600,400);
        }

    }

    private void RegisterOperation() throws IOException, ClassNotFoundException {
        User user = new User(login_field.getText(), password_field.getText(), "s");
        LoginConnector.SendString("register");
        String receivedString = (String) Connector.ois.readObject();
        if (receivedString.equals("Ok")) {
            LoginConnector.SendEnteredUserData(user);
            receivedString = (String) Connector.ois.readObject();
            System.out.println("Received status: " + receivedString);
            switch (receivedString) {
                case "Success":
                    Student student = new StudentBuilderImp().
                            setName(name_field.getText()).
                            setSurname(surname_field.getText()).
                            setDateOfBirth(date_field.getValue()).
                            setPhone(phone_field.getText()).
                            setEmail(email_field.getText()).build();
                    LoginConnector.SendEnteredStudentData(student);
                    receivedString = (String) Connector.ois.readObject();
                    System.out.println("Received status: " + receivedString);
                    error_text_label.setTextFill(Color.rgb(124, 212, 114));
                    error_text_label.setText("Регистрация прошла успешно!");
                    error_text_label.setVisible(true);
                    break;
                case "Failed":
                    error_text_label.setTextFill(Color.rgb(245, 50, 50));
                    error_text_label.setText("Такой пользователь уже существует!");
                    error_text_label.setVisible(true);
                    break;
            }
        }
    }

    private boolean CheckAllFields(){
        if(name_field.getText().isEmpty() || surname_field.getText().isEmpty() || email_field.getText().isEmpty()) {
            error_text_label.setTextFill(Color.rgb(245, 50, 50));
            error_text_label.setText("Заполните обязательные поля!");
            error_text_label.setVisible(true);
            return false;
        }
        else if (!password_field.getText().equals(repeat_password_field.getText())) {
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

