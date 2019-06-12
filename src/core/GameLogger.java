package core;

import com.google.gson.Gson;
import players.optimisers.ParameterSet;
import utils.Board;
import utils.Types;

import java.util.HashMap;
import java.util.Map;

public class GameLogger
{
    private GameState gs;
    private ParameterSet[] agentsParams;

    public GameLogger(GameState gs, ParameterSet[] agentsParams)
    {
        this.gs = gs;
        this.agentsParams = agentsParams;
    }
}
