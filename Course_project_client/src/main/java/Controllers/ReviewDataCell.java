package Controllers;

import Entities.ReviewData;
import javafx.scene.control.ListCell;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class ReviewDataCell extends ListCell<ReviewData> {

    @Override
    protected void updateItem(ReviewData item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
        } else {
            // Ваш код для отображения данных объекта ReviewData
            setText("Студент: " + item.getStudent_name() + "\n"
                    + "Название курса: " + item.getCourse_name() + "\n"
                    + "Преподаватель: " + item.getTeacher_name() + "\n"
                    + "Оценка: " + item.getGrade() + "\n"
                    + "Текст отзыва: " + item.getText());

            // Установка шрифта напрямую в коде Java
            setFont(Font.font("Comic Sans MS", FontWeight.NORMAL, FontPosture.REGULAR, 12));
        }
    }
}
