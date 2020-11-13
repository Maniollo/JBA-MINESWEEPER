package masmar.home.jba.minesweeper

import spock.lang.Specification

import static masmar.home.jba.minesweeper.Symbol.MINE
import static masmar.home.jba.minesweeper.Symbol.SAFE

class MineFieldSpec extends Specification {
    def "should display empty MineField"() {
        expect:
        new MineField(0).toString() == ".........\n" +
                ".........\n" +
                ".........\n" +
                ".........\n" +
                ".........\n" +
                ".........\n" +
                ".........\n" +
                ".........\n" +
                "........."
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
                ".........\n" +
                ".111111..\n" +
                ".1X22X211\n" +
                ".112X33X1\n" +
                "...12X211\n" +
                "....1221.\n" +
                "..1111X1.\n" +
                "123X1222.\n" +
                "1XX211X1."
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
