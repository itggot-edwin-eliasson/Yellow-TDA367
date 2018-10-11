import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class Main extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        ResourceBundle bundle = java.util.ResourceBundle.getBundle("Yellow");

        Parent root = FXMLLoader.load(getClass().getResource("../resources/yellow.fxml"), bundle);

        Scene scene = new Scene(root);

        stage.setTitle(bundle.getString("application.name"));
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        System.out.println("Hejdå");

    }

}
