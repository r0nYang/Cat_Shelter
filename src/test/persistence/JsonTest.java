package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import model.Cat;

public class JsonTest {
    protected void checkCat(Cat cat, String expectedName, int expectedAge, String expectedBreed
            , Boolean expectedAvailability) {
        assertEquals(expectedName, cat.getName());
        assertEquals(expectedAge, cat.getAge());
        assertEquals(expectedBreed, cat.getBreed());
        assertEquals(expectedAvailability, cat.isAvailable());
    }
}
