package persistence;

import model.Cat;
import model.CatShelter;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNoSuchFile() {
        JsonReader reader = new JsonReader("./data/NoSuchFile.json");

        try {
            CatShelter cs = reader.read();
            fail("Should have thrown IOException");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyFile() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyCS.json");

        try {
            CatShelter cs = reader.read();
            assertEquals(0, cs.getCats().size());
        } catch (IOException e) {
            fail("Shouldn't have thrown IOException");
        }
    }

    @Test
    void testReaderNormalFile() {
        JsonReader reader = new JsonReader("./data/testReaderNormalCS.json");
        Cat c1 = new Cat("luna", 7, "Birman");
        Cat c2 = new Cat("puma", 2, "Ragdoll");
        c2.adoptCat();

        try {
            CatShelter cs = reader.read();
            assertEquals(2, cs.getCats().size());
            assertEquals(c1.getName(), cs.getCatNames().get(0));
            assertEquals(c2.getName(), cs.getCatNames().get(1));
            checkCat(c1, "luna", 7, "Birman", true);
            checkCat(c2, "puma", 2, "Ragdoll", false);
        } catch (IOException e) {
            fail("Shouldn't have thrown IOException");
        }
    }
}


