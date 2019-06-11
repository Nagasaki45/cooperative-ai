package utils;

import java.awt.*;
import java.util.ArrayList;

public class Types {
    public static int MAX_GAME_TICKS = 500;
    public static int FRAME_DELAY = 250;
    public enum TILETYPE {

        //Types and IDs
        PASSAGE(0),
        RIGID(1),
        AGENT0(10),
        AGENT1(11);

        private int key;
        TILETYPE(int numVal) {  this.key = numVal;  }
        public int getKey() {  return key; }

        /**
         * Sprites (Image objects) to use in the game for the different elements.
         * @return the image to use
         */

        public Image getImage()
        {
            if      (key == PASSAGE.key) return ImageIO.GetInstance().getImage("img/passage.png");
            else if (key == RIGID.key) return ImageIO.GetInstance().getImage("img/rigid.png");
            else if (key == AGENT0.key) return ImageIO.GetInstance().getImage("img/agent0.png");
            else if (key == AGENT1.key) return ImageIO.GetInstance().getImage("img/agent1.png");
            else return null;
        }
    }

    public enum ACTIONS {
        ACTION_STOP(0),
        ACTION_UP(1),
        ACTION_DOWN(2),
        ACTION_LEFT(3),
        ACTION_RIGHT(4);

        private int key;

        ACTIONS(int numVal) {
            this.key = numVal;
        }

        public int getKey() {
            return this.key;
        }

        /**
         * Gets all actions of the game
         *
         * @return all the actions in an array list.
         */
        public static ArrayList<ACTIONS> all() {
            ArrayList<ACTIONS> allActions = new ArrayList<ACTIONS>();
            allActions.add(ACTION_STOP);
            allActions.add(ACTION_UP);
            allActions.add(ACTION_DOWN);
            allActions.add(ACTION_LEFT);
            allActions.add(ACTION_RIGHT);
            return allActions;
        }

        public DIRECTIONS getDirection() {
                if(this == ACTION_UP)
                    return DIRECTIONS.UP;
                else if(this == ACTION_DOWN)
                    return DIRECTIONS.DOWN;
                else if(this == ACTION_LEFT)
                    return DIRECTIONS.LEFT;
                else if(this == ACTION_RIGHT)
                    return DIRECTIONS.RIGHT;
                else
                    return DIRECTIONS.NONE;
            }
    }

    public enum DIRECTIONS {
        NONE(0, 0),
        LEFT(-1, 0),
        RIGHT(1, 0),
        UP(0, -1),
        DOWN(0, 1);

        private int x, y;

        DIRECTIONS(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Vector2d toVec() {
            return new Vector2d(x, y);
        }

        public int x() {return x;}
        public int y() {return y;}
    }


}
