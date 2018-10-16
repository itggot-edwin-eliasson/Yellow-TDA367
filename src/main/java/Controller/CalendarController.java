package Controller;

import Model.Item;
import com.sun.javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;

import static javafx.application.Application.launch;

public class CalendarController {
    public void start(Stage primaryStage, Item theItem) {
        try {
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root, 400, 400);
            //scene.getStylesheets().add(getClass().getResource("yellow.css").toExternalForm());

            DatePicker asd = new DatePicker(LocalDate.now());
            Callback<DatePicker, DateCell> dayCellFactory= this.getDayCellFactory(theItem);
            asd.setDayCellFactory(dayCellFactory);

            DatePickerSkin datePickerSkin = new DatePickerSkin(asd);

            Node popupContent = datePickerSkin.getPopupContent();

            root.setCenter(popupContent);

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Callback<DatePicker, DateCell> getDayCellFactory(Item theItem) {

        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {

            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        //disables a date
                        if (!theItem.checkDateIsNotInRentedPeriod(item.toString())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }

                };
            }
        };
        return dayCellFactory;
    }


}
