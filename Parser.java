import java.util.Scanner;

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
    
    public void setName() {
        name = reader.nextLine();
    }
    
    public void addCommand(String command) {
        commands.addCommands(command);
    }

    public Command getCommand(boolean root) {
        String inputLine;
        String word1 = null;
        String word2 = null;

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
            }
        }

        if(commands.isCommand(word1)) {
            return new Command(word1, word2);
        }
        else {
            return new Command(null, word2); 
        }
    }
}
