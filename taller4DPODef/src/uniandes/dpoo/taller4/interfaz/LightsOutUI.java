    package uniandes.dpoo.taller4.interfaz;

    import java.awt.*;
    import java.awt.event.*;
    import java.io.File;
    import java.io.FileNotFoundException;
    import java.io.UnsupportedEncodingException;

    import javax.swing.*;

    import com.formdev.flatlaf.FlatLightLaf;

    import uniandes.dpoo.taller4.modelo.*;

    @SuppressWarnings("serial")
    public class LightsOutUI extends JFrame {
        private Tablero gameBoard;
        private static Top10 top10Scores;
        private File topScoresFile;

        private MainPanel mainPanel;

        public LightsOutUI() {
            setTitle("LightsOut");
            setSize(600, 500);
            setResizable(false);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());

            this.mainPanel = new MainPanel(this);

            add(mainPanel, BorderLayout.CENTER);

            this.gameBoard = new Tablero(mainPanel.getBoardSize()); 
            this.top10Scores = new Top10();
            this.topScoresFile = new File("data/top10.csv");
            top10Scores.cargarRecords(topScoresFile);

            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    try {
                        saveTop10();
                    } catch (FileNotFoundException | UnsupportedEncodingException e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }

        public static void main(String[] args) {
            LightsOutUI window = new LightsOutUI();
            window.setVisible(true);
            FlatLightLaf.install();
        }

        public Tablero getGameBoard() {
            return gameBoard;
        }

        public Top10 getTop10() {
            return top10Scores;
        }

        public boolean isTop10Score(int score) {
            return top10Scores.esTop10(score);
        }

        public void updateTop10(RegistroTop10 record) {
            top10Scores.agregarRegistro(record.darNombre(), record.darPuntos());
        }

        public void saveTop10() throws FileNotFoundException, UnsupportedEncodingException {
            top10Scores.salvarRecords(topScoresFile);
        }
    }
