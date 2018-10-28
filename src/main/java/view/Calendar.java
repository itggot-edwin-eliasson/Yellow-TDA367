package view;

import model.Item;

import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;

import java.time.LocalDate;

import static javafx.application.Application.launch;
public class Calendar {
    /*public void start(Stage primaryStage, Item theItem) {
        try {
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root, 400, 400);
            //scene.getStylesheets().add(getClass().getResource("yellow.css").toExternalForm());

            JFXDatePicker asd = new JFXDatePicker(LocalDate.now());
            Callback<DatePicker, DateCell> dayCellFactory= this.getDayCellFactory(theItem);
            asd.setDayCellFactory(dayCellFactory);

            JFXDatePickerSkin datePickerSkin = new JFXDatePickerSkin(asd);

            Node popupContent = datePickerSkin.getDisplayNode();

            root.setCenter(popupContent);

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

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
