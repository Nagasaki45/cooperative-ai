package players.mcts;

import core.GameState;
import players.optimisers.ParameterizedPlayer;
import core.Player;
import utils.ElapsedCpuTimer;
import utils.Types;

import java.util.ArrayList;
import java.util.Random;

public class MCTSPlayer extends ParameterizedPlayer {

    /**
     * Random generator.
     */
    private Random m_rnd;
    private double discountFactor;

    /**
     * All actions available.
     */
    public Types.ACTIONS[] actions;

    /**
     * Params for this MCTS
     */
    public MCTSParams params;

    public MCTSPlayer(long seed, int id, double discountFactor) {
        this(seed, id, discountFactor, null);
    }

    /**
     * Constructors that receive parameters
     * @param seed seed for the algorithm to use in the random generator
     * @param id ID of this player in the game.
     * @param params Parameters for MCTS.
     */
    public MCTSPlayer(long seed, int id, double discountFactor, MCTSParams params) {
        super(seed, id, params);
        reset(seed, id, discountFactor);

        ArrayList<Types.ACTIONS> actionsList = Types.ACTIONS.all();
        actions = new Types.ACTIONS[actionsList.size()];
        int i = 0;
        for (Types.ACTIONS act : actionsList) {
            actions[i++] = act;
        }
    }

    /**
     * Resets this player with seed and ID
     * @param seed seed for the algorithm to use in the random generator
     * @param playerID ID of this player in the game.
     */
    @Override
    public void reset(long seed, int playerID, double discountFactor) {
        this.seed = seed;
        this.playerID = playerID;
        this.discountFactor = discountFactor;
        m_rnd = new Random(seed);

        this.params = (MCTSParams) getParameters();
        if (this.params == null) {
            this.params = new MCTSParams();
        }
    }

    /**
     * Action called every game tick. It must return an action to play in the real game.
     * @param gs - current game state.
     * @return the action to apply in the game.
     */
    @Override
    public Types.ACTIONS act(GameState gs) {

        //This allows us to use a time-bounded budget for MCTS
        ElapsedCpuTimer ect = new ElapsedCpuTimer();
        ect.setMaxTimeMillis(params.num_time);

        // Number of actions available
        int num_actions = actions.length;

        // Root of the tree
        SingleTreeNode m_root = new SingleTreeNode(params, m_rnd, num_actions, actions, playerID, discountFactor);
        m_root.setRootGameState(gs);

        //Determine the action using MCTS...
        m_root.mctsSearch(ect);

        //Determine the best action to take and return it.
        int action = m_root.mostVisitedAction();

        //... and return it.
        return actions[action];
    }

    @Override
    public Player copy() {
        return new MCTSPlayer(seed, playerID, discountFactor, params);
    }
}