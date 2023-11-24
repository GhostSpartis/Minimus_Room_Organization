package model;

import presistence.Writable;
import org.json.JSONObject;
import org.json.JSONArray;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;





public class Room implements Writable {

    private final ArrayList<Item> listOfItems;

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
        Item itmList;
        if (listOfItems.get(x) == null) {
            return null;
        }
        return itmList = listOfItems.get(x);
    }

    // EFFECTS: Returns the given item's location in the list
    public int findItem(String itemName) {
        int jinx = 0;
        for (int n = 0; n < getNumberOfItemsInRoom(); n++) {
            if (getItem(n).getName() == itemName) {
                return jinx = n;
            }
        }
        return jinx;
    }

    // EFFECTS: lists all Items in Room with attribute titles
    public String getItems() {
        String items = "";
        for (int n = 0; n < getNumberOfItemsInRoom(); n++) {
            items += "Item Rank #" + n + "\n" + "Item Name:" + listOfItems.get(n).getName()
                    + "\n" + "Item Category:" + listOfItems.get(n).getCategory() + "\n" + "Item Rating:"
                    + listOfItems.get(n).getRating() + "\n" + "Item Price:" + listOfItems.get(n).getPriceInCAD()
                    + "\n" + "Is For Sale:" + listOfItems.get(n).isForSale() + "\n" + "What It Means To Me:"
                    + listOfItems.get(n).getDescription() + "\n\n";
        }
        return items;
    }

   // REQUIRES: non-Empty list
   // MODIFIES: This
   // EFFECTS: Sorts the given list based on rating descending from 10-1
    public void sortRating() {
        Collections.sort(listOfItems, new ItemComparator());
    }

    // REQUIRES: non-Empty list
    // MODIFIES: This
    // EFFECTS: Sorts the given list based on rating descending from 10-1
    public void sortSaleStatus() {
        Collections.sort(listOfItems, new BooleanComparator());
    }

    // EFFECTS: returns the Total Price of all items on the list that are up for sale
    public int getTotalPrice() {
        int ttp = 0;
        for (int n = 0; n < getNumberOfItemsInRoom(); n++) {
            if (getItem(n).isForSale() == true) {
                ttp = getItem(n).getPriceInCAD() + ttp;
            }
        }
        return ttp;
    }

    // EFFECTS: this
    // MODIFIES: removes all items on the list that are on sale
    public void removeSoldItems() {
        sortSaleStatus();
        for (int n = 0; n < getNumberOfItemsInRoom(); n++)  {
            if (getItem(n).isForSale() == true) {
                removeItem(getItem(n));
                removeSoldItems();
            }
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("items", itemsToJson());
        return json;
    }

    // EFFECTS: returns Items in a Room as a JSON array
    private JSONArray itemsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Item i : listOfItems) {
            jsonArray.put(i.toJson());
        }

        return jsonArray;
    }
}
