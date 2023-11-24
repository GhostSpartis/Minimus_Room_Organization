package model;

public class BooleanComparator implements java.util.Comparator<Item> {
    @Override
    public int compare(Item a, Item b) {
        return Boolean.compare(b.isForSale(), a.isForSale());
    }
}

