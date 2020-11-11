package masmar.home.jba.minesweeper;

import java.util.Scanner;

class Main {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("How many mines do you want on the field?");
        int minesCount = SCANNER.nextInt();
        MineField mineField = new MineField(minesCount);
        System.out.println(mineField);
    }
}
