package players.heuristics;

import core.GameState;

import java.util.Random;

public abstract class StateHeuristic {

//    protected Random m_rnd;
//    protected GameState m_prev_gs;
//    protected Integer m_playerId;
//
//    public StateHeuristic(GameState gs, Integer playerId, Random rnd)
//    {
//        m_prev_gs = gs;
//        m_playerId = playerId;
//        m_rnd = rnd;
//    }

    public abstract double evaluateState(GameState gs);
}
