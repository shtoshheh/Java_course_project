package Utils;

import Entities.User;
import com.example.course_project_client.ClientApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SceneManager {

    public static void LoadScene(ActionEvent event, String sceneName,int width,int height) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(ClientApplication.class.getResource(sceneName)));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, width, height);

        stage.setScene(scene);
        stage.show();
    }

    public static void LoadSceneFromMenu(ActionEvent event, String sceneName, int width, int height) throws IOException {

    }

}
