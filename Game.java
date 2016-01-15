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
    private Tool airfrack, spooftooth, wifi_snoop, macchanger, tor, connect, serversearcher, send;
    public enigma.console.Console s_console;
    private Random rand = new Random();
    
    private boolean isRoot = false;
    private boolean userIsNew = true;
    private boolean changedMac = true; // Anonymity test
    private boolean torred = true;     // Anonymity test
    private String rootPassword = "abc";
    private String secondLevel = null;
    private String wifi = null;
    private String home;
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
        
        // Pre-game neccessary operations
        connect = new Tool("use of connect: \n" +
                        "--attach [PATH_TO_SERVER_POWER]" + "\t" + "to connect to a server");
                parser.addCommand("connect");
                currentDirectory.addTool(connect);
                
        send = new Tool("use of send: \n" +
                        "[PATH_TO_VIRUS] [PATH_TO_URL.SRVR.FRW]" + "\t" + "to send a virus to a server's firewall.");
                parser.addCommand("send");
                currentDirectory.addTool(send);
                
        level0 = new Firewall(0);
        level1 = new Firewall(1);
        level2 = new Firewall(2);
        level3 = new Firewall(3);
        
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
            if (isRoot) {
                changeColor(GREEN, ("etc" + "\t" + "malware" + "\t" + "firewalls" + "\t" + "desktop" + "\t" + "music" + "\t" + "pictures"));
            } else {
                changeColor(GREEN, ("desktop" + "\t" + "music" + "\t" + "pictures"));
            }
        } else if (currentDirectory.getCurrentDirectory().equals("pictures") || currentDirectory.getCurrentDirectory().equals("music") || 
                currentDirectory.getCurrentDirectory().equals("desktop") || currentDirectory.getCurrentDirectory().equals("firewalls")) {
            changeColor(GREEN, currentDirectory.getDirectoryContents());
        } else if (currentDirectory.getCurrentDirectory().equals("my_stuff") || currentDirectory.getCurrentDirectory().equals("beyonce") || 
                currentDirectory.getCurrentDirectory().equals("backgrounds") || currentDirectory.getCurrentDirectory().equals("etc") ||
                currentDirectory.getCurrentDirectory().equals("malware") ||currentDirectory.getCurrentDirectory().equals("firewall_files")){
            changeColor(BLUE, currentDirectory.getDirectoryContents());
        }
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
                } else {
                    System.out.println("command [sudo] cannot be applied to " + command.getSecondWord());
                }
            }
            else  {
                System.out.println("command [sudo] must be followed by 'su'");
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
                        changedMac = true;
                        
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
                                elliot();
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
                            
                            System.out.println("\n Successfully connected to the anonymous forum. \n");
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
                        if (command.getThirdWord().contains("www.")) {
                            System.out.println(" ERROR: serversearcher url cannot have prefix of www.");
                        } else {
                            String url = command.getThirdWord();
                            currentDirectory.addFile("firewall_files", url);
                            System.out.println(url + " added to /firewalls/firewall_files \n");
                        }
                    } else {
                        System.out.println("Error: Missing second argument [URL]");
                    }
                }
            } else {
                System.out.println(serversearcher.getHelp());
            }
        }
        else if (commandWord.equals("send")) {
            if (command.hasSecondWord()) {
                if (command.getSecondWord().equals("/malware/codered.vr")) {
                    if (command.hasThirdWord()) {
                        String tempFirewall = command.getThirdWord();
                        String firewallFileName = tempFirewall.substring(26, tempFirewall.length());
                        String firewall = null;
                        
                        if (currentDirectory.serverExists(firewallFileName)) {
                            if (!torred || !changedMac) {
                                elliot();
                            }
                            
                            if (tempFirewall.length() > 26) {
                                firewall = tempFirewall.substring(26, tempFirewall.length() - 9);
                            } else {
                                System.out.println(command.getThirdWord() + " is not a valid path to a .srvr.frw file");
                                return wantToQuit;
                            }
                            
                            try {
                                System.out.println();
                                System.out.println("sudo :? bash:/shell?");
                                Thread.sleep(100);
                                System.out.println("sudo :? bash:/shell? = true; /n");
                                Thread.sleep(500);
                                System.out.println("bash : sending codered virus to server firewall " + firewall);
                                Thread.sleep(1000);
                                System.out.println("codered :? isRunning = true;");
                                System.out.println("codered : waiting for " + firewall + " to ping \n");
                                Thread.sleep(400);
                                
                                System.out.println("PING " + firewall + " (" + rand.nextInt(21) + "." + rand.nextInt(200) + "." +
                                         rand.nextInt(500) + "." + rand.nextInt(100) + "): 56 data bytes \n");
                                Thread.sleep(1000);
                                System.out.println("--- " + firewall + " ping statistics ---");
                                Thread.sleep(320);
                                System.out.println("1 packets transmitted, 1 packets received, 0.0% packet loss \n");
                                
                                System.out.println("PING success!");
                                System.out.print("Burrying virus into firewall");
                                
                                for (int elipses = 0; elipses < 2; elipses++) {
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
                                
                                int level;
                                if (firewall.equalsIgnoreCase("vpnsecure.com")) {
                                    level = 0;
                                } else if (firewall.equalsIgnoreCase("filmoreandson.com")) {
                                    level = 1;
                                } else if (firewall.equalsIgnoreCase("sourcefeed.com")) {
                                    level = 2;
                                } else if (firewall.equalsIgnoreCase("nextfinancial.com")) {
                                    level = 3;
                                } else {
                                    level = rand.nextInt(4);
                                }
                                
                                changeColor("#F19001", ("FIREWALL SECURITY PATCH LEVEL " + level));
                                
                                System.out.println();
                                System.out.println("Virus successfully burried in " + firewall);
                                System.out.println();
                                
                                System.out.print("Getting list of possible passwords");
                                for (int elipses = 0; elipses < 2; elipses++) {
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
                                
                                switch (level) {
                                    case 0:
                                        changeColor("#C6E2FF", (level0.getPasswordList() + "\n"));
                                        break;
                                        
                                    case 1:
                                        changeColor("#C6E2FF", (level1.getPasswordList() + "\n"));
                                        break;
                                        
                                    case 2:
                                        changeColor("#C6E2FF", (level2.getPasswordList() + "\n"));
                                        break;
                                        
                                    case 3:
                                        changeColor("#C6E2FF", (level3.getPasswordList() + "\n"));
                                        break;
                                }
                                
                                Scanner input = new Scanner(System.in);
                                
                                boolean guessing = true;
                                int counter = 0;
                                do {
                                    System.out.print("Password for " + firewall + ": ");
                                    String guess = input.nextLine();
                                    
                                    switch (level) {
                                        case 0:
                                            if (level0.checkPassword(guess)) {
                                                guessing = false;
                                            } else {
                                                changeColor("#FF0000", "Incorrect Password.");
                                                if (counter > 4) {
                                                    System.out.print(" hint: use 'exit' to stop guessing. \n");
                                                }
                                            }
                                            break;
                                            
                                        case 1:
                                            if (level1.checkPassword(guess)) {
                                                guessing = false;
                                            } else {
                                                changeColor("#FF0000", "Incorrect Password.");
                                                if (counter > 4) {
                                                    System.out.print(" hint: use 'exit' to stop guessing. \n");
                                                }
                                            }
                                            break;
                                            
                                        case 2:
                                            if (level2.checkPassword(guess)) {
                                                guessing = false;
                                            } else {
                                                changeColor("#FF0000", "Incorrect Password.");
                                                if (counter > 4) {
                                                    System.out.print(" hint: use 'exit' to stop guessing. \n");
                                                }
                                            }
                                            break;
                                            
                                        case 3:
                                            if (level3.checkPassword(guess)) {
                                                guessing = false;
                                            } else {
                                                changeColor("#FF0000", "Incorrect Password.");
                                                if (counter > 4) {
                                                    System.out.print(" hint: use 'exit' to stop guessing. \n");
                                                }
                                            }
                                            break;
                                    }
                                    
                                    if (guess.equals("exit")) {
                                        return false;
                                    }
                                } while (guessing);
                                
                                System.out.println("Connected to " + firewall + " servers.");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            System.out.println(command.getThirdWord() + " is not a valid path to a .srvr.frw file");
                        }
                    } else {
                        System.out.println("Error: Missing second argument [PATH_TO_URL.SRVR.FRW]");
                    }
                } else {
                    System.out.println("Error: Missing second argument [PATH_TO_VIRUS]");
                }
            } else {
                System.out.println(send.getHelp());
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
                airfrack = new Tool("use of airfrack-ng: \n" +
                        "--crack [WiFi Name]" + "\t" + "crack a router's password" + "\n" +
                        "--connect [WiFi Name] [Password]" + "\t" + "connect to a WiFi network");
                parser.addCommand("airfrack");
                currentDirectory.addTool(airfrack);
                break;
                
            case "wifi_snoop":
                wifi_snoop = new Tool("use of wifi_snoop: \n" +
                        "--snoop" + "\t" + "find ESSID of all near WiFi routers");
                parser.addCommand("wifi_snoop");
                currentDirectory.addTool(wifi_snoop);
                break;
                
            case "spooftooth":
                spooftooth = new Tool("use of spooftooth: \n" +
                        "--seize [Bluetooth MAC]" + "\t" + "connect to a bluetooth device");
                parser.addCommand("spooftooth");
                currentDirectory.addTool(spooftooth);
                break;
                
            case "macchanger":
                macchanger = new Tool("use of macchanger: \n" +
                        "--auto" + "\t" + "randomly generate a new MAC address");
                parser.addCommand("macchanger");
                currentDirectory.addTool(macchanger);
                break;
                
            case "tor":
                tor = new Tool("use of tor: \n" +
                        "--connect" + "\t" + "start TOR networking");
                parser.addCommand("tor");
                currentDirectory.addTool(tor);
                break;
                
            case "serversearcher":
                serversearcher = new Tool("use of serversearcher: \n" +
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
            return;
        }
        
        /** USER SPECIFIED DIRECTORY **/
        String newDirectory = command.getSecondWord();
        
        if (newDirectory.equals("..")) {
            currentDirectory.upOne();
            printPath();
            return;
        }
        
        switch (currentDirectory.getCurrentDirectory()) {
            case "/":
                if (newDirectory.equalsIgnoreCase("pictures") || newDirectory.equalsIgnoreCase("music") || newDirectory.equalsIgnoreCase("desktop") || 
                    ((newDirectory.equalsIgnoreCase("etc") || newDirectory.equalsIgnoreCase("malware") || 
                    newDirectory.equalsIgnoreCase("firewalls")) && isRoot)) {
                    currentDirectory.setPath(newDirectory);
                } else {
                    System.out.println(newDirectory + " is not a file or directory");
                }
                break;
                
            case "pictures":
                if (newDirectory.equalsIgnoreCase("backgrounds")) {
                    currentDirectory.setPath(newDirectory);
                } else {
                    System.out.println(newDirectory + " is not a file or directory");
                }
                break;
                
            case "music":
                if (newDirectory.equalsIgnoreCase("beyonce")) {
                    currentDirectory.setPath(newDirectory);
                } else {
                    System.out.println(newDirectory + " is not a file or directory");
                }
                break;
                
            case "desktop":
                if (newDirectory.equalsIgnoreCase("my_stuff")) {
                    currentDirectory.setPath(newDirectory);
                } else {
                    System.out.println(newDirectory + " is not a file or directory");
                }
                break;
                
            case "firewalls":
                if (newDirectory.equalsIgnoreCase("firewall_files")) {
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
    
    public void elliot() {
        String OS = System.getProperty("os.name"); // Find out the user's OS
        home = String.valueOf(javax.swing.filechooser.FileSystemView
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
            trippyPrint("This message will self terminate in: \n 3");
            Thread.sleep(1000);
            trippyPrint("2");
            Thread.sleep(1000);
            trippyPrint("1");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0); //User is bad at game... bye!
    }
    
    public void changeColor(String color, String data) {
        TextAttributes attrs = new TextAttributes(Color.decode(color));
        s_console.setTextAttributes(attrs);
        System.out.println(data + "\n");
        attrs = new TextAttributes(Color.WHITE);
        s_console.setTextAttributes(attrs);
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
