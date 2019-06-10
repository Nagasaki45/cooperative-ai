package core;

import utils.Types;
import utils.Vector2d;

import java.util.HashMap;
import java.util.Map;


public class GameState {

    private Types.TILETYPE[][] board;
    private Map<Integer, Vector2d> playerPositions;


    public GameState(Types.TILETYPE[][] board) {
        this.board = board;
        this.playerPositions = new HashMap<Integer, Vector2d>();
    }

    public void addPlayer(int playerID)
    {
        int y;
        int x = board.length / 2;

        if (playerID == 0) {
            y = 1;
            this.board[y][x] = Types.TILETYPE.AGENT0;
        } else {
            y = board.length - 2;
            this.board[y][x] = Types.TILETYPE.AGENT1;
        }

        playerPositions.put(playerID, new Vector2d(x, y));
    }

    public void next(Types.ACTIONS[] actions) {
        ForwardModel.applyActions(this, actions);
    }

    public GameState copy() {
        GameState copy = new GameState(this.board);
        copy.playerPositions = new HashMap<Integer, Vector2d>();
        for (Map.Entry<Integer, Vector2d> entry : this.playerPositions.entrySet())
        {
            copy.playerPositions.put(entry.getKey(), entry.getValue());
        }
        return copy;
    }

    public Types.TILETYPE[][] getBoard()
    {
        return board;
    }
}
