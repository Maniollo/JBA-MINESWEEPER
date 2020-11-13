package masmar.home.jba.minesweeper;

enum Symbol {
    MINE('X'),
    ONE('1'),
    TWO('2'),
    THREE('3'),
    FOUR('4'),
    FIVE('5'),
    SIX('6'),
    SEVEN('7'),
    EIGHT('8'),
    SAFE('.');
    private final char symbol;

    Symbol(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
}
