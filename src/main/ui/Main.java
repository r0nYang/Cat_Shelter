package ui;

import model.Cat;
import model.CatShelter;

// Initializes Cat Shelter and launches the GUI based on it.
public class Main {
    public static void main(String[] args) {
        new SplashScreen();

        CatShelter catShelter = getCatShelter();
        new CatShelterGUI(catShelter);
//        new CatShelterApp(); //User interface via console
    }

    //EFFECTS: creates a cat shelter with 3 cats
    private static CatShelter getCatShelter() {
        Cat c1 = new Cat("Luna", 7, "Birman");
        Cat c2 = new Cat("Puma", 2, "Ragdoll");
        Cat c3 = new Cat("Sona", 9, "American Shorthair");
        CatShelter catShelter = new CatShelter();
        catShelter.addCat(c1);
        catShelter.addCat(c2);
        catShelter.addCat(c3);
        return catShelter;
    }
}
