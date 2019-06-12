import ntbea.NTupleBanditEA;
import ntbea.NTupleSystem;
import ntbea.NTupleSystemReport;
import players.mcts.MCTSParams;
import players.optimisers.EvaluateCooperation;
import utilities.ElapsedTimer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;


public class RunHeuristicSpecificNTBEA {

    public static final int NUM_OF_EVALUATIONS = 100;  // Each evaluation averages ticks on all boards
    public static final double NOISE = 1.0;
    public static final int K_EXPLORE = 2;
    public static final double EPSILON = 0.5;

    public static void main(String[] args) {

        // Optimising parameters
        MCTSParams parameterSet = new MCTSParams();
        Map<String, Object[]> params = parameterSet.getParameterValues();
        ArrayList<String> paramList = parameterSet.getParameters();

        ArrayList<Integer> possibleValues = new ArrayList<>();
        for (String p : paramList) {
            if (parameterSet.getParameterParent(p) == null) {  // Use only top level parameters
                possibleValues.add(params.get(p).length);
            }
        }

        for (int heuristicID = 1; heuristicID < 5; heuristicID++)
        {
            EvaluateCooperation problem = new EvaluateCooperation(possibleValues, NOISE, heuristicID);

            NTupleBanditEA banditEA = new NTupleBanditEA().setKExplore(K_EXPLORE).setEpsilon(EPSILON);
            // set a particlar NTuple System as the model
            // if this is not set, then it will use a default model
            NTupleSystem model = new NTupleSystem();
            // set up a non-standard tuple pattern
            model.use1Tuple = true;
            model.use2Tuple = true;
            model.use3Tuple = false;
            model.useNTuple = true;
            banditEA.setModel(model);

            ElapsedTimer timer = new ElapsedTimer();
            int[] solution = banditEA.runTrial(problem, NUM_OF_EVALUATIONS);

//            System.out.println("Report: ");
//            new NTupleSystemReport().setModel(model).printDetailedReport();
//            new NTupleSystemReport().setModel(model).printSummaryReport();

            System.out.println("Heuristic ID: " + heuristicID);
            System.out.println("Solution returned: " + Arrays.toString(solution));
            System.out.println("Solution fitness:  " + problem.trueFitness(solution));
            System.out.println(timer);
        }
    }
}

