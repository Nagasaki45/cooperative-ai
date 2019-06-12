import com.google.gson.Gson;
import core.Config;
import core.Game;
import core.GameLogger;
import players.mcts.MCTSPlayer;
import players.optimisers.ParameterizedPlayer;
import utils.Board;
import utils.Types;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class MultiTest {

    public static void main(String[] args) {

        ArrayList<GameLogger> gameLoggers = new ArrayList<>();

        for (int boardID = 0; boardID < Board.NUM_OF_BOARDS; boardID++) {

            for (int i = 0; i < Config.MULTI_TESTS_ITERATIONS_PER_BOARD; i++) {
                // Game parameters
                Types.TILETYPE[][] board = Board.simpleBoard(boardID);
                ArrayList<ParameterizedPlayer> players = new ArrayList<ParameterizedPlayer>();
                players.add(new MCTSPlayer(0, 0, 0.99));
                players.add(new MCTSPlayer(1, 1, 0.99));
                Game game = new Game(board, players);

                GameLogger gameLogger = game.run(null, null);
                System.out.println(gameLogger);

                gameLoggers.add(gameLogger);
            }
        }

        Gson gson = new Gson();
        String allResults = gson.toJson(gameLoggers);
        PrintWriter writer = null;
        try
        {
            writer = new PrintWriter("data/" + (System.currentTimeMillis() / 1000) + ".json", "UTF-8");
            writer.print(allResults);
            writer.close();
        }
        catch (FileNotFoundException | UnsupportedEncodingException e)
        {
            System.out.println("Cannot write to file " + e);
        }
        finally {
            if (writer != null)
            {
                writer.close();
            }
        }
    }
}
