package players.rhea;

import core.GameState;
import players.optimisers.ParameterizedPlayer;
import players.rhea.utils.RHEAParams;
import utils.ElapsedCpuTimer;
import utils.Types;

import java.util.Random;
import static players.rhea.utils.Constants.TIME_BUDGET;

public class RHEAPlayer extends ParameterizedPlayer {
    private RollingHorizonPlayer player;
    private GameInterface gInterface;
    private RHEAParams params;
    private double discountFactor;

    public RHEAPlayer(long seed, int playerID, double discountFactor) {
        this(seed, playerID, discountFactor,new RHEAParams());
    }

    public RHEAPlayer(long seed, int playerID, double discountFactor, RHEAParams params) {
        super(seed, playerID, params);

            this.seed = seed;
            this.playerID = playerID;
            this.discountFactor = discountFactor;

            // Make sure we have parameters
            this.params = (RHEAParams) getParameters();
            if (this.params == null) {
                this.params = new RHEAParams();
            }

            // Set up random generator
            Random randomGenerator = new Random(seed);

            // Create interface with game
            gInterface = new GameInterface(this.params, randomGenerator, playerID);

            // Set up player
            player = new RollingHorizonPlayer(randomGenerator, this.params, gInterface);

    }


    @Override
    public Types.ACTIONS act(GameState gs) {
        ElapsedCpuTimer elapsedTimer = null;
        if (params.budget_type == TIME_BUDGET) {
            elapsedTimer = new ElapsedCpuTimer();
            elapsedTimer.setMaxTimeMillis(params.time_budget);
        }
        setup(gs, elapsedTimer);
        return gInterface.translate(player.getAction(elapsedTimer, gs.nActions()));
    }

    private void setup(GameState rootState, ElapsedCpuTimer elapsedTimer) {
        gInterface.initTick(rootState, elapsedTimer);
    }

    @Override
    public ParameterizedPlayer copy() {
        return new RHEAPlayer(seed, playerID, discountFactor, params);
    }
}
