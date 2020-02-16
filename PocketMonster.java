/*Austin Van Braeckel
01/10/2019
Represents a pocket monster and its information/stats and abilities
- Must have a name to exist
 */
package pocketmonstersvanbraeckel;



public class PocketMonster {

    //Attributes/stats of the pocket monster
    private String name;
    private String type;
    private int level;
    private double xp;
    private double hp;
    private double weight; //kilos
    private double height; //metres
    private String[] ability = new String[4];

    /**
     * Constructor Creates a pocket monster slot that is empty, and sets all
     * default values
     */
    private PocketMonster() {
        name = "empty";
        type = "not assigned";
        level = 0;
        xp = 0;
        hp = 0;
        weight = 0;
        height = 0;
        ability[0] = "None";
        ability[1] = "None";
        ability[2] = "None";
        ability[3] = "None";
    }

    /**
     * Constructor Creates a pocket monster with the given name and sets all
     * default values
     */
    public PocketMonster(String n) {
        this();
        name = n;
    }

    /**
     * Constructor makes a pocket monster with the same data as an existing one
     *
     * @param p existing pocket monster object
     */
    public PocketMonster(PocketMonster p) {
        name = p.getName();
        type = p.getType();
        level = p.getLevel();
        xp = p.getXp();
        hp = p.getHp();
        weight = p.getWeight();
        height = p.getHeight();
        for (int i = 0; i < 4; i++) {
            ability[i] = p.getAbility(i);
        }
    }

    /**
     * Displays message saying that a specific pocket monster has activated an
     * ability
     *
     * @param num integer of ability number (1,2,3,4)
     */
    public String activateAbility(int num) {
        String s = name + " has activated " + ability[num - 1];
        return s;
    }

    /**
     * Retrieves name of the pocket monster
     *
     * @return a String of the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the pocket monster
     *
     * @param n String of the name
     */
    public void setName(String n) {
        name = n;
    }

    /**
     * Sets the type of the pocket monster
     *
     * @param s String of the type name
     */
    public void setType(String s) {
        type = s;
    }

    /**
     * Retrieves type
     *
     * @return String of the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the level
     *
     * @param l integer of the level
     */
    public void setLevel(int l) {
        level = l;
    }

    /**
     * Retrieves the level
     *
     * @return integer of the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * \
     * Sets the XP (Experience Points)
     *
     * @param x number (decimals accepted) of XP
     */
    public void setXp(Double x) {
        xp = x;
    }

    /**
     * Retrieves the XP
     *
     * @return number of XP
     */
    public double getXp() {
        return xp;
    }

    /**
     * Sets the HP (Hit Points)
     *
     * @param h number (decimals accepted) of HP
     */
    public void setHp(double h) {
        hp = h;
    }

    /**
     * Retrieves the HP
     *
     * @return the number of HP
     */
    public double getHp() {
        return hp;
    }

    /**
     * Sets the weight in kilograms
     *
     * @param w weight in kilograms (decimals accepted)
     */
    public void setWeight(double w) {
        weight = w;
    }

    /**
     * Retrieves the weight
     *
     * @return weight in kilograms
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets the height in meters
     *
     * @param h height in meters (decimals accepted)
     */
    public void setHeight(double h) {
        height = h;
    }

    /**
     * Retrieves the height
     *
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Sets the ability of the given number to the given name
     *
     * @param num 1, 2, 3, or 4 - the ability number (4 maximum for each pocket
     * monster)
     * @param name String of the ability name
     */
    public void setAbility(int num, String name) {
        ability[num - 1] = name;
    }

    /**
     * Retrieves Ability name of the specified Ability number
     *
     * @param num 0, 1, 2, 3 - the Ability number
     * @return String of the name of the specified Ability
     */
    public String getAbility(int num) {
        return ability[num];
    }

    /**
     * Randomizes the statistics of the monster that can vary between 
     * different ones found in the wild
     */
    public void randomizeData(){
        level = (int)((Math.random() * 99) + 1); //between 1 and 99
        xp = ((double)(int)(Math.random() * 9999 + 1)) / 10; //between 0.1 and 999.9
        hp = ((double)(int)(Math.random() * 9999 + 100)) / 10; //between 10.0 and 999.9
        weight = ((double)(int)(Math.random() * 200 + 5)) / 10; //between 0.5 and 20.0
        height = ((double)(int)(Math.random() * 10 + 1)) / 10; //between 0.1 and 1
    }
    
    /**
     * Puts all the data of the pocket monster into a formatted string
     *
     * @return String of the data
     */
    public String toString() {
        String s = "Name: " + name + "\nType: " + type + "\nLevel: " + level
                + "\nExperience Points: " + xp + "\nHit Points: " + hp
                + "\nWeight: " + weight + "kg\nHeight: " + height + "m\nAbilities: "
                + ability[0] + ", " + ability[1] + ", " + ability[2] + ", " + ability[3];
        return s;
    }

}
