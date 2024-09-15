package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a cat with a name, age, breed, and its availability for adoption
public class Cat implements Writable {
    private final String name;
    private final int age;
    private final String breed;
    private boolean availability;

    //REQUIRES: age > 0
    //EFFECTS: Constructs a cat with the given name, age, and breed. Availability of adoption
    // will default on true until cat has been adopted
    public Cat(String name, int age, String breed) {
        this.name = name;
        this.age = age;
        this.breed = breed;
        availability = true;
    }

    //MODIFIES: this
    //EFFECTS: sets availability to false unless it already is false. Returns a string on whether
    // the cat has been adopted or not
    public String adoptCat() {
        if (this.availability) {
            this.availability = false;
            EventLog.getInstance().logEvent(new Event(name + " has been adopted!"));
            return "Please take care of " + this.name + "!";
        } else {
            return this.name + " has been adopted already!";
        }
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public String getBreed() {
        return this.breed;
    }

    public Boolean isAvailable() {
        return this.availability;
    }

    //CODE ATTRIBUTE: Used similar method from Teller application to convert int to String
    // (https://github.students.cs.ubc.ca/CPSC210/TellerApp)
    //EFFECTS: Displays the current information of the cat
    public String displayInformation() {
        String ageInStr = String.valueOf(this.age);
        EventLog.getInstance().logEvent(
                new Event("A potential adopter has requested for " + name + "'s information"));
        return "[Name: " + this.name + " |" + " Age: " + ageInStr + " |"
                + " Breed: " + this.breed + " |" + " Availability: " + this.availability + "]";
    }

    //CODE ATTRIBUTE: JsonSerializationDemo
    // (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo)
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("age", age);
        json.put("breed", breed);
        json.put("Availability for adoption", availability);
        return json;
    }
}
