package main.common.book;

public enum Level {

    ORDINARY("普通"),
    RARE("珍藏本");

    private String literal;

    Level(String literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return literal;
    }

}
