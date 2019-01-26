package main.common.user.message;

import java.util.ArrayList;
import java.util.List;

public class MessageBox {

    private List<Message> messages;

    public MessageBox() {
        this.messages = new ArrayList<>();
    }

    public void add(Message msg) {
        if (msg != null) {
            messages.add(msg);
        }
    }

    public void remove(Message msg) {
        if (msg != null) {
            messages.remove(msg);
        }
    }

    /**
     * 将消息标记为已读
     *
     * @param msg 消息
     */
    public void check(Message msg) {
        if (msg != null && messages.contains(msg)) {
            int i = messages.indexOf(msg);
            msg.setChecked(true);
            messages.set(i, msg);
        }
    }

    /**
     * 将消息标记为未读
     *
     * @param msg 消息
     */
    public void uncheck(Message msg) {
        if (msg != null && messages.contains(msg)) {
            int i = messages.indexOf(msg);
            msg.setChecked(false);
            messages.set(i, msg);
        }
    }

    public List<Message> getMessages() {
        return messages;
    }

}
