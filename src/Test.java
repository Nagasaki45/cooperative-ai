import core.GameLogger;
import core.Game;
import players.mcts.MCTSPlayer;
import players.optimisers.ParameterizedPlayer;
import utils.Board;
import utils.GUI;
import utils.Types;
import utils.WindowInput;

import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {

        // Game parameters
        Types.TILETYPE[][] board = Board.simpleBoard();
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(new MCTSPlayer(0,0, 0.99));
        players.add(new MCTSPlayer(1,1,0.99));
        //players.add(new ExampleAgent(0,1));
        Game game = new Game(board, players);

        GUI frame = new GUI(game, "cooperative-ai", false);
        WindowInput wi = new WindowInput();
        GameLogger gameLogger = game.run(frame, wi);
        System.out.println(gameLogger);
    }

}
