package ru.project.game;

import org.junit.jupiter.api.Test;
import ru.project.board.Key;
import ru.project.board.NotEnoughSpace;
import ru.project.board.SquareBoard;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.project.game.Game2048.GAME_SIZE;

public class SquareBoardTest {


    private SquareBoard<Integer> squareBoard;
    private final Game<Key, Integer> game = new Game2048();


    @Test
    void testFillBoardWrongLIstSize() {
        squareBoard = new SquareBoard<>(4);
        List<Integer> list = new ArrayList<>();
        for (var i = 0; i < 17; i++) {
            list.add(i);
        }
        assertThrows(RuntimeException.class, () -> squareBoard.fillBoard(list));
    }

    @Test
    void init_exception() throws NotEnoughSpace {
        game.init();
        for (var i = 0; i < GAME_SIZE * GAME_SIZE - 2; i++) {
            game.addItem();
        }
        assertThrows(NotEnoughSpace.class, game::addItem);
    }
}
