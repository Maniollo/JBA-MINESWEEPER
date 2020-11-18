package masmar.home.jba.minesweeper

import spock.lang.Specification

import static masmar.home.jba.minesweeper.Command.FREE
import static masmar.home.jba.minesweeper.Result.ON_GOING
import static masmar.home.jba.minesweeper.Symbol.MINE
import static masmar.home.jba.minesweeper.Symbol.SAFE

class MineFieldSpec extends Specification {
    def "should user win by marking all mines correctly"() {
        when:
        Symbol[][] field = [
                [SAFE, MINE, SAFE, SAFE, SAFE, SAFE, MINE, SAFE, MINE],
                [SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE],
                [SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, MINE, SAFE, SAFE],
                [SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE],
                [SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE],
                [SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, MINE, SAFE],
                [SAFE, SAFE, MINE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE],
                [MINE, MINE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE],
                [SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE],
        ] as Symbol[][]
        def mineField = new MineField(field, 8)

        then:
        mineField.display() ==
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
                "9|.........|\n" +
                "-|---------|"

        when:
        def status = mineField.shot(5, 5, FREE)
        then:
        status == ON_GOING
        and:
        mineField.display() ==
                " |123456789|\n" +
                "-|---------|\n" +
                "1|..1//1...|\n" +
                "2|111//2...|\n" +
                "3|/////1...|\n" +
                "4|/////11..|\n" +
                "5|//////1..|\n" +
                "6|/111//1..|\n" +
                "7|23.1//111|\n" +
                "8|..21/////|\n" +
                "9|..1//////|\n" +
                "-|---------|"

        when:
        status = mineField.shot(1, 2, Command.MINE)
        then:
        status == ON_GOING
        and:
        mineField.display() ==
                " |123456789|\n" +
                "-|---------|\n" +
                "1|.*1//1...|\n" +
                "2|111//2...|\n" +
                "3|/////1...|\n" +
                "4|/////11..|\n" +
                "5|//////1..|\n" +
                "6|/111//1..|\n" +
                "7|23.1//111|\n" +
                "8|..21/////|\n" +
                "9|..1//////|\n" +
                "-|---------|"

        when:
        status = mineField.shot(7, 3, Command.MINE)
        then:
        status == ON_GOING
        and:
        mineField.display() ==
                " |123456789|\n" +
                "-|---------|\n" +
                "1|.*1//1...|\n" +
                "2|111//2...|\n" +
                "3|/////1...|\n" +
                "4|/////11..|\n" +
                "5|//////1..|\n" +
                "6|/111//1..|\n" +
                "7|23*1//111|\n" +
                "8|..21/////|\n" +
                "9|..1//////|\n" +
                "-|---------|"

        when:
        status = mineField.shot(8, 2, Command.MINE)
        then:
        status == ON_GOING
        and:
        mineField.display() ==
                " |123456789|\n" +
                "-|---------|\n" +
                "1|.*1//1...|\n" +
                "2|111//2...|\n" +
                "3|/////1...|\n" +
                "4|/////11..|\n" +
                "5|//////1..|\n" +
                "6|/111//1..|\n" +
                "7|23*1//111|\n" +
                "8|.*21/////|\n" +
                "9|..1//////|\n" +
                "-|---------|"

        when:
        status = mineField.shot(8, 1, Command.MINE)
        then:
        status == ON_GOING
        and:
        mineField.display() ==
                " |123456789|\n" +
                "-|---------|\n" +
                "1|.*1//1...|\n" +
                "2|111//2...|\n" +
                "3|/////1...|\n" +
                "4|/////11..|\n" +
                "5|//////1..|\n" +
                "6|/111//1..|\n" +
                "7|23*1//111|\n" +
                "8|**21/////|\n" +
                "9|..1//////|\n" +
                "-|---------|"

        when:
        status = mineField.shot(3, 7, Command.MINE)
        then:
        status == ON_GOING
        and:
        mineField.display() ==
                " |123456789|\n" +
                "-|---------|\n" +
                "1|.*1//1...|\n" +
                "2|111//2...|\n" +
                "3|/////1*..|\n" +
                "4|/////11..|\n" +
                "5|//////1..|\n" +
                "6|/111//1..|\n" +
                "7|23*1//111|\n" +
                "8|**21/////|\n" +
                "9|..1//////|\n" +
                "-|---------|"

        when:
        status = mineField.shot(3, 8, FREE)
        then:
        status == ON_GOING
        and:
        mineField.display() ==
                " |123456789|\n" +
                "-|---------|\n" +
                "1|.*1//1...|\n" +
                "2|111//2...|\n" +
                "3|/////1*1.|\n" +
                "4|/////11..|\n" +
                "5|//////1..|\n" +
                "6|/111//1..|\n" +
                "7|23*1//111|\n" +
                "8|**21/////|\n" +
                "9|..1//////|\n" +
                "-|---------|"

        when:
        status = mineField.shot(3, 9, FREE)
        then:
        status == ON_GOING
        and:
        mineField.display() ==
                " |123456789|\n" +
                "-|---------|\n" +
                "1|.*1//1...|\n" +
                "2|111//2.31|\n" +
                "3|/////1*1/|\n" +
                "4|/////111/|\n" +
                "5|//////111|\n" +
                "6|/111//1..|\n" +
                "7|23*1//111|\n" +
                "8|**21/////|\n" +
                "9|..1//////|\n" +
                "-|---------|"

        when:
        status = mineField.shot(6, 8, Command.MINE)
        then:
        status == ON_GOING
        and:
        mineField.display() ==
                " |123456789|\n" +
                "-|---------|\n" +
                "1|.*1//1...|\n" +
                "2|111//2.31|\n" +
                "3|/////1*1/|\n" +
                "4|/////111/|\n" +
                "5|//////111|\n" +
                "6|/111//1*.|\n" +
                "7|23*1//111|\n" +
                "8|**21/////|\n" +
                "9|..1//////|\n" +
                "-|---------|"

        when:
        status = mineField.shot(2, 7, Command.MINE)
        then:
        status == ON_GOING
        and:
        mineField.display() ==
                " |123456789|\n" +
                "-|---------|\n" +
                "1|.*1//1...|\n" +
                "2|111//2*31|\n" +
                "3|/////1*1/|\n" +
                "4|/////111/|\n" +
                "5|//////111|\n" +
                "6|/111//1*.|\n" +
                "7|23*1//111|\n" +
                "8|**21/////|\n" +
                "9|..1//////|\n" +
                "-|---------|"

        when:
        status = mineField.shot(1, 7, Command.MINE)
        then:
        status == ON_GOING
        and:
        mineField.display() ==
                " |123456789|\n" +
                "-|---------|\n" +
                "1|.*1//1*..|\n" +
                "2|111//2*31|\n" +
                "3|/////1*1/|\n" +
                "4|/////111/|\n" +
                "5|//////111|\n" +
                "6|/111//1*.|\n" +
                "7|23*1//111|\n" +
                "8|**21/////|\n" +
                "9|..1//////|\n" +
                "-|---------|"

        when:
        status = mineField.shot(1, 9, Command.MINE)
        then:
        status == ON_GOING
        and:
        mineField.display() ==
                " |123456789|\n" +
                "-|---------|\n" +
                "1|.*1//1*.*|\n" +
                "2|111//2*31|\n" +
                "3|/////1*1/|\n" +
                "4|/////111/|\n" +
                "5|//////111|\n" +
                "6|/111//1*.|\n" +
                "7|23*1//111|\n" +
                "8|**21/////|\n" +
                "9|..1//////|\n" +
                "-|---------|"

        when:
        status = mineField.shot(2, 7, FREE)
        then:
        status == Result.WIN
        and:
        mineField.display() ==
                " |123456789|\n" +
                "-|---------|\n" +
                "1|.*1//1*.*|\n" +
                "2|111//2231|\n" +
                "3|/////1*1/|\n" +
                "4|/////111/|\n" +
                "5|//////111|\n" +
                "6|/111//1*.|\n" +
                "7|23*1//111|\n" +
                "8|**21/////|\n" +
                "9|..1//////|\n" +
                "-|---------|"
    }

    def "should user win by exploring all safe cells"() {
        when:
        Symbol[][] field = [
                [SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE],
                [SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE],
                [SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, MINE, SAFE, SAFE],
                [SAFE, MINE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE],
                [SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, MINE],
                [SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, MINE, SAFE, SAFE],
                [SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE],
                [SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE],
                [SAFE, MINE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE],
        ] as Symbol[][]
        def mineField = new MineField(field, 5)

        then:
        mineField.display() ==
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
                "9|.........|\n" +
                "-|---------|"

        when:
        def status = mineField.shot(5, 5, FREE)
        then:
        status == ON_GOING
        and:
        mineField.display() ==
                " |123456789|\n" +
                "-|---------|\n" +
                "1|/////////|\n" +
                "2|/////111/|\n" +
                "3|111//1.1/|\n" +
                "4|..1//1.21|\n" +
                "5|111//1...|\n" +
                "6|/////1.21|\n" +
                "7|/////111/|\n" +
                "8|111//////|\n" +
                "9|..1//////|\n" +
                "-|---------|"

        when:
        status = mineField.shot(9, 1, FREE)
        then:
        status == ON_GOING
        and:
        mineField.display() ==
                " |123456789|\n" +
                "-|---------|\n" +
                "1|/////////|\n" +
                "2|/////111/|\n" +
                "3|111//1.1/|\n" +
                "4|..1//1.21|\n" +
                "5|111//1...|\n" +
                "6|/////1.21|\n" +
                "7|/////111/|\n" +
                "8|111//////|\n" +
                "9|1.1//////|\n" +
                "-|---------|"

        when:
        status = mineField.shot(4, 1, FREE)
        then:
        status == ON_GOING
        and:
        mineField.display() ==
                " |123456789|\n" +
                "-|---------|\n" +
                "1|/////////|\n" +
                "2|/////111/|\n" +
                "3|111//1.1/|\n" +
                "4|1.1//1.21|\n" +
                "5|111//1...|\n" +
                "6|/////1.21|\n" +
                "7|/////111/|\n" +
                "8|111//////|\n" +
                "9|1.1//////|\n" +
                "-|---------|"

        when:
        status = mineField.shot(4, 7, FREE)
        then:
        status == ON_GOING
        and:
        mineField.display() ==
                " |123456789|\n" +
                "-|---------|\n" +
                "1|/////////|\n" +
                "2|/////111/|\n" +
                "3|111//1.1/|\n" +
                "4|1.1//1121|\n" +
                "5|111//1...|\n" +
                "6|/////1.21|\n" +
                "7|/////111/|\n" +
                "8|111//////|\n" +
                "9|1.1//////|\n" +
                "-|---------|"

        when:
        status = mineField.shot(5, 7, FREE)
        then:
        status == ON_GOING
        and:
        mineField.display() ==
                " |123456789|\n" +
                "-|---------|\n" +
                "1|/////////|\n" +
                "2|/////111/|\n" +
                "3|111//1.1/|\n" +
                "4|1.1//1121|\n" +
                "5|111//11..|\n" +
                "6|/////1.21|\n" +
                "7|/////111/|\n" +
                "8|111//////|\n" +
                "9|1.1//////|\n" +
                "-|---------|"

        when:
        status = mineField.shot(5, 8, FREE)
        then:
        status == Result.WIN
        and:
        mineField.display() ==
                " |123456789|\n" +
                "-|---------|\n" +
                "1|/////////|\n" +
                "2|/////111/|\n" +
                "3|111//1.1/|\n" +
                "4|1.1//1121|\n" +
                "5|111//112.|\n" +
                "6|/////1.21|\n" +
                "7|/////111/|\n" +
                "8|111//////|\n" +
                "9|1.1//////|\n" +
                "-|---------|"
    }

    def "should user lose after exploring a cell that contains a mine"() {
        when:
        Symbol[][] field = [
                [SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE],
                [SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE],
                [SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, MINE, SAFE, SAFE],
                [SAFE, MINE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE],
                [SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, MINE],
                [SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, MINE, SAFE, SAFE],
                [SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE],
                [SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE],
                [SAFE, MINE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE],
        ] as Symbol[][]
        def mineField = new MineField(field, 5)

        then:
        mineField.display() ==
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
                "9|.........|\n" +
                "-|---------|"

        when:
        def status = mineField.shot(5, 5, FREE)
        then:
        status == ON_GOING
        and:
        mineField.display() ==
                " |123456789|\n" +
                "-|---------|\n" +
                "1|/////////|\n" +
                "2|/////111/|\n" +
                "3|111//1.1/|\n" +
                "4|..1//1.21|\n" +
                "5|111//1...|\n" +
                "6|/////1.21|\n" +
                "7|/////111/|\n" +
                "8|111//////|\n" +
                "9|..1//////|\n" +
                "-|---------|"

        when:
        status = mineField.shot(9, 2, Command.MINE)
        then:
        status == ON_GOING
        and:
        mineField.display() ==
                " |123456789|\n" +
                "-|---------|\n" +
                "1|/////////|\n" +
                "2|/////111/|\n" +
                "3|111//1.1/|\n" +
                "4|..1//1.21|\n" +
                "5|111//1...|\n" +
                "6|/////1.21|\n" +
                "7|/////111/|\n" +
                "8|111//////|\n" +
                "9|.*1//////|\n" +
                "-|---------|"

        when:
        status = mineField.shot(4, 1, FREE)
        then:
        status == ON_GOING
        and:
        mineField.display() ==
                " |123456789|\n" +
                "-|---------|\n" +
                "1|/////////|\n" +
                "2|/////111/|\n" +
                "3|111//1.1/|\n" +
                "4|1.1//1.21|\n" +
                "5|111//1...|\n" +
                "6|/////1.21|\n" +
                "7|/////111/|\n" +
                "8|111//////|\n" +
                "9|.*1//////|\n" +
                "-|---------|"

        when:
        status = mineField.shot(6, 7, FREE)
        then:
        status == Result.LOSE
        and:
        mineField.display() ==
                " |123456789|\n" +
                "-|---------|\n" +
                "1|/////////|\n" +
                "2|/////111/|\n" +
                "3|111//1.1/|\n" +
                "4|1.1//1.21|\n" +
                "5|111//1...|\n" +
                "6|/////1X21|\n" +
                "7|/////111/|\n" +
                "8|111//////|\n" +
                "9|.*1//////|\n" +
                "-|---------|"
    }

    def "should replace marked mine field once considered as safe"() {
        when:
        Symbol[][] field = [
                [SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE],
                [SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE],
                [SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, MINE, SAFE, SAFE],
                [SAFE, MINE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE],
                [SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, MINE],
                [SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, MINE, SAFE, SAFE],
                [SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE],
                [SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE],
                [SAFE, MINE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE],
        ] as Symbol[][]
        def mineField = new MineField(field, 5)

        then:
        mineField.display() ==
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
                "9|.........|\n" +
                "-|---------|"

        when:
        def status = mineField.shot(8, 3, Command.MINE)
        then:
        status == ON_GOING
        and:
        mineField.display() ==
                " |123456789|\n" +
                "-|---------|\n" +
                "1|.........|\n" +
                "2|.........|\n" +
                "3|.........|\n" +
                "4|.........|\n" +
                "5|.........|\n" +
                "6|.........|\n" +
                "7|.........|\n" +
                "8|..*......|\n" +
                "9|.........|\n" +
                "-|---------|"


        when:
        status = mineField.shot(5, 5, FREE)
        then:
        status == ON_GOING
        and:
        mineField.display() ==
                " |123456789|\n" +
                "-|---------|\n" +
                "1|/////////|\n" +
                "2|/////111/|\n" +
                "3|111//1.1/|\n" +
                "4|..1//1.21|\n" +
                "5|111//1...|\n" +
                "6|/////1.21|\n" +
                "7|/////111/|\n" +
                "8|111//////|\n" +
                "9|..1//////|\n" +
                "-|---------|"
    }
}
