/*
 * Kevin Dixson
 * 12-9-15
 */
import java.util.*;

public class Directory {
    private String path;
    private int level;
    
    private HashMap <String, Directory> exits;
    private String[][] validDirectories;
    private ArrayList <Tool> tools = new ArrayList <Tool>();
    
    public Directory(String path) {
        this.path = path;
        validDirectories = new String[4][4];
        
        for (int row = 0; row < validDirectories.length; row++) {
            for (int col = 0; col < validDirectories[row].length; col++) {
                validDirectories[row][col] = "";
            }
        }
        setValidDirectories(false);
        exits = new HashMap<String, Directory>();
    }
    
    public void setValidDirectories(boolean isRoot) {
        if (isRoot) {
            validDirectories[0][0] = "root";
            validDirectories[0][1] = "etc";
        } else {
            validDirectories[1][0] = "pictures";
                validDirectories[1][1] = "backgrounds";
            
            validDirectories[2][0] = "music";
                validDirectories [2][1] = "beyonce";
            
            validDirectories[3][0] = "desktop";
        }
    }
    
    public boolean directoryIsParent(String newDirectory) {
        boolean isParent = false;
        
        for (int row = 0; row < validDirectories.length; row++) {
            if (validDirectories[row][0].equals(newDirectory)) {
                return true;
            } else {
                isParent = false;
            }
        }
        return isParent;
    }

    public String getExitString() {
        String exitString = "Exits: ";
        
        Set <String> keys = exits.keySet();
        for (String exit : keys) {
            exitString += " " + exit;
        }
        
        return exitString;
    }
    
    public boolean directoryExists(String newDirectory, int fileLevel) {
        boolean exists = false;
        
        for (int row = 0; row < validDirectories.length; row++) {
            if (validDirectories[row][0].equals(newDirectory)) {
                exists = true;
            } else {
                for (int col = 0; col < validDirectories[row].length; col++) {
                    if (validDirectories[row][col].equals(newDirectory)) {
                        if(fileLevel == row){
                            exists = true;
                        }
                    }
                }
            }
        }
        
        return exists;
    }
    
    public int[][] getDirectoryLocation(String directory) {
        int[][] location = new int[validDirectories.length][validDirectories[0].length];
        
        for (int row = 0; row < location.length; row++) {
            for (int col = 0; col < location[row].length; col++) {
                location[row][col] = 0;
            }
        }
        
        for (int row = 0; row < location.length; row++) {
            if (validDirectories[row][0].equals(directory)) {
                location[row][0] = 314;
            } else {
                for (int col = 0; col < location[row].length; col++) {
                    if (validDirectories[row][col].equals(directory)) {
                        location[row][col] = 314;
                    }
                }
            }
        }
        return location;
    }
    
    public int[][] goBack(int[][] workingDirectory, int fileLevel) {
        int targetRow = 0;
        int targetCol = 0;
        int[][] newPath = new int[4][4];
        
        for (int row = 0; row < newPath.length; row++) {
            for (int col = 0; col < newPath[row].length; col++) {
                newPath[row][col] = 0;
            }
        }
        
        for (int row = 0; row < workingDirectory.length; row++) {
            for (int col = 0; col < workingDirectory[row].length; col++) {
                if (workingDirectory[row][col] == 314) {
                    switch (row) {
                        case 0:
                            path = "/";
                            break;
                            
                        case 1:
                            if (col == 0) {
                                path = "/";
                            } else if (col == 1) {
                                path = "/pictures";
                            }
                            break;
                            
                        case 2:
                            if (col == 0) {
                                path = "/";
                            } else if (col == 1) {
                                path = "/music";
                            }
                            break;
                            
                        case 3:
                            if (col == 0) {
                                path = "/";
                            } else if (col == 1) {
                                path = "/desktop";
                            }
                            break;
                    }
                }
            }
        }
        
        for (int row = 0; row < workingDirectory.length; row++) {
            for (int col = 0; col < workingDirectory[row].length; col++) {
                if (workingDirectory[row][col] == 314) {
                    targetRow = row;
                    targetCol = col - 1;
                    if (targetCol < 0) {
                        newPath[0][0] = 314; // User is at top and wants to go to /
                    } else {
                        newPath[targetRow][targetCol] = 314;
                    }
                    return newPath;
                }
            }
        }
        return newPath;
    }
    
    public void addTool(Tool tool) {
        tools.add(tool);
    }
    
    public void setPath(String newDirectory, boolean append) {
        if (append) {
            path += "/" + newDirectory;
        } else {
            path = "/" + newDirectory;
        }
    }
    
    public String getPath() {
        return ("Current path: " + path);
    }
}