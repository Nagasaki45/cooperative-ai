package core;

import utils.Types;
import utils.Vector2d;

import java.util.HashMap;
import java.util.Map;


public class ForwardModel {

    public static void applyActions(GameState gs, Types.ACTIONS[] actions)
    {
        Map<Integer, Vector2d> playerPositions = gs.getPlayerPositions();
        Map<Integer, Vector2d> newPlayerPosisitions = new HashMap<Integer, Vector2d>();;

        for (int i = 0; i < actions.length; i++) {
                Types.ACTIONS action = actions[i];
                Vector2d directionVec = action.getDirection().toVec();
                Vector2d pos = playerPositions.get(i);
                Vector2d targetPos = new Vector2d( pos.x + directionVec.x, pos.y + directionVec.y );

                Types.TILETYPE[][] board = gs.getBoard();

                if( board[targetPos.y][targetPos.x] == Types.TILETYPE.RIGID) {
                    targetPos = pos;
                }

            newPlayerPosisitions.put(i, targetPos);
        }

        if(newPlayerPosisitions.get(0) != newPlayerPosisitions.get(1)) {
            gs.updatePlayerPositions(newPlayerPosisitions);
        }

    }
}
