package uniandes.dpoo.taller4.interfaz;

import java.awt.*;
import javax.swing.*;

public class DetailsPanel extends JPanel {
    // Attributes
    private JTextField txtMoves;
    private JTextField txtPlayer;

    // Constructor
    public DetailsPanel() {
        setLayout(new GridLayout(2, 2, 10, 10));
        setBackground(new Color(50, 70, 90));

        JLabel moves = new JLabel("Moves:");
        Font fMoves = moves.getFont();
        moves.setFont(fMoves.deriveFont(fMoves.getStyle() & ~Font.BOLD));
        moves.setForeground(Color.WHITE);
        this.txtMoves = new JTextField();

        JLabel player = new JLabel("Player:");
        Font fPlayer = player.getFont();
        player.setFont(fPlayer.deriveFont(fPlayer.getStyle() & ~Font.BOLD));
        player.setForeground(Color.WHITE);
        this.txtPlayer = new JTextField();

        txtMoves.setEditable(false);
        txtPlayer.setEditable(false);

        add(moves);
        add(txtMoves);
        add(player);
        add(txtPlayer);
    }

    // Methods
    public String getMoves() {
        return txtMoves.getText();
    }

    public String getPlayer() {
        return txtPlayer.getText();
    }

    public void setPlayer(String name) {
        String playerName = name;
        txtPlayer.setText(playerName);
    }

    public void setMoves(int moves) {
        txtMoves.setText(Integer.toString(moves));
    }
}
