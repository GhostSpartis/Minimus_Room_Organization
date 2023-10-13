package ui;


import model.Item;
import model.Room;

import java.util.Scanner;

public class MinimusApp {
    private Item user;
    private Item item;
    private Room rm;
    private Scanner input;

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
        System.out.println("\tn -> new item");
        System.out.println("\tv -> view room");
        System.out.println("\tr -> remove item");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: Shows the items placed in the room
    private void viewRoom() {
        System.out.println("Items in Room: \n" + rm.getItems());
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

        String rmvitem;
        int x;

        System.out.println("Name of the Item You Want to remove: \n");
        rmvitem = input.next();
        System.out.println("Rank Of the Item You Want to Remove\n");
        x = input.nextInt();

        if (rmvitem == rm.getItem(x).getName()) {
            rm.removeItem(rm.getItem(x));
        }
        System.out.println("Item Has Been Removed \n");
    }
}

