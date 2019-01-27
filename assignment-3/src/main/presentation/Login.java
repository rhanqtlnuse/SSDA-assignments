package main.presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.presentation.controller.LoginController;
import main.presentation.controller.MainController;
import main.presentation.controller.ReaderController;
import sun.rmi.runtime.Log;

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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/login.fxml"));
            Parent root = fxmlLoader.load();
            mainStage.setTitle("图书管理系统");
            LoginController loginController = fxmlLoader.getController();
            loginController.setApp(this);
            Scene scene = new Scene(root, 700, 460);
            scene.getStylesheets().add(Login.class.getResource("css/main.css").toExternalForm());
            mainStage.setScene(scene);
            mainStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gotoMainUi(String username) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/main.fxml"));
            Parent root = loader.load();
            mainStage.setTitle("图书管理系统");
            MainController controller = loader.getController();
            controller.setApp(this);
            controller.setMyName(username);
            Scene scene = new Scene(root, 700, 460);
            scene.getStylesheets().add(Login.class.getResource("css/main.css").toExternalForm());
            mainStage.setScene(scene);
            mainStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gotoReaderUi(String username) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/reader_ui.fxml"));
            Parent root = loader.load();
            mainStage.setTitle("图书管理系统");
            ReaderController controller = loader.getController();
            controller.setApp(this);
            controller.setUserInfo(username);
            Scene scene = new Scene(root, 700, 460);
            scene.getStylesheets().add(Login.class.getResource("css/main.css").toExternalForm());
            mainStage.setScene(scene);
            mainStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
