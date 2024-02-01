package lab2;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector3DTest {
    private Vector3D v1;
    private Vector3D v2;

    @BeforeEach
    public void setup() {
        v1 = new Vector3D(-1.00, 2.50, 3.25);
        v2 = new Vector3D(0.00, 2.50, 1.245);
    }

    @Test
    public void getXWorks() {
        assertEquals(-1.0, v1.getX());
        assertEquals(0.0, v2.getX());
    }

    @Test
    public void getYWorks() {
        assertEquals(2.5, v1.getY());
        assertEquals(2.5, v2.getY());
    }

    @Test
    public void getZWorks() {
        assertEquals(3.25, v1.getZ());
        assertEquals(1.245, v2.getZ());
    }

    @Test
    public void toStringWorks() {
        assertEquals("(-1.00, 2.50, 3.25)", v1.toString());
        assertEquals("(0.00, 2.50, 1.25)", v2.toString());
    }

    @Test
    public void getMagnitudeWorks() {
        assertEquals(4.22, v1.getMagnitude(), 0.01);
        assertEquals(2.79, v2.getMagnitude(), 0.01);
    }

    @Test
    public void normalizeNormalCase() {
        assertEquals("(-0.24, 0.59, 0.77)", v1.normalize().toString());
        assertEquals("(0.00, 0.90, 0.45)", v2.normalize().toString());
    }

    @Test
    public void normalizeEdgeCase() {
        Vector3D edge = new Vector3D(-1000000, 0, 10000000);
        assertEquals("(-0.10, 0.00, 1.00)", edge.normalize().toString());
    }

    @Test
    public void normalizeInvalidCase() {
        Vector3D invalidVector = new Vector3D(0,0,0);
        assertThrows(IllegalStateException.class, invalidVector::normalize);
    }

    @Test
    public void addWorks() {
        assertEquals("(-1.00, 5.00, 4.50)", v1.add(v2).toString());
        assertEquals("(-1.00, 5.00, 4.50)", v2.add(v1).toString());
    }

    @Test
    public void multiplyWorks() {
        assertEquals(new Vector3D(-2.00,5.00,6.50), v1.multiply(2));
        assertEquals(new Vector3D(0.00,5.00,2.49), v2.multiply(2));
    }

    @Test
    public void dotProductWorks() {
        assertEquals(8.495, v1.dotProduct(v2), 0.01);
    }

    @Test
    public void angleBetweenValidCase() {
        assertEquals(43.8879119837593, v1.angleBetween(v2));
    }

    @Test
    public void angleBetweenInvalidCase() {
        Vector3D invalidVector = new Vector3D(0,0,0);
        assertThrows(IllegalStateException.class, () -> {
            invalidVector.angleBetween(v1);
        });
    }

    @Test
    public void crossProductWorks() {
        assertEquals("(-5.01, 1.25, -2.50)", v1.crossProduct(v2).toString());
    }

    // class 100%, method 92%, line 64%
}