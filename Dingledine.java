import java.util.*;
import java.text.*;

public class Dingledine {
    private static final long STANDARDDELAY = 80; //80ms
    private static final long LONGDELAY = 0; //110ms
    private static final long ONESECOND = 1000; //1000ms
    private static final long TWOSECOND = 2000; //2000ms
    private static final long FIVESECOND = 5000; //2000ms
    private static final long THREEQUARTERSECOND = 0; //750ms
    
    public void intro() {
        System.out.println();
        try {
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
            
            printWithDelays("As you can see, if Obama declares war on ISIS,", STANDARDDELAY);
            printWithDelays("he will therefore go to war on all 7 nations.", STANDARDDELAY);
            printWithDelays("If this happens, it's only a matter of time before", STANDARDDELAY);
            printWithDelays("the US turns into nothing more than a radioactive crater.", STANDARDDELAY);
            System.out.println();
            Thread.sleep(TWOSECOND);
            
            printWithDelays("Over the past few months you have came up with a master plan of", STANDARDDELAY);
            printWithDelays("how to take down ISIS.", STANDARDDELAY);
            printWithDelays("Keeping in mind that anonymity is of the utmost priority, you must...", STANDARDDELAY);
            printWithDelays("   1) Set up a secure environment", STANDARDDELAY);
            printWithDelays("   2) Install hacking tools as you find them", STANDARDDELAY);
            printWithDelays("   3) Take out ISIS's primary funding sources", STANDARDDELAY);
            printWithDelays("   4) Deliver the final blow to ISIS", STANDARDDELAY);
            System.out.println();
            Thread.sleep(TWOSECOND);
            
            printWithDelays("With that in mind, I wish you good luck.", STANDARDDELAY);
            printWithDelays("We're all counting on you.", 1); //200
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
            
            printWithDelays("", STANDARDDELAY);
            Thread.sleep(ONESECOND);
            
            printWithDelays("To complete this mission, you will need to use two tools: tor and connect", STANDARDDELAY);
            printWithDelays("To use tor, install it like any other program.", STANDARDDELAY);
            printWithDelays("Connect comes pre-installed, so you do not have to install it.", STANDARDDELAY);
            Thread.sleep(ONESECOND);
            System.out.println();
            
            printWithDelays("See you soon... Hopefully...", STANDARDDELAY);
            Thread.sleep(ONESECOND);
            System.out.println();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public void anonForum(int log) {
        try {
            System.out.println("\t" + "\t" + "************ NEW LIVETYPE IRC @ CHANNEL --> #0p1s1s ************");
            System.out.println();
            switch (log) {
                case 1:
                    System.out.print(new Clock().getTime() + "| d1ngledino: ");
                    printWithDelays("he seems like a nice guy, but he's a little slow on the uptake, " + 
                            "if you know what i mean...", STANDARDDELAY);
                    Thread.sleep(ONESECOND);
                    
                    System.out.print(new Clock().getTime() + "| XxT0rvaldsxX: ");
                    printWithDelays("yeah i get it", STANDARDDELAY);
                    Thread.sleep(TWOSECOND);
                            
                    System.out.print(new Clock().getTime() + "| > user pudlow3 joined #0p1s1s");
                    Thread.sleep(ONESECOND);
                    System.out.println();
                    
                    System.out.print(new Clock().getTime() + "| d1ngledino: ");
                    printWithDelays("oh hey, you're here... finally...", STANDARDDELAY);
                    Thread.sleep(ONESECOND);
                    
                    System.out.print(new Clock().getTime() + "| d1ngledino: ");
                    printWithDelays("pudlow3? i couldn't have come up with a weirder name myself. ", STANDARDDELAY);
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
            }
        } catch (InterruptedException e) {
            
        }
    }
   
    private void printWithDelays(String data, long delay) throws InterruptedException {
        for (char ch : data.toCharArray()) {
            System.out.print(ch);
            Thread.sleep(delay);
        }
        System.out.println();
    }
    
    class Clock {
        public String getTime() {
            return (new SimpleDateFormat("HH:mm:ss").format(new Date()));
        }
    }
}