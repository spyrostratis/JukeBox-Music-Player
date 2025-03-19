package jukebox.jukebox;

// Defines methods for argument validation
public interface Arguments {

    boolean isValidArgCount(int count);  // Checks if the number of arguments is valid

    boolean isValidFileType(String filePath);  // Checks if the file type is supported

    String getValidStrategy(String strategyInput);  // Returns a valid strategy (or empty string if invalid)

    boolean isValidPlaylistType(String listPath);  // Checks if the playlist type is supported

    void handleInvalidArguments(String strategyInput, String filePath);  // Handles special cases
}