/*
 * Kevin Dixson
 * 12-9-15
 */
import java.util.*;
import java.io.*;
import java.awt.Color; 
import enigma.console.*;
import enigma.core.Enigma;

public class Game {
    private Parser parser;
    private Directory currentDirectory;
    private Tool airfrack, spooftooth, bt_snoop;
    private enigma.console.Console s_console;
    private Random rand = new Random();
    
    private boolean isRoot = false;
    private boolean userIsNew = true;
    private boolean cdAble = true;
    private String rootPassword = "abc";
    private String secondLevel = null;
    private int fileLevel = -1;
    private int[][] workingDirectory = new int[4][4];
    
    private static final long standardDelay = 1; //80ms
    private static final long shortDelay = 1; //50ms
    private static final long longDelay = 1; //110ms
    
    public Game() {
        // Enigma Console garb
        s_console = Enigma.getConsole("Anonymous Console");
        TextAttributes attrs = new TextAttributes(Color.WHITE);
        s_console.setTextAttributes(attrs);
        
        createStructure();
        parser = new Parser();
    }

    private void createStructure() {
        Directory root;
        root = new Directory("/");
        currentDirectory = root;
    }

    public static void main(String[] args) {
        Game here = new Game();
        here.start();
    }
    
    private void start() {
        printWelcome();
        
        for (int row = 0; row < workingDirectory.length; row++) {
            for (int col = 0; col < workingDirectory[row].length; col++) {
                workingDirectory[row][col] = 0;
            }
        }
        
        workingDirectory[0][0] = 314;
        
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand(isRoot);
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }
    
    private void printPath() {
        System.out.print(currentDirectory.getPath());
        System.out.println();
    }

    private void printWelcome() {
        System.out.println();
        try {
            printWithDelays("We are Anonymous", standardDelay);
            Thread.sleep(1); //750
            printWithDelays("We are a legion", standardDelay);
            Thread.sleep(1); //750
            printWithDelays("We do not forgive", standardDelay);
            Thread.sleep(1); //750
            printWithDelays("We do not forget", standardDelay);
            Thread.sleep(1); //750
            printWithDelays("Expect us.", longDelay);
            System.out.println();
            Thread.sleep(1); //2000
            
            printWithDelays("Welcome to your Linux shell environment.", standardDelay);
            Thread.sleep(1); //1000
            printWithDelays("This is where you will spend the entirety of your time hacking.", standardDelay);
            System.out.println();
            Thread.sleep(1); //2000
            
            printWithDelays("Recently, Barack Obama has threatened to declare war on ISIS.", standardDelay);
            printWithDelays("Little does he know that ISIS has formed a pact with the following countries:", standardDelay);
            printWithDelays("   Syria", longDelay);
            printWithDelays("   Lebanon", longDelay);
            printWithDelays("   Jordan", longDelay);
            printWithDelays("   Egypt", longDelay);
            printWithDelays("   Iraq", longDelay);
            printWithDelays("   Yemen", longDelay);
            printWithDelays("   Afghanistan", longDelay);
            System.out.println();
            Thread.sleep(1); //2000
            
            printWithDelays("As you can see, if Obama declares war on ISIS,", standardDelay);
            printWithDelays("he will therefore go to war on all 7 nations.", standardDelay);
            printWithDelays("If this happens, it's only a matter of time before", standardDelay);
            printWithDelays("the US turns into nothing more than a radioactive crater.", standardDelay);
            System.out.println();
            Thread.sleep(1); //2000
            
            printWithDelays("Over the past few months you have came up with a master plan of", standardDelay);
            printWithDelays("how to take down ISIS.", standardDelay);
            printWithDelays("Keeping in mind that anonymity is of the utmost priority, you must...", standardDelay);
            printWithDelays("   1) Set up a secure environment", standardDelay);
            printWithDelays("   2) Install hacking tools as you find them", standardDelay);
            printWithDelays("   3) Take out ISIS's primary funding sources", standardDelay);
            printWithDelays("   4) Deliver the final blow to ISIS", standardDelay);
            System.out.println();
            Thread.sleep(1); //2000
            
            printWithDelays("With that in mind, I wish you good luck.", standardDelay);
            printWithDelays("We're all counting on you.", 1); //200
            System.out.println();
            System.out.println("------------------------------------------------------------------------");
            System.out.println();
            
            printWithDelays("Hello. My name is Roger Dingledine. I am the leader of Anonymous.", standardDelay);
            printWithDelays("I am here to assist you through Zero Day.", standardDelay);
            printWithDelays("Let's start with the basic Linux shell commands.", standardDelay);
            Thread.sleep(1); //2000
            System.out.println();
            
            printWithDelays("To change to a different folder (referred to as directories) use 'cd'", standardDelay);
            printWithDelays("Use cd followed by a directory to make that your working directory.", standardDelay);
            Thread.sleep(1); //1000
            System.out.println();
            
            printWithDelays("To see what files and directories are beneath you, use 'ls'", standardDelay);
            printWithDelays("Files will be printed in blue and directories will be printed in green", standardDelay);
            printWithDelays("ls will list everything that you can cd into or open.", standardDelay);
            Thread.sleep(1); //1000
            System.out.println();
            
            printWithDelays("Speaking of open... You can use the command 'open' to open files", standardDelay);
            printWithDelays("Use open followed by the text file name.", standardDelay);
            Thread.sleep(1); //2000
            System.out.println();
            
            printWithDelays("Your first mission is to become the root user.", standardDelay);
            printWithDelays("Use the command 'sudo su' to become root.", standardDelay);
            printWithDelays("You may have to explore the file system to find the password.", standardDelay);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println();
        printPath();
        System.out.println();
    }
    
    public String executeCommand(String command) {
        StringBuffer output = new StringBuffer();
        try {
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";           
            while ((line = reader.readLine())!= null) {
                output.append(line + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("cd")) {
            cdTo(command);
        }
        else if (commandWord.equals("ls")) {
            printLS();
        }
        else if (commandWord.equals("sudo")) {
            if (command.hasSecondWord() != false) {
                if (command.getSecondWord().equals("su")) {
                    enterPassword();
                }
            }
            else  {
                System.out.println("What's the point in being root for only one command?");
            }
        }
        else if (commandWord.equals("exit")) {
            isRoot = false;
            System.out.println();
        }
        else if (commandWord.equals("open")) {
            if (command.hasSecondWord() != false) {
                if ((command.getSecondWord().equals("anon_root_pswd.txt")) && (checkDirectory(3, 0))) {
                    final String alph = "abcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+{}|[];':./>?,<~`";
                    final int nAlph = alph.length();
                    for (int x = 0; x <= 7; x++) {
                        rootPassword += (alph.charAt(rand.nextInt(nAlph)));
                    }
                    parser.addCommand(rootPassword);
                    System.out.println();
                    System.out.print("root password: " + rootPassword);
                    System.out.println("\n");
                }
                else {
                    System.out.println(command.getSecondWord() + " is not a file");
                }
            }
        }
        else if (commandWord.equals("install")) {
            if (isRoot) {
                if (command.hasSecondWord() != false) {
                    switch (command.getSecondWord()) {
                        case "airfrack":
                            installTool("airfrack");
                            System.out.println("airfrack successfully installed!");
                            break;
                            
                        case "bt_snoop":
                            installTool("bt_snoop");
                            System.out.println("bt_snoop successfully installed!");
                            break;
                            
                        case "spooftooth":
                            installTool("spooftooth");
                            System.out.println("spooftooth successfully installed!");
                            break;
                            
                        default:
                            System.out.println("package " + command.getSecondWord() + " does not exist");
                            break;
                    }
                    System.out.println();
                }
            }
            else {
                System.out.println("You must be root to install tools.");
                System.out.println();
            }
        }
        else if (commandWord.equals("airfrack")) {
            if (command.hasSecondWord()) {
                System.out.println("airfrack has not been programmed yet.");
            } else {
                System.out.println(airfrack.getHelp());
            }
            System.out.println();
        }
        else if (commandWord.equals("bt_snoop")) {
            if (command.hasSecondWord()) {
                System.out.println("bt_snoop has not been programmed yet.");
            } else {
                System.out.println(bt_snoop.getHelp());
            }
            System.out.println();
        }
        else if (commandWord.equals("spooftooth")) {
            if (command.hasSecondWord()) {
                System.out.println("spooftooth has not been programmed yet.");
            } else {
                System.out.println(spooftooth.getHelp());
            }
            System.out.println();
        }
        else if ((!rootPassword.equals("abc")) && (commandWord.equals(rootPassword))) {
            System.out.println("I don't know what you mean...");
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }

        return wantToQuit;
    }
    
    private void enterPassword() {
        boolean passwording = true;
        
        while (passwording) {
            System.out.println("[sudo] root password: ");
            Command command = parser.getCommand(false);
            String password = command.getCommandWord();
            
            if ((password != null) && (password.equals(rootPassword))) {
                passwording = false;
                isRoot = true;
                fileLevel = 0;
                currentDirectory.setValidDirectories(true);
            } else if ((password != null) && (password.equals("exit"))) {
                passwording = false;
            } else {
                System.out.println("Error. Please try again. Or type exit if you don't know the password.");
            }
        }
    }
    
    private boolean checkDirectory(int row, int col) {
        if (workingDirectory[row][col] == 314) {
            return true;
        } else {
            return false;
        }
    }
    
    private void printLS() {
        printPath();
        if (checkDirectory(0,0)) {
            TextAttributes attrs = new TextAttributes(Color.GREEN);
            s_console.setTextAttributes(attrs);
            if (isRoot) {
                System.out.println("etc" + "\t" + "desktop" + "\t" + "music" + "\t" + "pictures");
            } else {
                System.out.println("desktop" + "\t" + "music" + "\t" + "pictures");
            }
        } else if (checkDirectory(1,0)) {
            TextAttributes attrs = new TextAttributes(Color.GREEN);
            s_console.setTextAttributes(attrs);
            System.out.println("backgrounds");
        } else if (checkDirectory(2,0)) {
            TextAttributes attrs = new TextAttributes(Color.GREEN);
            s_console.setTextAttributes(attrs);
            System.out.println("beyonce");
        } else if (checkDirectory(3,0)) {
            TextAttributes attrs = new TextAttributes(Color.BLUE);
            s_console.setTextAttributes(attrs);
            System.out.println("anon_root_pswd.txt");
        }
        // Back to white!
        TextAttributes attrs = new TextAttributes(Color.WHITE);
        s_console.setTextAttributes(attrs);
        // New line
        System.out.println();
    }

    private void installTool(String sTool) {
        switch (sTool) {
            case "airfrack":
                airfrack = new Tool("use of airfrack-ng:" + "\n" +
                        "--crack [WiFi Name]" + "\t" + "crack a router's password" + "\n" +
                        "--connect [WiFi Name]" + "\t" + "connect to a WiFi network");
                parser.addCommand("airfrack");
                currentDirectory.addTool(airfrack);
                break;
                
            case "bt_snoop":
                bt_snoop = new Tool("use of bt_snoop:" + "\n" +
                        "--snoop" + "\t" + "find MAC adresses of bluetooth devices");
                parser.addCommand("bt_snoop");
                currentDirectory.addTool(bt_snoop);
                break;
                
            case "spooftooth":
                spooftooth = new Tool("use of spooftooth:" + "\n" +
                        "--seize [Bluetooth MAC]" + "\t" + "connect to a bluetooth device");
                parser.addCommand("spooftooth");
                currentDirectory.addTool(spooftooth);
                break;
        }
    }

    private void printHelp() {
        System.out.println("The following Unix commands have been installed:");
        System.out.println("    " + parser.showCommands());
    }
    
    private void cdTo(Command command) {
        if(!command.hasSecondWord()) {
            currentDirectory.setPath("", false);
            workingDirectory[0][0] = 314;
            cdAble = true;
            printPath();
            System.out.println();
            return;
        }

        String newDirectory = command.getSecondWord();
        
        if (userIsNew) {
            workingDirectory = currentDirectory.getDirectoryLocation(newDirectory);
            userIsNew = false;
        } else {
            if ((cdAble) && (!newDirectory.equals(".."))) {
                workingDirectory = currentDirectory.getDirectoryLocation(newDirectory);
            } else if (newDirectory.equals("..")) {
                cdAble = true;
            } else {
                System.out.println(newDirectory + " is not a file or directory");
                return;
            }
        }
        
        switch (newDirectory) {
            case "root":
                cdAble = true;
                fileLevel = 0;
                break;
                
            case "pictures":
                cdAble = true;
                fileLevel = 1;
                break;
                
            case "music":
                cdAble = true;
                fileLevel = 2;
                break;
                
            case "desktop":
                cdAble = true;
                fileLevel = 3;
                break;
                
            case "..":
                cdAble = true;
                break;
                
            default:
                 cdAble = false;
                 break;
        }
        
        if (currentDirectory.directoryExists(newDirectory, fileLevel)) {
            if (currentDirectory.directoryIsParent(newDirectory)) {
                currentDirectory.setPath(newDirectory, false);
            } else {
                currentDirectory.setPath(newDirectory, true);
            }
            if (newDirectory.equals("etc")) {
                secondLevel = "etc";
            } else {
                secondLevel = null;
            }
            printPath();
            System.out.println();
        } else {
            if (newDirectory.equals("..")) {
                workingDirectory = currentDirectory.goBack(workingDirectory, fileLevel);
                printPath();
            } else {
                System.out.println(newDirectory + " is not a file or directory");
            }
        }
        // Refresh cdAble boolean
        cdAble = true;
    }
    
    private void printWithDelays(String data, long delay) throws InterruptedException {
        for (char ch : data.toCharArray()) {
            System.out.print(ch);
            Thread.sleep(delay);
        }
        System.out.println();
    }

    private boolean quit(Command command) {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
