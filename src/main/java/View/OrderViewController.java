package View;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;

public class OrderViewController extends ViewController {

    @FXML private JFXButton activeOrderButton;
    @FXML private JFXButton ongoingOrderButton;
    @FXML private JFXButton oldOrderButton;
    @FXML private ScrollPane orderScrollPane;

    public void setOrderScrollPane(Node node){
        orderScrollPane.setContent(node);
    }

    public void setActiveOrderButton(){

    }

    public void setOngoingOrderButton(){

    }

    public void setOldOrderButton(){

    }

}
