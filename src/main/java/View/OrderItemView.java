package View;

import Model.ItemInterface;
import Model.OrderInterface;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class OrderItemView extends AnchorPane {

    @FXML private Label orderDate;
    @FXML private Label renterName;

    OrderInterface order;

    public OrderItemView(OrderInterface order) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../orderItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.order = order;

        orderDate.setText(order.getDateOfReturn());
        renterName.setText(order.getRenter().getName());
    }
}
