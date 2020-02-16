/*Austin Van Braeckel
01/10/2018
Represents a trainer, all of his/her information, as well as their
maximum of 3 pocket monsters
- Must have a name and age to exist
 */
package pocketmonstersvanbraeckel;

public class Trainer {

    //Attributes of the trainer - name, age, and team of 3 (Maximum) pocket monsters
    private String name;
    private int age;
    private PocketMonster p1;
    private PocketMonster p2;
    private PocketMonster p3;

    /**
     * Creates a trainer and sets all default values
     */
    private Trainer() {
        name = "";
        age = 1;
        p1 = new PocketMonster("empty");
        p2 = new PocketMonster("empty");
        p3 = new PocketMonster("empty");
    }

    /**
     * Creates a trainer with the given name and age, and sets all default
     * values
     *
     * @param n String of the name given to the trainer
     * @param a integer of the age given to the trainer
     */
    public Trainer(String n, int a) {
        this();
        name = n;
        age = a;
    }

    /**
     * Determines whether the attempt to catch a pocket monster is successful or
     * not
     *
     * @param p pocket monster object
     * @return true if the monster is caught, and false if it is not, or there
     * is no empty slot
     */
    public boolean catchMonster(PocketMonster p) {
        int num = (int) ((Math.random() * 4) + 1);
        boolean success = true;
        //Set-up string to account for duplicate monsters for a trainer
        String duplicate = "";
        if (num == 4) { //1/4 chance of failure for the attempt
            success = false;
        }

        //If the capture is initially successful, check to see if there is space
        if (success) {
            if (!p1.getName().equals("empty") && !p2.getName().equals("empty")
                    && !p3.getName().equals("empty")) { //Checks if there is an empty slot
                success = false;
            } else if (p1.getName().equals("empty")) { //Assigns to first slot if empty
                p1 = new PocketMonster(p);
                //Checks if there are any duplicate names, and assigns a reasonable number if one or two duplicates already exist
                if (p2.getName().equals(p1.getName()) || p3.getName().equals(p1.getName())){ //same name
                    duplicate = "(2)";
                } else if (p2.getName().equals(p1.getName() + "(2)") || p3.getName().equals(p1.getName() + "(2)")){ //one duplicate already exists
                        duplicate = "(3)";
                }
                //sets name, with a number if necessary
                p1.setName(p1.getName() + duplicate);
            } else if (p2.getName().equals("empty")) { //Assigns to second slot if empty
                p2 = new PocketMonster(p);
                //Checks if there are any duplicate names, and assigns a reasonable number if one or two duplicates already exist
                if (p1.getName().equals(p2.getName()) || p3.getName().equals(p2.getName())){ //same name
                    duplicate = "(2)";
                } else if (p1.getName().equals(p2.getName() + "(2)") || p3.getName().equals(p2.getName() + "(2)")){ //one duplicate already exists
                        duplicate = "(3)";
                }
                //sets name, with a number if necessary
                p2.setName(p2.getName() + duplicate);
            } else { //Assigns to third slot if empty
                p3 = new PocketMonster(p);
                //Checks if there are any duplicate names, and assigns a reasonable number if one or two duplicates already exist
                if (p1.getName().equals(p3.getName()) || p2.getName().equals(p3.getName())){ //same name
                    duplicate = "(2)";
                } else if (p1.getName().equals(p3.getName() + "(2)") || p2.getName().equals(p3.getName() + "(2)")){ //one duplicate already exists
                        duplicate = "(3)";
                }
                //sets name, with a number if necessary
                p3.setName(p3.getName() + duplicate);
            }
        
        
            
        }

        return success;
    }

    /**
     *
     *
     * @param name String of the pocket monster name
     * @return true if the trainer has a pocket monster that matches the given
     * name and false if no name matches
     */
    public boolean release(String name) {
        //Set-up boolean to indicate success
        boolean success = true;
        //Makes sure the pocket monster isn't named "empty" because it's empty
        if (p1.getName().equalsIgnoreCase(name) && !name.equalsIgnoreCase("empty")) { //Releases slot one monster
            p1 = new PocketMonster("empty");
        } else if (p2.getName().equalsIgnoreCase(name) && !name.equalsIgnoreCase("empty")) { //Releases slot two monster
            p2 = new PocketMonster("empty");
        } else if (p3.getName().equalsIgnoreCase(name) && !name.equalsIgnoreCase("empty")) { //Releases slot three monster
            p3 = new PocketMonster("empty");
        } else { //No name matches
            success = false;
        }

        return success;
    }

    /**
     * Sets the name of the trainer
     *
     * @param n String of the specified name for the trainer
     */
    public void setName(String n) {
        name = n;
    }

    /**
     * Retrieves the name of the trainer
     *
     * @return String of the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the age of the trainer
     *
     * @param a integer of the specified age
     */
    public void setAge(int a) {
        age = a;
    }

    /**
     * Retrieves the age
     *
     * @return integer of the age
     */
    public int getAge() {
        return age;
    }
    
    /**
     * Sets the pocket monsters for the trainer
     * @param monster1 the first pocket monster
     * @param monster2 the second pocket monster
     * @param monster3 the third pocket monster
     */
    public void setP(PocketMonster monster1, PocketMonster monster2, PocketMonster monster3){
        p1 = monster1;
        p2 = monster2;
        p3 = monster3;
    }
    
    /**
     * Sets the specified slot with the specified new monster
     * 
     * @param monster PocketMonster object to be used to overwrite
     * @param numSlot integer of the number of the slot to be overwritten with new data
     * (1,2, or 3)
     */
    public void setP(PocketMonster monster, int numSlot){
        if (numSlot == 1){
            p1 = monster;
        } else if (numSlot == 2){
            p2 = monster;
        } else if (numSlot == 3){
            p3 = monster;
        } 
    }
    
    /**
     * Retrieves the pocket monster object of the first slot of the trainer
     * 
     * @return the pocket monster object
     */
    public PocketMonster getP1(){
        return p1;
    }
    
    /**
     * Retrieves the pocket monster object of the second slot of the trainer
     * 
     * @return the pocket monster object
     */
    public PocketMonster getP2(){
        return p2;
    }
    
    /**
     * Retrieves the pocket monster object of the third slot of the trainer
     * 
     * @return the pocket monster object
     */
    public PocketMonster getP3(){
        return p3;
    }

    /**
     * Retrieves a String of all three pocket monsters of the trainer
     * 
     * @return String of all pocket monster data in regards to the trainer
     */
    public String getP(){
        String s = "Slot 1:\n_________\n" + p1.toString()
                + "\n\nSlot 2:\n_________\n" + p2.toString() +
                "\n\nSlot 3:\n_________\n" + p3.toString();
        return s;
    }
    
    /**
     * Puts all of the data of the trainer into a formatted string
     *
     * @return String of the data
     */
    public String toString() {
        String s = "Name: " + name + "\nAge: " + age + "\nPocket Monsters:\n"
                + p1.toString() + "_____________" + p2.toString() + "_____________"
                + p3.toString();
        return s;
    }
}
