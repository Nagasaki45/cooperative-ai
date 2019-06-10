package utils;

import core.Config;
import core.Game;

import javax.swing.*;
import java.awt.*;


public class GUI extends JFrame {
    private GameView view;
    private Game game;
    private String title;


    /**
     * Constructor
     * @param title Title of the window.
     */
    public GUI(Game game, String title, boolean closeAppOnClosingWindow) {
        super(title);
        this.game = game;
        this.title = title;

        this.view = new GameView(game.getBoard(), Config.CELL_SIZE);

        // Create frame layout
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weighty = 0;
        setLayout(gbl);

        // Main panel definition
        JPanel mainPanel = getMainPanel();

        /* Add all elements to the content pane */
        getContentPane().add(mainPanel, gbc);

        // Frame properties
        pack();
        this.setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        if(closeAppOnClosingWindow){
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
        repaint();
    }

    /**
     * Creates the main panel containing main view of true game state (or human if human playing).
     * Includes information about the game:
     *  - game tick
     *  - game mode
     *  - game avatars status
     * @return main panel.
     */
    @SuppressWarnings("Duplicates")
    private JPanel getMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.SOUTH;
        c.weighty = 0;

        JLabel appTitle = new JLabel(title);
        Font textFont = new Font(appTitle.getFont().getName(), Font.BOLD, 20);
        appTitle.setFont(textFont);

        // Add everything to main panel
        mainPanel.add(appTitle, c);
        c.gridy++;
        c.gridy++;
        mainPanel.add(view, c);

        return mainPanel;
    }

    /**
     * Paints the GUI, to be called at every game tick.
     */
    public void paint() {
        view.repaint();
    }
}
