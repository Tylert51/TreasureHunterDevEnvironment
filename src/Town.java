/**
 * The Town Class is where it all happens.
 * The Town is designed to manage all of the things a Hunter can do in town.
 */
public class Town
{
    //instance variables
    private Hunter hunter;
    private Shop shop;
    private Terrain terrain;
    private String printMessage;
    private boolean toughTown;
    private boolean isSearched;
    private Treasure treasure;

    //Constructor
    /**
     * The Town Constructor takes in a shop and the surrounding terrain, but leaves the hunter as null until one arrives.
     * @param shop The town's shoppe.
     * @param toughness The surrounding terrain.
     */
    public Town(Shop shop, double toughness)
    {
        treasure = new Treasure();

        this.shop = shop;
        this.terrain = getNewTerrain();


        // the hunter gets set using the hunterArrives method, which
        // gets called from a client class
        hunter = null;

        printMessage = "";

        // higher toughness = more likely to be a tough town
        toughTown = (Math.random() < toughness);

        isSearched = false;
    }

    //accessor
    public String getLatestNews()
    {
        return printMessage;
    }

    /**
     * Assigns an object to the Hunter in town.
     * @param hunter The arriving Hunter.
     */
    public void hunterArrives(Hunter hunter)
    {
        this.hunter = hunter;
        printMessage = "Welcome to town, " + hunter.getHunterName() + ".";

        if (toughTown)
        {
            printMessage += "\nIt's pretty rough around here, so watch yourself.";
        }
        else
        {
            printMessage += "\nWe're just a sleepy little town with mild mannered folk.";
        }
    }

    /**
     * Handles the action of the Hunter leaving the town.
     * @return true if the Hunter was able to leave town.
     */
    public boolean leaveTown()
    {
        boolean canLeaveTown = terrain.canCrossTerrain(hunter);
        if (canLeaveTown)
        {
            String item = terrain.getNeededItem();
            printMessage = "You used your " + item + " to cross the " + terrain.getTerrainName() + ".";
            if (checkItemBreak())
            {
                hunter.removeItemFromKit(item);
                printMessage += "\nUnfortunately, your " + item + " broke.";
            }

            return true;
        }

        printMessage = "You can't leave town, " + hunter.getHunterName() + ". You don't have a " + terrain.getNeededItem() + ".";
        return false;
    }

    /**
     * allows the user to enter the shop (called method from the shop class)
     * @param choice represents the choice that the user picks when entering the shop (either buying (B) or selling (S))
     */
    public void enterShop(String choice)
    {
        shop.enter(hunter, choice);
    }

    /**
     * Gives the hunter a chance to fight for some gold.<p>
     * The chances of finding a fight and winning the gold are based on the toughness of the town.<p>
     * The tougher the town, the easier it is to find a fight, and the harder it is to win one.
     * 100% win rate when in cheat mode and win 100 gold each time you find a brawl
     */
    public void lookForTrouble()
    {
        double noTroubleChance;
        if (toughTown)
        {
            noTroubleChance = 0.66;
        }
        else
        {
            noTroubleChance = 0.33;
        }

        if (Math.random() == noTroubleChance)
        {
            printMessage = "You couldn't find any trouble";
        }
        else
        {
            printMessage = "You want trouble, stranger!  You got it!\nOof! Umph! Ow!\n";

            int goldDiff;

            if (TreasureHunter.isCheatMode()) {
                goldDiff = 100;
            }
            else {
                goldDiff = (int) (Math.random() * 10) + 1;
            }
            if (TreasureHunter.isCheatMode()||Math.random() > noTroubleChance){
                if (TreasureHunter.getEasyMode()){
                    goldDiff *=2;
                }
                printMessage += "Okay, stranger! You proved yer mettle. Here, take my gold.";
                printMessage += "\nYou won the brawl and receive " +  goldDiff + " gold.";
                hunter.changeGold(goldDiff);
            }
            else  {
                if(TreasureHunter.getEasyMode()){
                    goldDiff/=3;
                }
                printMessage += "That'll teach you to go lookin' fer trouble in MY town! Now pay up!";
                printMessage += "\nYou lost the brawl and pay " +  goldDiff + " gold.";
                hunter.changeGold(-1 * goldDiff);
            }
        }
    }

    /**
     * to string method that just displays where the user is currently
     * @return a string that displays to the user the current terrain the user is surrounded by (ex Ocean)
     */
    public String toString()
    {
        return "This nice little town is surrounded by " + terrain.getTerrainName() + ".";
    }

    /**
     * Determines the surrounding terrain for a town, and the item needed in order to cross that terrain.
     *
     * @return A Terrain object.
     */
    private Terrain getNewTerrain()
    {
        double rnd = Math.random();
        if (rnd < .2)
        {
            return new Terrain("Mountains", "Rope");
        }
        else if (rnd < .4)
        {
            return new Terrain("Ocean", "Boat");
        }
        else if (rnd < .6)
        {
            return new Terrain("Plains", "Horse");
        }
        else if (rnd < .8)
        {
            return new Terrain("Desert", "Water");
        }
        else
        {
            return new Terrain("Jungle", "Machete");
        }
    }

    /**
     * Determines whether or not a used item has broken.
     * @return true if the item broke.
     */
    private boolean checkItemBreak()
    {
        double rand = Math.random();
        return (rand < 0.5);
    }


    /**
     * method that allows the user to search for treasure
     * random chance of finding 1 of 3 treasures (25% for each, 1 our of 4 times you will find nothing)
     * adds or discards treasure to inventory depending on if you found it already
     */
    public void searchTreasure() {
        int randInt = (int) (Math.random() * 4);
        if(!isSearched) {

            if(randInt == 3) {
                printMessage = "Unlucky, the only that you have found is a couple of worms";

            } else {
                String treasureFound = Treasure.getTreasureItems(randInt);

                printMessage = "Congratulations, you have found " + treasureFound + "!";

                if (Treasure.isNewTreasure(randInt)) {
                    Treasure.newTreasure(randInt);
                    printMessage += "\nThe item has been added to your inventory!";
                    hunter.addItem(treasureFound);

                } else {
                    printMessage += "\nYou already found this treasure and it has been discarded from your inventory";

                }
            }

            isSearched = true;

        } else {
            printMessage = "You have already serached for treasure in this town\nMove onto another town to search for more";
        }

    }
}