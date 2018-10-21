package View;

import Model.Item;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.skins.JFXDatePickerSkin;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.time.LocalDate;

public class CalendarViewController {
    public void start(Stage primaryStage, Callback<DatePicker, DateCell> dayCellFactory, AnchorPane root) {
        try {
            //scene.getStylesheets().add(getClass().getResource("yellow.css").toExternalForm());

            JFXDatePicker asd = new JFXDatePicker(LocalDate.now());
            Callback<DatePicker, DateCell> dayCells = dayCellFactory;
            asd.setDefaultColor(Paint.valueOf("#ffcc00"));
            asd.setDayCellFactory(dayCells);


            root.getChildren().add(asd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
