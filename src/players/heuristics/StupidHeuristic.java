package players.heuristics;

import core.GameState;
import java.util.Random;

public class StupidHeuristic extends StateHeuristic {

    private Random m_rnd;
    private GameState m_prev_gs;
    private Integer m_playerId;

    public StupidHeuristic(GameState gs, Integer playerId, Random rnd)
    {
        m_prev_gs = gs;
        m_playerId = playerId;
        m_rnd = rnd;
    }

    @Override
    public double evaluateState(GameState gs) {
        return 1;
    }
}
