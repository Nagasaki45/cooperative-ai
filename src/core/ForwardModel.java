package core;

import utils.Types;
import utils.Vector2d;

import java.util.HashMap;
import java.util.Map;

public class ForwardModel {

    private static boolean validCrossing(Map<Integer, Vector2d> playerPositions, Map<Integer, Vector2d> newPlayerPosisitions) {
        if (playerPositions.get(0).equals(newPlayerPosisitions.get(1)) && playerPositions.get(1).equals(newPlayerPosisitions.get(0))) {
            return false;
        } else {
            return true;
        }
    }

    public static void applyActions(GameState gs, Types.ACTIONS[] actions)
    {
        Map<Integer, Vector2d> playerPositions = gs.getPlayerPositions();
        Map<Integer, Vector2d> newPlayerPositions = new HashMap<Integer, Vector2d>();;

        for (int i = 0; i < 2; i++) { //IS THIS SUPPOSED TO BE PLAYER LENGTH?
                Types.ACTIONS action = actions[i];
                Vector2d directionVec = action.getDirection().toVec();
                Vector2d pos = playerPositions.get(i);

                Vector2d targetPos = new Vector2d( pos.x + directionVec.x, pos.y + directionVec.y );

                Types.TILETYPE[][] board = gs.getBoard();

                if( board[targetPos.y][targetPos.x] == Types.TILETYPE.RIGID) {
                    targetPos = pos;
                }

            newPlayerPositions.put(i, targetPos);
        }

        if(!newPlayerPositions.get(0).equals(newPlayerPositions.get(1)) && validCrossing(playerPositions, newPlayerPositions)) {
            gs.updatePlayerPositions(newPlayerPositions);
        }


    }
}
