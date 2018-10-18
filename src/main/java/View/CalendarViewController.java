package View;

import Model.Item;
import com.sun.javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.time.LocalDate;

public class CalendarViewController {
    public void start(Stage primaryStage, Callback<DatePicker, DateCell> dayCellFactory) {
        try {
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root, 400, 400);
            //scene.getStylesheets().add(getClass().getResource("yellow.css").toExternalForm());

            DatePicker asd = new DatePicker(LocalDate.now());
            Callback<DatePicker, DateCell> dayCells = dayCellFactory;
            asd.setDayCellFactory(dayCells);

            DatePickerSkin datePickerSkin = new DatePickerSkin(asd);

            Node popupContent = datePickerSkin.getPopupContent();

            root.setCenter(popupContent);

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
