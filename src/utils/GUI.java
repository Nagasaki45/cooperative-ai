package utils;

import core.Config;
import core.Game;

import javax.swing.*;
import java.awt.*;


public class GUI extends JFrame {
    private JLabel appTick;
    private GameView[] views;
    private Game game;
//    private AvatarView avatarDisplayPanel;
//    private KeyController ki;
    private int humanIdx;  // human player index in array of players
    private boolean displayPOHuman;  // if side views should be displayed when human is playing

    /**
     * Constructor
     * @param title Title of the window.
     */
    public GUI(Game game, String title, boolean closeAppOnClosingWindow, boolean displayPOHuman) {
        super(title);
        this.game = game;
        this.displayPOHuman = displayPOHuman;

        // Create all the game views. Main view is first in list, showing true fully observable game state
        createGameViews();

        // Create frame layout
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weighty = 0;
        setLayout(gbl);

        // Main panel definition
        JPanel mainPanel = getMainPanel();

        // Add everything to side panel if we need it to be displayed
        JPanel poPanel = getPoPanel();

        /* Add all elements to the content pane */

        // Content + side padding
        gbc.gridx = 0;
        getContentPane().add(Box.createRigidArea(new Dimension(10, 0)), gbc);
        gbc.gridx++;
        getContentPane().add(mainPanel, gbc);
        gbc.gridx++;
        getContentPane().add(Box.createRigidArea(new Dimension(10, 0)), gbc);
        if (poPanel != null) {
            gbc.gridx++;
            getContentPane().add(poPanel, gbc);
        }
        gbc.gridx++;
        getContentPane().add(Box.createRigidArea(new Dimension(10, 0)), gbc);

        // Bottom row, bottom margin padding
        gbc.gridy++;
        gbc.gridx = 0;
        getContentPane().add(Box.createRigidArea(new Dimension(0, 10)), gbc);

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
     * Creates game views to be displayed.
     */
    private void createGameViews() {
        views = new GameView[1];  // N individual views + main true game state
        for (int i = 0; i < views.length; i++) {
            views[i] = new GameView(game.getBoard(), Config.CELL_SIZE);
        }
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

        JLabel appTitle = new JLabel("Java-Pommerman");
        Font textFont = new Font(appTitle.getFont().getName(), Font.BOLD, 20);
        appTitle.setFont(textFont);

        JLabel modeLabel = new JLabel("game mode: ASDASDASD");
        textFont = new Font(appTitle.getFont().getName(), Font.PLAIN, 16);
        modeLabel.setFont(textFont);

        appTick = new JLabel("tick: 0");
        appTick.setFont(textFont);

//        avatarDisplayPanel = new AvatarView(game.getAvatars(-1));

        // Add everything to main panel
        mainPanel.add(appTitle, c);
        c.gridy++;
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)), c);
        c.gridy++;
        mainPanel.add(appTick, c);
        c.gridy++;
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)), c);
        c.gridy++;
        mainPanel.add(modeLabel, c);
        c.gridy++;
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)), c);
        c.gridy++;
//        mainPanel.add(avatarDisplayPanel, c);
//        c.gridy++;
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)), c);
        c.gridy++;
        mainPanel.add(views[0], c);

        return mainPanel;
    }

    /**
     * Creates the side panel.
     * @return null if side panel should not be displayed, side panel otherwise.
     */
    private JPanel getPoPanel() {
        JPanel poPanel = null;
        if (humanIdx == -1 || displayPOHuman) {
            poPanel = new JPanel();
            poPanel.setLayout(new BoxLayout(poPanel, BoxLayout.Y_AXIS));
            poPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            for (int i = 1; i < views.length; i++) {
                poPanel.add(views[i]);
                poPanel.add(Box.createRigidArea(new Dimension(0, 5)));
            }
        }
        return poPanel;
    }

    /**
     * Paints the GUI, to be called at every game tick.
     */
    public void paint() {
        // Update all views
        for (int i = 0; i < views.length; i++) {
            if (views[i] != null) {  // Side views (i > 0) may be null if human is playing and side view not displayed.
                views[i].repaint();
            }
        }

        // Update avatar display panel.
//        avatarDisplayPanel.paint(game.getAvatars(focusedPlayer));

        // If human player died, show full observability for the rest of the match. Allows main view switching.
//        if (humanIdx > -1 && !avatarDisplayPanel.getAlive()[humanIdx]) {
//            humanIdx = -1;
//        }

        // Update game tick.
        appTick.setText("tick: ASDASD");
    }
}
