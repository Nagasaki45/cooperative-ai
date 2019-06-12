package players.mcts;

import java.util.ArrayList;

public class MCTSParamsWithoutHeuristic extends MCTSParams {

    @Override
    public ArrayList<String> getParameters() {
        ArrayList<String> paramList = new ArrayList<>();
        paramList.add("K");
        paramList.add("rollout_depth");
        paramList.add("discount_factor");
        return paramList;
    }
}
