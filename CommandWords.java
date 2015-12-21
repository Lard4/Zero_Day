import java.util.*;

public class CommandWords {
    // a constant array that holds all valid command words
    private HashMap<Integer, String> validCommands = new HashMap<>();
    private static final int MAX_COMMANDS = 100;
    //8998542
    
    public CommandWords() {
        validCommands.put(0, "cd");
        validCommands.put(1, "quit");
        validCommands.put(2, "help");
        validCommands.put(3, "ls");
        validCommands.put(4, "sudo");
        validCommands.put(5, "install");
        validCommands.put(6, "open");
        validCommands.put(7, "exit");
    }
    
    public void addCommands(String newCommand) {
        boolean found = false;
        for (int x = 0; x <= validCommands.size(); x++) {
            if ((!validCommands.containsKey(x)) && (!found)){
                validCommands.put(x, newCommand);
                found = true;
            }
        }
    }
   
    public String showAll() {
        String listOfCommands = "";
        for (int x = 0; x < validCommands.size(); x++) {
            listOfCommands += " " + validCommands.get(x);
        }
        return listOfCommands;
    }

    public boolean isCommand(String aString) {
        for (int x = 0; x < validCommands.size(); x++) {
            if (validCommands.get(x).equals(aString)) {
                return true;
            }
        }
        return false;
    }
}
