package masmar.home.jba.minesweeper;

import java.util.Random;

class MineField {
    private static final int SIZE = 9;
    private Symbol[][] field;

    public MineField(int minesCount) {
        initField();
        addMinesRandomly(minesCount);
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
            if (field[i][j] == Symbol.SAFE) {
                field[i][j] = Symbol.MINE;
                addedMines += 1;
            }
        }

    }

    private void initField() {
        field = new Symbol[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                field[i][j] = Symbol.SAFE;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                output.append(field[i][j].getSymbol());
                if (j == 8) {
                    output.append("\n");
                }
            }
        }
        output.deleteCharAt(output.length() - 1);
        return output.toString();
    }
}
