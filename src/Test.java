import agents.ExampleAgent;
import core.Game;
import core.Player;
import utils.GUI;
import utils.Types;
import utils.WindowInput;

import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {

        // Game parameters
        Types.TILETYPE[][] board = new Types.TILETYPE[0][0];
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(new ExampleAgent(0,0));
        boolean visuals = true;
        int frameDelay = 100;
        Game game = new Game(board, players, visuals, frameDelay);

        GUI frame = new GUI(game, "Java-Pommerman", false, true);
        WindowInput wi = new WindowInput();
        game.run(frame, wi);
    }

}
