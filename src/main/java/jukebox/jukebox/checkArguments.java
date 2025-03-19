package jukebox.jukebox;

// Implements the Arguments interface
public class checkArguments implements Arguments {

    private static final String[] SUPPORTED_FILES = {"mp3"};
    private static final String[] SUPPORTED_PLAYLISTS = {"m3u"};
    private static final String[] SUPPORTED_STRATEGIES = {"loop", "random", "order"};

    private int maxArgs;  // Stores the maximum allowed arguments

    // Constructor to set the maximum number of arguments
    public checkArguments(int maxArgs) {
        this.maxArgs = maxArgs;
    }

    // Default constructor (allows calls without specifying maxArgs)
    public checkArguments() {
    }

    // Validates the number of arguments
    @Override
    public boolean isValidArgCount(int count) {
        return count >= 1 && count <= maxArgs;
    }

    // Checks if the strategy is valid
    @Override
    public String getValidStrategy(String strategyInput) {
        for (String strategy : SUPPORTED_STRATEGIES) {
            if (strategy.equalsIgnoreCase(strategyInput)) {
                return strategy.toLowerCase();
            }
        }
        return "";
    }

    // Checks if the file type is supported
    @Override
    public boolean isValidFileType(String filePath) {
        int dotIndex = filePath.lastIndexOf(".");
        if (dotIndex == -1) return false;  // No file extension found

        String fileExtension = filePath.substring(dotIndex + 1).toLowerCase();
        for (String type : SUPPORTED_FILES) {
            if (fileExtension.equals(type)) {
                return true;
            }
        }
        return false;
    }

    // Checks if the playlist type is supported
    @Override
    public boolean isValidPlaylistType(String listPath) {
        int dotIndex = listPath.lastIndexOf(".");
        if (dotIndex == -1) return false;  // No file extension found

        String fileExtension = listPath.substring(dotIndex + 1).toLowerCase();
        for (String type : SUPPORTED_PLAYLISTS) {
            if (fileExtension.equals(type)) {
                return true;
            }
        }
        return false;
    }



    // Checks if the input is a directory (not a file)
    public boolean isValidDirectory(String input) {
        return !input.substring(input.lastIndexOf("\\") + 1).contains(".");
    }

    // Handles invalid arguments
    @Override
    public void handleInvalidArguments(String strategy , String filePath) {
        if (getValidStrategy(strategy).isEmpty()) {
            System.out.println(">>> Error: Strategy '" + strategy  + "' not found.");
        } else if (isValidFileType(filePath)) {
            System.out.println(">>> Error: Cannot use strategy '" + strategy  + "' with a file.");
        } else if (isValidPlaylistType(filePath)) {
            System.out.println(">>> Error: Cannot use strategy '" + strategy  + "' with a playlist.");
        } else {
            System.out.println(">>> Error: Invalid input provided.");
        }
    }
}
