package persistence;

import model.CatShelter;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

//Represents a writer that writes JSON representation/data of CatShelter to file
//CODE ATTRIBUTE: JsonSerializationDemo
//(https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo)
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    //EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    //MODIFIES: this
    //EFFECTS: opens writer for the destination file;
    // throws FileNotFoundException if destination file can't be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(destination);
    }

    //MODIFIES: this
    //EFFECTS: writes JSON representation/data of CatShelter and saves it to the destination file.
    public void write(CatShelter cs) {
        JSONObject json = cs.toJson();
        saveToFile(json.toString(TAB));
    }

    //MODIFIES: this
    //EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }

    //MODIFIES: this
    //EFFECTS: closes writer
    public void close() {
        writer.close();
    }
}
