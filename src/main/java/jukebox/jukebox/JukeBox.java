package jukebox.jukebox;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JukeBox {
    public static void main(String[] args) {
        if (args.length == 0) {
            launchGUI(); // GUI mode if no arguments are provided
        } else {
            handleCommandLineArgs(args); // Command-line mode
        }
    }

    // Encapsulated GUI launching into a separate method for cleaner structure
    private static void launchGUI() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            Window window = new Window();
            window.setVisible(true);
        });
    }
    // Encapsulated command-line handling into a separate method for readability
    private static void handleCommandLineArgs(String[] args) {
        checkArguments jukebox = new checkArguments(2);

        if (!jukebox.isValidArgCount(args.length)) {
            System.out.println(">>> Argument requirement not met");
            System.exit(1);
        }

        Song song = new Song(args[0]);

        if (args.length == 1) { // If only one argument is provided (a file or playlist)
            if (jukebox.isValidFileType(args[0])) {
                song.play(args[0]); // Play single song
            } else if (jukebox.isValidPlaylistType(args[0])) {
                song.order(); // Play playlist in order
            } else {
                System.out.println(">>> File not supported");
            }
        } else { // If two arguments are provided (file + strategy)
            String strategy = jukebox.getValidStrategy(args[1]);

            if (jukebox.isValidFileType(args[0])) {
                if ("loop".equals(strategy)) {
                    song.loop(); // Loop single song
                } else {
                    jukebox.handleInvalidArguments(args[1], args[0]); // Handle other strategies
                }
            } else if (jukebox.isValidPlaylistType(args[0])) {
                switch (strategy) { // Replaced repetitive if-else with switch-case
                    case "random":
                        song.random(); // Play playlist in random order
                        break;
                    case "order":
                        song.order(); // Play playlist in order
                        break;
                    case "loop":
                        song.loop(); // Loop playlist
                        break;
                    default:
                        jukebox.handleInvalidArguments(args[1], args[0]); // Handle invalid strategy
                        break;
                }
            } else {
                System.out.println(">>> File not supported");
            }
        }
        song.closePlayer(); // Ensure player is closed after execution
    }
}
