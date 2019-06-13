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
import java.util.Random;

public class TestHeuristic4 {

    public static void main(String[] args) {

        Random random = new Random();
        ArrayList<GameLogger> gameLoggers = new ArrayList<>();
        for (int run = 0 ; run < 2; run++)
        {
            // Game parameters
            MCTSParams params = new MCTSParams();
            if (run == 0)
            {
                System.out.println("Using parameters specific for heuristic 4");
                params.setOptimizedParams(4);
            }
            else
            {
                System.out.println("Using overall optimized parameters");
                params.setOptimizedParams();
            }
            for (int boardID = 0; boardID < Board.NUM_OF_BOARDS; boardID++)
            {
                System.out.println("board " + boardID);
                for (int i = 0; i < Config.MULTI_TESTS_ITERATIONS_PER_BOARD; i++)
                {
                    ArrayList<ParameterizedPlayer> players = new ArrayList<ParameterizedPlayer>();
                    players.add(new MCTSPlayer(random.nextInt(), 0, params));
                    players.add(new MCTSPlayer(random.nextInt(), 1, params));
                    Game game = new Game(boardID, players);

                    GameLogger gameLogger = game.run(null, null);
                    System.out.print(".");

                    gameLoggers.add(gameLogger);
                }
                System.out.print("\n");
            }
            System.out.println();
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
