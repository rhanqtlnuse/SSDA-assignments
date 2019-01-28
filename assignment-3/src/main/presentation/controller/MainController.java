package main.presentation.controller;

import com.jfoenix.controls.*;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import main.business.impl.BookBusinessServiceImpl;
import main.business.impl.UserBusinessServiceImpl;
import main.business.service.BookBusinessService;
import main.business.service.UserBusinessService;
import main.common.book.Book;
import main.common.book.CheckOutRecord;
import main.common.book.Level;
import main.common.resultmessage.BookManagementResultMessage;
import main.common.resultmessage.CancelResultMessage;
import main.common.resultmessage.CheckOutResultMessage;
import main.common.resultmessage.SignUpResultMessage;
import main.common.user.*;
import main.common.user.message.Message;
import main.Login;
import main.presentation.entity.*;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainController implements Initializable {
    private static final String DEFAULT_PASSWORD = "123456";

    private Login myApp;

    private String username;

    private BookBusinessService bookBusinessService = BookBusinessServiceImpl.getInstance();

    private UserBusinessService userBusinessService = UserBusinessServiceImpl.getInstance();

    //所有书目显示列表信息
    @FXML
    private TableView tbv_book;//数目列表
    @FXML
    private TableColumn tb_column_book_id;
    @FXML
    private TableColumn tb_column_book_name;
    @FXML
    private TableColumn tb_column_book_type;
    @FXML
    private TableColumn tb_column_book_author;
    @FXML
    private TableColumn tb_column_book_level;
    @FXML
    private TableColumn tb_column_book_outCount;
    @FXML
    private TableColumn tb_column_book_remainingCount;

    //所有读者显示列表信息
    @FXML
    private TableView tbv_reader;//读者列表
    @FXML
    private TableColumn tb_column_reader_id;
    @FXML
    private TableColumn tb_column_reader_name;
    @FXML
    private TableColumn tb_column_reader_type;
    @FXML
    private TableColumn tb_column_reader_sex;
    @FXML
    private TableColumn tb_column_reader_numbers;
    @FXML
    private TableColumn tb_column_reader_days;
    @FXML
    private TableColumn tb_column_reader_forfeit;
    @FXML
    private TableColumn tb_column_reader_borrowed_books;


    //所有借阅记录显示列表
    @FXML
    private TableView tbv_borrow;//借阅列表
    @FXML
    private TableColumn tb_column_borrow_id;
    @FXML
    private TableColumn tb_column_borrow_bookId;
    @FXML
    private TableColumn tb_column_borrow_readerId;
    @FXML
    private TableColumn tb_column_borrow_borrowDate;
    @FXML
    private TableColumn tb_column_borrow_backDate;
    @FXML
    private TableColumn tb_column_borrow_isBack;


    //借书---图书信息
    @FXML
    private JFXTextField tf_js_book_id;//图书编号
    @FXML
    private JFXTextField tf_js_book_name;//图书名称
    @FXML
    private JFXTextField tf_js_book_author;//出版社
    @FXML
    private JFXTextField tf_js_book_type;//出版时间

    //借书---读者信息
    @FXML
    private JFXTextField tf_js_reader_id;//读者编号
    @FXML
    private JFXTextField tf_js_reader_type;//读者类别
    @FXML
    private JFXTextField tf_js_reader_maxNum;//性别
    @FXML
    private JFXTextField tf_js_reader_maxDay;//读者名称

    //借书---button
    @FXML
    private JFXButton btn_jieshu_confirm;//确认按钮
    @FXML
    private JFXButton btn_jieshu_clear;//清楚按钮

    //借书---label---日期
    @FXML
    private Label lb_js_reader_jieshu_date;//借书时间
    @FXML
    private Label lb_js_reader_huanshu_date;//还书时间


    //还书---表格信息
    @FXML
    private TableView tbv_huanshu_record;
    @FXML
    private TableColumn tb_column_huanshu_bookId;
    @FXML
    private TableColumn tb_column_huanshu_bookName;
    @FXML
    private TableColumn tb_column_huanshu_borrowDate;
    @FXML
    private TableColumn tb_column_huanshu_backDate;

    //还书----读者信息
    @FXML
    private JFXTextField tf_hs_reader_id;
    @FXML
    private JFXTextField tf_hs_reader_type;
    @FXML
    private JFXTextField tf_hs_reader_maxNum;
    @FXML
    private JFXTextField tf_hs_reader_maxDay;

    //还书----图书信息
    @FXML
    private JFXTextField tf_hs_book_id;
    @FXML
    private JFXTextField tf_hs_book_name;


    //图书维护---添加
    @FXML
    private JFXTextField tf_ts_add_book_id;//图书编号
    @FXML
    private JFXTextField tf_ts_add_book_name;//图书名称
    @FXML
    private JFXTextField tf_ts_add_book_type;//图书类别
    @FXML
    private JFXTextField tf_ts_add_book_author;//作者
    @FXML
    private JFXTextField tf_ts_add_book_level;//图书等级
    @FXML
    private JFXTextField tf_ts_add_book_outCount;//借出数量
    @FXML
    private JFXTextField tf_ts_add_book_stock;//库存数量

    @FXML
    private JFXButton btn_ts_add_book_add;//确认添加按钮
    @FXML
    private JFXButton btn_ts_add_book_clear;//清楚按钮


    //图书维护----修改
    @FXML
    private JFXTextField tf_ts_alter_book_search_id;//搜索图书编号
    @FXML
    private JFXTextField tf_ts_alter_book_id;//图书编号
    @FXML
    private JFXTextField tf_ts_alter_book_name;//图书名称
    @FXML
    private JFXTextField tf_ts_alter_book_type;//图书类别
    @FXML
    private JFXTextField tf_ts_alter_book_author;//作者
    @FXML
    private JFXTextField tf_ts_alter_book_level;//图书等级
    @FXML
    private JFXTextField tf_ts_alter_book_outCount;//借出数目
    @FXML
    private JFXTextField tf_ts_alter_book_stock;//库存数量

    @FXML
    private JFXButton btn_ts_alter_book_alterBtn;//确认修改按钮
    @FXML
    private JFXButton btn_ts_alter_book_clearBtn;//清楚按钮
    @FXML
    private JFXButton btn_ts_alter_book_searchBtn;//查询按钮

    //图书维护----删除
    @FXML
    private JFXTextField tf_ts_delete_book_search_id;//搜索图书编号
    @FXML
    private JFXTextField tf_ts_delete_book_id;//图书编号
    @FXML
    private JFXTextField tf_ts_delete_book_name;//图书名称
    @FXML
    private JFXTextField tf_ts_delete_book_type;//图书类别
    @FXML
    private JFXTextField tf_ts_delete_book_author;//作者
    @FXML
    private JFXTextField tf_ts_delete_book_level;//图书等级
    @FXML
    private JFXTextField tf_ts_delete_book_outCount;//借出数量
    @FXML
    private JFXTextField tf_ts_delete_book_stock;//库存数量

    @FXML
    private JFXButton btn_ts_delete_book_deleteBtn;//确认修改按钮
    @FXML
    private JFXButton btn_ts_delete_book_clearBtn;//清楚按钮
    @FXML
    private JFXButton btn_ts_delete_book_searchBtn;//查询按钮



    //读者维护---添加
    @FXML
    private JFXTextField tf_rd_add_reader_id;//读者编号
    @FXML
    private JFXTextField tf_rd_add_reader_name;//读者名称
    @FXML
    private JFXComboBox cb_rd_add_reader_type;//读者类别
    @FXML
    private JFXComboBox cb_rd_add_reader_sex;//性别
    @FXML
    private JFXTextField tf_rd_add_reader_numbers;//可借数量
    @FXML
    private JFXTextField tf_rd_add_reader_days;//可借天数

    @FXML
    private JFXButton btn_rd_add_reader_addBtn;//确认添加按钮
    @FXML
    private JFXButton btn_rd_add_reader_clearBtn;//清楚按钮


    //读者维护----修改
    @FXML
    private JFXTextField tf_rd_alter_reader_search_id;//搜索读者编号
    @FXML
    private JFXTextField tf_rd_alter_reader_id;//读者编号
    @FXML
    private JFXTextField tf_rd_alter_reader_name;//读者名称
    @FXML
    private JFXComboBox cb_rd_alter_reader_type;//读者类别
    @FXML
    private JFXComboBox cb_rd_alter_reader_sex;//性别
    @FXML
    private JFXTextField tf_rd_alter_reader_numbers;//可借数量
    @FXML
    private JFXTextField tf_rd_alter_reader_days;//可借天数
    @FXML
    private JFXToggleButton tgBtn_rd_alter_reader_password_reset;//初始化密码

    @FXML
    private JFXButton btn_rd_alter_reader_alterBtn;//确认修改按钮
    @FXML
    private JFXButton btn_rd_alter_reader_clearBtn;//清楚按钮


    //读者维护----删除
    @FXML
    private JFXTextField tf_rd_delete_reader_search_id;//搜索读者编号
    @FXML
    private JFXTextField tf_rd_delete_reader_id;//读者编号
    @FXML
    private JFXTextField tf_rd_delete_reader_name;//读者名称
    @FXML
    private JFXComboBox cb_rd_delete_reader_type;//读者类别
    @FXML
    private JFXComboBox cb_rd_delete_reader_sex;//性别
    @FXML
    private JFXTextField tf_rd_delete_reader_numbers;//可借数量
    @FXML
    private JFXTextField tf_rd_delete_reader_days;//可借天数

    @FXML
    private JFXButton btn_rd_delete_reader_deleteBtn;//确认删除按钮
    @FXML
    private JFXButton btn_rd_delete_reader_clearBtn;//清楚按钮
    @FXML
    private JFXButton btn_rd_delete_reader_searchBtn;//查询按钮

    @FXML
    private TableView tbv_message;//消息
    @FXML
    private Label lb_message_number;

    @FXML
    private Label lb_welcome;//欢迎标语

    /**
     * 主类传递进来，方便界面管理
     * @param myApp
     */
    public void setApp(Login myApp) {
        this.myApp = myApp;
    }

    /**
     * 设置欢迎语句
     * @param myName
     */
    public void setMyName(String myName) {
        this.username = myName;
        User user = userBusinessService.findByUsername(myName);
        if (user != null) {
            lb_welcome.setText(user.getUsername()+" ,您好！");
        }
    }

    /**
     * 管理员获取消息
     * */
    public void getMessage() {
        ObservableList<MessageItem> messageItems = FXCollections.observableArrayList();
        for(Message m : userBusinessService.findByUsername(username).getMessageList()) {
            messageItems.add(new MessageItem(m));
        }
        tbv_message.setItems(messageItems);
        lb_message_number.setText(tbv_message.getItems().size() + " 条消息");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        //初始化当前时间
        lb_js_reader_jieshu_date.setText(df.format(new Date()));

        RequiredFieldValidator validator_tf_js_book_id = new RequiredFieldValidator();
        validator_tf_js_book_id.setMessage("请输入图书编号...");
        tf_js_book_id.getValidators().add(validator_tf_js_book_id);
        tf_js_book_id.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) tf_js_book_id.validate();
        });


        RequiredFieldValidator validator_tf_js_reader_id = new RequiredFieldValidator();
        validator_tf_js_reader_id.setMessage("请输入读者编号...");
        tf_js_reader_id.getValidators().add(validator_tf_js_reader_id);
        tf_js_reader_id.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) tf_js_reader_id.validate();
        });

        RequiredFieldValidator validator_tf_reqireInput = new RequiredFieldValidator();
        validator_tf_reqireInput.setMessage("请输入...");

        tf_hs_reader_id.getValidators().add(validator_tf_reqireInput);
        tf_hs_reader_id.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) tf_hs_reader_id.validate();
        });

        tf_hs_book_id.getValidators().add(validator_tf_reqireInput);
        tf_hs_book_id.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) tf_hs_book_id.validate();
        });

        //添加键盘监听
        tf_js_book_id.setOnKeyPressed(this::tf_js_book_id_keyEvent);
        tf_js_reader_id.setOnKeyPressed(this::tf_js_reader_id_keyEvent);
        tf_hs_reader_id.setOnKeyPressed(this::tf_hs_reader_id_keyEvent);
        tf_hs_book_id.setOnKeyPressed(this::tf_hs_book_id_keyEvent);

        //图书维护---初始化
        initBookAddUi();

        //读者维护---初始化
        initReaderAddUi();

        //所有书目列表初始化
        tb_column_book_id.setCellValueFactory(new PropertyValueFactory("ISBN"));
        tb_column_book_name.setCellValueFactory(new PropertyValueFactory("title"));
        tb_column_book_type.setCellValueFactory(new PropertyValueFactory("types"));
        tb_column_book_author.setCellValueFactory(new PropertyValueFactory("author"));
        tb_column_book_level.setCellValueFactory(new PropertyValueFactory("level"));
        tb_column_book_outCount.setCellValueFactory(new PropertyValueFactory("outCount"));
        tb_column_book_remainingCount.setCellValueFactory(new PropertyValueFactory("remainingCount"));

        tb_column_reader_id.setCellValueFactory(new PropertyValueFactory("id"));
        tb_column_reader_name.setCellValueFactory(new PropertyValueFactory("name"));
        tb_column_reader_type.setCellValueFactory(new PropertyValueFactory("type"));
        tb_column_reader_sex.setCellValueFactory(new PropertyValueFactory("sex"));
        tb_column_reader_numbers.setCellValueFactory(new PropertyValueFactory("max_num"));
        tb_column_reader_days.setCellValueFactory(new PropertyValueFactory("days_num"));
        tb_column_reader_forfeit.setCellValueFactory(new PropertyValueFactory("forfeit"));
        tb_column_reader_borrowed_books.setCellFactory(new PropertyValueFactory("borrowedBook"));

        tb_column_borrow_id.setCellValueFactory(new PropertyValueFactory("id"));
        tb_column_borrow_bookId.setCellValueFactory(new PropertyValueFactory("bookId"));
        tb_column_borrow_readerId.setCellValueFactory(new PropertyValueFactory("readerId"));
        tb_column_borrow_borrowDate.setCellValueFactory(new PropertyValueFactory("borrowDate"));
        tb_column_borrow_backDate.setCellValueFactory(new PropertyValueFactory("backDate"));
        tb_column_borrow_isBack.setCellValueFactory(new PropertyValueFactory("isBack"));

        tb_column_huanshu_bookId.setCellValueFactory(new PropertyValueFactory("bookId"));
        tb_column_huanshu_bookName.setCellValueFactory(new PropertyValueFactory("bookName"));
        tb_column_huanshu_borrowDate.setCellValueFactory(new PropertyValueFactory("borrowDate"));
        tb_column_huanshu_backDate.setCellValueFactory(new PropertyValueFactory("backDate"));

    }

    /**
     * 退出登录
     */
    @FXML
    public void logout() {
        userBusinessService.signOut(this.username);
        myApp.gotoLoginUi();
    }

    /**
     * *********************************************借书模块-------开始************************************************
     */

    /**
     * 监听----借书--button--确认
     */
    @FXML
    public void js_confirm_start() {
        User user = userBusinessService.findByUsername(tf_js_reader_id.getText());

        if(!tf_js_book_name.getText().equals("") && !tf_js_reader_id.getText().equals("")) {
            String bookISBN = tf_js_book_id.getText().trim();
            Book book = bookBusinessService.findByISBN(bookISBN);
            String userType = user.getClass().getSimpleName();
            CheckOutResultMessage resultMessage = null;
            switch (userType) {
                case "Undergraduate":
                    Undergraduate u = (Undergraduate) user;
                    resultMessage = bookBusinessService.checkOut(u, book);
                    break;
                case "Graduate":
                    Graduate g = (Graduate) user;
                    resultMessage = bookBusinessService.checkOut(g, book);
                    break;
                case "Teacher":
                    Teacher t = (Teacher) user;
                    resultMessage = bookBusinessService.checkOut(t, book);
                    break;
            }
            if(CheckOutResultMessage.SUCCEEDED == resultMessage) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("借书成功！");
                alert.setTitle("借书成功！");
                alert.show();
                tf_js_book_id.setText("");
                tf_js_reader_id.setText("");
                clear_js_book();
                clear_js_reader();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("借书失败！");
                alert.setTitle("借书失败！");
                alert.show();
            }
        }
        else {
            tf_js_book_id.setText("");
            tf_js_reader_id.setText("");
            clear_js_book();
            clear_js_reader();
            tf_js_book_id.validate();
            tf_js_reader_id.validate();
        }
    }

    /**
     * 监听----借书--书--id--输入回车
     * @param keyEvent
     */
    public void tf_js_book_id_keyEvent(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            String bookISBN = tf_js_book_id.getText().trim();
            Book book = bookBusinessService.findByISBN(bookISBN);
            if (book != null) {
                BookItem bit = new BookItem(book);
                tf_js_book_name.setText(bit.getTitle());
                tf_js_book_author.setText(bit.getAuthor());
                tf_js_book_type.setText(bit.getCategories());
            } else {
                clear_js_book();
            }
        }
    }

    /**
     * 监听----借书--读者--id--输入回车
     * @param keyEvent
     */
    public void tf_js_reader_id_keyEvent(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            User user = userBusinessService.findByUsername(tf_js_reader_id.getText());
            if (user != null) {
                UserItem uit = new UserItem(user);
                tf_js_reader_id.setText(uit.getUsername());
                tf_js_reader_type.setText(uit.getIdentity());
                tf_js_reader_maxNum.setText(String.valueOf(uit.getCountLimitation()));
                tf_js_reader_maxDay.setText(String.valueOf(uit.getPeriodLimitation()));
                lb_js_reader_huanshu_date.setText(getCheckInDate(lb_js_reader_jieshu_date.getText(), user.getPeriodLimitation()));
            } else {
                clear_js_reader();
            }

        }
    }

    private String getCheckInDate(String checkoutDate, int periodLimitation) {
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = parser.parse(checkoutDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, periodLimitation);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            return simpleDateFormat.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 监听----借书--button--清楚
     */
    @FXML
    public void js_clear_start() {
        tf_js_book_id.setText("");
        tf_js_reader_id.setText("");
        clear_js_book();
        clear_js_reader();
    }

    /**
     * 清楚---借书
     */
    public void clear_js_book() {
        tf_js_book_name.setText("");
        tf_js_book_author.setText("");
        tf_js_book_type.setText("");
    }

    /**
     * 清楚---借书
     */
    public void clear_js_reader() {
        tf_js_reader_id.setText("");
        tf_js_reader_type.setText("");
        tf_js_reader_maxNum.setText("");
        tf_js_reader_maxDay.setText("");
        lb_js_reader_huanshu_date.setText("");
    }

    /**
     * *********************************************还书模块-------开始************************************************
     */

    /**
     * 还书模块---监听---读者id
     * @param keyEvent
     */
    private void tf_hs_reader_id_keyEvent(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            User user = userBusinessService.findByUsername(tf_hs_reader_id.getText());
            //如果不为空，则进行
            if (user != null) {
                UserItem uit = new UserItem(user);
                tf_hs_reader_id.setText(uit.getUsername());
                tf_hs_reader_type.setText(uit.getIdentity());
                tf_hs_reader_maxNum.setText(String.valueOf(uit.getCountLimitation()));
                tf_hs_reader_maxDay.setText(String.valueOf(uit.getPeriodLimitation()));
                getReaderBorrowRecord(username);
            } else {
                clear_hs_reader();
            }
        }
    }

    /**
     * 还书模块---用于还书成功后刷新
     */
    private void tf_hs_reader_id_keyEvent() {
        User user = userBusinessService.findByUsername(tf_hs_reader_id.getText());
        //如果不为空，则进行
        if (user != null) {
            UserItem uit = new UserItem(user);
            tf_hs_reader_id.setText(uit.getUsername());
            tf_hs_reader_type.setText(uit.getIdentity());
            tf_hs_reader_maxNum.setText(String.valueOf(uit.getCountLimitation()));
            tf_hs_reader_maxDay.setText(String.valueOf(uit.getPeriodLimitation()));
            getReaderBorrowRecord(username);
        } else {
            clear_hs_reader();
        }
    }

    /**
     * 清楚---还书
     */
    @FXML
    public void clear_hs_reader() {
        tf_hs_reader_id.setText(null);
        tf_hs_reader_type.setText("");
        tf_hs_reader_maxNum.setText("");
        tf_hs_reader_maxDay.setText("");
        tf_hs_book_id.setText("");
        tf_hs_book_name.setText("");
        //先清理原来表格记录
        int size = tbv_huanshu_record.getItems().size();
        for (int i = 0; i < size; i++) {
            tbv_huanshu_record.getItems().remove(0);
        }
    }

    private void getReaderBorrowRecord(String id) {
        ObservableList<CheckOutRecordItem> records = FXCollections.observableArrayList();
        User user = userBusinessService.findByUsername(id);
        for(CheckOutRecord r : userBusinessService.getCheckOutRecordsOf(user)) {
            records.add(new CheckOutRecordItem(r));
        }
        tbv_huanshu_record.setItems(records);
    }

    /**
     * 还书模块---监听---读者id
     * @param keyEvent
     */
    private void tf_hs_book_id_keyEvent(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            String bookISBN = tf_hs_book_id.getText().trim();
            Book book = bookBusinessService.findByISBN(bookISBN);;
            //如果不为空，则进行
            if (book != null) {
                tf_hs_book_name.setText(book.getTitle());
            } else {
                tf_hs_book_name.setText("");
            }
        }
    }

    /**
     * 点击还书按钮----进行还书
     */
    @FXML
    private void huanshu_start() {
        if (!tf_hs_reader_id.getText().trim().equals("") && !tf_hs_book_id.getText().trim().equals("")) {
            User user = userBusinessService.findByUsername(tf_hs_reader_id.getText());
            String bookISBN = tf_js_book_id.getText().trim();
            Book book = bookBusinessService.findByISBN(bookISBN);
            String userType = user.getClass().getSimpleName();
            switch (userType) {
                case "Undergraduate":
                    Undergraduate u = (Undergraduate) user;
                    bookBusinessService.checkIn(u, book);
                    break;
                case "Graduate":
                    Graduate g = (Graduate) user;
                    bookBusinessService.checkIn(g, book);
                    break;
                case "Teacher":
                    Teacher t = (Teacher) user;
                    bookBusinessService.checkIn(t, book);
                    break;
            }
            tf_hs_reader_id_keyEvent();
            tf_hs_book_id.setText("");
            tf_hs_book_name.setText("");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("还书成功！");
            alert.setTitle("还书成功！");
            alert.show();
        } else {
            tf_hs_book_id.validate();
            tf_hs_reader_id.validate();
        }
    }


    /**
     * *********************************************图书维护模块-------开始************************************************
     */

    /**
     * 初始化--图书维护模块
     */
    public void initBookAddUi() {

        RequiredFieldValidator validator_ts_book_add = new RequiredFieldValidator();
        validator_ts_book_add.setMessage("请输入...");
        tf_ts_add_book_id.getValidators().add(validator_ts_book_add);
        tf_ts_add_book_id.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) tf_ts_add_book_id.validate();
        });

        tf_ts_add_book_name.getValidators().add(validator_ts_book_add);
        tf_ts_add_book_name.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) tf_ts_add_book_name.validate();
        });

        tf_ts_add_book_type.getValidators().add(validator_ts_book_add);
        tf_ts_add_book_type.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) tf_ts_add_book_type.validate();
        });

        tf_ts_add_book_author.getValidators().add(validator_ts_book_add);
        tf_ts_add_book_author.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) tf_ts_add_book_author.validate();
        });

        tf_ts_add_book_level.getValidators().add(validator_ts_book_add);
        tf_ts_add_book_level.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) tf_ts_add_book_level.validate();
        });

        tf_ts_add_book_outCount.getValidators().add(validator_ts_book_add);
        tf_ts_add_book_outCount.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) tf_ts_add_book_outCount.validate();
        });

        tf_ts_add_book_stock.getValidators().add(validator_ts_book_add);
        tf_ts_add_book_stock.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) tf_ts_add_book_stock.validate();
        });

        //////*****//////
        tf_ts_alter_book_search_id.getValidators().add(validator_ts_book_add);
        tf_ts_alter_book_search_id.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) tf_ts_alter_book_search_id.validate();
        });

        tf_ts_delete_book_search_id.getValidators().add(validator_ts_book_add);
        tf_ts_delete_book_search_id.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) tf_ts_delete_book_search_id.validate();
        });

    }

    /**
     * 图书信息维护界面-----添加
     * 添加按钮点击事件
     */
    @FXML
    public void ts_book_add() {
        if (!tf_ts_add_book_id.getText().equals("") && !tf_ts_add_book_name.getText().equals("") &&
                !tf_ts_add_book_author.getText().equals("") && !tf_ts_add_book_type.getText().equals("") &&
                !tf_ts_add_book_level.getText().equals("") && !tf_ts_add_book_outCount.getText().equals("") &&
                !tf_ts_add_book_stock.getText().equals("")) {
            String isbn = tf_ts_add_book_id.getText();
            String title = tf_ts_alter_book_name.getText();
            String author = tf_ts_add_book_author.getText();
            String types = tf_ts_add_book_type.getText();
            List<String> categories = new ArrayList<>();
            String[] strs = types.split("、");
            Collections.addAll(categories, strs);
            Level level = Level.valueOf(tf_ts_add_book_level.getText());
            int stock = Integer.parseInt(tf_ts_add_book_stock.getText());
            Book book = new Book(isbn, title, author, categories, "", level, stock);
            BookManagementResultMessage result = bookBusinessService.add(book);

            if (BookManagementResultMessage.SUCCEEDED == result) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("添加成功！");
                alert.setTitle("添加成功！");
                alert.show();
                ts_book_add_clear();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText(result.toString());
                alert.setTitle("添加失败！");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("信息不完整！");
            alert.setTitle("添加错误！");
            alert.show();
        }
    }

    /**
     * 图书信息维护界面-----添加
     * 清楚按钮点击事件
     */
    @FXML
    private void ts_book_add_clear() {
        tf_ts_add_book_id.setText("");
        tf_ts_add_book_name.setText("");
        tf_ts_add_book_author.setText("");
        tf_ts_add_book_type.setText("");
        tf_ts_add_book_level.setText("");
        tf_ts_add_book_outCount.setText("");
        tf_ts_add_book_stock.setText("");
    }

    /**
     * 图书维护---修改---查询图书
     */
    @FXML
    public void ts_book_alter_search() {
        if (!tf_ts_alter_book_search_id.getText().equals("")) {
            Book book = bookBusinessService.findByISBN(tf_ts_alter_book_search_id.getText().trim());
            if (book != null) {
                BookItem bit = new BookItem(book);
                tf_ts_alter_book_id.setText(bit.getIsbn());
                tf_ts_alter_book_name.setText(bit.getTitle());
                tf_ts_alter_book_type.setText(bit.getCategories());
                tf_ts_alter_book_author.setText(bit.getAuthor());
                tf_ts_alter_book_level.setText(bit.getLevel());
                tf_ts_alter_book_outCount.setText(String.valueOf(bit.getOutCount()));
                tf_ts_alter_book_stock.setText(String.valueOf(bit.getRemainingCount()));
            } else {
                tf_ts_alter_book_search_id.setText("");
                tf_ts_alter_book_search_id.validate();
            }
        }
    }

    /**
     * 图书维护---修改
     */
    @FXML
    public void ts_book_alter_start() {
        if (!tf_ts_add_book_id.getText().equals("") && !tf_ts_add_book_name.getText().equals("") &&
                !tf_ts_add_book_author.getText().equals("") && !tf_ts_add_book_type.getText().equals("") &&
                !tf_ts_add_book_level.getText().equals("") && !tf_ts_add_book_outCount.getText().equals("") &&
                !tf_ts_add_book_stock.getText().equals("")) {
            String isbn = tf_ts_add_book_id.getText();
            String title = tf_ts_alter_book_name.getText();
            String author = tf_ts_add_book_author.getText();
            String types = tf_ts_add_book_type.getText();
            List<String> categories = new ArrayList<>();
            String[] strs = types.split("、");
            Collections.addAll(categories, strs);
            Level level = Level.valueOf(tf_ts_add_book_level.getText());
            int stock = Integer.parseInt(tf_ts_add_book_stock.getText());
            Book book = new Book(isbn, title, author, categories, "", level, stock);
            BookManagementResultMessage result = bookBusinessService.editBookInfo(book);
            if (BookManagementResultMessage.SUCCEEDED == result) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("修改成功！");
                alert.setTitle("修改成功！");
                alert.show();
                ts_book_alter_clear();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText(result.toString());
                alert.setTitle("修改失败！");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("信息不完整！");
            alert.setTitle("修改错误！");
            alert.show();
        }
    }

    /**
     * 图书信息维护界面-----修改
     * 清楚按钮点击事件
     */
    @FXML
    private void ts_book_alter_clear() {
        tf_ts_add_book_id.setText("");
        tf_ts_add_book_name.setText("");
        tf_ts_add_book_author.setText("");
        tf_ts_add_book_type.setText("");
        tf_ts_add_book_level.setText("");
        tf_ts_add_book_outCount.setText("");
        tf_ts_add_book_stock.setText("");
    }


    /**
     * 图书维护---删除---查询图书
     */
    @FXML
    public void ts_book_delete_search() {
        if (!tf_ts_delete_book_search_id.getText().equals("")) {
            Book book = bookBusinessService.findByISBN(tf_ts_alter_book_search_id.getText().trim());
            if (book != null) {
                BookItem bit = new BookItem(book);
                tf_ts_alter_book_id.setText(bit.getIsbn());
                tf_ts_alter_book_name.setText(bit.getTitle());
                tf_ts_alter_book_type.setText(bit.getCategories());
                tf_ts_alter_book_author.setText(bit.getAuthor());
                tf_ts_alter_book_level.setText(bit.getLevel());
                tf_ts_alter_book_outCount.setText(String.valueOf(bit.getOutCount()));
                tf_ts_alter_book_stock.setText(String.valueOf(bit.getRemainingCount()));
            } else {
                tf_ts_delete_book_search_id.setText("");
                tf_ts_delete_book_search_id.validate();
            }
        }

    }

    /**
     * 图书维护---删除--按钮
     */
    @FXML
    public void ts_book_delete() {
        if (!tf_ts_delete_book_id.getText().equals("")) {
            String isbn = tf_ts_add_book_id.getText();
            String title = tf_ts_alter_book_name.getText();
            String author = tf_ts_add_book_author.getText();
            String types = tf_ts_add_book_type.getText();
            List<String> categories = new ArrayList<>();
            String[] strs = types.split("、");
            Collections.addAll(categories, strs);
            Level level = Level.valueOf(tf_ts_add_book_level.getText());
            int stock = Integer.parseInt(tf_ts_add_book_stock.getText());
            Book book = new Book(isbn, title, author, categories, "", level, stock);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setAlertType(Alert.AlertType.CONFIRMATION);
            alert.setContentText("确认删除？");
            alert.setTitle("确认删除！");
            alert.showAndWait();
            ButtonType type = alert.getResult();
            System.out.println("type="+type.getText());
            if (type == ButtonType.OK) {
                BookManagementResultMessage result = bookBusinessService.remove(book);
                if (BookManagementResultMessage.SUCCEEDED == result) {
                    Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                    alert1.setAlertType(Alert.AlertType.INFORMATION);
                    alert1.setContentText(result.toString());
                    alert1.setTitle("删除成功！");
                    alert1.show();
                    ts_book_delete_clear();
                } else {
                    Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                    alert2.setAlertType(Alert.AlertType.ERROR);
                    alert2.setContentText("删除失败！");
                    alert2.setTitle("删除失败！");
                    alert2.show();
                }
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("信息不完整！");
            alert.setTitle("删除错误！");
            alert.show();
        }
    }

    /**
     * 图书信息维护界面-----删除
     * 清楚按钮点击事件
     */
    @FXML
    private void ts_book_delete_clear() {
        tf_ts_add_book_id.setText("");
        tf_ts_add_book_name.setText("");
        tf_ts_add_book_author.setText("");
        tf_ts_add_book_type.setText("");
        tf_ts_add_book_level.setText("");
        tf_ts_add_book_outCount.setText("");
        tf_ts_add_book_stock.setText("");
    }


    /**
     * *********************************************图书维护模块-------结束************************************************
     */


    /**
     * *********************************************读者维护模块-------开始************************************************
     */

    /**
     * 读者维护模块初始化
     */
    private void initReaderAddUi() {

        for (int i = 0; i < Constant.READER_YTPES.length; i++) {
            cb_rd_add_reader_type.getItems().addAll(Constant.READER_YTPES[i]);
            cb_rd_alter_reader_type.getItems().addAll(Constant.READER_YTPES[i]);
            cb_rd_delete_reader_type.getItems().addAll(Constant.READER_YTPES[i]);
        }
        cb_rd_add_reader_type.getSelectionModel().selectFirst();
        cb_rd_alter_reader_type.getSelectionModel().selectFirst();
        cb_rd_delete_reader_type.getSelectionModel().selectFirst();

        for (int i = 0; i < Constant.SEX.length; i++) {
            cb_rd_add_reader_sex.getItems().addAll(Constant.SEX[i]);
            cb_rd_alter_reader_sex.getItems().addAll(Constant.SEX[i]);
            cb_rd_delete_reader_sex.getItems().addAll(Constant.SEX[i]);
        }

        cb_rd_add_reader_sex.getSelectionModel().selectFirst();
        cb_rd_alter_reader_sex.getSelectionModel().selectFirst();
        cb_rd_delete_reader_sex.getSelectionModel().selectFirst();

        RequiredFieldValidator validator_ts_book_add = new RequiredFieldValidator();
        validator_ts_book_add.setMessage("请输入...");
        tf_rd_alter_reader_search_id.getValidators().add(validator_ts_book_add);
        tf_rd_alter_reader_search_id.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) tf_rd_alter_reader_search_id.validate();
        });

        tf_rd_delete_reader_search_id.getValidators().add(validator_ts_book_add);
        tf_rd_delete_reader_search_id.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) tf_rd_delete_reader_search_id.validate();
        });

    }

    /**
     * 添加新的读者
     */
    @FXML
    public void add_new_reader() {
        if (!tf_rd_add_reader_id.getText().equals("") && !tf_rd_add_reader_name.getText().equals("") && !tf_rd_add_reader_numbers.getText().equals("") && !tf_rd_add_reader_days.getText().equals("") &&
                !cb_rd_add_reader_type.getSelectionModel().getSelectedItem().toString().equals("") && !cb_rd_add_reader_sex.getSelectionModel().getSelectedItem().toString().equals("")) {

            String identity = cb_rd_add_reader_type.getSelectionModel().getSelectedItem().toString();
            SignUpResultMessage result = null;
            switch (identity) {
                case "本科生":
                    result = userBusinessService.signUp(tf_rd_add_reader_id.getText(), DEFAULT_PASSWORD, UserType.UNDERGRADUATE);
                    break;
                case "研究生":
                    result = userBusinessService.signUp(tf_rd_add_reader_id.getText(), DEFAULT_PASSWORD, UserType.GRADUATE);
                    break;
                case "教师":
                    result = userBusinessService.signUp(tf_rd_add_reader_id.getText(), DEFAULT_PASSWORD, UserType.TEACHER);
                    break;
                case "管理员":
                    result = userBusinessService.signUp(tf_rd_add_reader_id.getText(), DEFAULT_PASSWORD, UserType.ADMINISTRATOR);
                    break;
            }

            if (SignUpResultMessage.SUCCEEDED == result) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("添加成功！");
                alert.setTitle("添加成功！");
                alert.show();
                rd_reader_add_clear();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText(result.toString());
                alert.setTitle("添加失败！");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("信息不完整！");
            alert.setTitle("添加错误！");
            alert.show();
        }
    }

    /**
     * 读者信息维护界面-----添加
     * 清楚按钮点击事件
     */
    @FXML
    private void rd_reader_add_clear() {
        tf_rd_add_reader_id.setText("");
        tf_rd_add_reader_name.setText("");
        tf_rd_add_reader_numbers.setText("");
        tf_rd_add_reader_days.setText("");
    }

    /**
     * 修改读者
     */
    @FXML
    public void alter_rd_reader() {
        if (!tf_rd_alter_reader_id.getText().equals("") && !tf_rd_alter_reader_name.getText().equals("") && !tf_rd_alter_reader_numbers.getText().equals("") && !tf_rd_alter_reader_days.getText().equals("") &&
                !cb_rd_alter_reader_type.getSelectionModel().getSelectedItem().toString().equals("") && !cb_rd_alter_reader_sex.getSelectionModel().getSelectedItem().toString().equals("")) {
            User user = userBusinessService.findByUsername(tf_rd_alter_reader_days.getText());

            userBusinessService.updatePersonalInfo(user);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("修改成功！");
            alert.setTitle("修改成功！");
            alert.show();
            rd_reader_alter_clear();
        }
    }

    /**
     * 读者维护---修改---查询读者
     */
    @FXML
    public void rd_reader_alter_search() {
        if (!tf_rd_alter_reader_search_id.getText().equals("")) {
            User user = userBusinessService.findByUsername(tf_rd_alter_reader_days.getText());
            if (user != null) {
                UserItem uit = new UserItem(user);
                tf_rd_delete_reader_id.setText(uit.getUsername());;
                if (uit.getIdentity().equals("教师")) {
                    cb_rd_delete_reader_type.getSelectionModel().selectFirst();
                } else if (uit.getIdentity().equals("学生")) {
                    cb_rd_delete_reader_type.getSelectionModel().select(1);
                } else {
                    cb_rd_delete_reader_type.getSelectionModel().select(2);
                }
                tf_rd_delete_reader_numbers.setText(String.valueOf(uit.getCountLimitation()));
                tf_rd_delete_reader_days.setText(String.valueOf(uit.getPeriodLimitation()));
            } else {
                tf_rd_alter_reader_search_id.setText("");
                tf_rd_alter_reader_search_id.validate();
            }
        }

    }

    /**
     * 读者信息维护界面-----修改
     * 清楚按钮点击事件
     */
    @FXML
    private void rd_reader_alter_clear() {
        tf_rd_alter_reader_id.setText("");
        tf_rd_alter_reader_name.setText("");
        tf_rd_alter_reader_numbers.setText("");
        tf_rd_alter_reader_days.setText("");
    }


    /**
     * 读者维护---删除---读者查询
     */
    @FXML
    public void rd_reader_delete_search() {
        if (!tf_rd_delete_reader_search_id.getText().equals("")) {
            User user = userBusinessService.findByUsername(tf_rd_delete_reader_days.getText());
            if (user != null) {
                UserItem uit = new UserItem(user);
                tf_rd_delete_reader_id.setText(uit.getUsername());;
                if (uit.getIdentity().equals("教师")) {
                    cb_rd_delete_reader_type.getSelectionModel().selectFirst();
                } else if (uit.getIdentity().equals("学生")) {
                    cb_rd_delete_reader_type.getSelectionModel().select(1);
                } else {
                    cb_rd_delete_reader_type.getSelectionModel().select(2);
                }
                tf_rd_delete_reader_numbers.setText(String.valueOf(uit.getCountLimitation()));
                tf_rd_delete_reader_days.setText(String.valueOf(uit.getPeriodLimitation()));
            } else {
                tf_rd_delete_reader_search_id.setText("");
                tf_rd_delete_reader_search_id.validate();
            }
        }

    }

    /**
     * 删除读者
     */
    @FXML
    public void delete_rd_reader() {
        if (!tf_rd_delete_reader_id.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setAlertType(Alert.AlertType.CONFIRMATION);
            alert.setContentText("确认删除？");
            alert.setTitle("确认删除！");
            alert.showAndWait();
            ButtonType type = alert.getResult();
            if (type == ButtonType.OK) {
                User user = userBusinessService.findByUsername(tf_rd_delete_reader_days.getText());
                CancelResultMessage result = userBusinessService.cancel(user.getUsername(), user.getPassword());
                if(CancelResultMessage.SUCCEEDED == result) {
                    Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                    alert1.setAlertType(Alert.AlertType.INFORMATION);
                    alert1.setContentText("删除成功！");
                    alert1.setTitle("删除成功！");
                    alert1.show();
                    rd_reader_delete_clear();
                }
                else {
                    Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setContentText("信息不完整！");
                    alert.setTitle("删除错误！");
                    alert.show();
                }
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("信息不完整！");
            alert.setTitle("删除错误！");
            alert.show();
        }
    }

    /**
     * 读者信息维护界面-----删除
     * 清楚按钮点击事件
     */
    @FXML
    private void rd_reader_delete_clear() {
        tf_rd_delete_reader_id.setText("");
        tf_rd_delete_reader_name.setText("");
        tf_rd_delete_reader_numbers.setText("");
        tf_rd_delete_reader_days.setText("");
    }


    /**
     * *********************************************读者维护模块-------结束************************************************
     */


    /**
     * *********************************************所有图书、读者、借阅显示模块-------开始************************************************
     */

    /**
     * 获取全部书目,并显示
     */
    @FXML
    public void getAllBooks() {
        ObservableList<BookItem> books = FXCollections.observableArrayList();
        for(Book b : bookBusinessService.findAll()) {
            books.add(new BookItem(b));
        }
        tbv_book.setItems(books);
    }

    /**
     * 获取全部读者,并显示
     */
    @FXML
    public void getAllReaders() {
        ObservableList<UserItem> users = FXCollections.observableArrayList();
        for(User u : userBusinessService.findAll()) {
            users.add(new UserItem(u));
            System.out.println(u.toString());
        }
        tbv_reader.setItems(users);
    }

    /**
     * 获取全部借阅记录
     */
    public void getAllBorrowedRecordings() {
        ObservableList<CheckOutRecordItem> records = FXCollections.observableArrayList();
        for(CheckOutRecord r : userBusinessService.getAllCheckOutRecords()) {
            records.add(new CheckOutRecordItem(r));
        }
        tbv_borrow.setItems(records);
    }

}
