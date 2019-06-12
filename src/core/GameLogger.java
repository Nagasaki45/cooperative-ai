package core;

import com.google.gson.Gson;
import players.optimisers.ParameterSet;
import utils.Board;
import utils.Types;

import java.util.HashMap;
import java.util.Map;

public class GameLogger
{
    private String board;
    private ParameterSet[] agentsParams;
    private int ticks;

    public GameLogger(Types.TILETYPE[][] board, ParameterSet[] agentsParams)
    {
        this.board = Board.toString(board);
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
            Gson gson = new Gson();
            toReturn.put("agent_" + i + "_params", gson.toJson(params));
        }
        toReturn.put("board", board);
        return toReturn;
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
