package core;

import utils.Types;

/**
 * All Java-Pommerman players should extend this class and implement at least:
 * - a constructor invoking the super's constructor.
 * - the act(GameState) method.
 * They may also implement the result(GameState) method for any end of game post-processing.
 */
public abstract class Player {
    protected int playerID;
    protected long seed;

    /**
     * Default constructor, to be called in subclasses (initializes player ID and random seed for this agent.
     * @param seed - random seed for this player.
     * @param pId - this player's ID.
     */
    protected Player(long seed, int pId) {
        this.playerID = pId;
        this.seed = seed;
    }

    /**
     * Function requests an action from the agent, given current game state observation.
     * @param gs - current game state.
     * @return - action to play in this game state.
     */
    public abstract Types.ACTIONS act(GameState gs);

    /**
     * Getter for player ID field.
     * @return - this player's ID.
     */
    public final int getPlayerID() {
        return playerID;
    }

    /**
     * Getter for seed field.
     * @return - this player's random seed.
     */
    public final long getSeed() {
        return seed;
    }

    public abstract Player copy();
}
