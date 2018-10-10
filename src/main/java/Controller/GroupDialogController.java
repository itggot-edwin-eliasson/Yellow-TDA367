package Controller;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

public class GroupDialogController {

    @FXML private JFXTextField groupName;
    @FXML private JFXTextField groupColor;

    MainController mainController;

    public void injectMainController(MainController mainController){
        this.mainController = mainController;
    }

    public void initialize(){

    }

    @FXML
    public void createGroup(){
        mainController.createGroup();
    }

}
