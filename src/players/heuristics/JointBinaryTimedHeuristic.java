package players.heuristics;

import core.GameState;

import java.util.Random;

public class JointBinaryTimedHeuristic extends StateHeuristic {

    private Random m_rnd;
    private GameState m_prev_gs;
    private Integer m_playerId;

    public JointBinaryTimedHeuristic(GameState gs, Integer playerId, Random rnd)
    {
        m_prev_gs = gs;
        m_playerId = playerId;
        m_rnd = rnd;
    }

    @Override
    public double evaluateState(GameState gs) {

        double score = 0;
        //System.out.println(gs.getTick());
        if(gs.getPlayerPositions().get(0).y == gs.getBoardHeight()-2)
        {
            score += 250 - gs.getTick()/2;
        }
        if(gs.getPlayerPositions().get(1).y == 1)
        {
            score += 250 - gs.getTick()/2;
        }
        System.out.println(score);

        return score;
    }
}
