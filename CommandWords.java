import java.util.*;

public class CommandWords {
    private HashMap<Integer, String> validCommands = new HashMap<>();
    private static final int MAX_COMMANDS = 100;
    // 8998542
    
    public CommandWords() {
        validCommands.put(0, "cd");
        validCommands.put(1, "quit");
        validCommands.put(2, "help");
        validCommands.put(3, "ls");
        validCommands.put(4, "sudo");
        validCommands.put(5, "install");
        validCommands.put(6, "open");
        validCommands.put(7, "send");
        validCommands.put(8, "exit");
    }
    
    public void addCommands(String newCommand) {
        boolean found = false;
        for (int x = 0; x <= validCommands.size(); x++) {
            if ((!validCommands.containsKey(x)) && (!found)) {
                validCommands.put(x, newCommand);
                found = true;
            }
        }
    }
   
    public String showAll() {
        String listOfCommands = "";
        for (int x = 0; x < validCommands.size(); x++) {
            if (!isRootPassword(validCommands.get(x))) {
                if (x % 5 == 0) {
                    listOfCommands += "\n" + validCommands.get(x);
                } else {
                    listOfCommands += "\t" + "\t" + validCommands.get(x);
                }
            }
        }
        return listOfCommands;
    }
    
    public boolean isRootPassword(String key) {
        char a, b, c;
        if (key.length() >= 3) {
            a = key.charAt(0);
            b = key.charAt(1);
            c = key.charAt(2);
        } else {
            return false;
        }
        
        if ((a == 'a') && (b == 'b') && (c == 'c')) {
            return true;
        } else {
            return false;
        }
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
