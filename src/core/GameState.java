package core;

import utils.Types;


public class GameState {

    private Types.TILETYPE[][] board;


    public GameState(Types.TILETYPE[][] board) {
        this.board = board;
    }

    public void next(Types.ACTIONS[] actions) {
        ForwardModel.applyActions(this, actions);
    }

    public GameState copy() {
        return new GameState(this.board);
    }

    public Types.TILETYPE[][] getBoard()
    {
        return board;
    }
}
