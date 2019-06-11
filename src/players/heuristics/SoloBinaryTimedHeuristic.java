package players.heuristics;

import core.GameState;

import java.util.Random;

public class SoloBinaryTimedHeuristic extends StateHeuristic {

    private Random m_rnd;
    private GameState m_prev_gs;
    private Integer m_playerId;

    public SoloBinaryTimedHeuristic(GameState gs, Integer playerId, Random rnd)
    {
        m_prev_gs = gs;
        m_playerId = playerId;
        m_rnd = rnd;
    }

    @Override
    public double evaluateState(GameState gs) {

        if(m_playerId == 0)
        {
            if(gs.getPlayerPositions().get(0).y == gs.getBoardHeight()-2)
            {
                return 500 - gs.getTick();
            }
            else
            {
                return 0;
            }
        }
        else if(m_playerId == 1)
        {
            if(gs.getPlayerPositions().get(1).y == 1)
            {
                return 500 - gs.getTick();
            }
            else
            {
                return 0;
            }
        }
        else {
            System.out.println("Something went wrong, player ID should only be 0 or 1.");
            return 0;
        }
    }
}
