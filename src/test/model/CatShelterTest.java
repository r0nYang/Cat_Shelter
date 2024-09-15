package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CatShelterTest {
    private CatShelter testCats;
    private Cat c1;
    private Cat c2;
    private Cat c3;

    @BeforeEach
    void runBefore() {
        testCats = new CatShelter();
        c1 = new Cat("sugar", 3, "breed1");
        c2 = new Cat("luna", 5, "breed2");
        c3 = new Cat("sam", 1, "breed3");
    }

    @Test
    void testConstructor() {
        assertEquals(0, testCats.getCats().size());
    }

    @Test
    void testAddCat() {
        testCats.addCat(c1);
        assertEquals(1, testCats.getCats().size());
        assertEquals(c1, testCats.getCats().get(0));
    }

    @Test
    void testAddMultipleCat() {
        testCats.addCat(c1);
        testCats.addCat(c2);
        testCats.addCat(c3);
        assertEquals(3, testCats.getCats().size());
        assertEquals(c1, testCats.getCats().get(0));
        assertEquals(c2, testCats.getCats().get(1));
        assertEquals(c3, testCats.getCats().get(2));
    }

    @Test
    void testRemoveCat() {
        testCats.addCat(c1);
        assertEquals(1, testCats.getCats().size());
        assertEquals(c1, testCats.getCats().get(0));
        testCats.removeCat(c1);
        assertEquals(0, testCats.getCats().size());
    }

    @Test
    void testRemoveNotPresentCat() {
        testCats.addCat(c1);
        assertEquals(1, testCats.getCats().size());
        assertEquals(c1, testCats.getCats().get(0));
        testCats.removeCat(c2);
        assertEquals(1, testCats.getCats().size());
        assertEquals(c1, testCats.getCats().get(0));
    }

    @Test
    void testRemoveMultipleCat() {
        testCats.addCat(c1);
        testCats.addCat(c2);
        testCats.addCat(c3);
        assertEquals(3, testCats.getCats().size());
        testCats.removeCat(c1);
        testCats.removeCat(c2);
        assertEquals(1, testCats.getCats().size());
    }

    @Test
    void testGetCat() {
        testCats.addCat(c1);
        testCats.addCat(c2);
        testCats.addCat(c3);
        assertEquals(c1, testCats.getCat(0));
        assertEquals(c2, testCats.getCat(1));
        assertEquals(c3, testCats.getCat(2));
    }

    @Test
    void testGetCatNames() {
        testCats.addCat(c1);
        testCats.addCat(c2);
        testCats.addCat(c3);
        List<String> names = testCats.getCatNames();
        assertEquals(3, names.size());
        assertEquals(c1.getName(), names.get(0));
        assertEquals(c2.getName(), names.get(1));
    }
}
