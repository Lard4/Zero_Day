import java.util.*;

public class Parser {
    private CommandWords commands;
    private Scanner reader;
    private Directory directory;
    
    private boolean hasName;
    private String name = "unknown";

    public Parser() {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }
    
    public String showCommands() {
        return commands.showAll();
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void addCommand(String command) {
        commands.addCommands(command);
    }

    public Command getCommand(boolean root) {
        String inputLine;
        String word1 = null;
        String word2 = null;
        String word3 = null;
        String word4 = null;

        if (!root) {
            System.out.print(name + "@anonymous: " + "$ ");
        }
        else {
            System.out.print(name + "@anonymous: " + "# ");
        }

        inputLine = reader.nextLine();
        
        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next();
            if(tokenizer.hasNext()) {
                word2 = tokenizer.next();
                if(tokenizer.hasNext()) {
                    word3 = tokenizer.next();
                    if(tokenizer.hasNext()) {
                        word4 = tokenizer.next();
                    }
                }
            }
        }

        if(commands.isCommand(word1)) {
            return new Command(word1, word2, word3, word4);
        }
        else {
            return new Command(null, word2, word3, word4); 
        }
    }
}
