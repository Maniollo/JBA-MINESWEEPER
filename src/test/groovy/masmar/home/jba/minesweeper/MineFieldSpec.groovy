package masmar.home.jba.minesweeper

import spock.lang.Specification

class MineFieldSpec extends Specification {
    def "should display MineField"() {
        expect:
        new MineField().toString() == "X........\n" +
                ".X.......\n" +
                "..X......\n" +
                "...X.....\n" +
                "....X....\n" +
                ".....X...\n" +
                "......X..\n" +
                ".......X.\n" +
                "........X"
    }
}
