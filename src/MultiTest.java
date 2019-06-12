import core.Config;
import core.Game;
import core.GameLogger;
import players.mcts.MCTSPlayer;
import players.optimisers.ParameterizedPlayer;
import utils.Board;
import utils.Types;

import java.util.ArrayList;

public class MultiTest {

    public static void main(String[] args) {

        for (int i = 0; i < Config.MULTI_TESTS_ITERATIONS; i++)
        {
            // Game parameters
            Types.TILETYPE[][] board = Board.simpleBoard();
            ArrayList<ParameterizedPlayer> players = new ArrayList<ParameterizedPlayer>();
            players.add(new MCTSPlayer(0,0));
            players.add(new MCTSPlayer(1,1));
            Game game = new Game(board, players);

            GameLogger gameLogger = game.run(null, null);
            System.out.println(gameLogger);
        }
    }
}
