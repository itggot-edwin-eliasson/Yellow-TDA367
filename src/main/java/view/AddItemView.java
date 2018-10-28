package view;

import model.GroupInterface;
import model.InventoryInterface;
import model.Observer;
import model.YellowHandlerInterface;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class AddItemView extends ViewParent implements Observer {

    private Stage dialogStage;

    private boolean okClicked = false;

    @FXML
    private TextField nameTextField;
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private TextField amountTextField;
    @FXML
    private Button uploadImageButton;
    @FXML
    private ImageView itemImageView;
    @FXML private JFXComboBox groupComboBox;
    @FXML private JFXComboBox inventoryComboBox;


    public int getAmount() {
        String amount = amountTextField.getText();
        for (char c : amount.toCharArray()) {
            if (!Character.isDigit(c)) {
                return 0;
            }
        }
        if (!amount.isEmpty()) {
            return Integer.parseInt(amount);
        } else {
            return 1;
        }
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void saveItem() {
        okClicked = true;
        dialogStage.close();
    }


    public void setImage(Image image) {
        itemImageView.setImage(image);
    }

    @FXML
    private void cancelItem() {
        dialogStage.close();
    }

    public void uploadImage(EventHandler<ActionEvent> clicked) {
        uploadImageButton.setOnAction(clicked);
    }

    public void itemAmount(EventHandler<ActionEvent> clicked) { amountTextField.setOnAction(clicked); }

    public Stage getDialogStage() { return dialogStage; }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public String getItemName() {
        return nameTextField.getText();
    }

    public String getItemDescription() {
        return descriptionTextArea.getText();
    }

    public Image getImage(){
        return itemImageView.getImage();
    }

    public InventoryInterface getInventory(){
        return (InventoryInterface) inventoryComboBox.getValue();
    }

    public GroupInterface getGroup(){
        return (GroupInterface) groupComboBox.getValue();
    }

    @Override
    public void injectYellowHandler(YellowHandlerInterface yh){
        super.yh = yh;
        setGroupComboBox();
        setInventoryComboBox();
    }

    private void setInventoryComboBox(){
        GroupInterface group = (GroupInterface) groupComboBox.getValue();
        ObservableList<InventoryInterface> inventories = FXCollections.observableArrayList(group.getInventories());

        inventoryComboBox.setCellFactory(lv -> createInventoryCell());
        inventoryComboBox.setButtonCell(createInventoryCell());
        inventoryComboBox.setItems(inventories);

        inventoryComboBox.setValue(group.getSelectedInventory());
    }

    private void setGroupComboBox(){
        ObservableList<GroupInterface> groups = FXCollections.observableArrayList(super.yh.getGroups());

        groupComboBox.setCellFactory(lv -> createGroupCell());
        groupComboBox.setButtonCell(createGroupCell());
        groupComboBox.setItems(groups);

        groupComboBox.setValue(super.yh.getActiveGroup());
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
                    setInventoryComboBox();
                }
            }
        };
    }

    private ListCell<InventoryInterface> createInventoryCell() {
        return new ListCell<InventoryInterface>() {
            @Override
            protected void updateItem(InventoryInterface item, boolean empty) {
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

    @Override
    public void update() {

    }
}