package utils;

import javax.swing.*;
import java.awt.*;

import static utils.Types.*;

public class GameView extends JComponent {

    private int cellSize, gridSize;
    private Types.TILETYPE[][] objs;
    private int[][] bombLife;
    private Image backgroundImg;

    /**
     * Dimensions of the window.
     */
    private Dimension dimension;

    GameView(Types.TILETYPE[][] objects, int cellSize)
    {
        this.cellSize = cellSize;
        this.gridSize = objects.length;
        this.dimension = new Dimension(gridSize * cellSize, gridSize * cellSize);
        copyObjects(objects, new int[gridSize][gridSize]);
        backgroundImg = Types.TILETYPE.PASSAGE.getImage();
    }


    public void paintComponent(Graphics gx)
    {
        Graphics2D g = (Graphics2D) gx;
        paintWithGraphics(g);
    }

    private void paintWithGraphics(Graphics2D g)
    {
        //For a better graphics, enable this: (be aware this could bring performance issues depending on your HW & OS).
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.BLACK);
        g.fillRect(0, dimension.height, dimension.width, dimension.height);

        for(int i = 0; i < gridSize; ++i) {
            for(int j = 0; j < gridSize; ++j) {
                Types.TILETYPE gobj = objs[i][j];

                if (null != gobj) {
                    Rectangle rect = new Rectangle(j*cellSize, i*cellSize, cellSize, cellSize);
                    if(gobj != Types.TILETYPE.PASSAGE) {
                        //Background:
                        drawImage(g, backgroundImg, rect);
                    }

                    // Actual image (admits transparencies).
                    Image objImage = gobj.getImage();
                    if (objImage != null) {
                        drawImage(g, objImage, rect);
                    }
                }
            }
        }

        g.setColor(Color.BLACK);
        //player.draw(g); //if we want to give control to the agent to paint something (for debug), start here.
    }

    static void drawImage(Graphics2D gphx, Image img, Rectangle r)
    {
        int w = img.getWidth(null);
        int h = img.getHeight(null);
        float scaleX = (float)r.width/w;
        float scaleY = (float)r.height/h;

        gphx.drawImage(img, r.x, r.y, (int) (w*scaleX), (int) (h*scaleY), null);
    }

    private void copyObjects(Types.TILETYPE[][] objects, int[][] bombs)
    {
        objs = new Types.TILETYPE[gridSize][gridSize];
        bombLife = new int[gridSize][gridSize];

        for (int i = 0; i < gridSize; ++i) {
            System.arraycopy(objects[i], 0, objs[i], 0, gridSize);
            System.arraycopy(bombs[i], 0, bombLife[i], 0, gridSize);
        }
    }

    /**
     * Gets the dimensions of the window.
     * @return the dimensions of the window.
     */
    public Dimension getPreferredSize() {
        return dimension;
    }
}
