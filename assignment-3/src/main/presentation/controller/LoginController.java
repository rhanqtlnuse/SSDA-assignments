package main.presentation.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import main.business.impl.UserBusinessServiceImpl;
import main.business.service.UserBusinessService;
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
