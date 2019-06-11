package players;

import core.GameState;
import core.Player;
import utils.Types;

public class ExampleAgent extends Player {

    public ExampleAgent(long seed, int pId) {
        super(seed, pId);
    }

    @Override
    public Types.ACTIONS act(GameState gs) {
        return Types.ACTIONS.ACTION_LEFT;
    }

    @Override
    public Player copy() {
        return new ExampleAgent(this.seed, this.playerID);
    }
}
