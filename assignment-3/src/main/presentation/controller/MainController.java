package main.presentation.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import main.common.user.User;
import main.presentation.Login;

public class MainController {

    private Login myApp;
    private User user;

    public void setAdmin(User user) {
       this.user = user;
    }
    /**
     * 主类传递进来，方便界面管理
     * @param myApp
     */
    public void setApp(Login myApp) {
        this.myApp = myApp;
    }

    public void setMyName(String myName) {

    }
}
