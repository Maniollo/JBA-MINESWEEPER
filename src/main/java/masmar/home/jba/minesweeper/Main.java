package masmar.home.jba.minesweeper;

import java.util.Scanner;

class Main {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("How many mines do you want on the field?");
        int minesCount = SCANNER.nextInt();
        MineField mineField = new MineField(minesCount);
        System.out.println(mineField);
        Status status = Status.ON_GOING;
        while (status != Status.COMPLETED) {
            System.out.println("Set/delete mines marks (x and y coordinates)");
            int x = SCANNER.nextInt();
            int y = SCANNER.nextInt();
            status = mineField.shot(x, y);
            if (status == Status.HIT_NUMBER) {
                System.out.println("There is a number here!");
            } else if (status == Status.ON_GOING) {
                System.out.println(mineField);
            } else if (status == Status.COMPLETED) {
                System.out.println(mineField);
                System.out.println("Congratulations! You found all the mines!");
            }
        }
    }
}
