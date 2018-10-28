package View;

import Model.GroupInterface;
import Model.YellowHandlerInterface;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.util.List;

public class ViewInviteCodesDialogView extends ViewParent {

    @FXML private FlowPane inviteCodesFlowPane;
    private Stage dialogStage;

    public void injectYellowHandler(YellowHandlerInterface yh){
        super.yh = yh;
        updateInviteCodes();
    }

    public void setDialogStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }

    private void updateInviteCodes(){
        List<GroupInterface> groups = super.yh.getGroups();
        inviteCodesFlowPane.getChildren().clear();
        for(GroupInterface group: groups){
            ViewInviteCodesItemView item = new ViewInviteCodesItemView(group);
            inviteCodesFlowPane.getChildren().add(item);
        }
    }
}
