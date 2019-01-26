package main.common.user.visitor;

import main.common.resultmessage.ResultMessage;
import main.common.user.Graduate;
import main.common.user.Teacher;
import main.common.user.Undergraduate;

public abstract class Visitor {

    public abstract ResultMessage visit(Teacher e);
    public abstract ResultMessage visit(Graduate e);
    public abstract ResultMessage visit(Undergraduate e);

}
