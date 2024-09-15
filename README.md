# Cat Shelter

## Project Proposal:

What Will the Application do?
- The application will serve as a cat shelter where users can browse and select cats for adoption. 
Information such as the breed, name, age, and its availability to be adopted will be provided.

Who will use it?
- Potential cat owners
- Anyone interested in cats

Why is this project of interest to you?
- I am a proud cat owner myself and I believe that every cat deserves a home. Creating a cat shelter 
application allows me to combine my passion for cats with my interest in software development.

## User Stories:

- As a user, I want to be able to add a cat, along with all its information, to the cat shelter
- As a user, I want to be able to view the list of cats at the cat shelter
- As a user, I want to be able to remove a cat from the shelter
- As a user, I want to be able to select a cat from the list and view all its details, including its breed, 
name, age, and availability for adoption.
- As a user, I want to be able to change a pet's availability once it has been adopted.
- As a user, I want to be able to save the current list of cats in the shelter to file (if I choose to)
- As a user, I want to be able to load the list of cats from file (if I choose to)


# Instructions for Grader

- You can generate the first required action related to adding Xs to a Y by entering the name, age, and breed 
  of cat in the text fields at the bottom then clicking the addCat button.
- You can generate the second required action related to removing Xs from a Y by selecting a cat on the list and
  clicking the removeCat button.
- You can locate my visual component at the start of the program; It is the splash screen.
- You can save the state of my application by clicking the Save Cats button.
- You can reload the state of my application by clicking the load Cats button.


# Phase 4: Task 2

- Fri Nov 24 20:21:53 PST 2023
  Luna has been added to the shelter.

- Fri Nov 24 20:21:53 PST 2023
  Puma has been added to the shelter.

- Fri Nov 24 20:20:26 PST 2023
  A potential adopter has requested for Luna's information

- Fri Nov 24 20:20:28 PST 2023
  Luna has been adopted!

- Fri Nov 24 20:20:30 PST 2023
  Puma has been removed from the shelter.


# Phase 4: Task 3

- Looking at the UML class diagram and the code in CatShelterGUI, I noticed that I simply have too much stuff going on
  in one class; creating panels, labels, buttons, text fields and handling user actions. To improve the cohesion of 
  CatShelterGUI, I believe I can refactor the user action handling into dedicated classes such that there's a clear
  separation between the graphical user interface and its applications.