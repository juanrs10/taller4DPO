package uniandes.dpoo.taller4.interfaz;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import uniandes.dpoo.taller4.modelo.*;

public class Board extends JPanel {
    private Tablero gameBoard;
    private Image lightImage;
    private boolean completed;
    private MainPanel parent;
    private MainPanel mainPanel;

    public Board(MainPanel parent, int boardSize) {
        setLayout(new BorderLayout());
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!isEnabled()) {
                    return;
                }
                int clickX = e.getX();
                int clickY = e.getY();
                int[] cell = convertCoordsToCell(clickX, clickY);
                gameBoard.play(cell[0], cell[1]);
                repaint();
                parent.update();
            }
        });
        this.gameBoard = new Tablero(boardSize);
        try {
            this.lightImage = ImageIO.read(new File("data/luz.png"));
        } catch (IOException e) {
        }
        this.completed = false;
        this.parent = parent;
    }

    public Tablero getGameBoard() {
        return gameBoard;
    }

    public boolean isCompleted() {
        if (gameBoard.boardIlluminated() == true) {
            completed = true;
        }
        return completed;
    }

    public int getMoves() {
        return gameBoard.getMoves();
    }

    public void newGame() {
        gameBoard.shuffle(25); // You can adjust the number of shuffles to change the difficulty
        completed = false;
        repaint();
    }
    
    public void resetGame() {
        gameBoard.restart();
        completed = false;
        repaint();
    }

    public int getBoardSize() {
        return gameBoard.getBoard().length;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        int rowCounter = 0;
        for (boolean[] row : gameBoard.getBoard()) {
            int colCounter = 0;
            for (boolean cell : row) {
                double a = getWidth() / gameBoard.getBoard().length;
                double b = getHeight() / gameBoard.getBoard().length;
                g2d.setColor(Color.BLACK);
                RoundRectangle2D.Double rect = new RoundRectangle2D.Double((colCounter * a) + 1,
                        (rowCounter * b) + 1, a - 1, b - 1, 5, 5);
                g2d.draw(rect);
                if (cell) {
                    g2d.setPaint(new GradientPaint((int) (colCounter * a) + 1, (int) (rowCounter * b) + 1,
                            Color.YELLOW, (int) ((colCounter * a) + 1 + a), (int) ((rowCounter * b) + 1 + b),
                            Color.WHITE));
                    g2d.fill(rect);
                } else {
                    g2d.setPaint(new GradientPaint((int) (colCounter * a) + 1, (int) (rowCounter * b) + 1,
                            Color.BLACK, (int) ((colCounter * a) + 1 + a), (int) ((rowCounter * b) + 1 + b),
                            Color.DARK_GRAY));
                    g2d.fill(rect);
                }
                g2d.drawImage(lightImage, (int) Math.round((colCounter * a) + a / 6),
                        (int) Math.round((rowCounter * b) + b / 6), (int) (a / 1.5), (int) (b / 1.5), this);
                        colCounter++;
                    }
                    rowCounter++;
                }
            }
        
            private int[] convertCoordsToCell(int x, int y) {
                int boardSide = gameBoard.getBoard().length;
                int panelHeight = getHeight();
                int panelWidth = getWidth();
                int cellHeight = panelHeight / boardSide;
                int cellWidth = panelWidth / boardSide;
                int row = y / cellHeight;
                int col = x / cellWidth;
                return new int[] {row, col};
            }
        }
        
