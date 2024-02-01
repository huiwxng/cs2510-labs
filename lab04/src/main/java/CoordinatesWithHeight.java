public class CoordinatesWithHeight extends Coordinates {
    private double height;

    public CoordinatesWithHeight(double latitude, double longitude, double height) {
        super(latitude, longitude);
        this.height = height;
    }
}