package Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Connectors.LoginConnector;
import Entities.Review;
import Entities.ReviewData;
import Utils.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

public class TeacherReviewsScreenController {

    @FXML
    private ListView<ReviewData> reviewsListView;

    private ObservableList<ReviewData> reviews;

    @FXML
    void onBackButtonClick(ActionEvent event) throws IOException {
        SceneManager.LoadScene(event, "TeacherMenuScreen.fxml", 601, 423);
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
