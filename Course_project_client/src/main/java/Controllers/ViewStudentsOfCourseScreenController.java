package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import Connectors.Connector;
import Connectors.LoginConnector;
import Entities.Course;
import Entities.Student;
import Utils.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class ViewStudentsOfCourseScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Accordion accordion;

    @FXML
    private TextField search_field;
    private ObservableList<Student> courseStudents;

    @FXML
    void onBackButtonClick(ActionEvent event) throws IOException {
        LoginConnector.SendString("no_changed_progress");
        SceneManager.LoadScene(event, "TeacherCoursesScreen.fxml", 601, 423);
    }

    @FXML
    void onSetRatingButtonClick(Course selectedCourse) throws IOException {
        // TODO: Добавьте код для обработки события по нажатию на кнопку "выставить рейтинг"
    }

    @FXML
    void onSearchIconClick() {
        String searchText = search_field.getText().toLowerCase();

        // Фильтруем курсы по содержанию подстроки в названии
        List<Student> filteredStudents = courseStudents.stream()
                .filter(student -> (student.getName() + " " + student.getSurname()).toLowerCase().contains(searchText))
                .collect(Collectors.toList());

        // Обновляем Accordion с отфильтрованными курсами
        accordion.getPanes().setAll(createTitledPanes(FXCollections.observableArrayList(filteredStudents)));
    }

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        List<Student> students = FXCollections.observableArrayList((List<Student>) Connector.ois.readObject());
        courseStudents = FXCollections.observableArrayList(students);
        accordion.getPanes().setAll(createTitledPanes(courseStudents));
        var cssPath = Objects.requireNonNull(getClass().getResource("/styles.css")).toExternalForm();
        accordion.getStylesheets().add(cssPath);
    }

    private ObservableList<TitledPane> createTitledPanes(ObservableList<Student> students) {
        ObservableList<TitledPane> titledPanes = FXCollections.observableArrayList();

        for (Student s : students) {
            TitledPane titledPane = new TitledPane();
            titledPane.setText(s.getName() + " " + s.getSurname());

            // Создаем AnchorPane с информацией о курсе и кнопкой просмотра учеников
            AnchorPane coursePane = createStudentPane(s);

            // Устанавливаем содержимое TitledPane
            titledPane.setContent(coursePane);

            titledPanes.add(titledPane);
        }

        return titledPanes;
    }

    private AnchorPane createStudentPane(Student student) {
        double topMargin = 10.0;
        double leftMargin = 10.0;
        AnchorPane anchorPane = new AnchorPane();
        Label nameLabel = new Label("Имя: " + student.getName());
        Label surnameLabel = new Label("Фамилия: " + student.getSurname());
        anchorPane.getChildren().addAll(
                nameLabel, surnameLabel
        );
        if(student.getDate_of_birth() != null) {
            Label dobLabel = new Label("Дата рождения: " + student.getDate_of_birth());
            AnchorPane.setTopAnchor(dobLabel, topMargin += 20.0);
            AnchorPane.setLeftAnchor(dobLabel, leftMargin);
            anchorPane.getChildren().add(dobLabel);
        }
        if(student.getPhone() != null) {
            Label phoneLabel = new Label("Телефон: " + student.getPhone());
            AnchorPane.setTopAnchor(phoneLabel, topMargin += 20.0);
            AnchorPane.setLeftAnchor(phoneLabel, leftMargin);
            anchorPane.getChildren().add(phoneLabel);
        }
        if(student.getEmail() != null) {
            Label emailLabel = new Label("Email: " + student.getEmail());
            AnchorPane.setTopAnchor(emailLabel, topMargin += 20.0);
            AnchorPane.setLeftAnchor(emailLabel, leftMargin);
            anchorPane.getChildren().add(emailLabel);

        }

        Label progressLabel = new Label("Прогресс: " + student.getProgress_id());
        Label courseTypeLabel = new Label("Тип курса: " + (student.getCourse_type() == 'g' ? "Групповой" : "Индивидуальный"));
        Label enrollmentDateLabel = new Label("Дата зачисления: " + student.getEnrollment_date());

        Button setRatingButton = new Button("Выставить рейтинг");

        setRatingButton.setOnAction(e -> {
            // Добавьте код для обработки события по нажатию на кнопку "выставить рейтинг"
            // Может быть, вы захотите открыть новое окно или отобразить диалоговое окно для выставления рейтинга
        });

        anchorPane.getChildren().addAll(
                progressLabel,
                courseTypeLabel, enrollmentDateLabel,
                setRatingButton
        );

        AnchorPane.setTopAnchor(nameLabel, topMargin += 20.0);
        AnchorPane.setLeftAnchor(nameLabel, leftMargin);

        AnchorPane.setTopAnchor(surnameLabel, topMargin += 20.0);
        AnchorPane.setLeftAnchor(surnameLabel, leftMargin);


        AnchorPane.setTopAnchor(progressLabel, topMargin += 20.0);
        AnchorPane.setLeftAnchor(progressLabel, leftMargin);

        AnchorPane.setTopAnchor(courseTypeLabel, topMargin += 20.0);
        AnchorPane.setLeftAnchor(courseTypeLabel, leftMargin);

        AnchorPane.setTopAnchor(enrollmentDateLabel, topMargin += 20.0);
        AnchorPane.setLeftAnchor(enrollmentDateLabel, leftMargin);

        AnchorPane.setBottomAnchor(setRatingButton, 10.0);
        AnchorPane.setRightAnchor(setRatingButton, 10.0);

        return anchorPane;
    }



}
