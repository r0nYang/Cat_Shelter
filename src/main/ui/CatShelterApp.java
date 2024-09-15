package ui;

import model.Cat;
import model.CatShelter;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// Cat Shelter Application via console user interface
// CODE ATTRIBUTE: Scanner setup from the Teller application and JSON set up from JsonSerializationDemo
// (https://github.students.cs.ubc.ca/CPSC210/TellerApp)
public class CatShelterApp {
    private static final String JSON_STORE = "./data/catshelter.json";
    private CatShelter catShelter;
    private Scanner input;
    private boolean isRunning;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    //EFFECTS: runs the cat shelter application
    public CatShelterApp() {
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        runCatShelter();
    }

    //MODIFIES: this
    //EFFECTS: process user input
    private void runCatShelter() {
        isRunning = true;
        String command = null;
        input = new Scanner(System.in);
        input.useDelimiter("\n");

        initializeCats();

        while (isRunning) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("x")) {
                isRunning = false;
            } else {
                performCommand(command);
            }
        }
        System.out.println("Thank you for your visit!");
    }

    //EFFECTS: displays the interactions a user can perform at the shelter
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta --> View cats");
        System.out.println("\tb --> Add cat");
        System.out.println("\tc --> Remove cat");
        System.out.println("\td --> save cat shelter");
        System.out.println("\te --> load cat shelter");
        System.out.println("\tx --> exit");
    }

    //MODIFIES: this
    //EFFECTS: processes the user command
    private void performCommand(String command) {
        if (command.equals("a")) {
            doViewCats();
        } else if (command.equals("b")) {
            doAddCat();
        } else if (command.equals("c")) {
            doRemoveCat();
        } else if (command.equals("d")) {
            saveCatShelter();
        } else if (command.equals("e")) {
            loadCatShelter();
        } else {
            System.out.println("Selection not valid!");
        }
    }

    //MODIFIES: this
    //EFFECTS: displays the list of cats in the shelter to the user.
    private void doViewCats() {
        displayCats();
        int catIndex = input.nextInt();

        System.out.println("\nWhat would you like to do with the cat?");
        System.out.println("\t0 --> View information");
        System.out.println("\t1 --> Adopt");

        int command = input.nextInt();
        Cat cat = catShelter.getCat(catIndex);

        if (command == 0) {
            System.out.println(cat.displayInformation());
        } else if (command == 1) {
            System.out.println(cat.adoptCat());
        } else {
            System.out.println("Invalid option!");
        }
    }

    //EFFECTS: displays the names of the cats in the order they were added
    private void displayCats() {
        List<String> names = catShelter.getCatNames();
        int currentIndex = 0;

        if (!names.isEmpty()) {
            System.out.println("\nSelect from:");
            for (String s : names) {
                currentIndex = names.indexOf(s);
                System.out.println("\t " + currentIndex + " --> " + s);
            }
        } else {
            System.out.println("No more cats at the shelter!");
            isRunning = false;
        }
    }

    //MODIFIES: this
    //EFFECTS: adds a cat with the given name, age, and breed to the list of cats
    private void doAddCat() {
        String name;
        int age;
        String breed;

        System.out.println("What is the name of the cat?");
        name = input.next();

        System.out.println("How old is the cat?");
        age = input.nextInt();

        System.out.println("What is the breed of the cat?");
        breed = input.next();

        System.out.println(catShelter.addCat(new Cat(name, age, breed)));
    }

    //MODIFIES: this
    //EFFECTS: Allows user to remove the selected cat from cats list.
    // Exit program if cat list is empty
    private void doRemoveCat() {
        if (!catShelter.getCats().isEmpty()) {
            displayCats();
            Cat c = selectCat();
            catShelter.removeCat(c);
        } else {
            System.out.println("No more cats at the shelter!");
            isRunning = false;
        }
    }

    //EFFECTS: saves current cat shelter to file
    private void saveCatShelter() {
        try {
            jsonWriter.open();
            jsonWriter.write(catShelter);
            jsonWriter.close();
            System.out.println("saved the list of cats to: " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    //MODIFIES: this
    //EFFECTS: loads catShelter from file
    private void loadCatShelter() {
        try {
            catShelter = jsonReader.read();
            System.out.println("Loaded the saved list of cats from: " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    //REQUIRES: must select a cat from the list displayed
    //EFFECTS: returns the selected cat.
    private Cat selectCat() {
        int selection = 100;
        int validSelection = catShelter.getCats().size() - 1; //index starts from 0

        while (selection > validSelection) {
            selection = input.nextInt();
        }

        return catShelter.getCats().get(selection);
    }

    //MODIFIES: this
    //EFFECTS: initialize the cats and add them to the new list.
    private void initializeCats() {
        Cat c1 = new Cat("luna", 7, "Birman");
        Cat c2 = new Cat("puma", 2, "Ragdoll");
        Cat c3 = new Cat("sona", 9, "American Shorthair");
        catShelter = new CatShelter();
        catShelter.addCat(c1);
        catShelter.addCat(c2);
        catShelter.addCat(c3);
    }
}
