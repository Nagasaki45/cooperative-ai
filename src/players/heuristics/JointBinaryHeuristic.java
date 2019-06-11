package players.heuristics;

import core.GameState;
import java.util.Random;

public class JointBinaryHeuristic extends StateHeuristic {

    private Random m_rnd;
    private GameState m_prev_gs;
    private Integer m_playerId;

    public JointBinaryHeuristic(GameState gs, Integer playerId, Random rnd)
    {
        m_prev_gs = gs;
        m_playerId = playerId;
        m_rnd = rnd;
    }

    @Override
    public double evaluateState(GameState gs) {

        double score = 0;

        if(gs.getPlayerPositions().get(0).y == gs.getBoardHeight()-2)
        {
            score += 500;
        }
        if(gs.getPlayerPositions().get(1).y == 1)
        {
            score += 500;
        }
        System.out.println(score);

        return score;
    }
}
