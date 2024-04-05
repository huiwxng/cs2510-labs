public class GameState {
    private final int SIZE = 3;
    private Player[][] board;

    public GameState() {
        board = new Player[SIZE][SIZE];
    }

    public boolean gameOver() {
        if (gameOverHelper() != null) {
            return true;
        }
        return boardFull();
    }

    public Player winner() {
        if (gameOver()) {
            if (gameOverHelper() != null) {
                return gameOverHelper();
            }
        }
        return null;
    }

    public Player spot(int row, int col) {
        return board[row][col];
    }

    public GameState move(int row, int col, Player player) {
        GameState copy = copy();
        if (copy.spot(row, col) == null) {
            copy.board[row][col] = player;
        } else {
            return null;
        }
        return copy;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int row = 0; row < SIZE; row++) {
            str.append("[");
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] != null) {
                    if (board[row][col] == Player.X) {
                        str.append("X");
                    } else {
                        str.append("O");
                    }
                } else {
                    str.append("N");
                }
                if (col < SIZE - 1) {
                    str.append(",");
                }
            }
            str.append("]");
            str.append("\n");
        }
        return str.toString();
    }

    private Player gameOverHelper() {
        for (int row = 0; row < SIZE; row++) {
            if (board[row][0] != null) {
                Player player = board[row][0];
                if (board[row][1] == player && board[row][2] == player) {
                    return player;
                }
            }
        }

        for (int col = 0; col < SIZE; col++) {
            if (board[0][col] != null) {
                Player player = board[0][col];
                if (board[1][col] == player && board[2][col] == player) {
                    return player;
                }
            }
        }

        if (board[0][0] != null) {
            Player player = board[0][0];
            if (board[1][1] == player && board[2][2] == player) {
                return player;
            }
        }

        if (board[0][2] != null) {
            Player player = board[0][2];
            if (board[1][1] == player && board[2][0] == player) {
                return player;
            }
        }

        return null;
    }

    private boolean boardFull() {
        int count = SIZE * SIZE;
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] != null) {
                    count--;
                }
            }
        }
        return count == 0;
    }

    private GameState copy() {
        GameState copy = new GameState();
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                Player player = board[row][col];
                copy.board[row][col] = player;
            }
        }
        return copy;
    }
}
