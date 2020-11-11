package masmar.home.jba.minesweeper

import spock.lang.Specification

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
