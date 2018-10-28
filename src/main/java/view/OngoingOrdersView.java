package view;

import model.Observer;
import model.OrderInterface;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;

import java.util.List;

public class OngoingOrdersView extends ViewParent implements Observer {


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
