package main.presentation.controller;

import com.jfoenix.controls.*;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.business.impl.UserBusinessServiceImpl;
import main.business.service.UserBusinessService;
import main.common.resultmessage.SignInResultMessage;
import main.common.resultmessage.SignUpResultMessage;
import main.common.user.*;
import main.presentation.Login;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Thread.sleep;

public class LoginController implements Initializable {
    @FXML
    private JFXProgressBar prgs_login;

    @FXML
    private JFXButton btn_start;

    @FXML
    private JFXTextField tf_user;

    @FXML
    private JFXPasswordField tf_passWord;

    private UserBusinessService userBusinessService = UserBusinessServiceImpl.getInstance();

    private Login myApp;

    public void setApp(Login myApp) { this.myApp = myApp; }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("请输入用户名...");
        tf_user.getValidators().add(validator);
        tf_user.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) tf_user.validate();
        });

        RequiredFieldValidator validator2 = new RequiredFieldValidator();
        validator2.setMessage("请输入密码...");
        tf_passWord.getValidators().add(validator2);
        tf_passWord.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) tf_passWord.validate();
        });

        prgs_login.setVisible(false);

    }

    /**
     * 登录按钮点击事件
     */
    @FXML
    public void onStart() {
        prgs_login.setVisible(true);
        //创建线程登录
        myProgress myProgress = new myProgress(prgs_login);
        Thread thread = new Thread(myProgress);
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
        //登录界面控件不可见
        setDisable(true);
    }

    /**
     * 登录期间------组件的控制-----登录界面控件不可见
     */
    public void setDisable(Boolean bool) {
        btn_start.setDisable(bool);
        tf_user.setDisable(bool);
        tf_passWord.setDisable(bool);
    }

    /**
     * 检查并登录
     */
    private void doCheckUser() {
        String username = tf_user.getText().trim();
        String password = tf_passWord.getText().trim();
        SignInResultMessage resultMessage = userBusinessService.signIn(username, password);
        if(SignInResultMessage.SUCCEEDED == resultMessage) {
            User user = userBusinessService.findByUsername(username);
            if(user.getClass().getSimpleName().equals(UserType.ADMINISTRATOR.toString())) {
                myApp.gotoMainUi(username);
            }
            else {
                myApp.gotoReaderUi(username);
            }
        }
        else {
            setDisable(false);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("登录失败！");
            alert.setContentText(resultMessage.toString());
            alert.show();
        }
    }

    /**
     * 登录界面--点击登录按钮后---启用新的线程检查用户身份是否正确
     */
    class myProgress implements Runnable {

        private JFXProgressBar prgs_login;

        myProgress(JFXProgressBar prgs_login) {
            this.prgs_login = prgs_login;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i <= 100; i++) {
                    prgs_login.setProgress(i);
                }
                sleep(100);
                //更新JavaFX的主线程的代码放在此处
                Platform.runLater(LoginController.this::doCheckUser);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
