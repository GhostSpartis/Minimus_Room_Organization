package model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class Room {

    private final ArrayList<Item> listOfItems;
    private Item itmList;

    // Creates a new ArrayList in the constructor
    public Room() {
        listOfItems = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: add an Item to the list(Room)
    public void addItem(Item item) {
        listOfItems.add(item);
    }

    // EFFECTS: returns the size of list
    public int getNumberOfItemsInRoom() {
        return listOfItems.size();
    }

    // EFFECTS: returns true if the item is in list,
    //          else false
    public boolean hasItem(Item item) {
        return listOfItems.contains(item);
    }

    // EFFECTS: returns false and removes the item if in list,
    //          else true
    public boolean removeItem(Item item) {
        if (hasItem(item)) {
            listOfItems.remove(item);
            return false;
        } else {
            return true;
        }
    }

    // EFFECTS: returns item at given point in list
    public Item getItem(int x) {
        if (listOfItems.get(x) == null) {
            return null;
        }
        return itmList = listOfItems.get(x);
    }

    // EFFECTS: lists all Items in Room and all Item attributes
    public String getItems() {
        String items = "";
        for (int n = 0; n < getNumberOfItemsInRoom(); n++) {
            items += "Item Rank #" + n + "\n" + "Item Name:" + listOfItems.get(n).getName()
                    + "\n" + "Item Category:" + listOfItems.get(n).getCategory() + "\n" + "Item Rating:"
                    + listOfItems.get(n).getRating() + "\n" + "Item Price:" + listOfItems.get(n).getCategory()
                    + "\n" + "Is For Sale:" + listOfItems.get(n).isForSale() + "\n" + "What It Means To Me:"
                    + listOfItems.get(n).getDescription() + "\n" + "\n";
        }
        return items;
    }
}
