package main.common.user;

import main.common.resultmessage.CheckOutResultMessage;
import main.common.resultmessage.ResultMessage;
import main.common.user.visitor.Visitor;

public class Administrator extends User {

    public Administrator(String username, String password) {
        super(username, password);
    }

    @Override
    public ResultMessage accept(Visitor visitor) {
        return CheckOutResultMessage.NOT_ALLOWED;
    }

}
