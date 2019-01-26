package main.common.user.visitor;

import main.common.resultmessage.ResultMessage;

public interface Element {

    ResultMessage accept(Visitor visitor);

}
