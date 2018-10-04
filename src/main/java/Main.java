import Model.YellowHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        ResourceBundle bundle = ResourceBundle.getBundle("Yellow");

        Parent root = FXMLLoader.load(getClass().getResource("yellow.fxml"), bundle);

        Scene scene = new Scene(root);

        stage.setTitle(bundle.getString("application.name"));
        stage.setScene(scene);
        stage.show();
    }
}
