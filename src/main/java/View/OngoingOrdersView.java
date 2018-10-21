package View;

import Model.Observer;
import Model.OrderInterface;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;

import java.util.List;

public class OngoingOrdersView extends ViewController implements Observer {


    @FXML private FlowPane ongoingOrdersFlowPane;

    public void updateOngoingOrders(){
        List<OrderInterface> orders = super.yh.getOngoingOrders();
        ongoingOrdersFlowPane.getChildren().clear();
        for(OrderInterface order: orders){
            OrderItemView item = new OrderItemView(order);
            ongoingOrdersFlowPane.getChildren().add(item);
        }
    }

    @Override
    public void update() {
        updateOngoingOrders();
    }
}
