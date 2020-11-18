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
    SAFE('.'),

    MARKED_MISS('*'),
    MARKED_MINE('*'),

    UNEXPLORED('.'),
    EXPLORED_WO_MINES('/');
    private final char symbol;

    Symbol(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public boolean isNumber() {
        return this != MINE && this != SAFE && this != MARKED_MINE && this != MARKED_MISS && this != UNEXPLORED && this != EXPLORED_WO_MINES;
    }
}
