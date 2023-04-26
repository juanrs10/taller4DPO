package uniandes.dpoo.taller4.interfaz;

import java.awt.*;
import java.util.Collection;

import javax.swing.*;

import uniandes.dpoo.taller4.modelo.*;

public class TextWindow extends JDialog {

    private Top10 top10;
    private JTextArea top10List;
    public TextWindow(Top10 top10) {
        setModal(true);
        setTitle("Top 10");
        setResizable(false);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel contentPanel = new JPanel(new BorderLayout());
        setContentPane(contentPanel);

        top10List = new JTextArea();
        top10List.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(top10List);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        this.top10 = top10;
        updateTop10List();

        pack();
    }

    public void updateTop10List() {
        StringBuilder sb = new StringBuilder();

        for (RegistroTop10 i : top10.darRegistros()) {
            sb.append(i.toString());
            sb.append(System.lineSeparator());
        }

        top10List.setText(sb.toString());
    }
}
