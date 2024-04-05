public class TicTacToeSolver {
    public static Move findBestMove(GameState state, Player player) {
        return solveMyMove(state, Integer.MIN_VALUE, Integer.MAX_VALUE, player);
    }

    private static Move solveMyMove(GameState state, int alpha, int beta, Player player) {
        if (state.gameOver()) {
            if (state.winner() == player) {
                return new Move(1);
            } else if (state.winner() == null) {
                return new Move(0);
            } else {
                return new Move(-1);
            }
        }

        return calcBestMove(true, state, alpha, beta, player);
    }

    private static Move solveOpponentMove(GameState state, int alpha, int beta, Player player) {
        if (state.gameOver()) {
            if (state.winner() == player) {
                return new Move(-1);
            } else if (state.winner() == null) {
                return new Move(0);
            } else {
                return new Move(1);
            }
        }

        return calcBestMove(false, state, alpha, beta, player);
    }

    private static Player nextPlayer(Player player) {
        return switch (player) {
            case Player.O -> Player.X;
            case Player.X -> Player.O;
        };
    }

    private static Move calcBestMove(boolean myMove, GameState state, int alpha, int beta, Player player) {
        Move bestMove = null;
        int boardSize = 3;

        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                if (state.spot(row, col) == null) {
                    GameState nextState = state.move(row, col, player);
                    Player nextPlayer = nextPlayer(player);
                    Move child;
                    if (myMove) {
                        child = solveOpponentMove(nextState, alpha, beta, nextPlayer);
                    } else {
                        child = solveMyMove(nextState, alpha, beta, nextPlayer);
                    }

                    if (bestMove == null || child.value() < bestMove.value()) {
                        bestMove = new Move(row, col, child.value());
                    }

                    if (bestMove.value() < alpha) {
                        return bestMove;
                    }
                    if (beta < bestMove.value()) {
                        beta = bestMove.value();
                    }
                }
            }
        }

        return bestMove;
    }

    public static void main(String[] args) {
        GameState game = new GameState();
        while (!game.gameOver()) {
            Move XMove = findBestMove(game, Player.X);
            game = game.move(XMove.row(), XMove.col(), Player.X);
            System.out.println(game);
            if (!game.gameOver()) {
                Move OMove = findBestMove(game, Player.O);
                game = game.move(OMove.row(), OMove.col(), Player.O);
                System.out.println(game);
            }
        }
        System.out.println(game.winner());
    }
}
