package core;

import players.optimisers.ParameterSet;
import players.optimisers.ParameterizedPlayer;
import utils.GUI;
import utils.Types;
import utils.WindowInput;

import java.util.ArrayList;


public class Game {

    private GameState gs;
    private ArrayList<ParameterizedPlayer> players;
    private boolean visuals;

    public Game(int boardID, ArrayList<ParameterizedPlayer> players) {
        this.gs = new GameState(boardID);
        this.players = players;
        this.visuals = Config.VISUALS;

        for (ParameterizedPlayer p : players)
        {
            this.gs.addPlayer(p.playerID);
        }
    }

    /**
     * Runs a game once. Receives frame and window input. If any is null, forces a run with no visuals.
     * @param frame window to draw the game
     * @param wi input for the window.
     * @return the results of the game, per player.
     */
    public GameLogger run(GUI frame, WindowInput wi)
    {
        if (frame == null || wi == null)
            visuals = false;

        boolean firstEnd = true;

        while(!gs.isEnded() || visuals && wi != null && !wi.windowClosed && !gs.isEnded()) {
            // Loop while window is still open, even if the game ended.
            // If not playing with visuals, loop while the game's not ended.
            tick();

            // Check end of game
            if (firstEnd && gs.isEnded()) {
                firstEnd = false;

                if (!visuals) {
                    // The game has ended, end the loop if we're running without visuals.
                    break;
                }
            }

            // Paint game state
            if (visuals && frame != null) {
                frame.paint();
                try {
                    Thread.sleep(Config.FRAME_DELAY);
                } catch (InterruptedException e) {
                    System.out.println("EXCEPTION " + e);
                }
            }
        }

        ParameterSet[] agentsParams = new ParameterSet[players.size()];
        for (ParameterizedPlayer p : players)
        {
            agentsParams[p.playerID] = p.getParameters();
        }
        return new GameLogger(this.gs, agentsParams);
    }

    public Types.TILETYPE[][] getBoard()
    {
        return gs.getBoard();
    }

    /**
     * Ticks the game forward. Asks agents for actions and applies returned actions to obtain the next game state.
     */
    private void tick () {

        // Retrieve agent actions
        Types.ACTIONS[] actions = getAvatarActions();

        // Advance the game state
        gs.next(actions);
    }

    /**
     * Get player actions, 1 for each avatar still in the game. Called at every frame.
     */
    private Types.ACTIONS[] getAvatarActions() {
        int numOfPlayers = players.size();
        // Get player actions, 1 for each avatar still in the game
        Types.ACTIONS[] actions = new Types.ACTIONS[numOfPlayers];
        for (int i = 0; i < numOfPlayers; i++) {
            Player p = players.get(i);
            // TODO Check if this player is still playing
            actions[i] = p.act(gs.copy());
        }
        return actions;
    }
}
