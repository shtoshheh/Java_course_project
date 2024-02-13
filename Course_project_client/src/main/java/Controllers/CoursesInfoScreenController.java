package Controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import Connectors.Connector;
import Connectors.LoginConnector;
import Entities.Course;
import Entities.StudentData;
import Utils.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class CoursesInfoScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Accordion accordion;

    @FXML
    private TextField search_field;
    private ObservableList<Course> allCourses;

    @FXML
    void onBackButtonClick(ActionEvent event) throws IOException {
        LoginConnector.SendString("no_enroll");
        SceneManager.LoadScene(event, "StudentMenuScreen.fxml", 600, 400);
    }

    @FXML
    void onSearchIconClick() {
        String searchText = search_field.getText().toLowerCase();

        // Фильтруем курсы по содержанию подстроки в названии
        List<Course> filteredCourses = allCourses.stream()
                .filter(course -> course.getCourse_name().toLowerCase().contains(searchText))
                .collect(Collectors.toList());

        // Обновляем Accordion с отфильтрованными курсами
        accordion.getPanes().setAll(createTitledPanes(FXCollections.observableArrayList(filteredCourses)));
    }


    @FXML
    void initialize() throws IOException, ClassNotFoundException {
            allCourses = FXCollections.observableArrayList((List<Course>) Connector.ois.readObject());
            accordion.getPanes().setAll(createTitledPanes(allCourses));
        String cssPath = Objects.requireNonNull(getClass().getResource("/styles.css")).toExternalForm();
        accordion.getStylesheets().add(cssPath);
    }

    private ObservableList<TitledPane> createTitledPanes(ObservableList<Course> courses) {
        ObservableList<TitledPane> titledPanes = FXCollections.observableArrayList();

        for (Course course : courses) {
            TitledPane titledPane = new TitledPane();
            titledPane.setText(course.getCourse_name());

            // Создаем AnchorPane с информацией о курсе и кнопкой оформления
            AnchorPane coursePane = createCoursePane(course);

            // Устанавливаем содержимое TitledPane
            titledPane.setContent(coursePane);

            titledPanes.add(titledPane);
        }

        return titledPanes;
    }

    private AnchorPane createCoursePane(Course course) {
        AnchorPane anchorPane = new AnchorPane();
        Label levelLabel = new Label("Уровень: " + course.getLanguage_level());
        Label startDateLabel = new Label("Начало: " + course.getStart_date());
        Label endDateLabel = new Label("Окончание: " + course.getEnd_date());
        Label priceGroupLabel = new Label("Цена для группы: " + course.getPrice_group());
        Label priceIndividualLabel = new Label("Индивидуальная цена: " + course.getPrice_individual());

        Label descriptionLabel = new Label("Описание: " + course.getDescription());

        Button enrollButton = new Button("Оформить курс");
        ChoiceBox<String> courseTypeChoiceBox = new ChoiceBox<>();
        courseTypeChoiceBox.getItems().addAll("Групповой", "Индивидуальный");
        courseTypeChoiceBox.setValue("Групповой"); // По умолчанию выбран групповой курс
        anchorPane.getChildren().addAll(
                 levelLabel, startDateLabel, endDateLabel,
                priceGroupLabel, priceIndividualLabel,
                descriptionLabel, enrollButton,courseTypeChoiceBox
        );

        enrollButton.setOnAction(e -> {
            String selectedCourseType = courseTypeChoiceBox.getValue();
            char courseType = (selectedCourseType.equals("Групповой")) ? 'g' : 'i';
            Long courseId = course.getCourse_id(); // Получение id текущего курса
            try {
                enrollInCourse(courseId, courseType);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        double topMargin = 10.0;
        double leftMargin = 10.0;

        AnchorPane.setTopAnchor(levelLabel, topMargin += 20.0);
        AnchorPane.setLeftAnchor(levelLabel, leftMargin);

        AnchorPane.setTopAnchor(startDateLabel, topMargin += 20.0);
        AnchorPane.setLeftAnchor(startDateLabel, leftMargin);

        AnchorPane.setTopAnchor(endDateLabel, topMargin += 20.0);
        AnchorPane.setLeftAnchor(endDateLabel, leftMargin);

        AnchorPane.setTopAnchor(priceGroupLabel, topMargin += 20.0);
        AnchorPane.setLeftAnchor(priceGroupLabel, leftMargin);

        AnchorPane.setTopAnchor(priceIndividualLabel, topMargin += 20.0);
        AnchorPane.setLeftAnchor(priceIndividualLabel, leftMargin);

        AnchorPane.setTopAnchor(descriptionLabel, topMargin += 20.0);
        AnchorPane.setLeftAnchor(descriptionLabel, leftMargin);

        AnchorPane.setBottomAnchor(enrollButton, 10.0);
        AnchorPane.setLeftAnchor(enrollButton, 10.0);

        AnchorPane.setBottomAnchor(courseTypeChoiceBox, 10.0);
        AnchorPane.setRightAnchor(courseTypeChoiceBox, 10.0);

        return anchorPane;
    }

    private void enrollInCourse(Long courseId, char courseType) throws IOException {
        StudentData.setCourse_id(courseId);
        StudentData.setCourse_type(courseType);
        StudentData.setEnrollment_date(LocalDate.now());
        LoginConnector.SendString("enroll");
        LoginConnector.SendEnteredStudentData(StudentData.toStudent());
    }

}
