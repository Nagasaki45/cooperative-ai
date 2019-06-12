import core.GameLogger;
import core.Game;
import players.mcts.MCTSPlayer;
import players.rhea.RHEAPlayer;
import players.optimisers.ParameterizedPlayer;
import utils.GUI;
import utils.WindowInput;

import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {

        // Game parameters
        ArrayList<ParameterizedPlayer> players = new ArrayList<ParameterizedPlayer>();
        players.add(new MCTSPlayer(0,0));
        players.add(new MCTSPlayer(1,1));
        //players.add(new RHEAPlayer(1,1,0.99));
        //players.add(new ExampleAgent(0,1));
        Game game = new Game(1, players);

        GUI frame = new GUI(game, "cooperative-ai", false);
        WindowInput wi = new WindowInput();
        GameLogger gameLogger = game.run(frame, wi);
        System.out.println(gameLogger);
    }

}
