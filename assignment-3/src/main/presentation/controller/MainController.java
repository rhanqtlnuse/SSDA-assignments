package main.presentation.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import main.common.user.User;

public class MainController {
    @FXML
    private StackPane mainContainer;
    @FXML
    private Label timeLabel;
    @FXML
    private ImageView adminAvatar;
    @FXML
    private Label adminName;

    private User user;

    public void setAdmin(User user) {
       this.user = user;
    }

}
