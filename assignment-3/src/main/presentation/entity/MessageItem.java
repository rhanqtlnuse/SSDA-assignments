package main.presentation.entity;

import main.common.user.message.Message;

import java.text.SimpleDateFormat;

public class MessageItem {
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");
    private String content;
    private String date;
    private int isChecked;

    public MessageItem() {}

    public MessageItem(Message m) {
        this.content = m.getContent();
        this.date = DATE_FORMATTER.format(m.getReceivedDate());
        this.isChecked = m.isChecked() ? 1 : 0;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(int isChecked) {
        this.isChecked = isChecked;
    }
}
