package model;

import org.json.JSONObject;
import presistence.Writable;

 // Represents an Item with its name, category, value (1-10), priceInCAD (in dollars), for sale or not and description
public class Item implements Writable {
    private String name;         // name of the item
    private String category;     // category to help sort item
    private int rating;           // value range from 1-10 from user
    private int priceInCAD;      // possible price upon sale of item
    private boolean saleStatus;     // true or false value of item about to be removed due to sale
    private String description;  // description of item's value to user

    /*
        REQUIRES: itmName has a non-zero length
        EFFECTS: name of item is set to itmName; category of item
                made by user is set to itmCategory; rating is set to a
                positive integer from 1-10; priceInCAD is a positive integer
                assigned to item as itmPrice; saleStatus is a boolean determining
                regardless of whether the item is going on sale is set to forSale;
                description is a String that describes value of item to user set
                as description
     */
    public Item(String itmName, String itmCategory, int itmRating, int itmPrice, boolean forSale, String description) {
        this.name = itmName;
        this.category = itmCategory;
        this.rating = itmRating;
        this.priceInCAD = itmPrice;
        this.saleStatus = forSale;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getRating() {
        return rating;
    }

    public int getPriceInCAD() {
        return priceInCAD;
    }

    public boolean isForSale() {
        return saleStatus;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("category", category);
        json.put("rating", rating);
        json.put("price", priceInCAD);
        json.put("on sale", saleStatus);
        json.put("description", description);
        return json;
    }

}
