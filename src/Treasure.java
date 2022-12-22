import java.util.ArrayList;

/**
 * This treasure class holds the differnt treasure that could be found and also handles the list of
 * treasure that have been found already
 **/

public class Treasure {
    private static final ArrayList<String> TREASURE = new ArrayList<String>();
    private static final ArrayList<String> FOUND_TREASURE = new ArrayList<String>();

    /**
     * constructor will add the three treasures to the treasure array list and leave foundTreasure
     * blank since no treasure has been found
     **/
    public Treasure() {
        TREASURE.add("Key to King Tut's Tomb");
        TREASURE.add("King Julian's Bandanda");
        TREASURE.add("The Potion of Eternal Youth");
    }

    /**
     * method that access the array list TREASURE and gets an item from it
     * @param indx represents the index of the list that is being accessed (ex indx 0 means you are accessing the Key to King Tut's Tomb)
     * @return a String that displays the treasure that has been accessed depending on the index
     */
    public static String getTreasureItems(int indx) {
        return TREASURE.get(indx);
    }

    /**
     * method that determines if the treasure has been found before or not
     * @param item represents the item that has just been found and being tested for duplicates
     * @return a boolean that represents if the treasure has been found before (if already found returns true)
     */
    public static boolean isNewTreasure(int item) {
        String treasureItem = getTreasureItems(item);
        return (!FOUND_TREASURE.contains( treasureItem ) );
    }

    /**
     * method that adds the certain treasure to the FOUND_TREASURE array list
     * @param item represents the treasure that has been found and will be added to the list of found treasures
     */
    public static void newTreasure(int item) {
        String treasureItem = getTreasureItems(item);

        FOUND_TREASURE.add(treasureItem);

    }

    /**
     * method that checks if all the treasures have been found
     * @return a boolean value that represents if all the treasures have been found
     * since there are 3 treasures, if the length of FOUND_TREASURE is equal to 3, they have found all the treasures and will return true
     */
    public static boolean isAllTreasureFound() {
        return FOUND_TREASURE.size() == 3;
    }
}