package jukebox.jukebox;

import java.io.*;
import java.util.*;
import java.util.regex.*;

// Processes M3U playlists and directories
public class Parser {

    private final ArrayList<String> playList; // Stores absolute paths of songs
    private final String filename; // The M3U file path or directory
    private final checkArguments argValidator; // Argument validation

    public Parser(String filename, checkArguments argValidator) {
        this.playList = new ArrayList<>();
        this.filename = filename;
        this.argValidator = argValidator;
    }

    // Returns the absolute paths of songs from the M3U file or directory
    public ArrayList<String> getPlayList() {
        try {
            File file = new File(filename);

            // If it's a directory, scan and add valid MP3 files
            if (argValidator.isValidDirectory(filename)) {
                File[] contentsOfDirectory = file.listFiles();
                if (contentsOfDirectory != null) {
                    for (File temp : contentsOfDirectory) {
                        String filePath = temp.getPath();
                        if (argValidator.isValidFileType(filePath)) {
                            playList.add(filePath);
                        }
                    }
                }
            }
            // If it's an M3U playlist, process each line
            else if (argValidator.isValidPlaylistType(filename)) {
                String parentDir = file.getParent();
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    addMP3(line, parentDir);
                }
                br.close();
            }
        } catch (IOException e) {
            System.err.println(">>> Error processing file: " + e.getMessage());
        }
        return playList;
    }

    // Adds an MP3's absolute path to the playlist
    private void addMP3(String line, String parentDir) {
        if (line.isEmpty() || line.startsWith("#")) return; // Ignore comments & empty lines

        File path = new File(line);
        String filePath = path.getPath();

        // If the line contains only a filename, prepend parent directory
        if (!line.contains("/") && !line.contains("\\")) {
            playList.add(parentDir + File.separator + line);
        }
        // If it's a relative path, join it with the parent directory
        else if (!Pattern.compile("[:]|^[/|\\\\]").matcher(line).find()) {
            playList.add(parentDir + File.separator + filePath);
        }
        // If it's an absolute path, add it as is
        else {
            playList.add(line);
        }
    }
}
