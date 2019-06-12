import ntbea.NTupleBanditEA;
import ntbea.NTupleSystem;
import ntbea.NTupleSystemReport;
import players.mcts.MCTSParams;
import players.optimisers.EvaluateCooperation;
import utilities.ElapsedTimer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;


public class RunNTBEA {

    public static final int NUM_OF_EVALUATIONS = 3;  // Each evaluation averages ticks on all boards

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

        EvaluateCooperation problem = new EvaluateCooperation(possibleValues, 0.5);

        double kExplore = 2;
        double epsilon = 0.5;
        NTupleBanditEA banditEA = new NTupleBanditEA().setKExplore(kExplore).setEpsilon(epsilon);
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

        System.out.println("Report: ");
        new NTupleSystemReport().setModel(model).printDetailedReport();
        new NTupleSystemReport().setModel(model).printSummaryReport();

        System.out.println();
        System.out.println("Solution returned: " + Arrays.toString(solution));
        System.out.println("Solution fitness:  " + problem.trueFitness(solution));
        System.out.println(timer);
    }
}

