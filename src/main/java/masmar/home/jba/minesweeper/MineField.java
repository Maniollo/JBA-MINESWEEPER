package masmar.home.jba.minesweeper;

import java.util.Random;

class MineField {
    private static final int SIZE = 9;
    private Symbol[][] field;

    public MineField(int minesCount) {
        field = new Symbol[SIZE][SIZE];
        addMinesRandomly(minesCount);
        addNeighbourCounters();
    }

    //    ONLY FOR TESTING
    MineField(Symbol[][] field) {
        this.field = field;
        addNeighbourCounters();
    }

    private void addNeighbourCounters() {
        int counter;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (field[i][j] == Symbol.MINE) {
                    continue;
                }
                counter = 0;
                if (i > 0) {
                    if (j > 0) {
                        if (field[i - 1][j - 1] == Symbol.MINE) {   // TOP LEFT
                            counter += 1;
                        }
                    }

                    if (j < SIZE - 1) {
                        if (field[i - 1][j + 1] == Symbol.MINE) {   // TOP RIGHT
                            counter += 1;
                        }
                    }

                    if (field[i - 1][j] == Symbol.MINE) {   // TOP
                        counter += 1;
                    }
                }

                if (i < SIZE - 1) {
                    if (j > 0) {
                        if (field[i + 1][j - 1] == Symbol.MINE) { // BOTTOM LEFT
                            counter += 1;
                        }
                    }

                    if (j < SIZE - 1) {
                        if (field[i + 1][j + 1] == Symbol.MINE) {   // BOTTOM RIGHT
                            counter += 1;
                        }
                    }

                    if (field[i + 1][j] == Symbol.MINE) {   // BOTTOM
                        counter += 1;
                    }
                }

                if (j > 0) {
                    if (field[i][j - 1] == Symbol.MINE) {   // LEFT
                        counter += 1;
                    }
                }

                if (j < SIZE - 1) {
                    if (field[i][j + 1] == Symbol.MINE) {   // RIGHT
                        counter += 1;
                    }
                }

                switch (counter) {
                    case 1:
                        field[i][j] = Symbol.ONE;
                        break;
                    case 2:
                        field[i][j] = Symbol.TWO;
                        break;
                    case 3:
                        field[i][j] = Symbol.THREE;
                        break;
                    case 4:
                        field[i][j] = Symbol.FOUR;
                        break;
                    case 5:
                        field[i][j] = Symbol.FIVE;
                        break;
                    case 6:
                        field[i][j] = Symbol.SIX;
                        break;
                    case 7:
                        field[i][j] = Symbol.SEVEN;
                        break;
                    case 8:
                        field[i][j] = Symbol.EIGHT;
                        break;
                    default:
                        field[i][j] = Symbol.SAFE;
                }

            }
        }
    }

    private void addMinesRandomly(int minesCount) {
        if (minesCount > SIZE * SIZE || minesCount < 1) {
            return;
        }
        int addedMines = 0;
        Random random = new Random();
        while (addedMines < minesCount) {
            int i = random.nextInt(SIZE);
            int j = random.nextInt(SIZE);
            if (field[i][j] != Symbol.MINE) {
                field[i][j] = Symbol.MINE;
                addedMines += 1;
            }
        }

    }

    @Override
    public String toString() {
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
        output.deleteCharAt(output.length() - 1);
        return output.toString();
    }
}
