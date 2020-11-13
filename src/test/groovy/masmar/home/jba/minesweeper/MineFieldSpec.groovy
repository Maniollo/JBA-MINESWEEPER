package masmar.home.jba.minesweeper

import spock.lang.Specification

import static masmar.home.jba.minesweeper.Symbol.MINE
import static masmar.home.jba.minesweeper.Symbol.SAFE

class MineFieldSpec extends Specification {
    def "should display empty MineField"() {
        expect:
        new MineField(0).toString() ==
                " |123456789|\n" +
                "-|---------|\n" +
                "1|.........|\n" +
                "2|.........|\n" +
                "3|.........|\n" +
                "4|.........|\n" +
                "5|.........|\n" +
                "6|.........|\n" +
                "7|.........|\n" +
                "8|.........|\n" +
                "9|.........|"
    }

    def "should create MineField with 15 mines"() {
        expect:
        countMines(new MineField(15).toString()) == 15
    }

    def "should add neighbour mines counter"() {
        when:
        Symbol[][] field = [
                [SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE],
                [SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE],
                [SAFE, SAFE, MINE, SAFE, SAFE, MINE, SAFE, SAFE, SAFE],
                [SAFE, SAFE, SAFE, SAFE, MINE, SAFE, SAFE, MINE, SAFE],
                [SAFE, SAFE, SAFE, SAFE, SAFE, MINE, SAFE, SAFE, SAFE],
                [SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE],
                [SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, MINE, SAFE, SAFE],
                [SAFE, SAFE, SAFE, MINE, SAFE, SAFE, SAFE, SAFE, SAFE],
                [SAFE, MINE, MINE, SAFE, SAFE, SAFE, MINE, SAFE, SAFE],
        ] as Symbol[][]

        then:
        new MineField(field).toString() ==
                " |123456789|\n" +
                "-|---------|\n" +
                "1|.........|\n" +
                "2|.111111..|\n" +
                "3|.1X22X211|\n" +
                "4|.112X33X1|\n" +
                "5|...12X211|\n" +
                "6|....1221.|\n" +
                "7|..1111X1.|\n" +
                "8|123X1222.|\n" +
                "9|1XX211X1.|"
    }

    private static int countMines(String s) {
        def chars = s.toCharArray()
        int count = 0
        for (c in chars) {
            if (c == 'X') {
                count += 1
            }
        }
        count
    }
}
