package masmar.home.jba.minesweeper;

enum Symbol {
    MINE('X'),
    SAFE('.');
    private final char symbol;

    Symbol(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
}
