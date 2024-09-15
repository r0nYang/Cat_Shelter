package ui;

import model.Cat;
import model.CatShelter;
import model.EventLog;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;

import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;

// CODE ATTRIBUTE: Java Swing tutorial via (https://youtu.be/Kmgo00avvEw?feature=shared)
// CODE ATTRIBUTE: ListDemo from https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
// Represents the graphical user interface of the cat shelter App.
public class CatShelterGUI extends JFrame implements ActionListener {
    private CatShelter catShelter;
    private JList<String> catList;
    private DefaultListModel<String> catListModel;

    private static final String JSON_STORE = "./data/catshelter.json";

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JButton viewCatInfoButton;
    private JButton adoptCatButton;
    private JButton addCatButton;
    private JButton removeCatButton;
    private JButton saveCatShelterButton;
    private JButton loadCatShelterButton;

    private JPanel rightSidePanel;
    private JPanel bottomPanel;
    private JScrollPane listScrollPlane;

    private JLabel nameLabel;
    private JLabel ageLabel;
    private JLabel breedLabel;

    private JTextField nameTextField;
    private JTextField ageTextField;
    private JTextField breedTextField;

    //EFFECTS: Sets CatShelter to the given cs and runs the GUI.
    public CatShelterGUI(CatShelter cs) {
        this.catShelter = cs;
        try {
            runGUI();
        } catch (Exception e) {
            System.out.println("An error has occurred while setting UIManager");
        }
    }

    //EFFECTS: creates the GUI for Cat Shelter App
    private void runGUI() throws UnsupportedLookAndFeelException, ClassNotFoundException,
            InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); // from Piazza @1322
        customizeGUI();
        initializeJson();

        catListModel = new DefaultListModel<>();
        catList = new JList<>(catListModel);

        initializePanels();
        addCatNamesToList();
        initializeButtons();
        addPanels();
        initiateTextAndLabel();
        addLabelAndTextField();
        addButtons();

        LogPrinter lp = new LogPrinter();
        addWindowListener(lp);

        this.setVisible(true);
    }



    //EFFECTS: initializes the panels
    private void initializePanels() {
        listScrollPlane = new JScrollPane(catList);
        rightSidePanel = new JPanel();
        bottomPanel = new JPanel();
        listScrollPlane.setPreferredSize(new Dimension(300, 100));
        rightSidePanel.setPreferredSize(new Dimension(180, 100));
        rightSidePanel.setBackground(Color.darkGray);
        bottomPanel.setPreferredSize(new Dimension(180, 50));
    }

    //EFFECTS: initializes jsonReader and jsonWriter
    private void initializeJson() {
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
    }

    //EFFECTS: add panels and plane to the frame
    private void addPanels() {
        this.add(rightSidePanel, BorderLayout.EAST);
        this.add(listScrollPlane, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    //MODIFIES: this
    //EFFECTS: updates catListModel based on names of cats in catShelter
    private void addCatNamesToList() {
        List<String> names = catShelter.getCatNames();
        catListModel.clear();
        for (String name : names) {
            catListModel.addElement(name);
        }
        catList.setSelectedIndex(0); //selects the first cat
    }

    //EFFECTS: initiates TextField and Label for addCat
    private void initiateTextAndLabel() {
        nameTextField = new JTextField(5);
        nameLabel = new JLabel(" Name:");
        ageTextField = new JTextField(5);
        ageLabel = new JLabel(" Age:");
        breedTextField = new JTextField(5);
        breedLabel = new JLabel(" Breed:");
    }

    //EFFECTS: adds label and text needed for adding a cat
    private void addLabelAndTextField() {
        bottomPanel.add(nameLabel);
        bottomPanel.add(nameTextField);
        bottomPanel.add(ageLabel);
        bottomPanel.add(ageTextField);
        bottomPanel.add(breedLabel);
        bottomPanel.add(breedTextField);
    }

    //EFFECTS: creates a custom frame
    private void customizeGUI() {
        this.setTitle("Cat Shelter");
        this.setSize(500, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        changeIcon(); //changes top left icon of GUI
    }

    //EFFECTS: add the buttons to panel
    private void addButtons() {
        rightSidePanel.add(viewCatInfoButton);
        rightSidePanel.add(adoptCatButton);
        rightSidePanel.add(removeCatButton);
        rightSidePanel.add(saveCatShelterButton);
        rightSidePanel.add(loadCatShelterButton);
        bottomPanel.add(addCatButton);
    }

    //EFFECTS: Perform action(s) when a specific button is clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewCatInfoButton) {
            displayCatInfo();
        } else if (e.getSource() == adoptCatButton) {
            adoptCat();
        } else if (e.getSource() == addCatButton) {
            addCat();
        } else if (e.getSource() == removeCatButton) {
            removeCat();
        } else if (e.getSource() == saveCatShelterButton) {
            saveCatShelter();
        } else if (e.getSource() == loadCatShelterButton) {
            loadCatShelter();
        }
    }

    //CODE ATTRIBUTE: JOptionPane from https://docs.oracle.com/javase/8/docs/api/javax/swing/JOptionPane.html
    //EFFECTS: saves current cat shelter to file
    private void saveCatShelter() {
        try {
            jsonWriter.open();
            jsonWriter.write(catShelter);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null, "Successfully saved the current cat shelter!",
                    "Aaron's Cat Shelter", JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "An error has occurred while saving the cat shelter",
                    "Aaron's Cat Shelter", JOptionPane.ERROR_MESSAGE);
        }
    }

    //MODIFIES: this
    //EFFECTS: loads catShelter from file
    private void loadCatShelter() {
        try {
            EventLog.getInstance().clear();
            catShelter = jsonReader.read();
            addCatNamesToList(); //updates catListModel
            JOptionPane.showMessageDialog(null, "Successfully loaded the saved cat shelter!",
                    "Aaron's Cat Shelter", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "An error has occurred while loading the cat shelter",
                    "Aaron's Cat Shelter", JOptionPane.ERROR_MESSAGE);
        }
    }

    //MODIFIES: this
    //EFFECTS: removes the selected cat from the list. If list is empty, disables the removeCatButton.
    private void removeCat() {
        int catIndex = catList.getSelectedIndex();
        Cat cat = catShelter.getCat(catIndex);
        catShelter.removeCat(cat);
        catListModel.remove(catIndex);
        if (catListModel.isEmpty()) {
            removeCatButton.setEnabled(false);
        } else {
            catList.setSelectedIndex(0);
        }
    }


    //CODE ATTRIBUTE: Method of converting string to int from
    // (https://stackoverflow.com/questions/5585779/how-do-i-convert-a-string-to-an-int-in-java)
    //MODIFIES: this
    //EFFECTS: adds cat to cat shelter and catListModel.
    private void addCat() {
        try {
            String name = nameTextField.getText();
            int age = Integer.parseInt(ageTextField.getText());
            String breed = breedTextField.getText();
            Cat cat = new Cat(name, age, breed);
            catShelter.addCat(cat);
            catListModel.addElement(cat.getName());
            removeCatButton.setEnabled(true);
            clearTextFields();
            catList.setSelectedIndex(catShelter.getCats().size() - 1);
        } catch (NumberFormatException e) {
            // Avoids user from setting a String as the cat's age.
        }
    }

    //EFFECTS: clears the text field after a cat is added
    private void clearTextFields() {
        nameTextField.setText("");
        ageTextField.setText("");
        breedTextField.setText("");
    }

    //EFFECTS: adopts the selected cat. Do nothing to the cat if it's unavailable.
    private void adoptCat() {
        if (!catListModel.isEmpty()) {
            int catIndex = catList.getSelectedIndex();
            Cat cat = catShelter.getCat(catIndex);
            if (cat.isAvailable()) {
                cat.adoptCat();
                JOptionPane.showMessageDialog(null, "Take care of " + cat.getName() + " !",
                        "Aaron's Cat Shelter", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, cat.getName()
                                + " has been adopted already :(",
                        "Aaron's Cat Shelter", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    //EFFECTS: displays the information of the selected cat
    private void displayCatInfo() {
        if (!catListModel.isEmpty()) {
            int catIndex = catList.getSelectedIndex();
            Cat cat = catShelter.getCat(catIndex);
            JOptionPane.showMessageDialog(null, cat.displayInformation(),
                    "Aaron's Cat Shelter", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    //EFFECTS: initializes the buttons
    private void initializeButtons() {
        viewCatInfoButton = createButton("View Information");
        adoptCatButton = createButton("Adopt Cat");
        addCatButton = createButton("Add Cat");
        removeCatButton = createButton("Remove Cat");
        saveCatShelterButton = createButton("Save Cats");
        loadCatShelterButton = createButton("Load Cats");
    }

    //EFFECTS: creates a button with given name
    private JButton createButton(String str) {
        Dimension buttonSize = new Dimension(140, 35);
        JButton button = new JButton(str);
        button.setPreferredSize(buttonSize);
        button.setFocusable(false);
        button.addActionListener(this);
        return button;
    }

    //EFFECTS: changes the icon of GUI
    private void changeIcon() {
        ImageIcon image = new ImageIcon("./data/photo/cat.jpg");
        this.setIconImage(image.getImage());
    }
}
