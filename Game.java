/*
 * Kevin Dixson
 * 12-9-15
 */
import java.util.*;
import java.io.*;
import java.net.*;
import java.awt.Color; 
import enigma.console.*;
import enigma.core.Enigma;

public class Game {
    private Parser parser;
    private Player player = new Player();
    private Firewall level0, level1, level2, level3;
    private Dingledine ding = new Dingledine();
    private Directory currentDirectory;
    private Tool airfrack, spooftooth, wifi_snoop, macchanger, tor, connect, serversearcher;
    private enigma.console.Console s_console;
    private Random rand = new Random();
    
    private boolean isRoot = false;
    private boolean userIsNew = true;
    private boolean changedMac = false; // Anonymity test
    private boolean torred = false;     // Anonymity test
    private String rootPassword = "abc";
    private String secondLevel = null;
    private String wifi = null;
    private String name = "";
    
    private static final String GREEN = "#6B9023";
    private static final String BLUE = "#236B90";

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
        
        // Pre-game neccessary operations
        connect = new Tool("use of connect:" + "\n" +
                        "--attach [PATH_TO_SERVER_POWER]" + "\t" + "to connect to a server");
                parser.addCommand("connect");
                currentDirectory.addTool(connect);
        
        boolean finished = false;
        while (!finished) {
            finished = processCommand(parser.getCommand(isRoot, false));
        }
        System.out.println("Later loser.");
    }
    
    private void printPath() {
        System.out.print(currentDirectory.getPath());
        System.out.println();
        
        if (currentDirectory.getCurrentDirectory().equals("/")) {
            TextAttributes attrs = new TextAttributes(Color.decode(GREEN));
            s_console.setTextAttributes(attrs);
            if (isRoot) {
                System.out.println("etc" + "\t" + "desktop" + "\t" + "music" + "\t" + "pictures");
            } else {
                System.out.println("desktop" + "\t" + "music" + "\t" + "pictures");
            }
        } else if (currentDirectory.getCurrentDirectory().equals("pictures")) {
            TextAttributes attrs = new TextAttributes(Color.decode(GREEN));
            s_console.setTextAttributes(attrs);
            System.out.println("backgrounds");
        } else if (currentDirectory.getCurrentDirectory().equals("music")) {
            TextAttributes attrs = new TextAttributes(Color.decode(GREEN));
            s_console.setTextAttributes(attrs);
            System.out.println("beyonce");
        } else if (currentDirectory.getCurrentDirectory().equals("desktop")) {
            TextAttributes attrs = new TextAttributes(Color.decode(GREEN));
            s_console.setTextAttributes(attrs);
            System.out.println("my_stuff");
        } else if (currentDirectory.getCurrentDirectory().equals("my_stuff")) {
            TextAttributes attrs = new TextAttributes(Color.decode(BLUE));
            s_console.setTextAttributes(attrs);
            System.out.println("anon_root_pswd.txt");
        } else if (currentDirectory.getCurrentDirectory().equals("beyonce")) {
            TextAttributes attrs = new TextAttributes(Color.decode(BLUE));
            s_console.setTextAttributes(attrs);
            System.out.println("beyonce_music.flac");
        } else if (currentDirectory.getCurrentDirectory().equals("backgrounds")) {
            TextAttributes attrs = new TextAttributes(Color.decode(BLUE));
            s_console.setTextAttributes(attrs);
            System.out.println("family_trip.png");
        } else if (currentDirectory.getCurrentDirectory().equals("etc")) {
            TextAttributes attrs = new TextAttributes(Color.decode(BLUE));
            s_console.setTextAttributes(attrs);
            System.out.println("anon_forum.srvr");
        }
        // Back to white!
        TextAttributes attrs = new TextAttributes(Color.WHITE);
        s_console.setTextAttributes(attrs);
        // New line
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
                if ((command.getSecondWord().equals("anon_root_pswd.txt")) && 
                    (currentDirectory.getCurrentDirectory().equals("my_stuff"))) {
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
                    System.out.println(command.getSecondWord() + " cannot be opened.");
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
                            
                        case "serversearcher":
                            installTool("serversearcher");
                            System.out.println("serversearcher successfully installed!");
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
                if (command.getSecondWord().equals("--auto")) {
                    changedMac = true;
                    try {
                        InetAddress ip = InetAddress.getLocalHost();
                        NetworkInterface network = NetworkInterface.getByInetAddress(ip);
                        
                        byte[] mac = network.getHardwareAddress();
                        
                        System.out.print("New MAC address: ");
                        
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < mac.length; i++) {
                            sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));        
                        }
                        System.out.println(sb.toString());
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    } catch (SocketException e){
                        e.printStackTrace();
                    }
                }
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
                    System.out.println();
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
                            if (!torred || !changedMac) { // This is not malware, trust me.
                                String OS = System.getProperty("os.name"); // Find out the user's OS
                                String home = String.valueOf(javax.swing.filechooser.FileSystemView
                                        .getFileSystemView().getHomeDirectory()); // Find path_to_home_dir
                                        
                                System.out.println("Your computer's security has been compromised.");
                                System.out.println("Your insecure connection has been intercepted by a hacker.");
                                
                                for (int counter = 1; counter < 10; counter++) {
                                    if (counter % 25 == 0) {
                                        trippyPrint("I HAVE TAKEN OVER YOUR COMPUTER!");
                                        trippyPrint("YOU THINK YOU ARE A PART OF ANONYMOUS?!");
                                        trippyPrint("WAKE UP, KID!!!");
                                        System.out.println();
                                    }
                                    
                                    try {
                                        if (counter < 20) { // Only 20 to ensure the computer will be more than a paperweight at the end
                                            if (OS.equals("Mac OS X")) {
                                                Process browse = Runtime.getRuntime().exec("open -n /Applications/Safari.app/");
                                            } else {
                                                Process browse = Runtime.getRuntime().exec("google-chrome");
                                            }
                                        }
                                        File textFile = new File(home + "/Desktop", "hacked" + counter + ".txt");
                                        BufferedWriter out = new BufferedWriter(new FileWriter(textFile));
                                        for (int x = 0; x < 500000; x++) {
                                            if (x % 50 == 0) {
                                                out.write("\n");
                                            }
                                            out.write("fsociety  ");
                                        }
                                        out.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                                
                                try {
                                    System.out.println();
                                    trippyPrint("I have created hundreds of files that total about close to 1 gigabyte of data.");
                                    trippyPrint("I have opened chrome 30 times, and if your computer didn't crash, you are lucky.");
                                    trippyPrint("I've never found it hard to hack most people.");
                                    trippyPrint("If you listen to them, watch them, their vulnerabilities are like a neon sign " + 
                                            "screwed into their head.");
                                    Thread.sleep(15000);
                                    trippyPrint("This message will self terminate in:" + "\n" + "3");
                                    Thread.sleep(1000);
                                    trippyPrint("2");
                                    Thread.sleep(1000);
                                    trippyPrint("1");
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                System.exit(0); //Bye
                            }
                            
                            /** ----- USER DID NOT GET HACKED ----- **/
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
                            
                            System.out.println();
                            System.out.println("Successfully connected to the anonymous forum.");
                            System.out.println();
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
        else if (commandWord.equals("serversearcher")) {
            if (command.hasSecondWord()) {
                if (command.getSecondWord().equals("--url")) {
                    if (command.hasThirdWord()) {
                        String url = command.getThirdWord();
                        currentDirectory.addFile(url);
                    }
                }
            } else {
                System.out.println(serversearcher.getHelp());
            }
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
            "hate", "bye", "don't", "never", "didn't"
        };
        String[] sarcastic = new String[] {
            "actually", "fair enough", "why", "maybe"
        };
        String[] happy = new String[] {
            "good", "you too", "friend", "thanks"
        };
        String[] creepy = new String[] {
            "love you", "babe", ":)", ";)"
        };
        String[] fancy = new String[] {
            "indubitably"
        };
        
        System.out.print("Before you enter the chatroom, you must enter a name: ");
        name = input.nextLine();
        
        parser.setName(name);
        System.out.println("NOTE: Please allow up to 10 seconds to connect to the LiveType IRC");
        
        ding.anonForum(1, null, name);
        
        int dialog = 1;
        while (ding.isIRC()) {
            System.out.print(new Dingledine.Clock().getTime() + "| " + name + ": ");
            String sInput = input.nextLine();
            
            for (int x = 0; x < mean.length; x++) {
                if (sInput.toLowerCase().contains(mean[x])) {
                    player.setMood("mean");
                    found = true;
                }
            } 
            if (!found) {
                for (int x = 0; x < sarcastic.length; x++) {
                    if (sInput.toLowerCase().contains(sarcastic[x])) {
                        player.setMood("sarcastic");
                        found = true;
                    }
                }
            } 
            if (!found) {
                for (int x = 0; x < creepy.length; x++) {
                    if (sInput.toLowerCase().contains(creepy[x])) {
                        player.setMood("creepy");
                        found = true;
                    }
                }
            } 
            if (!found) {
                for (int x = 0; x < happy.length; x++) {
                    if (sInput.toLowerCase().contains(happy[x])) {
                        player.setMood("happy");
                        found = true;
                    }
                }
            }
            if (!found) {
                for (int x = 0; x < fancy.length; x++) {
                    if (sInput.toLowerCase().contains(fancy[x])) player.setMood("fancy");
                }
            }
            dialog++;
            ding.anonForum(dialog, player.getMood(), name);
        }
        
        ding.levelFour();
    }
    
    private void enterPassword() {
        boolean passwording = true;
        
        // BACKDOOR BACKDOOR BACKDOOR BACKDOOR BACKDOOR BACKDOOR BACKDOOR
        // BACKDOOR BACKDOOR BACKDOOR BACKDOOR BACKDOOR BACKDOOR BACKDOOR
        passwording = false;                            // BACKDOOR
        isRoot = true;                                  // BACKDOOR
        currentDirectory.setValidDirectories(true);     // BACKDOOR
        ding.levelTwo();                                // BACKDOOR
        player.setLocation("home");                     // BACKDOOR
        // BACKDOOR BACKDOOR BACKDOOR BACKDOOR BACKDOOR BACKDOOR BACKDOOR
        // BACKDOOR BACKDOOR BACKDOOR BACKDOOR BACKDOOR BACKDOOR BACKDOOR
                
        while (passwording) {
            System.out.println();
            System.out.print("[sudo] root password: ");
            Command command = parser.getCommand(false, true);
            String password = command.getCommandWord();
            
            if ((password != null) && (password.equals(rootPassword))) {
                passwording = false;
                isRoot = true;
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
    
    private void printLS() {
        printPath();
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
                
            case "serversearcher":
                serversearcher = new Tool("use of serversearcher:" + "\n" +
                        "--url [URL]" + "\t" + "to save the srvr.frw file");
                parser.addCommand("serversearcher");
                currentDirectory.addTool(serversearcher);
                break;
        }
    }

    private void printHelp() {
        System.out.println("The following Unix commands have been installed:");
        System.out.println("    " + parser.showCommands());
    }
    
    private void cdTo(Command command) {
        if (!command.hasSecondWord()) {
            currentDirectory.setPath("");
            printPath();
            System.out.println();
            return;
        }
        
        /** USER SPECIFIED DIRECTORY **/
        String newDirectory = command.getSecondWord();
        
        switch (currentDirectory.getCurrentDirectory()) {
            case "/":
                if (newDirectory.equals("pictures") || newDirectory.equals("music") || newDirectory.equals("desktop") || 
                    (newDirectory.equals("etc") && isRoot)) {
                    currentDirectory.setPath(newDirectory);
                } else {
                    System.out.println(newDirectory + " is not a file or directory");
                }
                break;
                
            case "pictures":
                if (newDirectory.equals("backgrounds")) {
                    currentDirectory.setPath(newDirectory);
                } else {
                    System.out.println(newDirectory + " is not a file or directory");
                }
                break;
                
            case "music":
                if (newDirectory.equals("beyonce")) {
                    currentDirectory.setPath(newDirectory);
                } else {
                    System.out.println(newDirectory + " is not a file or directory");
                }
                break;
                
            case "desktop":
                if (newDirectory.equals("my_stuff")) {
                    currentDirectory.setPath(newDirectory);
                } else {
                    System.out.println(newDirectory + " is not a file or directory");
                }
                break;
                
            default:
                System.out.println(newDirectory + " is not a file or directory");
                return;
        }
        
        printPath();
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
            System.out.println(command.getSecondWord() + " cannot be quit");
            return false;
        }
        else {
            return true;
        }
    }
}
