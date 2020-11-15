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
                "9|.........|\n" +
                "-|---------|"
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
        new MineField(field, 10).toString() ==
                " |123456789|\n" +
                "-|---------|\n" +
                "1|.........|\n" +
                "2|.111111..|\n" +
                "3|.1.22.211|\n" +
                "4|.112.33.1|\n" +
                "5|...12.211|\n" +
                "6|....1221.|\n" +
                "7|..1111.1.|\n" +
                "8|123.1222.|\n" +
                "9|1..211.1.|\n" +
                "-|---------|"
    }

    def "should simulate game"() {
        when:
        Symbol[][] field = [
                [SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE],
                [SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE],
                [SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE],
                [SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, MINE, SAFE],
                [SAFE, SAFE, SAFE, SAFE, SAFE, MINE, SAFE, SAFE, SAFE],
                [SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE],
                [SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE],
                [SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE],
                [SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE, SAFE],
        ] as Symbol[][]
        def mineField = new MineField(field, 2)

        then:
        mineField.toString() ==
                " |123456789|\n" +
                "-|---------|\n" +
                "1|.........|\n" +
                "2|.........|\n" +
                "3|......111|\n" +
                "4|....112.1|\n" +
                "5|....1.211|\n" +
                "6|....111..|\n" +
                "7|.........|\n" +
                "8|.........|\n" +
                "9|.........|\n" +
                "-|---------|"

        when:
        def firstShot = mineField.shot(1, 1)
        then:
        firstShot == Status.ON_GOING
        and:
        mineField.toString() ==
                " |123456789|\n" +
                "-|---------|\n" +
                "1|*........|\n" +
                "2|.........|\n" +
                "3|......111|\n" +
                "4|....112.1|\n" +
                "5|....1.211|\n" +
                "6|....111..|\n" +
                "7|.........|\n" +
                "8|.........|\n" +
                "9|.........|\n" +
                "-|---------|"

        when:
        def secondShot = mineField.shot(8, 4)
        then:
        secondShot == Status.ON_GOING
        and:
        mineField.toString() ==
                " |123456789|\n" +
                "-|---------|\n" +
                "1|*........|\n" +
                "2|.........|\n" +
                "3|......111|\n" +
                "4|....112*1|\n" +
                "5|....1.211|\n" +
                "6|....111..|\n" +
                "7|.........|\n" +
                "8|.........|\n" +
                "9|.........|\n" +
                "-|---------|"

        when:
        def thirdShot = mineField.shot(9, 3)
        then:
        thirdShot == Status.HIT_NUMBER

        when:
        def fourthShot = mineField.shot(6, 5)
        then:
        fourthShot == Status.ON_GOING
        and:
        mineField.toString() ==
                " |123456789|\n" +
                "-|---------|\n" +
                "1|*........|\n" +
                "2|.........|\n" +
                "3|......111|\n" +
                "4|....112*1|\n" +
                "5|....1*211|\n" +
                "6|....111..|\n" +
                "7|.........|\n" +
                "8|.........|\n" +
                "9|.........|\n" +
                "-|---------|"

        when:
        def fifthShot = mineField.shot(1, 1)
        then:
        fifthShot == Status.COMPLETED
        and:
        mineField.toString() ==
                " |123456789|\n" +
                "-|---------|\n" +
                "1|.........|\n" +
                "2|.........|\n" +
                "3|......111|\n" +
                "4|....112*1|\n" +
                "5|....1*211|\n" +
                "6|....111..|\n" +
                "7|.........|\n" +
                "8|.........|\n" +
                "9|.........|\n" +
                "-|---------|"
    }
}
