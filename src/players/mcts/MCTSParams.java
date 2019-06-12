package players.mcts;

import javafx.util.Pair;
import players.optimisers.ParameterSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("WeakerAccess")
public class MCTSParams implements ParameterSet {

    // Constants
    public final double HUGE_NEGATIVE = -1000;
    public final double HUGE_POSITIVE =  1000;

    public final int STOP_TIME = 0;
    public final int STOP_ITERATIONS = 1;
    public final int STOP_FMCALLS = 2;

    public final int STUPID_HEURISTIC = 0;
    public final int SOLO_BINARY_HEURISTIC = 1;
    public final int JOINT_BINARY_HEURISTIC = 2;
    public final int SOLO_DISTANCE_HEURISTIC = 3;
    public final int JOINT_DISTANCE_HEURISTIC = 4;

    public double epsilon = 1e-6;

    // Parameters
    public double K = Math.sqrt(2);
    public int rollout_depth = 80;//10;
    public int heuristic_method = 1;
    public double discount_factor = 0.99;

    // Budget settings
    public int stop_type = STOP_FMCALLS;
    public int num_iterations = 200;
    public int num_fmcalls = 20000;
    public int num_time = 40;

    public void setOptimizedParams(int heuristic_method)
    {
        switch (heuristic_method) {
            case 1: translate(new int[]{0, 3, 1, 1}); break;
            case 2: translate(new int[]{4, 3, 2, 2}); break;
            case 3: translate(new int[]{1, 4, 3, 1}); break;
            case 4: translate(new int[]{0, 4, 4, 2}); break;
        }
    }

    @Override
    public void setParameterValue(String param, Object value) {
        switch(param) {
            case "K": K = (double) value; break;
            case "rollout_depth": rollout_depth = (int) value; break;
            case "heuristic_method": heuristic_method = (int) value; break;
            case "discount_factor": discount_factor = (double) value; break;
        }
    }

    @Override
    public Object getParameterValue(String param) {
        switch(param) {
            case "K": return K;
            case "rollout_depth": return rollout_depth;
            case "heuristic_method": return heuristic_method;
            case "discount_factor": return discount_factor;
        }
        return null;
    }

    @Override
    public ArrayList<String> getParameters() {
        ArrayList<String> paramList = new ArrayList<>();
        paramList.add("K");
        paramList.add("rollout_depth");
        paramList.add("heuristic_method");
        paramList.add("discount_factor");
        return paramList;
    }

    @Override
    public Map<String, Object[]> getParameterValues() {
        HashMap<String, Object[]> parameterValues = new HashMap<>();
        parameterValues.put("K", new Double[]{1.0, Math.sqrt(2), 2.0, 3.0, 4.0});
        parameterValues.put("rollout_depth", new Integer[]{5, 10, 20, 40, 80});
        parameterValues.put("heuristic_method", new Integer[]{STUPID_HEURISTIC, SOLO_BINARY_HEURISTIC, JOINT_BINARY_HEURISTIC, SOLO_DISTANCE_HEURISTIC, JOINT_DISTANCE_HEURISTIC});
        parameterValues.put("discount_factor", new Double[]{0.5, 0.85, 0.95, 0.99, 1.0});
        return parameterValues;
    }

    @Override
    public Pair<String, ArrayList<Object>> getParameterParent(String parameter) {
        return null;  // No parameter dependencies
    }

    @Override
    public Map<Object, ArrayList<String>> getParameterChildren(String root) {
        return new HashMap<>();  // No parameter dependencies
    }

    @Override
    public Map<String, String[]> constantNames() {
        HashMap<String, String[]> names = new HashMap<>();
        names.put("heuristic_method", new String[]{"STUPID_HEURISTIC", "SOLO_BINARY_HEURISTIC", "JOINT_BINARY_HEURISTIC", "SOLO_DISTANCE_HEURISTIC", "JOINT_DISTANCE_HEURISTIC"});
        return names;
    }
}
