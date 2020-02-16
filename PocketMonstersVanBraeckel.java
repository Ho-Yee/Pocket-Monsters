/*Austin Van Braeckel
 01/10/2018
 A simulator for pokemon/pocket-monsters, which allows trainers and pocket monsters
 and their data to be accepted from a data file, along with types and abilities
 accepted from a separate file
 */
package pocketmonstersvanbraeckel;

import javax.swing.JOptionPane;
import java.io.*;

public class PocketMonstersVanBraeckel {

    /**
     * Reads a file and returns a string array of the contents
     *
     * @param filePath String of the file path
     * @return String array of the data in the file
     */
    public static String[] readFile(String filePath, int numFields) {
        String array[] = new String[1];
        //Tries to read a file containing the types and abilities
        try {
            FileReader fr = new FileReader("");
            System.out.println("j");
            BufferedReader br = new BufferedReader(fr);
            //reads first line which specifies the number of types, with 4 abilities each
            String num = br.readLine();
            int records = Integer.parseInt(num); //converts into an integer
            //Creates an array of the proper length
            array = new String[records * numFields];
            //Fills the array with the data
            for (int i = 0; i < (records * numFields); i++) {
                array[i] = br.readLine();
            }

        } catch (Exception e) { //Prints error message if file cannot be found
            System.out.println("ERROR: " + e);
            System.exit(0); //Closes program
        } //End try/catch

        return array;
    }

    /**
     * Calculates and returns the number of records in a specified file
     *
     * @param filePath
     * @return integer of number of records in the file
     */
    public static int NumRecords(String filePath) {
        int numRecords = 0;//Makes a counter to count the number of lines in the file (for array size)
        //Tries to read a file containing the types and abilities
        try { //Tries to read file
            FileReader fr = new FileReader("src\\pocketmonstersvanbraeckel\\" + filePath); //If successful, execute code below
            BufferedReader br = new BufferedReader(fr);
            //creates a boolean representing if it is the end of the file
            boolean eof = false;
            //Creates a String for temporary input from the data file
            String input = "";

            //Repeats until end of file is reached
            while (!eof) {
                //reads first line and checks if it is the end of the file
                input = br.readLine();
                if (input == null) {
                    //If the line is empty/null, the end of the file has been reached
                    eof = true;
                } else { //Otherwise count it as a line
                    numRecords++;
                }
            }//End while loop
        } catch (IOException e) { //Displays error messafe
            System.out.println("ERROR: " + e);
            System.exit(0); //closes program
        }

        return numRecords;
    }

    /**
     * Reads a file and puts all data into a String array
     *
     * @param fileName String of the file name for the file path
     * @param array String array to store file data
     */
    public static void FileToArray(String fileName, String[] array) {
        //Tries to read a file containing the types and abilities
        try { //Tries to read file
            FileReader fr = new FileReader("src\\pocketmonstersvanbraeckel\\" + fileName); //If successful, execute code below
            BufferedReader br = new BufferedReader(fr);
            //Puts all of the data into a String array
            for (int i = 0; i < array.length; i++) { //Repeats until array is filled
                array[i] = br.readLine(); //Reads in the data file line and stores it
            }

        } catch (Exception e) { //Prints error message if file cannot be found{
            System.out.println("ERROR: " + e);
            System.exit(0); //Closes program
        } //End try/catch

    }

    /**
     * Fills an array of Trainer objects from the String array of the data file
     * information
     *
     * @param array String array of the data file's contents
     * @param trainerArray array of Trainer objects to store the data
     * @param numTrainers integer of number of trainers in the file
     */
    public static void LoadTrainers(String[] array, Trainer[] trainerArray, int numTrainers) {
        int position = 1; //Starts on the first line of trainer data
        for (int i = 0; i < numTrainers; i++) {//Creates all trainers, and puts them in the array
            trainerArray[i] = new Trainer(array[position], Integer.parseInt(array[position + 1]));
            position += 23; //23 lines of pocket monster data to skip
        }
    }

    /**
     * Fills an array of PocketMonster objects from the String array of the data
     * file information
     *
     * @param array String array of the data file's contents
     * @param monsters array of PocketMonster objects to store the data
     * @param numTrainers integer of number of trainers in the file
     */
    public static void LoadMonsters(String[] array, PocketMonster[] monsters, int numTrainers) {
        int position = 3; //Starts on the first line of pocket monster data
        int num = 0; //Used to fill in the array
        for (int x = 0; x < numTrainers; x++) { //Repeats adding the monsters for each trainer
            for (int i = 0; i < 3; i++) { //3 monsters per trainer
                //Creates new monster with the name from the file
                monsters[num] = new PocketMonster(array[position]);
                //Sets the rest of the monster's data
                monsters[num].setType(array[position + 1]);
                monsters[num].setHeight(Double.parseDouble(array[position + 2]));
                monsters[num].setWeight(Double.parseDouble(array[position + 3]));
                monsters[num].setHp(Double.parseDouble(array[position + 4]));
                monsters[num].setXp(Double.parseDouble(array[position + 5]));
                monsters[num].setLevel(Integer.parseInt(array[position + 6]));
                num++;
                position += 7; //Move on to next pocket monster
            }
            position += 2; //Skip the irrelevant Trainer information lines
        }

    }

    /**
     * Adds abilities to the given PocketMonsters array based on type, with the
     * abilities specified by the given String array of abilities
     *
     * @param monsters array of PocketMonster objects
     * @param abilities String array of types with 4 abilities after each
     */
    public static void addAbilities(PocketMonster monsters[], String[] abilities) {
        for (int i = 0; i < monsters.length; i++) { //Repeats until the end of the PocketMonster array
            for (int j = 0; j < abilities.length; j++) { //Repeats until the end of the String array
                if (monsters[i].getType().equals(abilities[j])) {
                    //If the type of the monster matches the line in the data file
                    //Add all 4 of the related abilities
                    monsters[i].setAbility(1, abilities[j]);
                    monsters[i].setAbility(2, abilities[j + 1]);
                    monsters[i].setAbility(3, abilities[j + 2]);
                    monsters[i].setAbility(4, abilities[j + 3]);
                }
            } //End abilities loop
        } //End monsters loop
    }

    /**
     * Assigns the correct pocket monsters to the trainer objects
     *
     * @param monsters array of PocketMonster objects to be assigned to the
     * trainers
     * @param trainers array of Trainer objects into which monsters will be
     * added
     */
    public static void assignMonsters(Trainer[] trainers, PocketMonster[] monsters) {
        int position = 0; //used to assign the correct pocket monsters to the trainers
        for (int i = 0; i < trainers.length; i++) { //Repeats until all trainers have their monsters assigned
            //Assigns all three monsters to the trainer
            trainers[i].setP(monsters[position], monsters[position + 1], monsters[position + 2]);
            position += 3; //move on to next set of 3 pocket monsters for the next trainer
        }//End loop
    }

    /**
     * Retrieves a formatted list containing names of the trainers
     *
     * @param trainers array of Trainer objects from which the name is retrieved
     * @return String of a formatted list of names
     */
    public static String getTrainerNames(Trainer[] trainers) {
        //set-up String to contain the list of trainers and initialize to blank
        String message = "";
        //Repeat until every trainer name is read
        for (int i = 0; i < trainers.length; i++) {
            //Adds to the message string
            message += "\n- " + trainers[i].getName();
        }
        return message;
    }

    /**
     * Displays an error message to the user regarding invalid input
     */
    public static void invalidInput() {
        JOptionPane.showMessageDialog(null, "That isn't an option! Please try again.",
                "Pocket Monsters! (Austin Van Braeckel)", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Makes a message dialog box pop-up with the specified message and type
     * @param s String of the desired message
     * @param type integer of type of JOptionPane style
     */
    public static void message(String s, int type) {
        JOptionPane.showMessageDialog(null, s, "Pocket Monsters! (Austin Van Braeckel)", type);
    }

    /**
     * Makes an input dialog box pop-up with the specified message and type, retrieving the user input
     * @param s String of the desired message
     * @param type integer of type of JOptionPane style
     * @return String of the user input
     */
    public static String input(String s, int type) {
        String input = JOptionPane.showInputDialog(null, s, "Pocket Monsters! (Austin Van Braeckel)", type);
        return input;
    }

    /**
     * Determines if a String is an integer, and returns true or false
     * 
     * @param s String that is to be tested
     * @return boolean of true or false (integer or non-integer)
     */
    public static boolean isInt(String s){
        //set-up variable to indicate if the number is numeric
        boolean numeric = false;
        try { //try to parse to integer
            int num = Integer.parseInt(s);
            //if it succeeds, it is numeric
            numeric = true;
        } catch (Exception e) { //not numeric
            numeric = false;
        }
        return numeric;
    }
    
    public static void main(String[] args) {

        //Set-up all Trainers and Pocket Monsters
        //Load String arrays of all abilities, and trainers and pocket monsters
        String abilities[] = new String[NumRecords("abilities.txt")];
        FileToArray("abilities.txt", abilities);

        //Load String array of all data of trainers and their pocket monsters
        String kanto[] = new String[NumRecords("kanto.txt")];
        FileToArray("kanto.txt", kanto);

        //Load trainers into an array
        int numTrainers = Integer.parseInt(kanto[0]);
        Trainer trainers[] = new Trainer[numTrainers];
        LoadTrainers(kanto, trainers, numTrainers);

        //Load pocket monsters into an array
        PocketMonster monsters[] = new PocketMonster[numTrainers * 3];
        LoadMonsters(kanto, monsters, numTrainers);

        //Add the abilities to the pocket monsters depending on their type
        addAbilities(monsters, abilities);

        //All monsters are in an array, and all trainers are in an array
        //Assign the pocket monsters to the trainers
        assignMonsters(trainers, monsters);

        //All trainers and pocket monsters are set-up
        //Now begin game loop(s)
        //Greeting message
        message("Welcome to Kanto!", JOptionPane.INFORMATION_MESSAGE);

        //Set-up variable for user input, and to specify which trainer is chosen at the beginning
        String input = "";
        String name = ""; //trainer name
        //pocket monsters of the trainer
        PocketMonster monster1;
        PocketMonster monster2;
        PocketMonster monster3;
        //Set-up variable to specify which trainer the user selects
        int trainerNum = 0;
        //Set-up String to contain the list of trainer names
        String trainerNames = "";
        //Set-up boolean to indicate the validity of the user's selected trainer
        boolean validName = false;

        while (!input.equalsIgnoreCase("done")) { //repeats until user types "done"
            /*Ask user which trainer they'd like to speak with
             calls the getTrainerNames method to create a list of the possible options*/
            input = input("Who would you like to speak with?" + getTrainerNames(trainers)
                    + "\n- Done (to quit)",
                    JOptionPane.QUESTION_MESSAGE);

            //set trainer number as default
            trainerNum = 0;

            //Check what the user typed
            if (input.equalsIgnoreCase("done")) { //user wants to quit
                //Set-up string to store user input for the confirmation of quitting
                String confirmation = "";
                while (!confirmation.equalsIgnoreCase("yes") && !confirmation.equalsIgnoreCase("no")) {
                    //Asks for confirmation for quitting
                    confirmation = input("Are you sure that you want to quit?", JOptionPane.WARNING_MESSAGE);
                    if (confirmation.equalsIgnoreCase("no")) { //Changed their mind about quitting
                        input = ""; //Resets input to default so the initial menu will loop again
                        trainerNum = 0;
                    } else {
                        //Close program
                        System.exit(0);
                    }
                } //end confirmation while loop
            } else {
                //user MAY have chosen valid trainer, so move on to next menu loop
                //First identify which trainer was selected

                //set boolean used for validity to default
                validName = false;
                
                //set-up counter for validity of the input for the while loop
                int counter = 0;
                    while (counter < trainers.length) {
                        if (input.equalsIgnoreCase(trainers[counter].getName())) { //matches
                            trainerNum = counter; //sets the new trainer number
                            counter = trainers.length; //end loop
                            validName = true; //Indicates it as a valid name
                        } else { //Does not match
                            counter++;
                            if (counter >= trainers.length) {
                                //No match has been found (invalid input)
                                invalidInput(); //display error message
                            }
                        }
                    }

            }//End else/if

            if (validName) {

                //stores the name of the trainer in a string for easy access
                name = trainers[trainerNum].getName();
                //Stores the pocket monsters for easy access
                monster1 = trainers[trainerNum].getP1();
                monster2 = trainers[trainerNum].getP2();
                monster3 = trainers[trainerNum].getP3();

                /*Next menu loop
                 ____________________________________________________________*/
                //reset user's input to be used in the loop
                input = "";
                //Repeats until user types "back"
                while (!input.equalsIgnoreCase("back")) {
                    /*
                     - Displays to the user the selected trainer's name, and the names of 
                     their 3 pocket monsters
                     - Also prompts them to make an action
                     */
                    input = input(trainers[trainerNum].getName()
                            + " holds the following:"
                            + "\n- " + (monster1).getName()
                            + "\n- " + (monster2).getName()
                            + "\n- " + (monster3).getName()
                            + "\n\nWhat would you like to do?"
                            + "\n- Walk (in the grass - you might find a wild pocket monster!)"
                            + "\n- Release (one of the pocket monsters in " + name + "'s care)"
                            + "\n- Examine (one of the pocket monsters above)"
                            + "\n- Train (against a training-dummy)"
                            + "\n- Back (to the previous menu)",
                            JOptionPane.QUESTION_MESSAGE);

                    if (input.equalsIgnoreCase("walk")) { //walk in the grass
                        //Walks in the grass
                        message(name + " wanders around in the grass for a bit...", JOptionPane.INFORMATION_MESSAGE);
                        //randomly generates an integer from 1 to 3 (inclusive)
                        int rNum = (int) ((Math.random() * 3) + 1);
                        //boolean to indicate when a monster has been randomly generated
                        boolean validMonster = false;
                        //set-up integer to store the random number used to generate a random monster
                        int randomMonster = 0;
                        //probability of encountering a pocket monster in the grass is 2/3
                        if (rNum == 1 || rNum == 2) { //encounter
                            //Repeats until a random, and valid, monster is generated
                            while (!validMonster) {
                                //generates random number between 0 and the number of monsters in the monster array
                                randomMonster = (int) (Math.random() * (monsters.length) + 0);
                                if (!monsters[randomMonster].getName().equals("empty")) {
                                    //if it selects a monster that isn't an empty slot, it is valid
                                    validMonster = true;
                                }
                            }//End monster-validity loop

                            //Store a random monster of the same species in a pocket monster object to be used if caught
                            //Create new pocket monster
                            PocketMonster wildMonster = new PocketMonster(monsters[randomMonster]);
                            //randomize stats (not name, type, or abilities)
                            wildMonster.randomizeData();

                            String catchOrIgnore = "";
                            while (!catchOrIgnore.equalsIgnoreCase("ignore")) {
                                catchOrIgnore = input(name + " encountered a " + wildMonster.getName()
                                        + ", the " + wildMonster.getType() + " type pocket monster."
                                        + " What would you like to do?"
                                        + "\n- Catch (chance of failure)"
                                        + "\n- Ignore", JOptionPane.QUESTION_MESSAGE);
                                if (catchOrIgnore.equalsIgnoreCase("catch")) {//attempt to catch it
                                    //displays message to simulate throwing a capture-ball
                                    message("You throw a capture-ball at the wild " + wildMonster.getName() + "!", JOptionPane.INFORMATION_MESSAGE);
                                    //Determines if it is caught or not
                                    if (trainers[trainerNum].catchMonster(wildMonster)) {
                                        //returns true, meaning it is caught
                                        //display congratulatory message
                                        message("Gotcha! " + wildMonster.getName() + " was caught!", JOptionPane.INFORMATION_MESSAGE);
                                        catchOrIgnore = "ignore"; //ends the loop
                                    } else if (!monster1.getName().equals("empty")
                                            && !monster2.getName().equals("empty")
                                            && !monster3.getName().equals("empty")) { //no empty slots available
                                        //Display error message
                                        message(name + " doesn't have any space! At least one pocket monster must be released."
                                                + " pocket monster before you can catch another.", JOptionPane.ERROR_MESSAGE);
                                        //display second message saying it got away
                                        message("The wild " + wildMonster.getName() + " got away...", JOptionPane.ERROR_MESSAGE);
                                        catchOrIgnore = "ignore"; //ends the loop
                                    } else { //it got away by chance
                                        message("Oh no! " + wildMonster.getName() + " got away!", JOptionPane.WARNING_MESSAGE);
                                        catchOrIgnore = "ignore"; //ends the loop
                                    }//end CatchMonster if/else
                                } else if (!catchOrIgnore.equalsIgnoreCase("ignore")) {
                                    //invalid input
                                    invalidInput();
                                } //otherwise, it is 'ignore"
                            }//End catch-or-ignore loop

                        } else { //no encounter
                            message("Nothing happened.", JOptionPane.INFORMATION_MESSAGE);
                        }

                        //resets the easy-access monster variables
                        monster1 = trainers[trainerNum].getP1();
                        monster2 = trainers[trainerNum].getP2();
                        monster3 = trainers[trainerNum].getP3();

                    } else if (input.equalsIgnoreCase("release")) { //release pocket monster(s)
                        //set-up variable to store user input
                        String release = "";
                        //set-up variable to indicate whihc monster is to be release
                        int slotNum = 0;
                        //repeats until user types "back"
                        while (!release.equalsIgnoreCase("back")) {
                            //ask user for which pocket monster they'd like to release
                            release = input("Which pocket monster would you like to release?"
                                    + "\n- " + monster1.getName()
                                    + "\n- " + monster2.getName()
                                    + "\n- " + monster3.getName()
                                    + "\n- Back (to previous menu)", JOptionPane.QUESTION_MESSAGE);

                            //Set-up string for user input
                            String confirmation = "";
                            //set-up boolean to loop until valid answer is given
                            boolean valid = false;
                            if (!release.equalsIgnoreCase("back")) {
                                //If the user typed something other than "back", ask if they're sure
                                while (!valid) {
                                    confirmation = input("You want to release \"" + release + "\"?", JOptionPane.WARNING_MESSAGE);
                                    if (confirmation.equalsIgnoreCase("yes")) {
                                        valid = true; //end loop
                                        //release method returns true (vailid monster name), but only valid if the monster has a name other than "empty"
                                        //("empty" is the name given to monsters that have been released, or haven't been caught yet)
                                        if (trainers[trainerNum].release(release) && !release.equalsIgnoreCase("empty")) { 
                                            message("Say goodbye to \"" + release + "\"!", JOptionPane.INFORMATION_MESSAGE);
                                            //reset easy-access variables of monsters
                                            monster1 = new PocketMonster(trainers[trainerNum].getP1());
                                            monster2 = new PocketMonster(trainers[trainerNum].getP2());
                                            monster3 = new PocketMonster(trainers[trainerNum].getP3());
                                        } else { //returns false
                                            if (!release.equalsIgnoreCase("back")) { //invalid input
                                                //displays alternate error message if "empty" is typed
                                                if(release.equalsIgnoreCase("empty")){
                                                    message("You can't release a monster from an empty slot!", JOptionPane.ERROR_MESSAGE);
                                                } else { //otherwise, invalid input
                                                    //displays error message if not "back"
                                                    invalidInput();
                                                }
                                            } //Otherwise, user chose to go back to previous menu
                                        }//End release if/else
                                    } else if (confirmation.equalsIgnoreCase("no")) {
                                        valid = true; //end loop
                                    } else { //invalid input
                                        //display error message
                                        invalidInput();
                                    }

                                }
                            }
                        }

                    } else if (input.equalsIgnoreCase("examine")) { //examine one of the pocket monsters
                        //Set-up string to store user input, and string to contain data to be displayed
                        String monster = "";
                        String data = "";
                        while (!monster.equalsIgnoreCase("back")) { //repeats until "back" is typed
                            //Asks user for which monster they wish to examine
                            monster = input("Which of " + name + "'s "
                                    + "pocket monsters would you like to examine?"
                                    + "\n- " + monster1.getName()
                                    + "\n- " + monster2.getName()
                                    + "\n- " + monster3.getName()
                                    + "\n- Back (to the previous menu)",
                                    JOptionPane.INFORMATION_MESSAGE);
                            //Identifies the user's choice
                            if(monster.equalsIgnoreCase("empty")){
                                //Different error message than invalid input
                                message("There is no pocket monster to examine!", JOptionPane.ERROR_MESSAGE);
                                //sets the data variable to default so it doesn't display the previous data again
                                data = "";
                            } else if (monster.equalsIgnoreCase(monster1.getName())) { //first monster
                                //Stores the monster's data in the string
                                data = monster1.toString();
                            } else if (monster.equalsIgnoreCase(monster2.getName())) { //second monster
                                //Stores the monster's data in the string
                                data = monster2.toString();
                            } else if (monster.equalsIgnoreCase(monster3.getName())) { //third monster
                                //Stores the monster's data in the string
                                data = monster3.toString();
                            } else {
                                if (!monster.equalsIgnoreCase("back") || monster.equalsIgnoreCase("empty")) { //invalid input if not "back"
                                     //invalid input
                                        //Displays error message
                                        invalidInput();
                                        //sets the data variable to default so it doesn't display the previous data again
                                        data = "";
                                } else { //otherwise, user chose to go back to the previous menu
                                    //sets the data variable to default so it doesn't display the previous data again
                                    data = "";
                                }

                            } //End else-if

                            //Checks to see if a valid choice was made
                            if (!data.equals("")) {
                                //if the data string has been filled with data, display data
                                message(data, JOptionPane.INFORMATION_MESSAGE);
                            } //otherwise, an invalid choice (or "back") was sekected
                        }//End examine-loop

                    } else if (input.equalsIgnoreCase("train")) { //attack training-dummy with abilities
                        //set-up String to indicate which monster is to be used in training
                        String trainingMonster = "";
                        //creates a new monster object to reference the selected monster
                        PocketMonster trainingMon = new PocketMonster(""); //initialize
                        //repeats until user types "back"
                        while(!trainingMonster.equalsIgnoreCase("back")){
                            //set-up boolean to indicate a proper monster has been selected
                            boolean validMonster = false;
                            //ask user which monster they'd like to use
                            trainingMonster = input("Which pocket monster would you like to use?" +
                                    "\n- " + monster1.getName() +
                                    "\n- " + monster2.getName() +
                                    "\n- " + monster3.getName() +
                                    "\n- Back (to previous menu)", JOptionPane.QUESTION_MESSAGE);
                            if(trainingMonster.equalsIgnoreCase(monster1.getName())){ //first monster
                                trainingMon = new PocketMonster(monster1);
                                //valid monster
                                validMonster = true;
                            } else if (trainingMonster.equalsIgnoreCase(monster2.getName())){ //second monster
                                trainingMon = new PocketMonster(monster2);
                                //valid monster
                                validMonster = true;
                            } else if (trainingMonster.equalsIgnoreCase(monster3.getName())){ //third monster
                                trainingMon = new PocketMonster(monster3);
                                //valid monster
                                validMonster = true;
                            } else {
                                if (!trainingMonster.equalsIgnoreCase("back")){
                                    //invalid input if not "back"
                                    invalidInput();
                                } //otherwise, user chose to go back to previous menu
                            }//end else-if
                            
                            //goes into the training field if "back" was not typed
                            if (validMonster){
                                //set-up string for user input
                                String trainingInput = "";
                                //repeats until user types "back"
                                while(!trainingInput.equals("back")){
                                    //asks user for an integer specifying which ability to use
                                    trainingInput = input("Select an ability to use on the training dummy. (\"1\", \"2\", \"3\", or \"4\")" +
                                            "\n1. " + trainingMon.getAbility(0) +
                                            "\n2. " + trainingMon.getAbility(1) +
                                            "\n3. " + trainingMon.getAbility(2) +
                                            "\n4. " + trainingMon.getAbility(3) +
                                            "\n- Back (to previous menu)", JOptionPane.INFORMATION_MESSAGE);
                                    
                                    if(isInt(trainingInput) && !trainingInput.equalsIgnoreCase("empty")){ //input is an integer, and is not "empty"
                                        //check if it is between 1 and 4
                                        int abilityNum = Integer.parseInt(trainingInput);
                                        if(abilityNum >= 1 && abilityNum <= 4){ //valid number
                                            //activate ability and display message
                                            message(trainingMon.activateAbility(abilityNum) + " against the dummy!", JOptionPane.INFORMATION_MESSAGE);
                                            //determines if it hits or misses the target (4/5 chance to hit)
                                            int hitNum = (int)(Math.random() * 5 + 1); //random number between 1 and 5
                                            if (hitNum == 2){//misses target
                                                //displays message
                                                message("Oh no! It missed!", JOptionPane.ERROR_MESSAGE);
                                            } else {//hits target
                                                //determine if it is a critical hit or not (1/4 chance to get critical hit)
                                                int critNum = (int)(Math.random() * 4 + 1); //random number between 1 and 4
                                                if (critNum != 4){ //regular hit
                                                   //display message
                                                   message("It hit!", JOptionPane.INFORMATION_MESSAGE);
                                                } else { //critical hit
                                                    //display message
                                                    message("Critical hit! The dummy was hit so hard, that its head popped-off! " + 
                                                            name + " walks over and replaces it with a new one.", JOptionPane.INFORMATION_MESSAGE);
                                                }//end crit if
                                            }//end hit if
                                        } else { //invalid input
                                            invalidInput();
                                        }
                                    } else { //input is not an integer
                                     if(trainingInput.equalsIgnoreCase("empty")){
                                         //seperate error message
                                         message("You can't choose to train a pocket monster an empty slot!", JOptionPane.INFORMATION_MESSAGE);
                                    }else if(!trainingInput.equalsIgnoreCase("back")){ //invalid input if not "back"
                                            invalidInput();
                                        }//otherwise, user chose to go back
                                    }
                                    
                                }
                            }
                            
                        } // end training loop
                    } else {
                        if (!input.equalsIgnoreCase("back")) {
                            //invalid input if it is not "back"
                            invalidInput();
                        }//otherwise, user will go back to previous menu
                    }//End else-if

                }// End action-menu loop

            }//End trainer selection loop
        }//END GAME LOOP
    }

}
