import java.util.ArrayList;

/**
 * This treasure class holds the differnt treasure that could be found and also handles the list of
 * treasure that have been found already
 **/

public class Treasure {
    private ArrayList<String> treasure;
    private ArrayList<String> foundTreasure;

    /**
     * constructor will add the three treasures to the treasure array list and leave foundTreasure
     * blank since no treasure has been found
     **/
    public Treasure() {
        treasure = new ArrayList<String>();
        foundTreasure = new ArrayList<String>();

        treasure.add("Key to King Tut's Tomb");
        treasure.add("King Julian's Bandanda");
        treasure.add("The Potion of Eternal Youth");
    }

    public String getTreasureItems(int indx) {
        return treasure.get(indx);
    }

    public boolean isAlreadyFound(int item) {
        String treasureItem = getTreasureItems(item);
        return (!foundTreasure.contains( treasureItem ) );
    }

    public void newTreasure(int item) {
        String treasureItem = getTreasureItems(item);

        foundTreasure.add(treasureItem);

    }
}