/**
 * Hunter Class<br /><br />
 * This class represents the treasure hunter character (the player) in the Treasure Hunt game.
 */
public class Hunter
{
    //Keeps the items in the kit separate
    private static final String KIT_DELIMITER = ";";

    //instance variables
    private String hunterName;
    private String kit;
    private int gold;
    private boolean noGold;

    //Constructor
    /**
     * The base constructor of a Hunter assigns the name to the hunter and an empty kit.
     *
     * @param hunterName The hunter's name.
     * @param startingGold the starting gold the player receives
     */
    public Hunter(String hunterName, int startingGold)
    {
        this.hunterName = hunterName;
        kit = "";
        gold = startingGold;
        noGold = false;
    }

    //Accessors
    public String getHunterName()
    {
        return hunterName;
    }

    public String getKit()
    {
        return kit;
    }

    public int getGold()
    {
        return gold;
    }

    public void changeGold(int modifier)
    {
        gold += modifier;
        if (gold <= 0)
        {
            gold = 0;
            noGold=true;
        }
    }
    public boolean noGold() {
        return noGold;
    }

    /**
     * Buys an item from a shop.
     *
     * @param item The item the hunter is buying.
     * @param costOfItem  the cost of the item
     *
     * @return true if the item is successfully bought.
     */
    public boolean buyItem(String item, int costOfItem)
    {
        if (costOfItem == 0 || gold < costOfItem || hasItemInKit(item))
        {
            return false;
        }

        gold -= costOfItem;
        addItem(item);
        return true;
    }

    /**
     * The Hunter is selling an item to a shop for gold.<p>
     * This method checks to make sure that the seller has the item and that the seller is getting more than 0 gold.
     *
     * @param item The item being sold.
     * @param buyBackPrice  the amount of gold earned from selling the item
     * @return true if the item was successfully sold.
     */
    public boolean sellItem(String item, int buyBackPrice)
    {
        if (buyBackPrice <= 0 || !hasItemInKit(item))
        {
            return false;
        }

        gold += buyBackPrice;
        removeItemFromKit(item);
        return true;
    }

    /**
     *  Removes an item from the kit.
     *
     * @param item The item to be removed.
     */
    public void removeItemFromKit(String item)
    {
        int itmIdx = kit.indexOf(item);

        // if item is found
        if (itmIdx >= 0)
        {
            String tmpKit = kit.substring(0, itmIdx);
            int endIdx = kit.indexOf(KIT_DELIMITER, itmIdx);
            tmpKit += kit.substring(endIdx + 1);

            // update kit
            kit = tmpKit;
        }
    }

    /**
     * Checks to make sure that the item is not already in the kit.
     * If not, it adds an item to the end of the String representing the hunter's kit.<br /><br />
     * A KIT_DELIMITER character is added to the end of the of String.
     *
     * @param item The item to be added to the kit.
     * @return true if the item is not in the kit and has been added.
     */
    public boolean addItem(String item)
    {
        if (!hasItemInKit(item))
        {
            kit += item + KIT_DELIMITER;
            return true;
        }

        return false;
    }

    /**
     * Searches the kit String for a specified item.
     *
     * @param item The search item
     *
     * @return true if the item is found.
     */
    public boolean hasItemInKit(String item)
    {
        int placeholder = 0;

        while (placeholder < kit.length() - 1)
        {
            int endOfItem = kit.indexOf(KIT_DELIMITER, placeholder);
            String tmpItem = kit.substring(placeholder, endOfItem);
            placeholder = endOfItem + 1;
            if (tmpItem.equals(item))
            {
                // early return
                return true;
            }
        }
        return false;
    }

    /** Returns a printable representation of the inventory, which
     *  is a list of the items in kit, with the KIT_DELIMITER replaced with a space
     *
     * @return  The printable String representation of the inventory
     */
    public String getInventory()
    {
        String printableKit = kit;
        String space = " ";

        int index = 0;

        while (printableKit.indexOf(KIT_DELIMITER) != -1)
        {
            index = printableKit.indexOf(KIT_DELIMITER);
            printableKit = printableKit.substring(0, index) + space + printableKit.substring(index + 1);
        }
        return printableKit;
    }

    /**
     * @return A string representation of the hunter.
     */
    public String toString()
    {
        String str = hunterName + " has " + gold + " gold";
        if (!kit.equals(""))
        {
            str += " and " + getInventory();
        }
        return str;
    }
}