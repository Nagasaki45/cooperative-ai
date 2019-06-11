package players.heuristics;

import core.GameState;

public class StupidHeuristic extends StateHeuristic {
    @Override
    public double evaluateState(GameState gs) {
        return 1;
    }
}
