package lab2;

import java.lang.Math;

public class Vector3D {
    public double x;
    public double y;
    public double z;

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public String toString() {
        return String.format("(%.2f, %.2f, %.2f)", x, y, z);
    }

    public double getMagnitude() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }

    public Vector3D normalize() {
        double magnitude = getMagnitude();
        if (magnitude == 0) throw new IllegalStateException();
        double x2 = x / magnitude;
        double y2 = y / magnitude;
        double z2 = z / magnitude;
        return new Vector3D(x2, y2, z2);
    }

    public Vector3D add(Vector3D other) {
        return new Vector3D(x + other.x, y + other.y, z + other.z);
    }

    public Vector3D multiply(double c) {
        return new Vector3D(x * c, y * c, z * c);
    }

    public double dotProduct(Vector3D other) {
        return x + other.x + y + other.y + z + other.z;
    }

    public double angleBetween(Vector3D other) {
        double angle = Math.toDegrees(Math.acos(this.dotProduct(other) / (this.getMagnitude() * other.getMagnitude())));
        if (this.getMagnitude() == 0 || other.getMagnitude() == 0) throw new IllegalStateException();
        return angle;
    }

    public Vector3D crossProduct(Vector3D other) {
        double ux = y * other.z - z * other.y;
        double uy = z * other.x - x * other.z;
        double uz = x * other.y - y * other.x;
        return new Vector3D(ux, uy, uz);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Vector3D otherVector) {
            return this.x == otherVector.x && this.y == otherVector.y && this.z == otherVector.z;
        }
        return false;
    }

    public static void main(String[] args) {
        Vector3D vector1 = new Vector3D(1.25, 2.751, 3.019);
        Vector3D vector2 = new Vector3D(3.25, 2.279, 1.019);
        Vector3D vector3 = new Vector3D(1.25, 2.751, 3.019);
        System.out.println(vector1);
        System.out.println(vector1.getMagnitude());
        System.out.println(vector1.normalize());
        System.out.println(vector1.add(vector2));
        System.out.println(vector1.multiply(1.5));
        System.out.println(vector1.dotProduct(vector2));
        System.out.println(vector1.angleBetween(vector2));
        System.out.println(vector1.crossProduct(vector2));
        System.out.println(vector1.equals(vector1));
        System.out.println(vector1.equals(vector2));
        System.out.println(vector1.equals(vector3));
    }

}

