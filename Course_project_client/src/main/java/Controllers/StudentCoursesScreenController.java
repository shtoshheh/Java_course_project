package Controllers;

import Connectors.LoginConnector;
import Entities.Course;
import Entities.StudentData;
import Utils.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class StudentCoursesScreenController {

    @FXML
    private StackPane contentStackPane;

    @FXML
    void onBackButtonClick(ActionEvent event) throws IOException {
        SceneManager.LoadScene(event, "StudentMenuScreen.fxml", 600, 400);
    }

    public void initialize() throws IOException, ClassNotFoundException {
        String cssPath = getClass().getResource("/styles.css").toExternalForm();
        contentStackPane.getStylesheets().add(cssPath);
        Long courseId = StudentData.getCourse_id();
        VBox courseInfoVBox = new VBox();
        courseInfoVBox.setStyle("-fx-alignment: CENTER;");
        contentStackPane.getChildren().add(courseInfoVBox);
        if (courseId != null) {
            LoginConnector.SendString("course_exist");
            LoginConnector.SendLong(courseId);
            Course course = (Course)LoginConnector.ois.readObject();
            LoginConnector.SendLong(course.getTeacher_id());
            String teacherName = (String)LoginConnector.ois.readObject();
            courseInfoVBox.getChildren().addAll(
                    createLabel("Название: " + course.getCourse_name()),
                    createLabel("Уровень языка: " + course.getLanguage_level()),
                    createLabel("Начало: " + course.getStart_date()),
                    createLabel("Окончание: " + course.getEnd_date())
            );
            char courseType = StudentData.getCourse_type();
            if (courseType == 'g') {
                courseInfoVBox.getChildren().add(createLabel("Тип: групповой" ));
                courseInfoVBox.getChildren().add(createLabel("Цена: " + course.getPrice_group()));
            } else if (courseType == 'i') {
                courseInfoVBox.getChildren().add(createLabel("Тип: индивидуальный" ));
                courseInfoVBox.getChildren().add(createLabel("Цена: " + course.getPrice_individual()));
            }

            courseInfoVBox.getChildren().add(createLabel("Преподаватель: " + teacherName));

            // Добавление лейбла с датой зачисления
            courseInfoVBox.getChildren().add(createLabel("Дата зачисления: " + StudentData.getEnrollment_date()));
        } else {
            LoginConnector.SendString("course_dont_exist");
            courseInfoVBox.getChildren().add(createLabel("Пока нет курсов"));
            Button enrollButton = new Button("Оформить курс");
            enrollButton.getStylesheets().add(cssPath);;
            enrollButton.setOnAction(event -> {
                try {
                    onEnrollButtonClick(event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            courseInfoVBox.getChildren().add(enrollButton);
        }
    }

    private Label createLabel(String text) {
        Label label = new Label(text);
        String cssPath = getClass().getResource("/styles.css").toExternalForm();
        label.getStylesheets().add(cssPath);;
        label.setStyle("-fx-font-size: 17");
        label.setAlignment(Pos.CENTER);
        VBox.setVgrow(label, Priority.ALWAYS);
        return label;
    }

    private void onEnrollButtonClick(ActionEvent event) throws IOException {SceneManager.LoadScene(event,"CoursesInfoScreen.fxml",657,427);
    }
}