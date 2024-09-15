package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CatTest {
    private Cat testCat;

    @BeforeEach
    void runBefore() {
        testCat = new Cat("Oliver", 3, "Birman");
    }

    @Test
    void testConstructor() {
        assertEquals("Oliver", testCat.getName());
        assertEquals(3, testCat.getAge());
        assertEquals("Birman", testCat.getBreed());
        assertTrue(testCat.isAvailable());
    }

    @Test
    void testAdoptCat() {
        assertTrue(testCat.isAvailable());
        testCat.adoptCat();
        assertFalse(testCat.isAvailable());

        testCat.adoptCat();
        assertFalse(testCat.isAvailable());
    }

    @Test
    void testDisplayInformation() {
        String info = testCat.displayInformation();
        assertTrue(info.contains("[Name: " + "Oliver" + " |" + " Age: " + "3" + " |"
                + " Breed: " + "Birman" + " |" + " Availability: " + "true"));
    }
}
