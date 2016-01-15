/*
 * Kevin Dixson
 */
import java.util.*;

public class Firewall {
    
    private int level, key;
    private String[] level0Passwords = new String[] {
        "password", "13456", "qwertyuiop", "muchsecurity", "ihatepasswords"
    };
    private String[] level1Passwords = new String[15];
    private Random rand = new Random();
    
    public Firewall(int level) {
        this.level = level;
        for (int x = 0; x < level1Passwords.length; x++) {
            level1Passwords[x] = makePassword();
        }
        key = (rand.nextInt(level1Passwords.length));
    }
    
    public String makePassword() {
        String dictionary = "abcdefghijklmnopqrstuvwxyz";
        String password = "";
        for (int x = 0; x < 5; x++) {
            password += (dictionary.charAt(rand.nextInt(dictionary.length())));
        }
        return password;
    }
    
    public String getPasswordList() {
        String returnString = "";
        switch (level) {
            case 0:
                for (int x = 0; x < level0Passwords.length; x++) {
                    returnString += level0Passwords[x] + "    ";
                }
                return returnString;
                
            case 1:
                for (int x = 0; x < level1Passwords.length; x++) {
                    returnString += level1Passwords[x] + "    ";
                }
                return returnString;
        }
        return null;
    }
    
    public boolean checkPassword(String guess) {
        switch (level) {
            case 0:
                if (guess.equals("muchsecurity")) {
                    return true;
                } else {
                    return false;
                }
                
            case 1:
                if (guess.equals(level1Passwords[key])) {
                    return true;
                } else {
                    return false;
                }
        }
        return false;
    }
}