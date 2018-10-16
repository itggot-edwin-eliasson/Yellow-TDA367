package Controller;

import com.sun.javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.time.LocalDate;

import static javafx.application.Application.launch;

public class CalendarController {
    public void start(Stage primaryStage) {
        try {
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root, 400, 400);
            //scene.getStylesheets().add(getClass().getResource("yellow.css").toExternalForm());

            DatePickerSkin datePickerSkin = new DatePickerSkin(new DatePicker(LocalDate.now()));
            Node popupContent = datePickerSkin.getPopupContent();

            root.setCenter(popupContent);

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
