package ui;


import model.Item;
import model.Room;
import presistence.JsonReader;
import presistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class MinimusApp {
    private static final String JSON_STORE = "./data/room.json";
    private Item user;
    private Item item;
    private Room rm;
    private Scanner input;


    // initalizes app
    public MinimusApp() {
        runMinimus();
    }

    // MODIFIES: this
    // EFFECTS: processes user input, borrowed from TellerApp https://github.students.cs.ubc.ca/CPSC210/TellerApp
    private void runMinimus() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }


    // MODIFIES: this
    // EFFECTS: processes user command , borrowed from TellerApp
    //          https://github.students.cs.ubc.ca/CPSC210/TellerApp
    private void processCommand(String command) {
        if (command.equals("n")) {
            newItem();
        } else if (command.equals("v")) {
            viewRoom();
        } else if (command.equals("r")) {
            removeItem();
        } else if (command.equals("g")) {
            sortRoom();
        } else if (command.equals("t")) {
            totalPrice();
        } else if (command.equals("a")) {
            removeSold();
        } else if (command.equals("s")) {
            saveRoom();
        } else if (command.equals("l")) {
            loadRoom();
        } else {
            System.out.println("Selection not valid...");
        }
    }


    // MODIFIES: this
    // EFFECTS: initializes Room, borrowed from TellerApp
    //          https://github.students.cs.ubc.ca/CPSC210/TellerApp
    private void init() {
        rm = new Room();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // MODIFIES: this
    // EFFECTS: displays menu of options to user, borrowed from TellerApp
    //          https://github.students.cs.ubc.ca/CPSC210/TellerApp
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tn -> New Item");
        System.out.println("\tv -> View Room");
        System.out.println("\tg -> Sort by Rating (from Greatest to Least)");
        System.out.println("\tt -> Total Price for On Sale Items");
        System.out.println("\tr -> Remove Item");
        System.out.println("\ta -> Remove All Items On Sale");
        System.out.println("\ts -> Save Current Room");
        System.out.println("\tl -> Load Last Room");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: Shows the items placed in the room
    private void viewRoom() {
        System.out.println("Items in Room: \n" + rm.getItems());
    }

    // MODIFIES: this
    // EFFECTS: walks user through item creation process, then adds item to list via createitems()
    private void newItem() {

        String name;
        String category;
        int rating;
        int priceInCAD;
        boolean saleStatus;
        String description;

        System.out.println("Please enter the following details:\nName of Item:");

        name = input.next();
        System.out.println("Category of Item:\n");
        category = input.next();
        System.out.println("Rating (1-10):\n");
        rating = input.nextInt();
        System.out.println("Price in CAD:\n");
        priceInCAD = input.nextInt();
        System.out.println("Is for Sale ? (true or false)\n");
        saleStatus = input.nextBoolean();
        System.out.println("Value Of item to you:\n");
        description = input.next();

        user = createItems(name, category, rating, priceInCAD, saleStatus, description);
        checkItem(user);

    }


    // MODIFIES: This
    // EFFECTS: Removes a given item
    private void removeItem() {
        String x;

        System.out.println("Name of the Item You Want to remove: \n");
        x = input.next();

        rm.removeItem(bringItem(x));


        System.out.println("Item Has Been Removed \n");
    }

    // MODIFIES: This
    // EFFECTS: Sorts room from highest rating to lowest
    private void sortRoom() {
        rm.sortRating();
        System.out.println("Room Has Been SORTED!!! \n");
    }

    // EFFECTS: Returns the Total Price of all items on sale
    private void totalPrice() {
        System.out.println("Total Price of Items: $" + rm.getTotalPrice());
    }

    // MODIFIES: this
    // EFFECTS: Removes any items that are on sale
    private void removeSold() {
        rm.removeSoldItems();
        System.out.println("All On Sale Items Have been Removed");
    }


    // MODIFIES: this
    // EFFECTS: Helper made to create an item and place it into the room
    public Item createItems(String name, String category, int rating, int priceInCAD,
                            Boolean saleStatus, String description) {

        item = new Item(name, category, rating, priceInCAD, saleStatus, description);

        rm.addItem(item);

        return item;
    }

    // MODIFIES: this
    // EFFECTS: Helper made to check item status in a room
    private void checkItem(Item user) {

        if (rm.hasItem(user)) {
            System.out.println("The Item has been placed into the room!!");
        } else {
            System.out.println("The Item has been Removed!!");
        }
    }

    // MODIFIES: this
    // EFFECTS: Helper made to convert item name to Item
    public Item bringItem(String x) {
        return rm.getItem(rm.findItem(x));
    }

    // EFFECTS: saves the workroom to file
    private void saveRoom() {
        JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
        try {
            jsonWriter.open();
            jsonWriter.write(rm);
            jsonWriter.close();
            System.out.println("Saved Room" + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadRoom() {
        JsonReader jsonReader = new JsonReader();
        try {
            rm = jsonReader.readRoom(JSON_STORE);
            System.out.println("Loaded Room" + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}

