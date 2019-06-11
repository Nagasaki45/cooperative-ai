package players.heuristics;

import core.GameState;

import java.util.Random;

public class JointDistanceHeuristic extends StateHeuristic {

    private Random m_rnd;
    private GameState m_prev_gs;
    private Integer m_playerId;

    public JointDistanceHeuristic(GameState gs, Integer playerId, Random rnd)
    {
        m_prev_gs = gs;
        m_playerId = playerId;
        m_rnd = rnd;
    }

    @Override
    public double evaluateState(GameState gs) {
        double boardHeight = gs.getBoardHeight() - 2; //board height minus walls
        double score = 0;

        if(m_playerId == 0)
        {
            score += gs.getPlayerPositions().get(0).y / boardHeight;
        }
        else if(m_playerId == 1)
        {
            score += (boardHeight - gs.getPlayerPositions().get(1).y) / boardHeight;
        }

        return score;
    }
}
