package core;

import utils.GUI;
import utils.Types;
import utils.WindowInput;

import java.util.ArrayList;


public class Game {

    private GameState gs;
    private ArrayList<Player> players;
    private boolean visuals;

    public Game(Types.TILETYPE[][] board, ArrayList<Player> players) {
        this.gs = new GameState(board);
        this.players = players;
        this.visuals = Config.VISUALS;
        for (Player p : players)
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
    public Types.RESULT[] run(GUI frame, WindowInput wi)
    {
        if (frame == null || wi == null)
            visuals = false;

        boolean firstEnd = true;
        Types.RESULT[] results = null;

        while(!isEnded() || visuals && wi != null && !wi.windowClosed && !isEnded()) {
            // Loop while window is still open, even if the game ended.
            // If not playing with visuals, loop while the game's not ended.
            tick();

            // Check end of game
            if (firstEnd && isEnded()) {
                firstEnd = false;
                results = terminate();

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

        // The loop may have been broken out of before the game ended. Handle end-of-game:
        if (firstEnd) {
            results = terminate();
        }

        return results;
    }

    public Types.TILETYPE[][] getBoard()
    {
        return gs.getBoard();
    }

    private boolean isEnded()
    {
        // TODO proper isEnded.
        return false;
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
            actions[i] = p.act(gs);
        }
        return actions;
    }


    private Types.RESULT[] terminate() {
        //Build the results array
//        GameObject[] agents = gs.getAgents();
        Types.RESULT[] results = new Types.RESULT[players.size()];
//        for (int i = 0; i < NUM_PLAYERS; i++) {
//            Avatar av = (Avatar) agents[i];
//            results[i] = av.getWinner();
//        }
//
//        // Call all agents' end-of-game method for post-processing. Agents receive their final reward.
//        double[] finalRewards = getGameConfig().getRewards(getTick(), results);
//        for (int i = 0; i < NUM_PLAYERS; i++) {
//            Player p = players.get(i);
//            p.result(finalRewards[i]);
//        }
//
//        if (LOGGING_STATISTICS)
//            gs.model.saveEventsStatistics(gameIdStr, seed);
//
//        System.out.println(Arrays.toString(results));
        return results;
    }
}
