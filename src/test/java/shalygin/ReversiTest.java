package shalygin;

import org.junit.Test;
import shalygin.board.Board;

public class ReversiTest {

    @Test
    public void test(){
        Gui.endGameWindow(Board.createStandardBoard());
    }

}
