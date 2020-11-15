package masmar.home.jba.minesweeper;

enum Symbol {
    MINE('.'),
    ONE('1'),
    TWO('2'),
    THREE('3'),
    FOUR('4'),
    FIVE('5'),
    SIX('6'),
    SEVEN('7'),
    EIGHT('8'),
    MARKED_MISS('*'),
    MARKED_MINE('*'),
    SAFE('.');
    private final char symbol;

    Symbol(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public boolean isNumber() {
        return this != MINE && this != SAFE && this != MARKED_MINE && this != MARKED_MISS;
    }
}
