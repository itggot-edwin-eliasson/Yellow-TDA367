package View;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;

public class OrderView extends ViewController {

    @FXML private JFXButton activeOrderButton;
    @FXML private JFXButton ongoingOrderButton;
    @FXML private JFXButton oldOrderButton;
    @FXML private ScrollPane orderScrollPane;

    public void setOrderScrollPane(Node node){
        orderScrollPane.setContent(node);
    }

    public void setActiveOrderButton(EventHandler<ActionEvent> event){
        activeOrderButton.setOnAction(event);
    }

    public void setOngoingOrderButton(EventHandler<ActionEvent> event){
        ongoingOrderButton.setOnAction(event);
    }

    public void setOldOrderButton(EventHandler<ActionEvent> event){
        oldOrderButton.setOnAction(event);
    }

}
