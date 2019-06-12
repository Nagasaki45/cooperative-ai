package core;

import com.google.gson.Gson;
import players.optimisers.ParameterSet;
import utils.Board;
import utils.Types;

import java.util.HashMap;
import java.util.Map;

public class GameLogger
{
    private Types.TILETYPE[][] board;
    private ParameterSet[] agentsParams;
    private int ticks;

    public GameLogger(Types.TILETYPE[][] board, ParameterSet[] agentsParams)
    {
        this.board = board;
        this.agentsParams = agentsParams;
        ticks = 0;
    }

    public void incrementTicks()
    {
        ticks++;
    }

    public Map<String, Object> getJsonReady()
    {
        Map<String, Object> toReturn = new HashMap<>();
        toReturn.put("ticks", ticks);
        for (int i = 0; i < agentsParams.length; i++)
        {
            ParameterSet params = agentsParams[i];
            toReturn.put("agent_" + i + "_params", params.getJsonReady());
        }
        toReturn.put("board", Board.toString(board));
        return toReturn;
    }

    public String toJson()
    {
        Gson gson = new Gson();
        return gson.toJson(getJsonReady());
    }

    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, Object> entry : getJsonReady().entrySet())
        {
            if (entry.getKey() == "board")
            {
                stringBuilder.append((entry.getValue()));
            }
            else
            {
                stringBuilder.append(entry.getKey() + ":\t" + entry.getValue().toString() + "\n");
            }
        }
        return stringBuilder.toString();
    }
}
