package ui;

import model.Item;
import model.Room;
import presistence.JsonReader;
import presistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;


public class RoomGUI extends JFrame implements ActionListener {

    private Room room;
    private Item item;
    private static final String JSON_STORE = "./data/room.json";

    private JPanel home;
    private JPanel roomPanel;
    private JPanel itemsPage;
    private JButton addButton;
    private JButton viewButton;
    private JButton sortButton;
    private JButton removeButton;
    private JButton removeSoldButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton exitButton;
    private JButton addItem;
    private JLabel items;
    private JLabel allprice;
    private JLabel remove;
    private boolean inRoom;
    private JTextField rmv;
    private JTextField nme;
    private JTextField cat;
    private JTextField rat;
    private JTextField prc;
    private JTextField frs;
    private JTextField dsc;
    private JLabel name;
    private JLabel category;
    private JLabel rating;
    private JLabel price;
    private JLabel forsale;
    private JLabel description;
    private JLabel itemAdded;
    private JLabel img1;
    private Font ttlfnt = new Font("Audrey", Font.BOLD, 30);
    private Font fnt = new Font("Audrey", Font.BOLD, 20);
    private Dimension box = new Dimension(500, 100);

    // Makes a new JFrame with Room and Item attributes
    public RoomGUI() {
        super("Minimus App");
        room = new Room();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000,500));
        buildHome();
        buildRoomPanel();
        buildItemCreationPanel();
        JLabel title = new JLabel("MINIMUS");
        title.setFont(new Font("Audrey", Font.BOLD, 200));
        home.add(title);
        buildMenuButtons();
        JButton homeButton = new JButton("Return Home");
        homeButton.setActionCommand("Return Home");
        homeButton.addActionListener(this);
        placeButtons(addButton,viewButton,sortButton,removeSoldButton,saveButton,
                loadButton,exitButton);
        createButtonActions();
        home.setVisible(true);
    }

    // EFFECTS: Builds the home panel
    public void buildHome() {
        home = new JPanel();
        add(home);
        items = new JLabel();
    }

    // MODIFIES: this
    // EFFECTS: Builds panel that displays all Items in a room
    public void buildRoomPanel() {
        roomPanel = new JPanel(new GridLayout(4,3));
        JScrollPane scroll = new JScrollPane(items, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);


        removeButton = new JButton("Remove an Item");
        removeButton.setActionCommand("Remove an Item");
        removeButton.addActionListener(this);

        remove = new JLabel("Remove an Item");
        rmv = new JTextField(10);

        allprice = new JLabel();
        allprice.setText("Your Total Is: $" + room.getTotalPrice());
        allprice.setFont(fnt);

        JButton homeButton = new JButton("Return Home");
        homeButton.setActionCommand("Return Home");
        homeButton.addActionListener(this);
        addHomeButton(homeButton, roomPanel);
        items.setFont(fnt);
        removeButton.setFont(fnt);
        rmv.setMaximumSize(box);
        roomPanel.add(removeButton);
        roomPanel.add(scroll);
        roomPanel.add(rmv);
        roomPanel.add(allprice);
    }

    // EFFECTS: Creates Home button Attributes
    public void addHomeButton(JButton button1, JPanel panel) {
        button1.setFont(fnt);
        button1.setBackground(Color.WHITE);
        button1.setForeground(Color.BLACK);
        panel.add(button1);
        pack();
        setVisible(true);
        setResizable(true);
    }


    // MODIFIES: this
    // EFFECTS: builds panel that lets user make a new item
    public void buildItemCreationPanel() {
        itemsPage = new JPanel(new GridLayout(0,2));
        JButton homeButton = new JButton("Return Home");
        homeButton.setActionCommand("Return Home");
        homeButton.addActionListener(this);
        addHomeButton(homeButton, itemsPage);

        initializeItemAttributes();

        itemCreationDisplaySettings();

        itemPanelPlacement();


    }

    // MODIFIES: this
    // EFFECTS: Initializes all labels, textfields and creat button
    public void initializeItemAttributes() {
        addItem = new JButton("Create Item");
        addItem.setActionCommand("Create Item");
        addItem.addActionListener(this);

        name = new JLabel("Name of Item:");
        nme = new JTextField(10);
        category = new JLabel("Category of Item:");
        cat = new JTextField(10);
        rating = new JLabel("Rating (1-10):");
        rat = new JTextField(10);
        price = new JLabel("Price in CAD:");
        prc = new JTextField(10);
        forsale = new JLabel("Is for Sale ? (true or false)");
        frs = new JTextField(10);
        description = new JLabel("Value Of item to you:");
        dsc = new JTextField(10);

        inRoom = false;
        itemAdded = new JLabel();
    }

    // EFFECTS: Adds Graphics to Item creation proccess
    private void itemCreationDisplaySettings() {
        addItem.setBackground(Color.WHITE);
        addItem.setForeground(Color.BLACK);
        addItem.setFont(fnt);
        name.setFont(ttlfnt);
        category.setFont(ttlfnt);
        rating.setFont(ttlfnt);
        price.setFont(ttlfnt);
        forsale.setFont(ttlfnt);
        description.setFont(ttlfnt);
        nme.setMaximumSize(box);
        cat.setMaximumSize(box);
        rat.setMaximumSize(box);
        prc.setMaximumSize(box);
        frs.setMaximumSize(box);
        dsc.setMaximumSize(box);
    }

    //EFFECTS: Adds created Item Fields to the panel
    public void itemPanelPlacement() {
        itemsPage.add(addItem);

        itemsPage.add(name);
        itemsPage.add(nme);
        itemsPage.add(category);
        itemsPage.add(cat);
        itemsPage.add(rating);
        itemsPage.add(rat);
        itemsPage.add(price);
        itemsPage.add(prc);
        itemsPage.add(forsale);
        itemsPage.add(frs);
        itemsPage.add(description);
        itemsPage.add(dsc);

        itemsPage.add(itemAdded);
    }

    // EFFECTS: Initializes menu buttons and titles
    public void buildMenuButtons() {
        addButton = new JButton("Add New Item");
        viewButton = new JButton("View Your Room");
        sortButton = new JButton("Sort Room from Highest Rated Items to Lowest");
        removeSoldButton = new JButton("Remove all Items On Sale");
        saveButton = new JButton("Save Your Current Room");
        loadButton = new JButton("Load your Previously Saved Room");
        exitButton = new JButton("Close Your Room");

    }

    // EFFECTS: Calls the addButton method for each argument
    public void placeButtons(JButton btn1, JButton btn2, JButton btn3, JButton btn4, JButton btn5,
                             JButton btn6, JButton btn7) {

        placeButton(btn1, home);
        placeButton(btn2, home);
        placeButton(btn3, home);
        placeButton(btn4, home);
        placeButton(btn5, home);
        placeButton(btn6, home);
        placeButton(btn7, home);


    }


    // MODIFIES: this
    // EFFECTS: Places one button to given panel, helper for Place Buttons
    public void placeButton(JButton inputButton, JPanel panel) {
        inputButton.setBackground(Color.WHITE);
        inputButton.setFont(fnt);
        panel.add(inputButton);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(true);
    }

    //MODIFIES: this
    //EFFECTS: adds the functions of a given button
    public void createButtonActions() {
        addButton.addActionListener(this);
        addButton.setActionCommand("New Item");
        viewButton.addActionListener(this);
        viewButton.setActionCommand("View Room");
        sortButton.addActionListener(this);
        sortButton.setActionCommand("Sort by Rating (from Greatest to Least)");
        removeSoldButton.addActionListener(this);
        removeSoldButton.setActionCommand("Remove All Items On Sale");
        saveButton.addActionListener(this);
        saveButton.setActionCommand("Save Current Room");
        loadButton.addActionListener(this);
        loadButton.setActionCommand("Load Last Room");
        exitButton.addActionListener(this);
        exitButton.setActionCommand("quit");

    }


    //This is the method that is called when the JButton btn is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("New Item")) {
            buildItemPanel();
        } else if (e.getActionCommand().equals("View Room")) {
            viewItemsinRoom();
        } else if (e.getActionCommand().equals("Sort by Rating (from Greatest to Least)")) {
            sortItems();
        } else if (e.getActionCommand().equals("Remove an Item")) {
            removeItem();
        } else if (e.getActionCommand().equals("Remove All Items On Sale")) {
            room.removeSoldItems();
        } else if (e.getActionCommand().equals("Save Current Room")) {
            saveRoom();
        } else if (e.getActionCommand().equals("Load Last Room")) {
            loadRoom();
        } else if (e.getActionCommand().equals("quit")) {
            System.exit(0);
        } else if (e.getActionCommand().equals("Return Home")) {
            returnHome();
        } else if (e.getActionCommand().equals("Create Item")) {
            addItemtoRoom();
        }
    }

    // MODIFIES: this
    // EFFECTS: Sorts the room user is currently in
    public void sortItems() {
        room.sortRating();
        viewItemsinRoom();
    }

    // EFFECTS: Builds Item Creation page for adding items
    public void buildItemPanel() {
        add(itemsPage);
        itemsPage.setVisible(true);
        home.setVisible(false);
        roomPanel.setVisible(false);
    }

    // MODIFIES: this
    // EFFECTS: Adds user Item to Room
    public void addItemtoRoom() {
        try {
            item = new Item(nme.getText(), cat.getText(),
                    Integer.parseInt(rat.getText()), Integer.parseInt(prc.getText()),
                    Boolean.parseBoolean(frs.getText()), dsc.getText());
            room.addItem(item);
            inRoom = true;
            itemAdded.setText("item is in room? " + inRoom);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Input, Please Put a Number");
        } catch (InputMismatchException e) {
            System.out.println("Invalid Input, Please Put Either True or False");
        } catch (IndexOutOfBoundsException e) {
            items.setText("Room Isn't Built, build a Room First");
        }
    }

    // EFFECTS: Makes it so that the Items in Room are the only thing visible
    public void viewItemsinRoom() {
        add(roomPanel);
        itemsPage.setVisible(false);
        home.setVisible(false);
        roomPanel.setVisible(true);
        items.setText("<html><pre>Items in Room: \n" + room.getItems() + "\n</pre></html>");
        allprice.setText("Your Total Is: $" + room.getTotalPrice());
    }

    // MODIFIES: this
    // EFFECTS: Remove a given item from a room
    public void removeItem() {
        try {
            int n = Integer.parseInt(rmv.getText());
            room.removeItem(room.getItem(n));
            items.setText("<html><pre>Items in Room: \n" + room.getItems() + "\n</pre></html>");
            allprice.setText("Your Total Is: $" + room.getTotalPrice());
        } catch (NullPointerException e) {
            System.out.println("Add an Item before trying to remove it");
        } catch (IndexOutOfBoundsException e) {
            items.setText("Room hasn't been built yet, please build room");
        }
    }

    //EFFECTS: Saves the Current State of the Room
    private void saveRoom() {
        JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
        try {
            jsonWriter.open();
            jsonWriter.write(room);
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
            room = jsonReader.readRoom(JSON_STORE);
            System.out.println("Loaded Room" + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //EFFECTS: this is the return home function
    public void returnHome() {
        home.setVisible(true);
        roomPanel.setVisible(false);
        itemsPage.setVisible(false);
    }

}
