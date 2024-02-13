package Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import Connectors.LoginConnector;
import Entities.Review;
import Entities.ReviewData;
import Utils.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class StudentReviewsScreenController {

    @FXML
    private ListView<ReviewData> reviewsListView;

    private ObservableList<ReviewData> reviews;

    @FXML
    void onBackButtonClick(ActionEvent event) throws IOException {
        SceneManager.LoadScene(event, "StudentMenuScreen.fxml", 600, 400);
    }
    @FXML
    void onAddButtonClick(ActionEvent event) {
        // Создаем диалоговое окно для ввода текста отзыва
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setTitle("Добавить отзыв");
        textInputDialog.setHeaderText("Введите текст отзыва:");
        textInputDialog.setContentText("Отзыв:");

        // Получаем введенный текст
        textInputDialog.showAndWait().ifPresent(text -> {
            // Устанавливаем русскую локаль для кнопок
            Locale.setDefault(new Locale("ru", "RU"));

            // Создаем диалоговое окно для выбора оценки
            ChoiceDialog<String> choiceDialog = new ChoiceDialog<>("★", "★★", "★★★", "★★★★", "★★★★★");
            choiceDialog.setTitle("Выбрать оценку");
            choiceDialog.setHeaderText("Выберите оценку:");
            choiceDialog.setContentText("Оценка:");

            // Получаем выбранную оценку
            choiceDialog.showAndWait().ifPresent(grade -> {
                // Создаем диалоговое окно с двумя кнопками ("Сохранить отзыв" и "Назад")
                Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
                confirmDialog.setTitle("Подтверждение");
                confirmDialog.setHeaderText("Сохранить отзыв?");
                confirmDialog.getButtonTypes().setAll(new ButtonType("Сохранить отзыв", ButtonType.YES.getButtonData()),
                        new ButtonType("Назад", ButtonType.CANCEL.getButtonData()));

                // Применяем стиль для кнопок (Comic Sans MS и жирный шрифт)
                confirmDialog.getDialogPane().setStyle("-fx-font-family: 'Comic Sans MS'");
                confirmDialog.getDialogPane().getStyleClass().add("my-dialog");

                // Отображаем диалоговое окно и обрабатываем результат
                confirmDialog.showAndWait().ifPresent(result -> {
                    if (result.getButtonData() == ButtonType.YES.getButtonData()) {
                        // Если выбрана кнопка "Сохранить отзыв", добавляем отзыв в список
                        saveReview(text, grade);
                    }
                });
            });
        });
    }
    private void saveReview(String text, String grade) {
        // Здесь добавьте код для сохранения отзыва
        // Может быть, вам нужно отправить данные на сервер или сохранить их локально
        // В данном случае, вы можете использовать переменные text и grade для сохранения данных
    }

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        reviews = FXCollections.observableArrayList();
        loadReviews();
        reviewsListView.setItems(reviews);

        // Установка отображения элементов в списке
        reviewsListView.setCellFactory(listView -> new ReviewDataCell());
    }

    private void loadReviews() throws IOException, ClassNotFoundException {
        List<Review> reviewList = (List<Review>) LoginConnector.ois.readObject();
        List<String> studentNames = (List<String>) LoginConnector.ois.readObject();
        List<String> teacherNames = (List<String>) LoginConnector.ois.readObject();
        List<String> courseNames = (List<String>) LoginConnector.ois.readObject();

        // Создание списка ReviewData и заполнение его данными
        List<ReviewData> reviewDataList = new ArrayList<>();
        for (int i = 0; i < reviewList.size(); i++) {
            ReviewData reviewData = new ReviewData();
            reviewData.setText(reviewList.get(i).getText());
            reviewData.setGrade(reviewList.get(i).getGrade());
            reviewData.setStudent_name(studentNames.get(i));
            reviewData.setTeacher_name(teacherNames.get(i));
            reviewData.setCourse_name(courseNames.get(i));
            reviewDataList.add(reviewData);
        }

        // Добавление в ObservableList
        reviews.addAll(reviewDataList);
    }

}
