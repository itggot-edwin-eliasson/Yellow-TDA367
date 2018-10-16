package Controller;

import Model.GroupInterface;
import Model.UserInterface;
import Model.YellowHandler;
import Model.YellowHandlerInterface;
import View.LoginViewController;
import View.SignUpViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    private YellowHandlerInterface yh;

    private List<GroupInterface> groups;
    private List<UserInterface> users;


    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        users = new ArrayList<>();
        groups = new ArrayList<>();
        this.yh = new YellowHandler(users,groups);

        ResourceBundle bundle = java.util.ResourceBundle.getBundle("Yellow");

        stage.setTitle(bundle.getString("application.name"));
        initRoot();

        showLogin();
    }

    private void initRoot(){
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../rootLayout.fxml"));
            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);

            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void showYellow(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../yellow.fxml"));
            AnchorPane yellow = (AnchorPane) loader.load();

            rootLayout.setCenter(yellow);

            MainController mainController = loader.getController();
            mainController.injectYellowHandler(yh);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void showLogin(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../logIn.fxml"));
            AnchorPane login = (AnchorPane) loader.load();

            rootLayout.setCenter(login);

            LoginViewController controller = loader.getController();
            controller.injectYellowHandler(yh);
            controller.toSignupScreen(event -> {
                showSignUp();
            });
            controller.login(event -> {
                if(yh.logIn(controller.getUsername(),controller.getPassword())){
                    showYellow();
                }
                else {
                    //show error message
                    System.out.println("Finns ingen");
                }
            });
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void showSignUp(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../signUp.fxml"));
            AnchorPane login = (AnchorPane) loader.load();

            rootLayout.setCenter(login);

            SignUpViewController controller = loader.getController();
            controller.injectYellowHandler(yh);
            controller.signUp(event -> {
               if(yh.createUser(controller.getUsername(), controller.getFirstName(), controller.getLastName(),
                        controller.getEmail(), controller.getPassword())) {
                   showYellow();
               }
               else {
                   //TO DO:error message
               }
            });
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void showMenuScreen(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../firstYellowScreen.fxml"));
            AnchorPane menuScreen = (AnchorPane) loader.load();

            rootLayout.setCenter(menuScreen);

            MenuScreenController controller = loader.getController();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /*
    private ViewController loader(String fileName, ViewController controller){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../" + fileName + ".fxml"));
            AnchorPane login = (AnchorPane) loader.load();

            rootLayout.setCenter(login);

            controller = loader.getController();
            controller.injectYellowHandler(yh);
            return controller;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
    */

    @Override
    public void stop() {
        System.out.println("Hejdå");

    }

}
