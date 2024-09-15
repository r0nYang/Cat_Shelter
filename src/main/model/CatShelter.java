package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a cat shelter having a collection of cats.
public class CatShelter implements Writable {
    private List<Cat> cats;

    //EFFECTS: constructs an empty list of cats
    public CatShelter() {
        cats = new ArrayList<>();
    }

    //REQUIRES: Names of cats must be unique
    //MODIFIES: this
    //EFFECTS: adds a cat to the list of cats and returns "Cat added!"
    public String addCat(Cat cat) {
        cats.add(cat);
        EventLog.getInstance().logEvent(new Event(cat.getName() + " has been added to the shelter."));
        return "Cat added!";
    }

    //MODIFIES: this
    //EFFECTS: removes the cat from the list
    public void removeCat(Cat cat) {
        cats.remove(cat);
        EventLog.getInstance().logEvent(new Event(cat.getName() + " has been removed from the shelter."));
    }

    public List<Cat> getCats() {
        return this.cats;
    }

    //EFFECTS: returns the cat at index i
    public Cat getCat(int i) {
        return cats.get(i);
    }

    //EFFECTS: return the list of cat names in the order they were added
    public List<String> getCatNames() {
        List<String> catNames = new ArrayList<>();
        for (Cat c : cats) {
            catNames.add(c.getName());
        }
        return catNames;
    }

    //CODE ATTRIBUTE: JsonSerializationDemo
    // (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo)
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (Cat c: cats) {
            jsonArray.put(c.toJson());
        }

        json.put("cats", jsonArray);

        return json;
    }
}
