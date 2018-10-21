package View;

import Model.ItemInterface;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;

import java.util.List;

public class ActiveOrderView extends ViewController {

    @FXML private FlowPane activeOrderFlowPane;

    @FXML
    private void startDateDatePicker(){

    }

    public void updateActiveOrders(){
        List<ItemInterface> orderItems = super.yh.getActiveGroup().getActiveOrder().getOrderList();
        activeOrderFlowPane.getChildren().clear();
        for(ItemInterface item: orderItems){

        }
    }
}
