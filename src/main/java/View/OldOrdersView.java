package View;

import Model.Observer;
import Model.OrderInterface;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;

import java.util.List;

public class OldOrdersView extends ViewParent implements Observer {

    @FXML private FlowPane oldOrdersFlowPane;

    public void updateOldOrders(){
        List<OrderInterface> orders = super.yh.getOldOrders();
        oldOrdersFlowPane.getChildren().clear();
        for(OrderInterface order: orders){
            OrderItemView item = new OrderItemView(order);
            oldOrdersFlowPane.getChildren().add(item);
        }
    }

    @Override
    public void update() {
        updateOldOrders();
    }
}
