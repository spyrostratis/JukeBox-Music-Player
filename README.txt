# JukeBox - Music Player

### Version: 1.0.0

## What is JukeBox?
JukeBox is a powerful and intuitive music player that lets you play, manage, and enjoy your favorite audio files effortlessly. It supports both **command-line execution** and an intuitive **graphical interface (GUI)** for easy use.

---

## Installation & Compilation

### Clone the Repository
First, download or clone the repository:
```sh
git clone https://github.com/spyrostratis/JukeBox-Music-Player.git
cd JukeBox-Music-Player
```

### Compile the Application
To compile JukeBox, open a terminal inside the project directory and run:
```sh
mvn package
```
Once the process completes, a **`target/`** directory will be created containing the **JAR executable**.

---

## Running JukeBox

### Command-Line Execution
To run JukeBox via the terminal:
```sh
java -jar target/JukeBox-1.0.0-RELEASE.jar "path/to/music/folder"
```
**Example:**
```sh
java -jar target/JukeBox-1.0.0-RELEASE.jar "Music/"
```
This will play all **MP3 files** inside the specified directory.

#### 🛠 How to Use (CLI Mode)
JukeBox accepts **one or two arguments**:
- **First Argument** → The path to an **audio file** or **playlist folder**.
- **Second Argument** *(optional)* → A **playback strategy** (see strategies below).

**Example Usage:**
```sh
java -jar target/JukeBox-1.0.0-RELEASE.jar "Music/" random
```

#### Notes:
- If only a **file** or **playlist folder** is provided, it will **play in order**.
- A **playlist** can be an `.m3u` file or a **directory** containing music.
- Stop playback by closing the terminal.

---

## GUI Mode - Graphical Interface

### Launching the GUI
You can start the GUI mode by:
- Running from the console:
  ```sh
  java -jar target/JukeBox-1.0.0-RELEASE.jar
  ```
- Navigating to the `target/` folder and double-clicking the `.jar` file.

### Using the GUI
1. Click the **File Explorer** icon to add an **audio file, playlist, or folder** (max 20 files).
2. Select a song from the list to **start playback**.
3. Use the **control buttons** at the bottom for playback options.

### Playback Controls
| Button | Function |
|--------|----------|
| 🔀 **Shuffle** | Randomizes playback order |
| ⏹ **Stop** | Stops playback |
| ⏸ **Pause** | Pauses playback |
| ▶ **Play** | Resumes playback |
| ⏭ **Skip** | Jumps to the next track |
| 🔁 **Repeat** | Loops playback |

 *Note:* Shuffle and Repeat modes require re-selection of a song to take effect.

---

## Playback Strategies
**For any audio file:**
- `loop` → **Loops** the current file or entire playlist.

**For playlists only:**
- `order` → Plays the playlist in **sequential order**.
- `random` → Plays the playlist in **random order**.

---

## Supported Formats (v1.0.0)

| Type          | Formats Supported |
|--------------|------------------|
| 🎶 Audio Files  | `.mp3`  |
| 📂 Playlists   | `.m3u`, Directories |

---

## Final Notes
- All playback actions are logged in the **Status Console** and **Terminal**.
- Close the window (❌) to exit GUI mode.

🚀 Enjoy your music with JukeBox!
