/**
 * The Terrain class is designed to represent the zones between the towns in the Treasure Hunter game.
 */
public class Terrain
{
    //instance variables
    private String terrainName;
    private String neededItem;

    /**
     * Sets the class member variables
     * @param name The name of the zone.
     * @param item The item needed in order to cross the zone.
     */
    public Terrain(String name, String item)
    {
        terrainName = name;
        neededItem = item;
    }

    //Accessors
    public String getTerrainName()
    {
        return terrainName;
    }

    public String getNeededItem()
    {
        return neededItem;
    }

    /**
     * Guards against a hunter crossing the zone without the proper item.
     * Searches the hunter's inventory for the proper item and determines whether or not the hunter can cross.
     *
     * @param hunter The Hunter object trying to cross the terrain.
     * @returns  true if the Hunter has the proper item.
     */
    public boolean canCrossTerrain(Hunter hunter)
    {
        if(hunter.hasItemInKit(neededItem))
        {
            return true;
        }
        return false;
    }

    //toString
    public String toString()
    {
        return terrainName + " needs a(n) " + neededItem + " to cross.";
    }
}
