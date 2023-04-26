package uniandes.dpoo.taller4.interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SettingsBar extends JPanel implements ActionListener {
    // Attributes
    // Constants associated with difficulty
    public static final String EASY = "2";
    public static final String MEDIUM = "5";
    public static final String HARD = "10";

    // Default problem world attributes
    private int difficultyValue = 2;
    private int sizeValue = 3;

    private JComboBox<String> sizeList;

    // Constructor
    public SettingsBar() {
        setLayout(new GridLayout(2, 4, 10, 10));
        setPreferredSize(new Dimension(0, 70));
        setBackground(new Color(50, 70, 90));

        JLabel size = new JLabel("Size:");
        size.setForeground(Color.WHITE);
        JLabel difficulty = new JLabel("Difficulty:");
        difficulty.setForeground(Color.WHITE);

        String[] options = { "3x3", "4x4", "5x5", "6x6", "7x7", "8x8", "9x9", "10x10" };
        sizeList = new JComboBox<>(options);
        sizeList.setFont(sizeList.getFont().deriveFont(sizeList.getFont().getStyle() & ~Font.BOLD));
        sizeList.addActionListener(e -> {
            sizeValue = Integer.parseInt(sizeList.getSelectedItem().toString().split("x")[0]);
        });

        JRadioButton rbEasy = new JRadioButton("Easy", true);
        rbEasy.addActionListener(this);
        rbEasy.setActionCommand(EASY);
        rbEasy.setBackground(new Color(50, 70, 90));
        rbEasy.setForeground(Color.WHITE);
        rbEasy.setFont(rbEasy.getFont().deriveFont(rbEasy.getFont().getStyle() & ~Font.BOLD));

        JRadioButton rbMedium = new JRadioButton("Medium", false);
        rbMedium.addActionListener(this);
        rbMedium.setActionCommand(MEDIUM);
        rbMedium.setBackground(new Color(50, 70, 90));
        rbMedium.setForeground(Color.WHITE);
        rbMedium.setFont(rbMedium.getFont().deriveFont(rbMedium.getFont().getStyle() & ~Font.BOLD));

        JRadioButton rbHard = new JRadioButton("Hard", false);
        rbHard.addActionListener(this);
        rbHard.setActionCommand(HARD);
        rbHard.setBackground(new Color(50, 70, 90));
        rbHard.setForeground(Color.WHITE);
        rbHard.setFont(rbHard.getFont().deriveFont(rbHard.getFont().getStyle() & ~Font.BOLD));

        ButtonGroup btnGp = new ButtonGroup();
        btnGp.add(rbEasy);
        btnGp.add(rbMedium);
        btnGp.add(rbHard);

        add(size);
        add(sizeList);
        add(difficulty);
        add(rbEasy);
        add(rbMedium);
        add(rbHard);
    }

    // Methods
    public int getDifficulty() {
        return difficultyValue;
    }

    public int getSelectedBoardSize() {
        return Integer.parseInt(sizeList.getSelectedItem().toString().split("x")[0]);
    }

    @Override
    public void actionPerformed(ActionEvent pEvento) {
        String command = pEvento.getActionCommand();
        if (command.equals(EASY)) {
            difficultyValue = Integer.parseInt(EASY);
        } else if (command.equals(MEDIUM)) {
            difficultyValue = Integer.parseInt(MEDIUM);
        } else if (command.equals(HARD)) {
            difficultyValue = Integer.parseInt(HARD);
        }
    }
}
