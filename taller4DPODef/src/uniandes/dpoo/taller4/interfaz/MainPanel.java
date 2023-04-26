package uniandes.dpoo.taller4.interfaz;
//DONE
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uniandes.dpoo.taller4.modelo.*;

public class MainPanel extends JPanel implements ActionListener {

    private Board boardPanel;
    private DetailsPanel detailsPanel;
    private SettingsBar configPanel;
    private JButton btnNewGame, btnReset, btnTop10, btnChangePlayer;
    private LightsOutUI parent;
    private GameController gameController;

    public MainPanel(LightsOutUI parent) {
        setLayout(new BorderLayout());
        this.parent = parent;
        //gameController = new GameController(detailsPanel);

        boardPanel = new Board(this, 3);
        detailsPanel = new DetailsPanel();
        configPanel = new SettingsBar();
        add(boardPanel, BorderLayout.CENTER);
        add(detailsPanel, BorderLayout.SOUTH);
        add(configPanel, BorderLayout.NORTH);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(4, 1, 0, 10));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(buttonsPanel, BorderLayout.EAST);

        btnNewGame = createButton("New Game", "NEW_GAME", buttonsPanel);
        btnReset = createButton("Reset", "RESET", buttonsPanel);
        btnTop10 = createButton("Top 10", "TOP_10", buttonsPanel);
        btnChangePlayer = createButton("Change Player", "CHANGE_PLAYER", buttonsPanel);
    }

    private JButton createButton(String text, String actionCommand, JPanel parent) {
        JButton button = new JButton(text);
        button.addActionListener(this);
        button.setActionCommand(actionCommand);
        button.setBackground(new Color(42, 157, 143));
        button.setForeground(Color.WHITE);
        button.setFont(button.getFont().deriveFont(Font.BOLD));
        parent.add(button);
        return button;
    }

    public void update() {
        if (boardPanel.isCompleted()) {
            JOptionPane.showMessageDialog(this, "Congratulations! You completed the game.", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public int getBoardSize() {
        return boardPanel.getBoardSize();
    }

    private void updateBoardPanel(int newBoardSize) {
        remove(boardPanel);
        boardPanel = new Board(this, newBoardSize);
        add(boardPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("NEW_GAME")) {
            int newBoardSize = configPanel.getSelectedBoardSize();
            if (getBoardSize() != newBoardSize) {
                updateBoardPanel(newBoardSize);
            }
            boardPanel.newGame();
        } else if (command.equals("RESET")) {
            boardPanel.resetGame();
        } else if (command.equals("TOP_10")) {

        } else if (command.equals("CHANGE_PLAYER")) {

        }
    }

    

    
}
