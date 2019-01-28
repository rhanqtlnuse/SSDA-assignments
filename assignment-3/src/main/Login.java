package main;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.presentation.controller.LoginController;
import main.presentation.controller.MainController;
import main.presentation.controller.ReaderController;

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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("presentation/fxml/login.fxml"));
            Parent root = fxmlLoader.load();
            mainStage.setTitle("图书管理系统");
            LoginController loginController = fxmlLoader.getController();
            loginController.setApp(this);
            Scene scene = new Scene(root, 700, 500);
            scene.getStylesheets().add(Login.class.getResource("presentation/css/main.css").toExternalForm());
            mainStage.setScene(scene);
            mainStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gotoMainUi(String username) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("presentation/fxml/main.fxml"));
            Parent root = loader.load();
            mainStage.setTitle("图书管理系统");
            MainController controller = loader.getController();
            controller.setApp(this);
            controller.setMyName(username);
            Scene scene = new Scene(root, 700, 500);
            scene.getStylesheets().add(Login.class.getResource("presentation/css/main.css").toExternalForm());
            mainStage.setScene(scene);
            mainStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gotoReaderUi(String username) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("presentation/fxml/reader_ui.fxml"));
            Parent root = loader.load();
            mainStage.setTitle("图书管理系统");
            ReaderController controller = loader.getController();
            controller.setApp(this);
            controller.setUserInfo(username);
            Scene scene = new Scene(root, 700, 500);
            scene.getStylesheets().add(Login.class.getResource("presentation/css/main.css").toExternalForm());
            mainStage.setScene(scene);
            mainStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gotoOnlineReaderUi(String content, String username) {
        try {
            mainStage.setTitle("在线阅读器");
            JFXButton button = new JFXButton("返回");
            button.setMinWidth(50);
            button.setOnAction(action -> {
                this.gotoReaderUi(username);
            });

            JFXTextArea contentArea = new JFXTextArea();
            contentArea.setText(content);
            contentArea.setWrapText(true);
            contentArea.setEditable(false);
            VBox vBox = new VBox(contentArea, button);
            Scene scene = new Scene(vBox, 700, 500);
            scene.getStylesheets().add(Login.class.getResource("presentation/css/main.css").toExternalForm());
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
