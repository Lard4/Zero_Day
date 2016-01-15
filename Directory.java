/*
 * Kevin Dixson
 * 12-9-15
 */
import java.util.*;

public class Directory {
    private String path;
    private String currentDir;
    private int level;
    private ArrayList <ArrayList<ArrayList<String>>> validDirectoriesRoot = 
            new ArrayList <ArrayList<ArrayList<String>>>(); // Good lawd
    
    private ArrayList <ArrayList<String>> root = new ArrayList <ArrayList<String>>();
        // ARRAYLISTS BELOW HERE AT THIS INDENTATION ARE IN / AND HAVE NO CHILDREN DIRECTORIES!!!
        private ArrayList <String> etc = new ArrayList <String>();
        private ArrayList <String> malware = new ArrayList <String>();
        
    private ArrayList <ArrayList<String>> firewalls = new ArrayList <ArrayList<String>>();
        private ArrayList <String> firewall_files = new ArrayList <String>();
        
    private ArrayList <ArrayList<String>> pictures = new ArrayList <ArrayList<String>>();
        private ArrayList <String> backgrounds = new ArrayList <String>();
    
    private ArrayList <ArrayList<String>> music = new ArrayList <ArrayList<String>>();
        private ArrayList <String> beyonce = new ArrayList <String>();
        
    private ArrayList <ArrayList<String>> desktop = new ArrayList <ArrayList<String>>();
        private ArrayList <String> my_stuff = new ArrayList <String>();
    
    private ArrayList <Tool> tools = new ArrayList <Tool>();
    
    private CommandWords commandWords = new CommandWords();
    
    public Directory(String path) {
        this.path = path;
        currentDir = "/";
        setValidDirectories(false);
    }
    
    public void addFile(String directory, String newFile) {
        switch (directory) {
            case "etc":
                etc.add(newFile);
                root.add(etc);
                validDirectoriesRoot.add(root);
                break;
                
            case "malware":
                malware.add(newFile);
                root.add(malware);
                validDirectoriesRoot.add(root);
                break;
                
            case "firewall_files":
                firewall_files.add(newFile + ".srvr.frw");
                firewalls.add(firewall_files);
                validDirectoriesRoot.add(firewalls);
                break;
                
            case "backgrounds":
                backgrounds.add(newFile);
                pictures.add(backgrounds);
                validDirectoriesRoot.add(pictures);
                break;
                
            case "beyonce":
                beyonce.add(newFile);
                music.add(beyonce);
                validDirectoriesRoot.add(music);
                break;
                
            case "my_stuff":
                my_stuff.add(newFile);
                desktop.add(my_stuff);
                validDirectoriesRoot.add(desktop);
                break;
        }
    }
    
    public void setValidDirectories(boolean isRoot) {
        if (isRoot) {
            etc.add("anon_forum.srvr");
            etc.add("codered.vr");
            root.add(etc);
            
            malware.add("codered.vr");
            root.add(malware);
            
            firewall_files.add("");
            firewalls.add(firewall_files);
            
            validDirectoriesRoot.add(root);
            validDirectoriesRoot.add(firewalls);
        } else {
            backgrounds.add("family_trip.png");
            pictures.add(backgrounds);
            
            beyonce.add("beyonce_music.flac");
            music.add(beyonce);
            
            my_stuff.add("anon_root_pswd.txt");
            desktop.add(my_stuff);
            
            validDirectoriesRoot.add(pictures);
            validDirectoriesRoot.add(music);
            validDirectoriesRoot.add(desktop);
            // boolean brainExploded = truuuu;
        }
    }
    
    public boolean directoryIsParent(String newDirectory) {
        if (newDirectory.equals("root") || newDirectory.equals("pictures") || newDirectory.equals("music") || 
            newDirectory.equals("desktop")) {
            return true;
        } else {
            return false;
        }
    }
    
    public void addTool(Tool tool) {
        tools.add(tool);
    }
    
    public void setPath(String newDirectory) {
        switch (newDirectory) {
            case "":
                path = "/";
                currentDir = "/";
                break;
               
            /**  CAN ONLY CD FROM 'ROOT' **/
            case "pictures":
                path = "/pictures";
                currentDir = "pictures";
                break;
                
            case "music":
                path = "/music";
                currentDir = "music";
                break;
                
            case "desktop":
                path = "/desktop";
                currentDir = "desktop";
                break;
                
            case "firewalls":
                path = "/firewalls";
                currentDir = "firewalls";
                break;
            
            /**  CAN ONLY CD FROM RESPECTIVE PARENT **/    
            case "backgrounds":
                path = "/pictures/backgrounds";
                currentDir = "backgrounds";
                break;
                
            case "beyonce":
                path = "/music/beyonce";
                currentDir = "beyonce";
                break;
                
            case "my_stuff":
                path = "/desktop/my_stuff";
                currentDir = "my_stuff";
                break;
                
            case "etc":
                path = "/etc";
                currentDir = "etc";
                break;
                
            case "malware":
                path = "/malware";
                currentDir = "malware";
                break;
                
            case "firewall_files":
                path = "/firewalls/firewall_files";
                currentDir = "firewall_files";
                break;
        }
    }
    
    public void upOne() {
        switch (currentDir) {
            case "":
                path = "/";
                currentDir = "/";
                break;
               
            /**  CAN ONLY CD FROM 'ROOT' **/
            case "pictures":
                path = "/";
                currentDir = "/";
                break;
                
            case "music":
                path = "/";
                currentDir = "/";
                break;
                
            case "desktop":
                path = "/";
                currentDir = "/";
                break;
                
            case "firewalls":
                path = "/";
                currentDir = "/";
                break;
            
            /**  CAN ONLY CD FROM RESPECTIVE PARENT **/    
            case "backgrounds":
                path = "/pictures";
                currentDir = "pictures";
                break;
                
            case "beyonce":
                path = "/music";
                currentDir = "music";
                break;
                
            case "my_stuff":
                path = "/desktop";
                currentDir = "desktop";
                break;
                
            case "etc":
                path = "/";
                currentDir = "/";
                break;
                
            case "malware":
                path = "/";
                currentDir = "/";
                break;
                
            case "firewall_files":
                path = "/firewalls";
                currentDir = "firewalls";
                break;
        }
    }
    
    public String getDirectoryContents() {
        String returnString = "";
        
        switch (currentDir) {
            case "pictures":
                return "backgrounds";
                
            case "music":
                return "beyonce";
               
            case "desktop":
                return "my_stuff";
                
            case "firewalls":
                return "firewall_files";
                
            case "backgrounds":
                for (int x = 0; x < backgrounds.size(); x++) {
                    returnString += backgrounds.get(x) + "\n";
                }
                return returnString;
                
            case "beyonce":
                for (int x = 0; x < beyonce.size(); x++) {
                    returnString += beyonce.get(x) + "\n";
                }
                return returnString;
               
            case "my_stuff":
                for (int x = 0; x < my_stuff.size(); x++) {
                    returnString += my_stuff.get(x) + "\n";
                }
                return returnString;
                
            case "etc":
                for (int x = 0; x < etc.size(); x++) {
                    returnString += etc.get(x) + "\n";
                }
                return returnString;
                
            case "malware":
                for (int x = 0; x < malware.size(); x++) {
                    returnString += malware.get(x) + "\n";
                }
                return returnString;
                
            case "firewall_files":
                for (int x = 0; x < firewall_files.size(); x++) {
                    returnString += firewall_files.get(x) + "\n";
                }
                return returnString;
                
            default:
                return null;
        }
    }
    
    public boolean serverExists(String fileName) {
        if (firewall_files.contains(fileName)) {
            return true;
        } return false;
    }
    
    public String getCurrentDirectory() {
        return currentDir;
    }
    
    public String getPath() {
        return ("Current path: " + path);
    }
}