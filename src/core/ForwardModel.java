package core;

import utils.Types;
import utils.Vector2d;

import java.util.HashMap;
import java.util.Map;

public class ForwardModel {

    private static boolean validCrossing(Map<Integer, Vector2d> playerPositions, Map<Integer, Vector2d> newPlayerPosisitions, Types.ACTIONS[] actions) {
        if (playerPositions.get(0) == newPlayerPosisitions.get(1)
                || playerPositions.get(1) == newPlayerPosisitions.get(0)) {
            if (actions[0] == Types.ACTIONS.ACTION_RIGHT && actions[1] == Types.ACTIONS.ACTION_LEFT
                    || actions[0] == Types.ACTIONS.ACTION_LEFT && actions[1] == Types.ACTIONS.ACTION_RIGHT
                    || actions[0] == Types.ACTIONS.ACTION_UP && actions[1] == Types.ACTIONS.ACTION_DOWN
                    || actions[0] == Types.ACTIONS.ACTION_DOWN && actions[1] == Types.ACTIONS.ACTION_UP) {
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

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

        System.out.println("playerPositions" + playerPositions.toString());
        System.out.println("newPlayerPositions" + newPlayerPosisitions.toString());

        if(newPlayerPosisitions.get(0) != newPlayerPosisitions.get(1) && validCrossing(playerPositions, newPlayerPosisitions, actions)) {
            gs.updatePlayerPositions(newPlayerPosisitions);

        }


    }
}
