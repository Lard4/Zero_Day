/*
 * Kevin Dixson
 */

public class Firewall {
    
    private int level;
    private String[] level0Passwords = new String[] {
        "password", "13456", "qwertyuiop", "muchsecurity", "ihatepasswords"
    };
    
    public Firewall(int level) {
        this.level = level;
    }
    
    public String getPasswordList() {
        switch (level) {
            case 0:
                String returnString = "";
                for (int x = 0; x < level0Passwords.length; x++) {
                    returnString += level0Passwords[x] + "    ";
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
        }
        return false;
    }
}