package core;

import utils.Types;
import utils.Vector2d;

import java.util.HashMap;
import java.util.Map;


public class GameState {

    private Types.TILETYPE[][] board;
    private Map<Integer, Vector2d> playerPositions;
    private Integer tick = 0;


    public GameState(Types.TILETYPE[][] board) {
        this.board = board;
        this.playerPositions = new HashMap<Integer, Vector2d>();
    }

    public void addPlayer(int playerID)
    {
        Vector2d boardSize = getBoardSize();
        int x = boardSize.x / 2;
        int y = (playerID == 0) ? 1: boardSize.y - 2;
        playerPositions.put(playerID, new Vector2d(x, y));
    }

    public void updatePlayerPositions( Map<Integer, Vector2d> newPlayerPositions ){
        for (int i = 0; i < playerPositions.size(); i++)
        {
            Vector2d oldPosition = playerPositions.get(i);
            board[oldPosition.y][oldPosition.x] = Types.TILETYPE.PASSAGE;
        }
        for (int i = 0; i < playerPositions.size(); i++)
        {
            Vector2d newPosition = newPlayerPositions.get(i);
            board[newPosition.y][newPosition.x] = (i == 0) ? Types.TILETYPE.AGENT0 : Types.TILETYPE.AGENT1;
        }
        playerPositions = newPlayerPositions;
    }

    public Map<Integer, Vector2d> getPlayerPositions(){
        return playerPositions;
    }

    public void next(Types.ACTIONS[] actions) {
        tick++;
        ForwardModel.applyActions(this, actions);
    }

    public boolean isEnded()
    {
        if (tick >= Config.MAX_GAME_TICKS)
        {
            return true;
        }

        for (boolean winner : getWinners())
        {
            if (!winner)
            {
                return false;
            }
        }
        return true;
    }

    public Vector2d getBoardSize()
    {
        int y = board.length;
        int x = board[0].length;
        return new Vector2d(x, y);
    }

    public GameState copy() {
        Vector2d boardSize = getBoardSize();
        GameState copy = new GameState(new Types.TILETYPE[boardSize.y][boardSize.x]);

        // Copy board
        for (int y = 0; y < boardSize.y; y++)
        {
            for (int x = 0; x < boardSize.x; x++)
            {
                copy.board[y][x] = board[y][x];
            }
        }

        // Copy player positions
        copy.playerPositions = new HashMap<Integer, Vector2d>();
        for (Map.Entry<Integer, Vector2d> entry : this.playerPositions.entrySet())
        {
            copy.playerPositions.put(entry.getKey(), entry.getValue());
        }

        // Copy tick
        copy.tick = tick;

        return copy;
    }

    public Types.TILETYPE[][] getBoard()
    {
        return board;
    }

    public Integer getBoardHeight()
    {
        return board.length;
    }

    public Integer getBoardWidth()
    {
        return board[0].length;
    }

    public Integer getTick()
    {
        return tick;
    }


    public int nActions()
    {
        return Types.ACTIONS.all().size();
    }

    public boolean[] getWinners()
    {
        //Make all players win condition be to reach the top of the screen
        int numOfPlayers = playerPositions.size();
        boolean[] winners = new boolean[numOfPlayers];
        for(int i = 1; i < numOfPlayers; i++){
            winners[i] = (playerPositions.get(i).y == 1);
    }

        //...except player 1, who must reach the bottom
        winners[0] = (playerPositions.get(0).y == (getBoardSize().y - 2));


        return winners;
    }
}
