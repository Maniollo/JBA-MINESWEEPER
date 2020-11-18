package masmar.home.jba.minesweeper;

import java.util.Scanner;

class Main {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("How many mines do you want on the field?");
        int minesCount = SCANNER.nextInt();
        MineField mineField = new MineField(minesCount);
        System.out.println(mineField.display());
        Result result = Result.ON_GOING;
        while (result == Result.ON_GOING) {
            System.out.println("Set/unset mines marks or claim a cell as free:");
            int y = SCANNER.nextInt();
            int x = SCANNER.nextInt();
            Command command = Command.valueOf(SCANNER.next().toUpperCase());
            result = mineField.shot(x, y, command);
            System.out.println(mineField.display());
            if (result == Result.WIN) {
                System.out.println("Congratulations! You found all the mines!");
                break;
            } else if (result == Result.LOSE) {
                System.out.println("You stepped on a mine and failed!");
                break;
            }
        }
    }
}
