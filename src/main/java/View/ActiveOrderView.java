package View;

import Model.ItemInterface;
import Model.Observer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;

import java.util.List;

public class ActiveOrderView extends ViewController implements Observer {

    @FXML private FlowPane activeOrderFlowPane;

    private EventHandler<ActionEvent> event;

    @FXML
    private void startDateDatePicker(){

    }

    public void updateActiveOrders(){
        List<ItemInterface> orderItems = super.yh.getActiveOrderItems();
        activeOrderFlowPane.getChildren().clear();
        for(ItemInterface item: orderItems){
            ActiveOrderItemView orderItem = new ActiveOrderItemView(item);
            orderItem.setRemoveItemButton(event);
            activeOrderFlowPane.getChildren().add(orderItem);
        }
    }

    public void injectOrderItemListener(EventHandler<ActionEvent> event){
        this.event = event;
    }

    @Override
    public void update() {
        updateActiveOrders();
    }
}
