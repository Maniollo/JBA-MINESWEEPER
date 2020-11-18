package masmar.home.jba.minesweeper;

import java.util.Random;

import static masmar.home.jba.minesweeper.Result.LOSE;
import static masmar.home.jba.minesweeper.Result.ON_GOING;
import static masmar.home.jba.minesweeper.Result.WIN;
import static masmar.home.jba.minesweeper.Symbol.EIGHT;
import static masmar.home.jba.minesweeper.Symbol.EXPLORED_WO_MINES;
import static masmar.home.jba.minesweeper.Symbol.FIVE;
import static masmar.home.jba.minesweeper.Symbol.FOUR;
import static masmar.home.jba.minesweeper.Symbol.MARKED_MINE;
import static masmar.home.jba.minesweeper.Symbol.MARKED_MISS;
import static masmar.home.jba.minesweeper.Symbol.MINE;
import static masmar.home.jba.minesweeper.Symbol.ONE;
import static masmar.home.jba.minesweeper.Symbol.SAFE;
import static masmar.home.jba.minesweeper.Symbol.SEVEN;
import static masmar.home.jba.minesweeper.Symbol.SIX;
import static masmar.home.jba.minesweeper.Symbol.THREE;
import static masmar.home.jba.minesweeper.Symbol.TWO;
import static masmar.home.jba.minesweeper.Symbol.UNEXPLORED;

class MineField {
    private static final int SIZE = 9;
    private Symbol[][] field;
    private Symbol[][] currentField;
    private int shotCounter;
    private int missCounter;
    private int minesCount;

    public MineField(int minesCount) {
        this.field = new Symbol[SIZE][SIZE];
        this.currentField = new Symbol[SIZE][SIZE];
        this.minesCount = minesCount;
        initCurrentMineField();
        initPlayerMineField();
        addMinesRandomly();
        addNeighbourCounters();
    }

    //    ONLY FOR TESTING
    MineField(Symbol[][] field, int minesCount) {
        this.currentField = new Symbol[SIZE][SIZE];
        initCurrentMineField();
        this.field = field;
        this.minesCount = minesCount;
        addNeighbourCounters();
    }

    Result shot(int row, int column, Command command) {
        int fieldRow = row - 1;
        int fieldColumn = column - 1;

        switch (command) {
            case MINE:
                return handleMineCommand(fieldRow, fieldColumn);
            case FREE:
                return handleFreeCommand(fieldRow, fieldColumn);
            default:
                return ON_GOING;
        }
    }

    String display() {
        return displayMineField(currentField);
    }

    @Override
    public String toString() {
        return displayMineField(field);
    }

    private String displayMineField(Symbol[][] field) {
        StringBuilder output = new StringBuilder();
        output.append(" |123456789|\n").append("-|---------|\n");
        for (int i = 0; i < SIZE; i++) {
            output.append(i + 1).append("|");
            for (int j = 0; j < SIZE; j++) {
                output.append(field[i][j].getSymbol());
                if (j == 8) {
                    output.append("|\n");
                }
            }
        }
        output.append("-|---------|");
        return output.toString();
    }

    private Result handleFreeCommand(int row, int column) {
        Symbol value = field[row][column];
        if (value == MINE) {
            currentField[row][column] = MINE;
            return LOSE;
        } else if (value.isNumber()) {
            if (currentField[row][column] == MARKED_MISS) {
                missCounter -= 1;
            }
            currentField[row][column] = value;
        } else {
            findRecursive(row, column);
        }

        if ((shotCounter == minesCount || findMarked() == 81 - minesCount) && missCounter == 0) {
            return WIN;
        } else {
            return ON_GOING;
        }
    }

    private Result handleMineCommand(int row, int column) {
        Symbol value = field[row][column];
        if (value == MINE && currentField[row][column] == UNEXPLORED) {
            currentField[row][column] = MARKED_MINE;
            shotCounter += 1;
        } else if (value == MINE && currentField[row][column] == MARKED_MINE) {
            currentField[row][column] = UNEXPLORED;
            shotCounter -= 1;
        } else if (value != MINE && currentField[row][column] == UNEXPLORED) {
            currentField[row][column] = MARKED_MISS;
            missCounter += 1;
        } else if (value != MINE && currentField[row][column] == MARKED_MISS) {
            currentField[row][column] = UNEXPLORED;
            missCounter -= 1;
        }

        if ((shotCounter == minesCount || findMarked() == 81 - minesCount) && missCounter == 0) {
            return WIN;
        } else {
            return ON_GOING;
        }
    }

    private int findMarked() {
        int counter = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                Symbol symbol = currentField[i][j];
                if (symbol.isNumber() || symbol == EXPLORED_WO_MINES) {
                    counter += 1;
                }
            }
        }
        return counter;
    }

    private void recursiveFindSafe(int row, int column) {
        if (isOutOfRange(row, column) || currentField[row][column] == EXPLORED_WO_MINES) {
            return;
        }

        if (field[row][column].isNumber()) {
            currentField[row][column] = field[row][column];
            return;
        }

        if (currentField[row][column] == MARKED_MISS || currentField[row][column] == MARKED_MINE) {

            if (field[row][column].isNumber()) {
                currentField[row][column] = field[row][column];
            } else {
                currentField[row][column] = EXPLORED_WO_MINES;
            }
        }
        findRecursive(row, column);
    }

    private boolean isOutOfRange(int row, int column) {
        return row < 0 || row >= SIZE || column < 0 || column >= SIZE;
    }

    private void findRecursive(int row, int column) {
        currentField[row][column] = EXPLORED_WO_MINES;
        recursiveFindSafe(row - 1, column);
        recursiveFindSafe(row - 1, column + 1);
        recursiveFindSafe(row, column + 1);
        recursiveFindSafe(row + 1, column + 1);
        recursiveFindSafe(row + 1, column);
        recursiveFindSafe(row + 1, column - 1);
        recursiveFindSafe(row, column - 1);
        recursiveFindSafe(row - 1, column - 1);
    }

    private void initCurrentMineField() {
        fillField(currentField, UNEXPLORED);
    }

    private void initPlayerMineField() {
        fillField(field, SAFE);
    }

    private void fillField(Symbol[][] field, Symbol symbol) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                field[i][j] = symbol;
            }
        }
    }

    private void addNeighbourCounters() {
        int counter;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (field[i][j] == MINE) {
                    continue;
                }
                counter = 0;
                if (i > 0) {
                    if (j > 0) {
                        if (field[i - 1][j - 1] == MINE) {   // TOP LEFT
                            counter += 1;
                        }
                    }

                    if (j < SIZE - 1) {
                        if (field[i - 1][j + 1] == MINE) {   // TOP RIGHT
                            counter += 1;
                        }
                    }

                    if (field[i - 1][j] == MINE) {   // TOP
                        counter += 1;
                    }
                }

                if (i < SIZE - 1) {
                    if (j > 0) {
                        if (field[i + 1][j - 1] == MINE) { // BOTTOM LEFT
                            counter += 1;
                        }
                    }

                    if (j < SIZE - 1) {
                        if (field[i + 1][j + 1] == MINE) {   // BOTTOM RIGHT
                            counter += 1;
                        }
                    }

                    if (field[i + 1][j] == MINE) {   // BOTTOM
                        counter += 1;
                    }
                }

                if (j > 0) {
                    if (field[i][j - 1] == MINE) {   // LEFT
                        counter += 1;
                    }
                }

                if (j < SIZE - 1) {
                    if (field[i][j + 1] == MINE) {   // RIGHT
                        counter += 1;
                    }
                }

                switch (counter) {
                    case 1:
                        field[i][j] = ONE;
                        break;
                    case 2:
                        field[i][j] = TWO;
                        break;
                    case 3:
                        field[i][j] = THREE;
                        break;
                    case 4:
                        field[i][j] = FOUR;
                        break;
                    case 5:
                        field[i][j] = FIVE;
                        break;
                    case 6:
                        field[i][j] = SIX;
                        break;
                    case 7:
                        field[i][j] = SEVEN;
                        break;
                    case 8:
                        field[i][j] = EIGHT;
                        break;
                    default:
                        field[i][j] = SAFE;
                }

            }
        }
    }

    private void addMinesRandomly() {
        if (minesCount > SIZE * SIZE || minesCount < 1) {
            return;
        }
        int addedMines = 0;
        Random random = new Random();
        while (addedMines < minesCount) {
            int i = random.nextInt(SIZE);
            int j = random.nextInt(SIZE);
            if (field[i][j] != MINE) {
                field[i][j] = MINE;
                addedMines += 1;
            }
        }

    }
}
