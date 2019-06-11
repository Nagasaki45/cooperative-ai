package players.heuristics;

import core.GameState;
import java.util.Random;

public class SoloBinaryHeuristic extends StateHeuristic {

    private Random m_rnd;
    private GameState m_prev_gs;
    private Integer m_playerId;

    public SoloBinaryHeuristic(GameState gs, Integer playerId, Random rnd)
    {
        m_prev_gs = gs;
        m_playerId = playerId;
        m_rnd = rnd;
    }

    @Override
    public double evaluateState(GameState gs) {
        return 500 * (gs.getWinners()[m_playerId] ? 1 : 0);
    }
}
