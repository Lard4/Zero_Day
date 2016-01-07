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
    private Player player = new Player();
    private Dingledine ding = new Dingledine();
    private Directory currentDirectory;
    private Tool airfrack, spooftooth, wifi_snoop, macchanger, tor, connect;
    private enigma.console.Console s_console;
    private Random rand = new Random();
    
    private boolean isRoot = false;
    private boolean userIsNew = true;
    private boolean cdAble = true;
    private boolean changedMac = false;
    private boolean torred = false;
    private String rootPassword = "abc";
    private String secondLevel = null;
    private String wifi = null;
    private String name = "";
    private int fileLevel = -1;
    private int[][] workingDirectory = new int[4][4];

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
        //ding.intro();
        printPath();
        System.out.println();
        
        for (int row = 0; row < workingDirectory.length; row++) {
            for (int col = 0; col < workingDirectory[row].length; col++) {
                workingDirectory[row][col] = 0;
            }
        }
        
        // Pre-game neccessary operations
        workingDirectory[0][0] = 314;
        connect = new Tool("use of connect:" + "\n" +
                        "--attach [PATH_TO_SERVER_POWER]" + "\t" + "to connect to a server");
                parser.addCommand("connect");
                currentDirectory.addTool(connect);
        
        boolean finished = false;
        while (!finished) {
            finished = processCommand(parser.getCommand(isRoot));
        }
        System.out.println("Later loser.");
    }
    
    private void printPath() {
        System.out.print(currentDirectory.getPath());
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
            System.out.println("you have entered an invalid command.");
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
                    final String alph = "abcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+{}[]/>?<~";
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
                            
                        case "wifi_snoop":
                            installTool("wifi_snoop");
                            System.out.println("wifi_snoop successfully installed!");
                            break;
                            
                        case "spooftooth":
                            installTool("spooftooth");
                            System.out.println("spooftooth successfully installed!");
                            break;
                            
                        case "macchanger":
                            installTool("macchanger");
                            System.out.println("macchanger successfully installed!");
                            changedMac = true;
                            break;
                            
                        case "tor":
                            installTool("tor");
                            System.out.println("tor successfully installed!");
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
                if (command.getSecondWord().equals("--crack")) {
                    if (command.hasThirdWord()) {
                        if (command.getThirdWord().equals("Linksys")) {
                            Random rand = new Random();
                            final String alph = "abcdefghijklmnopqrstuvwxyz";
                            final int nAlph = alph.length();
                            String key = "";
                            int arrowCounter = 0;
                            for (int x = 0; x <= 100; x++) {
                                for (int z = 0; z <= 100; z++) { // Flush the screen (hacky ikr)
                                    System.out.println();
                                }
                                
                                for (int hash = 0; hash < 1000; hash++) {
                                    if (hash % 100 == 0) {
                                        System.out.println();
                                    }
                                    System.out.print("#");
                                }
                                System.out.println();
                                System.out.println();
                                
                                for (int tabs = 0; tabs < 8; tabs++) {
                                    System.out.print("\t");
                                }
                                
                                if (arrowCounter < 2) {
                                    System.out.print("airfrack -----> cracking Linksys network");
                                } else if (arrowCounter < 4) {
                                    System.out.print("airfrack ->     cracking Linksys network");
                                } else if (arrowCounter < 6) {
                                    System.out.print("airfrack -->    cracking Linksys network");
                                } else if (arrowCounter < 8) {
                                    System.out.print("airfrack --->   cracking Linksys network");
                                } else if (arrowCounter < 10) {
                                    System.out.print("airfrack ---->  cracking Linksys network");
                                    arrowCounter = -5;
                                } arrowCounter++;
                                System.out.println();
                                
                                for (int tabs = 0; tabs < 8; tabs++) {
                                    System.out.print("\t");
                                }
                                
                                Random ran = new Random();
                                System.out.print("brute force testing Linksys keys at 229" + ran.nextInt(3) + " kps");
                                System.out.println();
                                for (int y = 0; y <= 7; y++) {
                                    key += (alph.charAt(rand.nextInt(nAlph)));
                                }
                                
                                
                                for (int tabs = 0; tabs < 8; tabs++) {
                                    System.out.print("\t");
                                }
                                
                                if (x == 100) {
                                    System.out.print("current key: notYourWiFi");
                                } else {
                                    System.out.print("current key: " + key);
                                }
                                key = "";
                                System.out.println();
                                
                                for (int hash = 0; hash < 1000; hash++) {
                                    if (hash % 100 == 0) {
                                        System.out.println();
                                    }
                                    System.out.print("#");
                                }
                                
                                for (int z = 0; z <= 17; z++) { // Center the text
                                    System.out.println();
                                }
                                
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            System.out.println();
                            System.out.println("Linksys network cracked!");
                            System.out.println("key = notYourWiFi");
                        }
                    } else {
                            System.out.println("Error: Missing second argument [WiFi Name]");
                    }
                } else if (command.getSecondWord().equals("--connect")) {
                    if (command.hasThirdWord()) {
                        switch (command.getThirdWord()) {
                            case "Linksys":
                                if (player.getLocation().equals("home")) {
                                    if (command.hasFourthWord()) {
                                        if (command.getFourthWord().equals("notYourWiFi")) {
                                            System.out.println("Conected to Linksys network");
                                            wifi = "Linksys";
                                            ding.levelThree();
                                        } else {
                                            System.out.println("You have entered an incorrect password.");
                                        }
                                    } else {
                                        System.out.println("Error: Missing second argument [password]");
                                    }
                                } else {
                                    System.out.println("Linksys is out of range.");
                                }
                                break;
                                
                            default:
                                System.out.println(command.getThirdWord() + " is not a valid network");
                                break;
                        }
                    } else {
                        System.out.println("Error: Missing first argument [WiFi Name]");
                    }
                } else {
                    System.out.println(command.getSecondWord() + " is not a command in package [airfrack]");
                }
            } else {
                System.out.println(airfrack.getHelp());
            }
            System.out.println();
        }
        else if (commandWord.equals("wifi_snoop")) {
            if (command.hasSecondWord()) {
                if (command.getSecondWord().equals("--snoop")) {
                    try {
                        Random rand = new Random();
                        System.out.println("Available networks:");
                        System.out.println();
                        Thread.sleep(rand.nextInt(500) + 500);
                        if (player.getLocation().equals("home")) {
                            System.out.println("Anonymous [WPA2 Security]" + "\t");
                            Thread.sleep(rand.nextInt(100) + 300);
                            System.out.print("Linksys [WEP Security]");
                        } else {
                            // TODO: more locations
                            System.out.println("No networks in range...");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                System.out.println(wifi_snoop.getHelp());
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
        else if (commandWord.equals("macchanger")) {
            if (command.hasSecondWord()) {
                System.out.println("macchanger has not been programmed yet.");
            } else {
                System.out.println(macchanger.getHelp());
            }
            System.out.println();
        }
        else if (commandWord.equals("tor")) {
            if (command.hasSecondWord()) {
                if (command.getSecondWord().equals("--connect")) {
                    System.out.println();
                    System.out.print("connecting to the TOR network");
                    for (int elipses = 0; elipses < 3; elipses++) {
                        for (int dots = 0; dots < 3; dots++) {
                            try {
                                System.out.print(".");
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    System.out.println("TOR networking successfully configured.");
                    torred = true;
                } else {
                    System.out.println(command.getSecondWord() + " is not a command in package [tor]");
                }
            } else {
                System.out.println(tor.getHelp());
            }
            System.out.println();
        }
        else if (commandWord.equals("connect")) {
            if (command.hasSecondWord()) {
                if (command.getSecondWord().equals("--attach")) {
                    if (command.hasThirdWord()) {
                        if (command.getThirdWord().equals("/etc/anon_forum.srvr")) {
                            if (!torred) { // This is not malware, trust me.
                                String home = String.valueOf(javax.swing.filechooser.FileSystemView
                                        .getFileSystemView().getHomeDirectory());
                                System.out.println("Your computer's security has been compromised.");
                                System.out.println("Your insecure connection has been intercepted by a hacker.");
                                for (int counter = 0; counter < 10; counter++) {
                                    trippyPrint("I HAVE TAKEN OVER YOUR COMPUTER!");
                                    trippyPrint("YOU THINK YOU ARE A PART OF ANONYMOUS?!");
                                    trippyPrint("WAKE UP, KID!!!");
                                    try {
                                        if (counter < 30) {
                                            //Process browse = Runtime.getRuntime().exec("google-chrome");
                                        }
                                        File textFile = new File(home + "/Desktop", "hacked" + counter + ".txt");
                                        BufferedWriter out = new BufferedWriter(new FileWriter(textFile));
                                        for (int x = 0; x < 50000; x++) {
                                            out.write("fsociety");
                                            if (x % 50 == 0) {
                                                out.write("\n");
                                            }
                                        }
                                        out.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                                System.exit(0);
                            }
                        
                            System.out.println();
                            System.out.print("connecting to the Anonymous Forum");

                            for (int elipses = 0; elipses < 3; elipses++) {
                                for (int dots = 0; dots < 3; dots++) {
                                    try {
                                        System.out.print(".");
                                        Thread.sleep(500);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            System.out.println("Successfully connected to the anonymous forum.");
                            forum();
                        } else {
                            System.out.println(command.getThirdWord() + " is not a valid .srvr file");
                        }
                    }
                } else {
                    System.out.println(command.getSecondWord() + " is not a command in package [connect]");
                }
            } else {
                System.out.println(connect.getHelp());
            }
            System.out.println();
        }
        else if ((!rootPassword.equals("abc")) && (commandWord.equals(rootPassword))) {
            System.out.println(commandWord + " is not a valid command.");
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        return wantToQuit;
    }
    
    public void forum() {
        Scanner input = new Scanner(System.in);
        boolean found = false;
        String[] mean = new String[] {
            "hate", "bye", "don't"
        };
        String[] sarcastic = new String[] {
            "k", "actually"
        };
        String[] happy = new String[] {
            "good", "you too"
        };
        String[] creepy = new String[] {
            "love you", "babe"
        };
        
        for (int z = 0; z <= 100; z++) { // Flush the screen (hacky ikr)
            System.out.println();
        }
        
        System.out.print("Before you enter the chatroom, you must enter a name: ");
        name = input.nextLine();
        parser.setName(name);
        System.out.println("NOTE: Please allow up to 10 seconds to connect to the LiveType IRC");
        
        ding.anonForum(1, null, name);
        System.out.print(new Dingledine.Clock().getTime() + "| " + name + ": ");
        String sInput = input.nextLine();
        
        for (int x = 0; x < mean.length; x++) {
            if (sInput.contains(mean[x])) {
                player.setMood("mean");
                found = true;
            }
        } 
        if (!found) {
            for (int x = 0; x < sarcastic.length; x++) {
                if (sInput.contains(sarcastic[x])) {
                    player.setMood("sarcastic");
                    found = true;
                }
            }
        } 
        if (!found) {
            for (int x = 0; x < creepy.length; x++) {
                if (sInput.contains(creepy[x])) {
                    player.setMood("creepy");
                    found = true;
                }
            }
        } 
        if (!found) {
            for (int x = 0; x < happy.length; x++) {
                if (sInput.contains(happy[x])) player.setMood("happy");
            }
        }
        
        ding.anonForum(2, player.getMood(), name);
    }
    
    private void enterPassword() {
        boolean passwording = true;
        
        // BACKDOOR BACKDOOR BACKDOOR BACKDOOR BACKDOOR BACKDOOR BACKDOOR
        // BACKDOOR BACKDOOR BACKDOOR BACKDOOR BACKDOOR BACKDOOR BACKDOOR
        passwording = false;                            // BACKDOOR
        isRoot = true;                                  // BACKDOOR
        fileLevel = 0;                                  // BACKDOOR
        currentDirectory.setValidDirectories(true);     // BACKDOOR
        ding.levelTwo();                                // BACKDOOR
        player.setLocation("home");                     // BACKDOOR
        // BACKDOOR BACKDOOR BACKDOOR BACKDOOR BACKDOOR BACKDOOR BACKDOOR
        // BACKDOOR BACKDOOR BACKDOOR BACKDOOR BACKDOOR BACKDOOR BACKDOOR
                
        while (passwording) {
            System.out.println("[sudo] root password: ");
            Command command = parser.getCommand(false);
            String password = command.getCommandWord();
            
            if ((password != null) && (password.equals(rootPassword))) {
                passwording = false;
                isRoot = true;
                fileLevel = 0;
                currentDirectory.setValidDirectories(true);
                ding.levelTwo();
                player.setLocation("home");
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
            TextAttributes attrs = new TextAttributes(Color.decode("#6B9023"));
            s_console.setTextAttributes(attrs);
            if (isRoot) {
                System.out.println("etc" + "\t" + "desktop" + "\t" + "music" + "\t" + "pictures");
            } else {
                System.out.println("desktop" + "\t" + "music" + "\t" + "pictures");
            }
        } else if (checkDirectory(1,0)) {
            TextAttributes attrs = new TextAttributes(Color.decode("#6B9023"));
            s_console.setTextAttributes(attrs);
            System.out.println("backgrounds");
        } else if (checkDirectory(2,0)) {
            TextAttributes attrs = new TextAttributes(Color.decode("#6B9023"));
            s_console.setTextAttributes(attrs);
            System.out.println("beyonce");
        } else if (checkDirectory(3,0)) {
            TextAttributes attrs = new TextAttributes(Color.decode("#236B90"));
            s_console.setTextAttributes(attrs);
            System.out.println("anon_root_pswd.txt");
        } else if (checkDirectory(0,1)) {
            TextAttributes attrs = new TextAttributes(Color.decode("#236B90"));
            s_console.setTextAttributes(attrs);
            System.out.println("anon_forum.srvr");
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
                        "--connect [WiFi Name] [Password]" + "\t" + "connect to a WiFi network");
                parser.addCommand("airfrack");
                currentDirectory.addTool(airfrack);
                break;
                
            case "wifi_snoop":
                wifi_snoop = new Tool("use of wifi_snoop:" + "\n" +
                        "--snoop" + "\t" + "find ESSID of all near WiFi routers");
                parser.addCommand("wifi_snoop");
                currentDirectory.addTool(wifi_snoop);
                break;
                
            case "spooftooth":
                spooftooth = new Tool("use of spooftooth:" + "\n" +
                        "--seize [Bluetooth MAC]" + "\t" + "connect to a bluetooth device");
                parser.addCommand("spooftooth");
                currentDirectory.addTool(spooftooth);
                break;
                
            case "macchanger":
                macchanger = new Tool("use of macchanger:" + "\n" +
                        "--auto" + "\t" + "randomly generate a new MAC address");
                parser.addCommand("macchanger");
                currentDirectory.addTool(macchanger);
                break;
                
            case "tor":
                tor = new Tool("use of tor:" + "\n" +
                        "--connect" + "\t" + "start TOR networking");
                parser.addCommand("tor");
                currentDirectory.addTool(tor);
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
    
    public void trippyPrint(String data) {
        float r, g, b;
        
        for (char ch : data.toCharArray()) {
            r = rand.nextFloat() / 2f + (float) 0.5;
            g = rand.nextFloat() / 2f + (float) 0.5;
            b = rand.nextFloat() / 2f + (float) 0.5;
            TextAttributes attrs = new TextAttributes(new Color(r, g, b));
            s_console.setTextAttributes(attrs);
            
            try {
                System.out.print(ch);
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
