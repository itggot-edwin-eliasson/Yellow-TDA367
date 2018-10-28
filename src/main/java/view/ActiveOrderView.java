package view;

import model.ItemInterface;
import model.Observer;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;

import java.util.List;

public class ActiveOrderView extends ViewParent implements Observer {

    @FXML private FlowPane activeOrderFlowPane;
    @FXML private JFXButton confirmOrderButton;
    @FXML private JFXDatePicker startDateDatePicker;
    @FXML private JFXDatePicker returnDateDatePicker;

    @FXML private JFXTextField renterNameTextField;
    @FXML private JFXTextField renterPhoneNumberTextField;

    private EventHandler<ActionEvent> event;

    @FXML
    private void startDateDatePicker(){

    }

    public void confirmOrderButton(EventHandler<ActionEvent> clicked){
        confirmOrderButton.setOnAction(clicked);
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

    public String getStartDate(){
        return startDateDatePicker.getValue().toString();
    }

    public String getReturnDate(){
        return returnDateDatePicker.getValue().toString();
    }

    public String getRenterName(){
        return renterNameTextField.getText();
    }

    public String getRenterPhoneNumber(){
        return renterPhoneNumberTextField.getText();
    }

    public void injectOrderItemListener(EventHandler<ActionEvent> event){
        this.event = event;
    }

    @Override
    public void update() {
        updateActiveOrders();
        renterNameTextField.clear();
        renterPhoneNumberTextField.clear();
    }
}
