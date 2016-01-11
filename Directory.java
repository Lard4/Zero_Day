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
            new ArrayList <ArrayList<ArrayList<String>>>();
    
    private ArrayList <ArrayList<String>> root = new ArrayList <ArrayList<String>>();
        private ArrayList <String> etc = new ArrayList <String>();
        
    private ArrayList <ArrayList<String>> pictures = new ArrayList <ArrayList<String>>();
        private ArrayList <String> backgrounds = new ArrayList <String>();
    
    private ArrayList <ArrayList<String>> music = new ArrayList <ArrayList<String>>();
        private ArrayList <String> beyonce = new ArrayList <String>();
        
    private ArrayList <ArrayList<String>> desktop = new ArrayList <ArrayList<String>>();
        private ArrayList <String> myStuff = new ArrayList <String>();
    
    private ArrayList <Tool> tools = new ArrayList <Tool>();
    
    private CommandWords commandWords = new CommandWords();
    
    public Directory(String path) {
        this.path = path;
        currentDir = "/";
        setValidDirectories(false);
    }
    
    public void addFile(String newFile) {
        myStuff.add(newFile);
        desktop.add(myStuff);
        validDirectoriesRoot.add(desktop);
    }
    
    public void setValidDirectories(boolean isRoot) {
        if (isRoot) {
            etc.add("anon_forum.srvr");
            root.add(etc);
            validDirectoriesRoot.add(root);
        } else {
            backgrounds.add("family_trip.png");
            pictures.add(backgrounds);
            
            beyonce.add("beyonce_music.flac");
            music.add(beyonce);
            
            myStuff.add("zero_day.zip");
            desktop.add(myStuff);
            
            validDirectoriesRoot.add(pictures);
            validDirectoriesRoot.add(music);
            validDirectoriesRoot.add(desktop);
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
        }
    }
    
    public String getCurrentDirectory() {
        return currentDir;
    }
    
    public String getPath() {
        return ("Current path: " + path);
    }
}