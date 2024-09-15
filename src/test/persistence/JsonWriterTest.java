package persistence;

import model.Cat;
import model.CatShelter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{
    CatShelter cs;

    @BeforeEach
    void setUp() {
        cs = new CatShelter();
    }

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/\0NoSuchFile2.json");
            writer.open();
            fail("Should've thrown IOException");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyCatShelter() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyCS.json");
            writer.open();
            writer.write(cs);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyCS.json");
            cs = reader.read();
            assertEquals(0, cs.getCats().size());

        } catch (IOException e) { //FileNotFoundException is subtype of IOException
            fail("Not supposed to have exceptions thrown");
        }
    }

    @Test
    void testWriterNormalCatShelter() {
        Cat c1 = new Cat("luna", 7, "Birman");
        Cat c2 = new Cat("puma", 2, "Ragdoll");
        c2.adoptCat();

        try {
            JsonWriter writer = new JsonWriter("./data/testWriterNormalCS.json");
            cs.addCat(c1);
            cs.addCat(c2);
            writer.open();
            writer.write(cs);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterNormalCS.json");
            cs = reader.read();
            assertEquals(c1.getName(), cs.getCatNames().get(0));
            assertEquals(c2.getName(), cs.getCatNames().get(1));
            checkCat(c1, "luna", 7, "Birman", true);
            checkCat(c2, "puma", 2, "Ragdoll", false);

        } catch (IOException e) { //FileNotFoundException is subtype of IOException
            fail("Not supposed to have exceptions thrown");
        }
    }
}
