package players.optimisers;

import core.Game;
import core.GameLogger;
import evodef.EvolutionLogger;
import evodef.FitnessSpace;
import evodef.NoisySolutionEvaluator;
import evodef.SearchSpace;
import players.mcts.MCTSPlayer;
import utils.Board;

import java.util.ArrayList;
import java.util.Random;


public class EvaluateCooperation implements NoisySolutionEvaluator, SearchSpace, FitnessSpace {

    private static Random random = new Random();

    private int nDims;
    private ArrayList<Integer> m;
    private double noise;
    private EvolutionLogger logger;
    private int heuristicID;

    public EvaluateCooperation(ArrayList<Integer> possibleValues, double noise)
    {
        this(possibleValues, noise, -1);
    }

    public EvaluateCooperation(ArrayList<Integer> possibleValues, double noise, int heuristicID) {
        this.nDims = possibleValues.size();
        this.m = possibleValues;
        this.noise = noise;
        logger = new EvolutionLogger();
        this.heuristicID = heuristicID;
    }

    @Override
    public void reset() {
        logger.reset();
    }

    @Override
    public Double optimalIfKnown() {
        return 0.0;
    }

    @Override
    public double evaluate(int[] a) {
        double tot = trueFitness(a);
        boolean isOptimal = isOptimal(a);
        tot += noise * random.nextGaussian();
        logger.log(tot, a, isOptimal);
        return tot;
    }

    @Override
    public Double trueFitness(int[] a) {
        double fitness = 0;

        for (int boardID = 0; boardID < Board.NUM_OF_BOARDS; boardID++)
        {
            ArrayList<ParameterizedPlayer> players = new ArrayList<ParameterizedPlayer>();
            players.add(new MCTSPlayer(random.nextInt(),0));
            players.add(new MCTSPlayer(random.nextInt(),1));

            // Translate the given parameters, assign them to the player.
            for (ParameterizedPlayer p : players)
            {
                p.translateParameters(a);
                if (heuristicID != -1)
                {
                    p.getParameters().setParameterValue("heuristic_method", heuristicID);
                }
            }
            Game game = new Game(boardID, players);

            GameLogger gameLogger = game.run(null, null);
            fitness -= gameLogger.getGameState().getTick();
        }

        return fitness / Board.NUM_OF_BOARDS;
    }

    @Override
    public boolean optimalFound() {
        // return false for the noisy optimisation experiments in order
        // to prevent the optimisers from cheating
        return false;
    }

    @Override
    public SearchSpace searchSpace() {
        return this;
    }

    @Override
    public int nEvals() {
        return logger.nEvals();
    }

    @Override
    public EvolutionLogger logger() {
        return logger;
    }


    @Override
    public int nDims() {
        return nDims;
    }

    @Override
    public int nValues(int i) {
        return m.get(i);
    }

    @Override
    public Boolean isOptimal(int[] solution) {
        return false;
    }
}

