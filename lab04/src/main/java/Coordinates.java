import java.lang.Math;

public class Coordinates implements Comparable<Coordinates> {

    private static final double NYC_LATITUDE = 40.730610;
    private static final double NYC_LONGITUDE = -73.935242;

    private double latitude;
    private double longitude;

    public Coordinates() {
        this(NYC_LATITUDE, NYC_LONGITUDE);
    }

    public Coordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public int compareTo(Coordinates other) {
        double dist_this = this.distanceFromNYC();
        double dist_other = other.distanceFromNYC();
        double val = dist_this - dist_other;

        if (val < 0) {
            return -1;
        } else if (val > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    private double distanceFromNYC() {
        return Math.sqrt(Math.pow(NYC_LATITUDE - latitude, 2) + Math.pow(NYC_LONGITUDE - longitude, 2));
    }
}