package nl.inholland.javafx;

public class Player {
    boolean winStatus;
    Symbols symbol;

    public Player(Symbols symbol) {
        this.symbol = symbol;
        winStatus = false;
    }
}
