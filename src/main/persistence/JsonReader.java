package persistence;


import model.Cat;
import model.CatShelter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

//Represents a reader that reads Cat Shelter from JSON data stored in the file.
//CODE ATTRIBUTE: JsonSerializationDemo
//(https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo)
public class JsonReader {
    private String source;

    //EFFECTS: constructs reader to read JSON data from the given file
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads CatShelter from source file and returns a CatShelter Object based
    // on the JSON data. Throws IOException if an error occurs while reading data from the source file
    public CatShelter read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObj = new JSONObject(jsonData);
        return parseCatShelter(jsonObj);
    }

    //EFFECTS: Reads content of the file and returns the JSON data as a string
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //EFFECTS: Creates a CatShelter object and add cats to it based on the JSON data.
    private CatShelter parseCatShelter(JSONObject jsonObj) {
        CatShelter cs = new CatShelter();
        addCats(cs, jsonObj);
        return cs;
    }

    //MODIFIES: cs
    //EFFECTS: parses cats from JSON object and adds them to the CatShelter object
    private void addCats(CatShelter cs, JSONObject jsonObj) {
        JSONArray jsonArray = jsonObj.getJSONArray("cats");
        for (Object json: jsonArray) {
            JSONObject nextCat = (JSONObject) json;
            addCat(cs, nextCat);
        }
    }

    //MODIFIES: cs
    //EFFECTS: parses cat from JSON object and adds it to cat shelter
    private void addCat(CatShelter cs, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int age = jsonObject.getInt("age");
        String breed = jsonObject.getString("breed");
        boolean isAdopted = jsonObject.getBoolean("Availability for adoption");
        Cat cat = new Cat(name, age, breed);
        cs.addCat(cat);
        if (!isAdopted) {
            cat.adoptCat();
        }
    }
}
