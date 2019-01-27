package main.presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.presentation.controller.LoginController;

public class Login extends Application {
    private Stage mainStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        mainStage = primaryStage;
        mainStage.setResizable(false);
        gotoLoginUi();
    }

    public void gotoLoginUi() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
            Parent root = fxmlLoader.load();
            mainStage.setTitle("图书管理系统");
            LoginController loginController = fxmlLoader.getController();
            loginController.setApp(this);
            Scene scene = new Scene(root, 700, 460);
            scene.getStylesheets().add("/css/main.css");
            mainStage.setScene(scene);
            mainStage.show();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void gotoMainUi() {

    }

    public void closeWindow() {
        mainStage.close();
    }

    public void hideWindow(){ mainStage.hide();}

    public void showWindow(){ mainStage.show();}

    public static void main(String[] args) {
        launch(args);
    }
}
