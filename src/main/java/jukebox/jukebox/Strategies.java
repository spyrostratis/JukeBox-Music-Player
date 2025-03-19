package jukebox.jukebox;

public interface  Strategies {
    
    void play(String name);  // Plays the given song

    void loop();  // Plays a song or playlist in a continuous loop
    
    void order();  // Plays all songs in the playlist in order
    
    void random();  // Plays all songs in the playlist in a random order
    
    void closePlayer();  // Closes the player and stops playback
    
}
