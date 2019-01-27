package main.presentation.controller;

import com.jfoenix.controls.JFXRadioButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.business.impl.UserBusinessServiceImpl;
import main.business.service.UserBusinessService;
import main.common.resultmessage.SignInResultMessage;
import main.common.resultmessage.SignUpResultMessage;
import main.common.user.User;
import main.common.user.UserType;
import main.presentation.Login;

import java.awt.*;

public class LoginController {
    @FXML
    private TextField accountField;
    @FXML
    private PasswordField passwordField;

    private UserBusinessService userBusinessService = UserBusinessServiceImpl.getInstance();

    private Login login;

    public void setApp(Login login) { this.login = login; }

    public void onStart() {

    }


}
