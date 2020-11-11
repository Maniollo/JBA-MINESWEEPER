package masmar.home.jba.minesweeper;

class MineField {
    private Symbol[][] field;

    public MineField() {
        initField();
    }

    private void initField() {
        field = new Symbol[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (i == j) {
                    field[i][j] = Symbol.MINE;
                } else {
                    field[i][j] = Symbol.SAFE;
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
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
