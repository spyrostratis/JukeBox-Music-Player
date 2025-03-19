package jukebox.jukebox;

import java.io.*;
import java.util.*;
import gr.hua.dit.oop2.musicplayer.*;
import java.security.SecureRandom;
import java.util.logging.*;

public class Song implements Strategies {

    private static final Logger logger = Logger.getLogger(Song.class.getName());
    public static int threadCount = 0;

    private int currentSongIndex;
    private final checkArguments argValidator;
    private final Parser parser;
    private final Player player;
    private final String name;
    private final String absolutePath;
    private final String path;
    private final String parentPath;
    private final String fileInput;
    private ArrayList<String> playList = null;
    private InputStream stream;

    public Song(String fileInput) {
        this.fileInput = fileInput;
        this.currentSongIndex = 0;
        this.argValidator = new checkArguments();
        this.parser = new Parser(fileInput, argValidator);
        this.player = PlayerFactory.getPlayer();

        File f = new File(fileInput);
        this.name = f.getName();
        this.absolutePath = f.getAbsolutePath();
        this.path = f.getPath();
        this.parentPath = f.getParent();

        if (argValidator.isValidPlaylistType(fileInput) || argValidator.isValidDirectory(fileInput)) {
            this.playList = parser.getPlayList();
        }
    }

    public int getCurrentThreadCount() {
        return threadCount;
    }

    public void setCurrentSongIndex(int index) {
        this.currentSongIndex = index;
    }

    public int getCurrentSongIndex() {
        return this.currentSongIndex;
    }

    public String getName() {
        return this.name;
    }

    public String getAbsolutePath() {
        return this.absolutePath;
    }

    public String getPath() {
        return this.path;
    }

    public String getParentPath() {
        return this.parentPath;
    }

    public ArrayList<String> getPlayList() {
        return this.playList;
    }

    @Override
    public void play(String name) {
        try {
            File file = new File(name);
            if (file.exists()) {
                System.out.println(">>> PLAYING: " + file.getName());
            }
            player.play(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            System.err.println(">>> Error: File not found - " + e.getMessage());
        } catch (PlayerException e) {
            System.err.println(">>> Error: Player encountered an issue - " + e.getMessage());
        }
    }

    @Override
    public void loop() {
        while (true) {
            if (argValidator.isValidFileType(fileInput)) {
                play(absolutePath);
            } else if (argValidator.isValidPlaylistType(fileInput) || argValidator.isValidDirectory(fileInput)) {
                order();
            }
        }
    }

    @Override
    public void order() {
        for (String song : playList) {
            play(song);
        }
    }

    @Override
    public void random() {
        SecureRandom rand = new SecureRandom();
        ArrayList<String> shuffledPlaylist = new ArrayList<>(playList);

        for (int i = shuffledPlaylist.size() - 1; i > 0; i--) {
            int index = rand.nextInt(i + 1);
            String temp = shuffledPlaylist.get(i);
            shuffledPlaylist.set(i, shuffledPlaylist.get(index));
            shuffledPlaylist.set(index, temp);
        }

        for (String song : shuffledPlaylist) {
            play(song);
        }
    }

    @Override
    public void closePlayer() {
        player.close();
    }

    public String getStatus() {
        return player.getStatus().name();
    }

    public void stop() {
        player.stop();
    }

    public void pause() {
        player.pause();
    }

    public void resume() {
        player.resume();
    }

    public void startPlaying(String name) {
        try {
            File file = new File(name);
            if (file.exists()) {
                System.out.println(">>> CURRENT SONG: " + file.getName());
                Window.jLabel4.setText(trimSongTitle(file.getName()));
            }
            stream = new FileInputStream(file);
            player.startPlaying(stream);
        } catch (FileNotFoundException | PlayerException e) {
            logger.severe(e.getMessage());
        }
    }

    public void playGUI(String name) {
        new Thread(() -> {
            threadCount++;
            startPlaying(name);
            Window.jTextArea1.setText("Player Status : Playing");
            waitForIdle();
            threadCount--;
        }).start();
    }

    public void orderGUI() {
        new Thread(() -> {
            threadCount++;
            for (; currentSongIndex < playList.size(); currentSongIndex++) {
                startPlaying(playList.get(currentSongIndex));
                Window.jTextArea1.setText("Player Status : Playing");
                waitForIdle();
            }
            threadCount--;
        }).start();
    }

    public void loopGUI() {
        new Thread(() -> {
            while (true) {
                threadCount++;
                if (argValidator.isValidFileType(path)) {
                    startPlaying(absolutePath);
                } else if (argValidator.isValidPlaylistType(path) || argValidator.isValidDirectory(path)) {
                    orderGUI();
                }
                threadCount--;
            }
        }).start();
    }

    public void randomGUI() {
        new Thread(() -> {
            threadCount++;
            SecureRandom rand = new SecureRandom();
            ArrayList<String> shuffledPlaylist = new ArrayList<>(playList);

            for (int i = shuffledPlaylist.size() - 1; i > 0; i--) {
                int index = rand.nextInt(i + 1);
                String temp = shuffledPlaylist.get(i);
                shuffledPlaylist.set(i, shuffledPlaylist.get(index));
                shuffledPlaylist.set(index, temp);
            }

            for (String song : shuffledPlaylist) {
                startPlaying(song);
                Window.jTextArea1.setText("Player Status : Playing");
                waitForIdle();
            }
            threadCount--;
        }).start();
    }

    private void waitForIdle() {
        while (!"IDLE".equals(player.getStatus().name())) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                logger.log(Level.SEVERE, null, ex);
            }
            if (threadCount > 1) {
                threadCount--;
                return;
            }
        }
    }

    private String trimSongTitle(String title) {
        if (title.length() > 15) {
            return title.substring(0, 12) + "...";
        }
        return title;
    }

    public void loggers() {
        Handler handlerObj = new ConsoleHandler();
        handlerObj.setLevel(Level.INFO);
        logger.addHandler(handlerObj);
        logger.setLevel(Level.INFO);
        logger.setUseParentHandlers(false);

        player.addPlayerListener(e -> logger.info("Status changed to " + e.getStatus()));
        player.addProgressListener(e -> logger.fine("Seconds: " + (e.getMicroseconds() / 1_000_000.0f)));
    }
}
