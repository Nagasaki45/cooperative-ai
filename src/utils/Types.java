package utils;

import core.gameConfig.IGameConfig;
import core.gameConfig.OriginalGameConfig;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

public class Types {

    public enum TILETYPE {

        //Types and IDs
        PASSAGE(0),
        RIGID(1),
        WOOD(2),
        BOMB(3),
        FLAMES(4),
        FOG(5),
        EXTRABOMB(6),
        INCRRANGE(7),
        KICK(8),
        AGENTDUMMY(9),
        AGENT0(10),
        AGENT1(11),
        AGENT2(12),
        AGENT3(13);

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
            else if (key == WOOD.key) return ImageIO.GetInstance().getImage("img/wood.png");
            else if (key == BOMB.key) return ImageIO.GetInstance().getImage("img/bomb.png");
            else if (key == FLAMES.key) return ImageIO.GetInstance().getImage("img/flames.png");
            else if (key == FOG.key) return ImageIO.GetInstance().getImage("img/fog.png");
            else if (key == EXTRABOMB.key) return ImageIO.GetInstance().getImage("img/extrabomb.png");
            else if (key == INCRRANGE.key) return ImageIO.GetInstance().getImage("img/incrrange.png");
            else if (key == KICK.key) return ImageIO.GetInstance().getImage("img/kick.png");
            else if (key == AGENTDUMMY.key) return ImageIO.GetInstance().getImage("img/skull1.png");
            else if (key == AGENT0.key) return ImageIO.GetInstance().getImage("img/agent0.png");
            else if (key == AGENT1.key) return ImageIO.GetInstance().getImage("img/agent1.png");
            else if (key == AGENT2.key) return ImageIO.GetInstance().getImage("img/agent2.png");
            else if (key == AGENT3.key) return ImageIO.GetInstance().getImage("img/agent3.png");
            else return null;
        }
    }
    
    /**
     * Results of the game.
     */
    public enum RESULT {
        WIN(0),
        LOSS(1),
        TIE(2),
        INCOMPLETE(3);

        private int key;
        RESULT(int numVal) { this.key = numVal; }
        public int getKey() { return this.key; }

        /**
         * Returns the colour that represents such victory condition for the GUI.
         * @return colours of results.
         */
        public Color getColor() {
            if (key == WIN.key) return Color.green;
            if (key == LOSS.key) return Color.red;
            if (key == TIE.key) return Color.orange;
            return null;
        }
    }
}
