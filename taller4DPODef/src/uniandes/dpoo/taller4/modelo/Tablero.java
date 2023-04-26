package uniandes.dpoo.taller4.modelo;

public class Tablero {
    private boolean[][] board;
    private boolean[][] originalBoard;
    private int moves;

    public Tablero(int size) {
        board = new boolean[size][size];
        originalBoard = new boolean[size][size];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = true;
                originalBoard[i][j] = true;
            }
        }
        moves = 0;
    }

    public void restart() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = originalBoard[i][j];
            }
        }
        moves = 0;
    }

    public void saveBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                originalBoard[i][j] = board[i][j];
            }
        }
    }

    public void shuffle(int difficulty) {
        int temp = moves;
        for (int i = 0; i < difficulty; i++) {
            int size = board.length;
            int row = (int) (Math.random() * size);
            int col = (int) (Math.random() * size);
            play(row, col);
        }
        saveBoard();
        moves = temp;
    }

    public int getMoves() {
        return moves;
    }

    public boolean[][] getBoard() {
        return board;
    }

    public void play(int row, int col) {
        int size = board.length;
        for (int dRow = -1; dRow < 2; dRow++) {
            for (int dCol = -1; dCol < 2; dCol++) {
                int newRow = row + dRow;
                int newCol = col + dCol;
                if (newRow >= 0 && newRow < size && newCol >= 0 && newCol < size) {
                    board[newRow][newCol] = !board[newRow][newCol];
                }
            }
        }
        moves++;
    }

    public boolean boardIlluminated() {
		boolean lit = true;
		for (int i = 0; i < board.length && lit; i++) {
			for (int j = 0; j < board.length && lit; j++) {
				lit = board[i][j];
			}
		}
		return lit;
	}
	
    public int calculateScore() {
        int size = board.length;
        int difficultyFactor = size * size;
        return difficultyFactor - moves;
    }
}
