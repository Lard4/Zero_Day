import java.util.*;
import java.text.*;
import java.net.*;

public class Dingledine {
    private boolean isIRC = true;
    
    private static final long STANDARDDELAY = 30; //80ms
    private static final long LONGDELAY = 110; //110ms
    private static final long ONESECOND = 1000; //1000ms
    private static final long TWOSECOND = 2000; //2000ms
    private static final long FIVESECOND = 5000; //2000ms
    private static final long THREEQUARTERSECOND = 750; //750ms
    
    private InetAddress IP;
    
    public void intro() {
        System.out.println();
        try {
            printWithDelays("Press [ENTER] to begin Zero Day.", LONGDELAY);
            Scanner temp = new Scanner(System.in);
            temp.nextLine();
            
            printWithDelays("We are Anonymous", STANDARDDELAY);
            Thread.sleep(THREEQUARTERSECOND);
            printWithDelays("We are a legion", STANDARDDELAY);
            Thread.sleep(THREEQUARTERSECOND);
            printWithDelays("We do not forgive", STANDARDDELAY);
            Thread.sleep(THREEQUARTERSECOND);
            printWithDelays("We do not forget", STANDARDDELAY);
            Thread.sleep(THREEQUARTERSECOND);
            printWithDelays("Expect us.", LONGDELAY);
            System.out.println();
            Thread.sleep(TWOSECOND);
            
            printWithDelays("Welcome to your Linux shell environment.", STANDARDDELAY);
            Thread.sleep(ONESECOND);
            printWithDelays("This is where you will spend the entirety of your time hacking.", STANDARDDELAY);
            System.out.println();
            Thread.sleep(TWOSECOND);
            
            printWithDelays("Recently, Barack Obama has threatened to declare war on ISIS.", STANDARDDELAY);
            printWithDelays("Little does he know that ISIS has formed a pact with the following countries:", STANDARDDELAY);
            printWithDelays("   Syria", LONGDELAY);
            printWithDelays("   Lebanon", LONGDELAY);
            printWithDelays("   Jordan", LONGDELAY);
            printWithDelays("   Egypt", LONGDELAY);
            printWithDelays("   Iraq", LONGDELAY);
            printWithDelays("   Yemen", LONGDELAY);
            printWithDelays("   Afghanistan", LONGDELAY);
            System.out.println();
            Thread.sleep(TWOSECOND);
            
            printWithDelays("As you can see, if Obama declares war on ISIS, he will therefore go to" +
                    " war on all 7 nations.", STANDARDDELAY);
            printWithDelays("If this happens, it's only a matter of time before the US turns into " +
                    "nothing more than a radioactive crater.", STANDARDDELAY);
            System.out.println();
            Thread.sleep(TWOSECOND);
            
            printWithDelays("Over the past few months you have came up with a master plan of how to take down ISIS.", STANDARDDELAY);
            printWithDelays("Keeping in mind that anonymity is of the utmost priority, you must...", STANDARDDELAY);
            printWithDelays("   1) Set up a secure environment", STANDARDDELAY);
            printWithDelays("   2) Install hacking tools as you find them", STANDARDDELAY);
            printWithDelays("   3) Take out ISIS's primary funding sources", STANDARDDELAY);
            printWithDelays("   4) Deliver the final blow to ISIS", STANDARDDELAY);
            System.out.println();
            Thread.sleep(TWOSECOND);
            
            printWithDelays("With that in mind, I wish you good luck.", STANDARDDELAY);
            printWithDelays("We're all counting on you.", 200); //200
            System.out.println();
            System.out.println("------------------------------------------------------------------------");
            System.out.println();
            
            printWithDelays("Hello. My name is Roger Dingledine. I am the leader of Anonymous.", STANDARDDELAY);
            printWithDelays("I am here to assist you through Zero Day.", STANDARDDELAY);
            printWithDelays("Let's start with the basic Linux shell commands.", STANDARDDELAY);
            Thread.sleep(TWOSECOND);
            System.out.println();
            
            printWithDelays("To change to a different folder (referred to as directories) use 'cd'", STANDARDDELAY);
            printWithDelays("Use cd followed by a directory to make that your working directory.", STANDARDDELAY);
            Thread.sleep(ONESECOND);
            System.out.println();
            
            printWithDelays("To see what files and directories are beneath you, use 'ls'", STANDARDDELAY);
            printWithDelays("Files will be printed in blue and directories will be printed in green", STANDARDDELAY);
            printWithDelays("ls will list everything that you can cd into or open.", STANDARDDELAY);
            Thread.sleep(ONESECOND);
            System.out.println();
            
            printWithDelays("Speaking of open... You can use the command 'open' to open files", STANDARDDELAY);
            printWithDelays("Use open followed by the text file name.", STANDARDDELAY);
            Thread.sleep(TWOSECOND);
            System.out.println();
            
            printWithDelays("Your first mission is to become the root user.", STANDARDDELAY);
            printWithDelays("Use the command 'sudo su' to become root.", STANDARDDELAY);
            printWithDelays("You may have to explore the file system to find the password.", STANDARDDELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        
    }
    
    public void levelTwo() {
        System.out.println();
        try {
            printWithDelays("Good job, kid. You completed your first task.", STANDARDDELAY);
            Thread.sleep(ONESECOND);
            System.out.println();
            
            printWithDelays("Your next task is to hack into your neighbor's WiFi.", STANDARDDELAY);
            printWithDelays("Your neighbor has gigabit internet speeds via his fiber connection.", STANDARDDELAY);
            printWithDelays("Taking advatage of these internet speeds is a necessity.", STANDARDDELAY);
            printWithDelays("Remember, you must remain anonymous at all costs.", STANDARDDELAY);
            Thread.sleep(ONESECOND);
            System.out.println();
            
            System.out.println("--------------------------------------------------------------");
            System.out.println();
            
            printWithDelays("Now, for a little instruction.", STANDARDDELAY);
            printWithDelays("You must use the command 'install' to install different penetration tools.", STANDARDDELAY);
            printWithDelays("Use install followed by any of the following tools to install them:", STANDARDDELAY);
            System.out.println("airfrack" + "\t" + "wifi_snoop" + "\t" + "spooftooth" + "\t" + "macchanger");
            Thread.sleep(ONESECOND);
            System.out.println();
            
            printWithDelays("See you again once you hack into his WiFi.", STANDARDDELAY);
            System.out.println();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public void levelThree() {
        System.out.println();
        try {
            printWithDelays("Good job, kid. You completed your second task.", STANDARDDELAY);
            Thread.sleep(ONESECOND);
            System.out.println();
            
            printWithDelays("Your next task is to connect to the deep web in order to go on the Anonyous Forum.", STANDARDDELAY);
            printWithDelays("From the forum, you will then find the man who lives in Russia to set up a VPN for you.", STANDARDDELAY);
            printWithDelays("Using a VPN will mask your identity to people from the outside world.", STANDARDDELAY);
            Thread.sleep(ONESECOND);
            System.out.println();
            
            printWithDelays("To maintain the utmost anonymity during the rest of the game, you must use TOR networking.", STANDARDDELAY);
            printWithDelays("Imagine TOR as an invisibility cloak. TOR makes you nearly impossible to track.", STANDARDDELAY);
            printWithDelays("TOR scrambles your IP and sends you through servers around the world for free.", STANDARDDELAY);
            printWithDelays("TOR is your new best friend, love him and use him... all the time...", STANDARDDELAY);
            Thread.sleep(ONESECOND);
            System.out.println();
            
            printWithDelays("To complete this mission, you will need to use two tools: tor and connect", STANDARDDELAY);
            printWithDelays("To use tor, install it like any other program then type 'tor'.", STANDARDDELAY);
            printWithDelays("Connect comes pre-installed, so you don't even have to install it.", STANDARDDELAY);
            Thread.sleep(ONESECOND);
            System.out.println();
            
            printWithDelays("See you soon... Hopefully...", STANDARDDELAY);
            Thread.sleep(ONESECOND);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public void anonForum(int log, String mood, String name) {
        try {
            switch (log) {
                case 1:
                    for (int x = 0; x < 50; x++) {
                        System.out.println();
                    }
                    System.out.println("\t" + "\t" + "************ NEW LIVETYPE IRC @ CHANNEL --> #0p1s1s ************" + "\n");
                    System.out.print(new Clock().getTime() + "| d1ngledino: ");
                    printWithDelays("he seems like a nice guy, but he's a little slow on the uptake, " + 
                            "if you know what i mean...", STANDARDDELAY);
                    Thread.sleep(ONESECOND);
                    
                    System.out.print(new Clock().getTime() + "| XxT0rvaldsxX: ");
                    printWithDelays("yeah i get it", STANDARDDELAY);
                    Thread.sleep(TWOSECOND);
                            
                    System.out.print(new Clock().getTime() + "| > user " + name + " joined #0p1s1s");
                    Thread.sleep(ONESECOND);
                    System.out.println();
                    
                    System.out.print(new Clock().getTime() + "| d1ngledino: ");
                    printWithDelays("oh hey, you're here... finally...", STANDARDDELAY);
                    Thread.sleep(ONESECOND);
                    
                    System.out.print(new Clock().getTime() + "| d1ngledino: ");
                    printWithDelays(name + "? i couldn't have come up with a weirder name myself. ", STANDARDDELAY);
                    Thread.sleep(ONESECOND);

                    System.out.print(new Clock().getTime() + "| XxT0rvaldsxX: ");
                    printWithDelays("is this that dude you were talking about", STANDARDDELAY);
                    Thread.sleep(ONESECOND);
                            
                    System.out.print(new Clock().getTime() + "| d1ngledino: ");
                    printWithDelays("yup, this is the newbie. say hi, newbie", STANDARDDELAY);
                    Thread.sleep(FIVESECOND);
                    
                    System.out.print(new Clock().getTime() + "| d1ngledino: ");
                    printWithDelays("well alright then. you dont have to talk i guess", STANDARDDELAY);
                    break;
                    
                case 2:
                    try {
                        IP = InetAddress.getLocalHost();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    
                    if (mood != null) {
                        if (mood.equals("happy")) {
                            System.out.print(new Clock().getTime() + "| d1ngledino: ");
                            printWithDelays("hey, he does exist! i'm just kidding kid, glad to hear from ya", STANDARDDELAY);
                        } else if (mood.equals("mean")) {
                            System.out.print(new Clock().getTime() + "| d1ngledino: ");
                            printWithDelays("somebody woke up on the wrong side of the bed...", STANDARDDELAY);
                        } else if (mood.equals("sarcastic")) {
                            System.out.print(new Clock().getTime() + "| d1ngledino: ");
                            printWithDelays("at least i'm not the kid with the dumb name...", STANDARDDELAY);
                        } else if (mood.equals("creepy")) {
                            System.out.print(new Clock().getTime() + "| d1ngledino: ");
                            printWithDelays("have you ever hear the phrase some things are better left unsaid???", STANDARDDELAY);
                        } else if (mood.equals("fancy")) {
                            System.out.print(new Clock().getTime() + "| d1ngledino: ");
                            printWithDelays("mmmm salutations, good sir!", STANDARDDELAY);
                        }
                    } else { // Person is 'normal'
                        System.out.print(new Clock().getTime() + "| d1ngledino: ");
                        printWithDelays("top of the day to mr " + name + ", glad to hear from ya", STANDARDDELAY);
                    }
                    
                    System.out.print(new Clock().getTime() + "| d1ngledino: ");
                    printWithDelays("anyways, this is the guy i was talking about. his name is linus torvalds.", STANDARDDELAY);
                    Thread.sleep(ONESECOND);
                    
                    System.out.print(new Clock().getTime() + "| d1ngledino: ");
                    printWithDelays("he is our go-to guy for when we need to set up a VPN.", STANDARDDELAY);
                    Thread.sleep(ONESECOND);
                    
                    System.out.print(new Clock().getTime() + "| XxT0rvaldsxX: ");
                    printWithDelays("yeah vpns are my thing. i make a living off of setting these up for people since i live in North Korea.", STANDARDDELAY);
                    Thread.sleep(ONESECOND);
                    
                    System.out.print(new Clock().getTime() + "| d1ngledino: ");
                    printWithDelays("North Korea does not hand over any information to the US, so North Korean vpns are the most secure.", STANDARDDELAY);
                    Thread.sleep(ONESECOND);
                    
                    System.out.print(new Clock().getTime() + "| XxT0rvaldsxX: ");
                    printWithDelays("yep, so what are you trying to do again?", STANDARDDELAY);
                    Thread.sleep(ONESECOND);
                    
                    System.out.print(new Clock().getTime() + "| d1ngledino: ");
                    printWithDelays("this operation is on a strict need-to-know basis. we just need a vpn in NK, Russia, and Sweden.", STANDARDDELAY);
                    Thread.sleep(ONESECOND);
                    
                    System.out.print(new Clock().getTime() + "| XxT0rvaldsxX: ");
                    printWithDelays("ok, easy enough. what's your public IP?", STANDARDDELAY);
                    Thread.sleep(ONESECOND);
                    
                    System.out.print(new Clock().getTime() + "| d1ngledino: ");
                    printWithDelays("i'll find out for you... one sec", STANDARDDELAY);
                    Thread.sleep(ONESECOND);
                    
                    System.out.print(new Clock().getTime() + "| d1ngledino: ");
                    printWithDelays(String.valueOf(IP.getHostAddress()), STANDARDDELAY);
                    Thread.sleep(ONESECOND);
                    
                    System.out.print(new Clock().getTime() + "| XxT0rvaldsxX: ");
                    printWithDelays("cool. your vpns should be set up now.", STANDARDDELAY);
                    Thread.sleep(ONESECOND);
                    break;
                    
                case 3:
                    if (mood != null) {
                        if (mood.equals("happy")) {
                            System.out.print(new Clock().getTime() + "| XxT0rvaldsxX: ");
                            printWithDelays("no problem, glad to help", STANDARDDELAY);
                        } else if (mood.equals("mean")) {
                            System.out.print(new Clock().getTime() + "| XxT0rvaldsxX: ");
                            printWithDelays("ya know, i don't have to help next time...", STANDARDDELAY);
                            Thread.sleep(ONESECOND);
                            
                            System.out.print(new Clock().getTime() + "| d1ngledino: ");
                            printWithDelays("do you insist on being a jerk? ", STANDARDDELAY);
                        } else if (mood.equals("sarcastic")) {
                            System.out.print(new Clock().getTime() + "| XxT0rvaldsxX: ");
                            printWithDelays("k bye", STANDARDDELAY);
                        } else if (mood.equals("creepy")) {
                            System.out.print(new Clock().getTime() + "| XxT0rvaldsxX: ");
                            printWithDelays("im... gonna... go now?", STANDARDDELAY);
                        } else if (mood.equals("fancy")) {
                            System.out.print(new Clock().getTime() + "| XxT0rvaldsxX: ");
                            printWithDelays("mmmm indubitably... see ya bro", STANDARDDELAY);
                        }
                    } else { // Person is 'normal'
                        System.out.print(new Clock().getTime() + "| d1ngledino: ");
                        printWithDelays("i gotta go, see ya later", STANDARDDELAY);
                    }
                    
                    System.out.print(new Clock().getTime() + "| > user XxT0rvaldsxX left #0p1s1s");
                    Thread.sleep(ONESECOND);
                    System.out.println();
                    
                    System.out.print(new Clock().getTime() + "| > user " + name + " left #0p1s1s");
                    System.out.println();
                    
                    isIRC = false; // END CHAT
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public boolean isIRC() {
        return isIRC;
    }
    
    public void levelFour() {
        System.out.println();
        try {
            System.out.println("--------------------------------------------------------------");
            System.out.println();
            
            printWithDelays("Good job, kid. You completed your third task.", STANDARDDELAY);
            Thread.sleep(ONESECOND);
            System.out.println();
            
            printWithDelays("In your next mission, you will have to crack into the VPNSecure.com firewall.", STANDARDDELAY);
            printWithDelays("Breaking through firewalls is one of the hardest parts of hacking.", STANDARDDELAY);
            printWithDelays("But don't worry, I'll tell you all you need to know in order to succeed.", STANDARDDELAY);
            Thread.sleep(ONESECOND);
            System.out.println();
            
            printWithDelays("First, you're going to need to install a tool called 'serversearcher'", STANDARDDELAY);
            printWithDelays("seversearcher will take a URL of a website and save the server's firewall file.", STANDARDDELAY);
            printWithDelays("Unlike before, these files will actually be written to your physical desktop.", STANDARDDELAY);
            Thread.sleep(ONESECOND);
            System.out.println();
            
            printWithDelays("Then, you will use 'send' (it comes preinstalled) to send a virus in /etc to the firewall.", STANDARDDELAY);
            printWithDelays("The virus will return a series of possible passwords to crack into the firewall.", STANDARDDELAY);
            printWithDelays("Once you brute force through all the passwords, you will have full control over the servers.", STANDARDDELAY);
            Thread.sleep(ONESECOND);
            System.out.println();
            
            printWithDelays("See you in the next test... Hopefully...", STANDARDDELAY);
            Thread.sleep(ONESECOND);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
   
    private void printWithDelays(String data, long delay) throws InterruptedException {
        for (char ch : data.toCharArray()) {
            System.out.print(ch);
            Thread.sleep(delay);
        }
        System.out.println();
    }
    
    static class Clock {
        public String getTime() {
            return (new SimpleDateFormat("HH:mm:ss").format(new Date()));
        }
    }
}