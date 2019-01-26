package main.common.user.message;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Message {

    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private String content;
    private Date receivedDate;
    private boolean checked;

    public Message(String content, Date receivedDate) {
        this.content = content;
        this.receivedDate = receivedDate;
        this.checked = false;
    }

    public String getContent() {
        return content;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return (checked ? "已读" : "未读")
                + "    "
                + DATE_FORMATTER.format(receivedDate) + System.lineSeparator()
                + content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Message that = (Message) o;
        return Objects.equals(content, that.content)
                && Objects.equals(receivedDate, that.receivedDate)
                && (checked == that.checked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, receivedDate, checked);
    }

}
