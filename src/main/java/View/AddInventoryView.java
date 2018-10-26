package View;

import Model.Group;
import Model.GroupInterface;
import Model.YellowHandlerInterface;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;

public class AddInventoryView extends ViewParent {

    private Stage dialogStage;

    private boolean okClicked = false;

    @FXML private JFXButton addButton;
    @FXML private JFXButton cancelButton;
    @FXML private JFXComboBox groupsComboBox;
    @FXML private TextField nameTextField;

    public String getInventoryName () {
        return nameTextField.getText();
    }


    public boolean isOkClicked(){
        return okClicked;
    }

    @FXML
    private void saveInventory () {
        okClicked = true;
        dialogStage.close();
    }

    @FXML
    private void cancelInventory(){
        dialogStage.close();
    }

    public void setDialogStage (Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @Override
    public void injectYellowHandler(YellowHandlerInterface yh){
        super.yh = yh;
        setComboBox();
    }

    private void setComboBox(){
        ObservableList<GroupInterface> groups = FXCollections.observableArrayList(super.yh.getGroups());

        groupsComboBox.setCellFactory(lv -> createGroupCell());
        groupsComboBox.setButtonCell(createGroupCell());
        groupsComboBox.setItems(groups);

        groupsComboBox.setValue(super.yh.getActiveGroup());
    }

    private ListCell<GroupInterface> createGroupCell() {
        return new ListCell<GroupInterface>() {
            @Override
            protected void updateItem(GroupInterface item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item.getName());
                }
            }
        };
    }

}
