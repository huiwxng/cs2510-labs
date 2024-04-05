/**
 * Constructs a potential move.
 *
 * @param row   the row
 * @param col   the column
 * @param value the expected value of making this move
 */
public record Move(int row, int col, int value) {
    private static final int INVALID_COORD = -1;

    /**
     * Constructs a move without a row or position.
     *
     * @param value the value
     */
    public Move(int value) {
        this(INVALID_COORD, INVALID_COORD, value);
    }
}