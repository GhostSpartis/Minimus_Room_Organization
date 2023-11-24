package model;

class ItemComparator implements java.util.Comparator<Item> {
    @Override
    public int compare(Item a, Item b) {
        return b.getRating() - a.getRating();
    }
}
