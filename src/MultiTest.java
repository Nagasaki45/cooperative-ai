import com.google.gson.Gson;
import core.Config;
import core.Game;
import core.GameLogger;
import players.mcts.MCTSParams;
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

        for (int heuristicID = 1; heuristicID < 5; heuristicID++)
        {
            System.out.println("heuristic " + heuristicID);
            for (int boardID = 0; boardID < Board.NUM_OF_BOARDS; boardID++)
            {
                System.out.println("board " + boardID);
                for (int i = 0; i < Config.MULTI_TESTS_ITERATIONS_PER_BOARD; i++)
                {
                    // Game parameters
                    ArrayList<ParameterizedPlayer> players = new ArrayList<ParameterizedPlayer>();
                    MCTSParams params = new MCTSParams();
                    params.heuristic_method = heuristicID;
                    players.add(new MCTSPlayer(0, 0, params));
                    players.add(new MCTSPlayer(1, 1, params));
                    Game game = new Game(boardID, players);

                    GameLogger gameLogger = game.run(null, null);
                    System.out.print(".");

                    gameLoggers.add(gameLogger);
                }
                System.out.print("\n");
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
