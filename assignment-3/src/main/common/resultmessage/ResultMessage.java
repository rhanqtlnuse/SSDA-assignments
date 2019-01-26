package main.common.resultmessage;

public abstract class ResultMessage {

    private String name;
    private int ordinal;

    private String literal;

    protected ResultMessage(String name, int ordinal, String literal) {
        this.name = name;
        this.ordinal = ordinal;
        this.literal = literal;
    }

    public final int ordinal() {
        return ordinal;
    }

    @Override
    public String toString() {
        return literal;
    }

}
