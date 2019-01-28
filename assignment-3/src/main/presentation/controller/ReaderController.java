package main.presentation.controller;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import main.business.impl.BookBusinessServiceImpl;
import main.business.impl.ReaderBusinessServiceImpl;
import main.business.impl.UserBusinessServiceImpl;
import main.business.service.BookBusinessService;
import main.business.service.ReaderBusinessService;
import main.business.service.UserBusinessService;
import main.common.book.Book;
import main.common.book.CheckOutRecord;
import main.common.resultmessage.CheckOutResultMessage;
import main.common.user.*;
import main.Login;
import main.presentation.entity.BookItem;
import main.presentation.entity.CheckOutRecordItem;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReaderController implements Initializable {
    @FXML
    private JFXTextField tf_reader_search;//搜索框
    @FXML
    private JFXTextField tf_search_bookISBN;//要借图书isbn
    @FXML
    private JFXTextField tf_search_bookName;//要借图书名称

    @FXML
    private TableView tbv_search_Result;
    @FXML
    private TableColumn tb_column_book_isbn;
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
    @FXML
    private TableColumn tb_column_book_onlineReading;

    //用户信息
    @FXML
    private JFXTextField tf_userInfo_readerId;
    @FXML
    private JFXTextField tf_userInfo_readerType;
    @FXML
    private JFXTextField tf_userInfo_readerMaxNumbers;
    @FXML
    private JFXTextField tf_userInfo_readerMaxDays;
    @FXML
    private JFXTextField tf_userInfo_readerForfeit;

    @FXML
    private TableView tbv_userInfo_borrowRecord;
    @FXML
    private TableColumn tb_column_userInfo_bookId;
    @FXML
    private TableColumn tb_column_userInfo_bookName;
    @FXML
    private TableColumn tb_column_userInfo_backDate;
    @FXML
    private TableColumn tb_column_userInfo_reBorrow;

    @FXML
    private Label lb_search_resultNumber;
    @FXML
    private Label lb_welcome;
    @FXML
    private Label lb_borrow_reader_checkout_date;
    @FXML
    private Label lb_borrow_reader_checkin_date;
    @FXML
    private JFXTextField tf_userInfo_payment;

    private Login myApp;

    private String username;

    private UserBusinessService userBusinessService = UserBusinessServiceImpl.getInstance();

    private BookBusinessService bookBusinessService = BookBusinessServiceImpl.getInstance();

    private ReaderBusinessService readerBusinessService = ReaderBusinessServiceImpl.getInstance();

    public void setApp(Login myApp) {
        this.myApp = myApp;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tf_reader_search.setOnKeyPressed(this::tf_reader_search_keyEvent);
        tf_search_bookISBN.setOnKeyPressed(this::tf_search_book_keyEvent);

        //所有书目列表初始化
        tb_column_book_isbn.setCellValueFactory(new PropertyValueFactory("ISBN"));
        tb_column_book_name.setCellValueFactory(new PropertyValueFactory("title"));
        tb_column_book_type.setCellValueFactory(new PropertyValueFactory("types"));
        tb_column_book_author.setCellValueFactory(new PropertyValueFactory("author"));
        tb_column_book_level.setCellValueFactory(new PropertyValueFactory("level"));
        tb_column_book_outCount.setCellValueFactory(new PropertyValueFactory("outCount"));
        tb_column_book_remainingCount.setCellValueFactory(new PropertyValueFactory("remainingCount"));
        tb_column_book_onlineReading.setCellValueFactory((Callback<TableColumn.CellDataFeatures<BookItem, Boolean>, ObservableValue<Boolean>>) p -> new SimpleBooleanProperty(p.getValue() != null));
        tb_column_book_onlineReading.setCellFactory((Callback<TableColumn<BookItem, Boolean>, TableCell<BookItem, Boolean>>) p -> new ButtonCell_1());

        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("请输入...");
        tf_search_bookISBN.getValidators().add(validator);
        tf_search_bookISBN.getValidators().add(validator);
        tf_search_bookISBN.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) tf_search_bookISBN.validate();
        });
    }

    private class ButtonCell_1 extends TableCell<BookItem, Boolean> {
        final Button cellButton1 = new Button("阅读");
        ButtonCell_1(){
            cellButton1.setOnAction(t -> {
                // do something when button clicked
                tbv_search_Result.getSelectionModel().select(getTableRow().getIndex());
                ObservableList<BookItem> userbb = tbv_search_Result.getItems();
                String bookId = userbb.get(getTableRow().getIndex()).getIsbn();
                String content = readerBusinessService.show(bookId);
                myApp.gotoOnlineReaderUi(content, username);
            });
        }

        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
                setGraphic(cellButton1);
            }
        }
    }

    //Define the button cell
    private class ButtonCell extends TableCell<CheckOutRecordItem, Boolean> {
        final Button cellButton = new Button("续借");

        ButtonCell(){
            cellButton.setOnAction(t -> {
                // do something when button clicked
                tbv_userInfo_borrowRecord.getSelectionModel().select(getTableRow().getIndex());
                ObservableList<CheckOutRecordItem> userbb = tbv_userInfo_borrowRecord.getItems();
                String bookId = userbb.get(getTableRow().getIndex()).getBookIsbn();
                String backDate = userbb.get(getTableRow().getIndex()).getToDate();
                reBorrow(bookId, backDate);
            });
        }

        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
                setGraphic(cellButton);
            }
        }
    }

    private void reBorrow(String bookISBN, String checkInDate) {
        User user = userBusinessService.findByUsername(this.username);
        String newCheckInDate = getCheckInDate(checkInDate, user.getPeriodLimitation());
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
            getBorrowedRecordings(username);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("续借成功,应还日期为：" + newCheckInDate);
            alert.setTitle("续借成功！");
            alert.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("续借失败！");
            alert.setTitle("提示！");
            alert.show();
        }
    }

    /**
     * 确认图书
     * @param keyEvent
     */
    private void tf_search_book_keyEvent(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            Book book = bookBusinessService.findByISBN(tf_search_bookISBN.getText());
            if (book != null) {
                tf_search_bookName.setText(book.getTitle());
            }
        }
    }

    @FXML
    public void tf_search_book() {
        if (!tf_search_bookISBN.getText().equals("")) {
            Book book = bookBusinessService.findByISBN(tf_search_bookISBN.getText());
            if (book != null) {
                tf_search_bookName.setText(book.getTitle());
            }
        } else {
            tf_search_bookISBN.validate();
        }
    }

    /**
     * 搜索图书---监听回车
     * @param keyEvent
     */
    private void tf_reader_search_keyEvent(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            ObservableList<BookItem> books = FXCollections.observableArrayList();
            for(Book b : bookBusinessService.findByTitle(tf_reader_search.getText())) {
                BookItem bit = new BookItem(b);
                books.add(bit);
            }
            for(Book b : bookBusinessService.findByAuthor(tf_reader_search.getText())) {
                BookItem bit = new BookItem(b);
                books.add(bit);
            }
            tbv_search_Result.setItems(books);
            lb_search_resultNumber.setText(tbv_search_Result.getItems().size()+" 条记录");
        }
    }

    /**
     * 搜索图书
     */
    @FXML
    private void tf_reader_search() {
        ObservableList<BookItem> books = FXCollections.observableArrayList();
        for(Book b : bookBusinessService.findByTitle(tf_reader_search.getText())) {
            books.add(new BookItem(b));
        }
        for(Book b : bookBusinessService.findByAuthor(tf_reader_search.getText())) {
            books.add(new BookItem(b));
        }
        tbv_search_Result.setItems(books);
        lb_search_resultNumber.setText(tbv_search_Result.getItems().size()+" 条记录");
    }

    /**
     * 借书
     */
    @FXML
    public void borrowBook() {
        User user = userBusinessService.findByUsername(this.username);
        String bookISBN = tf_search_bookISBN.getText().trim();
        if(!bookISBN.equals("")) {
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
                getBorrowedRecordings(username);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("借书成功！");
                alert.setTitle("借书成功！");
                alert.show();
                getBorrowedRecordings(username);
                tf_search_bookISBN.setText("");
                tf_search_bookName.setText("");
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
            tf_search_bookISBN.validate();
        }
    }

    /**
     * 设置用户信息
     * @param username
     */
    public void setUserInfo(String username) {
        this.username = username;
        User user = userBusinessService.findByUsername(this.username);
        String userType = user.getClass().getSimpleName();
        UserType type = UserType.UNDERGRADUATE;
        switch (userType) {
            case "Graduate": type = UserType.GRADUATE; break;
            case "Teacher": type = UserType.TEACHER; break;
            case "Administrator":type = UserType.UNDERGRADUATE; break;
            default: assert false : "Unknown User Type" + userType;
        }
        tf_userInfo_readerId.setText(user.getUsername());
        tf_userInfo_readerType.setText(type.toString());
        tf_userInfo_readerMaxNumbers.setText(String.valueOf(user.getCountLimitation()));
        tf_userInfo_readerMaxDays.setText(String.valueOf(user.getPeriodLimitation()));
        tf_userInfo_readerForfeit.setText("0");

        lb_welcome.setText(username + " ，您好！");

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        //初始化当前时间
        lb_borrow_reader_checkout_date.setText(df.format(new Date()));
        lb_borrow_reader_checkin_date.setText(getCheckInDate(lb_borrow_reader_checkout_date.getText(), user.getPeriodLimitation()));

        getBorrowedRecordings(username);
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
     * 获取该用户全部借阅记录
     */
    private void getBorrowedRecordings(String username) {
        tb_column_userInfo_bookId.setCellValueFactory(new PropertyValueFactory("bookISBN"));
        tb_column_userInfo_bookName.setCellValueFactory(new PropertyValueFactory("bookTitle"));
        tb_column_userInfo_backDate.setCellValueFactory(new PropertyValueFactory("backDate"));
        tb_column_userInfo_reBorrow.setCellValueFactory((Callback<TableColumn.CellDataFeatures<CheckOutRecordItem, Boolean>, ObservableValue<Boolean>>) p -> new SimpleBooleanProperty(p.getValue() != null));
        tb_column_userInfo_reBorrow.setCellFactory((Callback<TableColumn<CheckOutRecordItem, Boolean>, TableCell<CheckOutRecordItem, Boolean>>) p -> new ButtonCell());

        ObservableList<CheckOutRecordItem> records = FXCollections.observableArrayList();
        User user = userBusinessService.findByUsername(username);
        for(CheckOutRecord r : userBusinessService.getCheckOutRecordsOf(user)) {
            records.add(new CheckOutRecordItem(r));
        }
        tbv_userInfo_borrowRecord.setItems(records);
    }

    /**
     * 还款
     */
    @FXML
    public void payment() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("支付成功！");
        alert.setTitle("提示！");
        alert.show();
        tf_userInfo_payment.setText("0");
    }

    /**
     * 退出登录
     */
    @FXML
    public void logout() {
        userBusinessService.signOut(this.username);
        myApp.gotoLoginUi();
    }

}
