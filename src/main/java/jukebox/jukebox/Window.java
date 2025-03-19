package jukebox.jukebox;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingWorker;
import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    
    private final int NO_FILES_OPENED = -1;
    private final int NEW_FILE_OPENED = -2;
    private final int SONG_IS_PAUSED = -3;
    private final int SONG_IS_STOPPED = -4;

    private static boolean FirstTime = true;
    private int flag;
    private Song song;
    private String path;
    private String[] names;

    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;

    private javax.swing.JButton jButton1; // play
    private javax.swing.JButton jButton2; // next
    private javax.swing.JButton jButton3; // pause
    private javax.swing.JButton jButton4; // stop
    private javax.swing.JButton jButton5; // open file

    private javax.swing.JList<String> songList;
    private javax.swing.DefaultListModel<String> songListModel;
    private javax.swing.JScrollPane songScrollPane;

    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    public static javax.swing.JLabel jLabel4;

    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;

    public static javax.swing.JTextArea jTextArea1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;


    // Clears the names array
    private void clearNames() {
        for (int i = 0; i < names.length; i++) {
            names[i] = "";
        }
    }

    public Window() {
        initComponents();
        names = new String[20];
        clearNames();
        flag = NO_FILES_OPENED;
        path = "";

    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jTextArea1 = new javax.swing.JTextArea();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JukeBox");
        setBackground(new java.awt.Color(179, 179, 179));
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(18, 18, 18));
        jPanel1.setPreferredSize(new Dimension(jPanel1.getWidth(), 100));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musical-note.png")));
        jLabel1.setPreferredSize(new java.awt.Dimension(512, 512));
        jLabel1.setRequestFocusEnabled(false);

        jToggleButton1.setBackground(new java.awt.Color(33, 33, 33));
        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/repeat.png")));
        jToggleButton1.setBorder(null);

        jToggleButton2.setBackground(new java.awt.Color(33, 33, 33));
        jToggleButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/random.png")));
        jToggleButton2.setBorder(null);

        jButton1.setBackground(new java.awt.Color(33, 33, 33));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/play-button.png")));
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() { //
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(33, 33, 33));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/next.png")));
        jButton2.setBorder(null);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(33, 33, 33));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pause.png")));
        jButton3.setBorder(null);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(33, 33, 33));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stop.png")));
        jButton4.setBorder(null);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(33, 33, 33));
        jLabel4.setFont(new java.awt.Font("Verdana", 1, 18));
        jLabel4.setForeground(new java.awt.Color(29, 185, 84));
        jLabel4.setText("");
        jLabel4.setBorder(null);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addGap(12, 12, 12)
                        .addComponent(jToggleButton1)
                        .addGap(12, 12, 12))
        );
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jToggleButton1)
                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jButton2)
                                                .addComponent(jButton1)
                                                .addComponent(jButton3)
                                                .addComponent(jButton4)
                                                .addComponent(jToggleButton2))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(83, 83, 83));

        jLabel2.setBackground(new java.awt.Color(83, 83, 83));
        jLabel2.setFont(new java.awt.Font("Verdana", 1, 16));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("New Playlist");

        jButton5.setBackground(new java.awt.Color(83, 83, 83));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/file-explorer.png")));
        jButton5.setBorder(null);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        // Scroll pane for better usability
        songScrollPane = new JScrollPane(songList);
        songScrollPane.setPreferredSize(new Dimension(200, 400));
        // Initialize JList for the playlist
        songListModel = new DefaultListModel<>();
        songList = new JList<>(songListModel);
        songList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        songList.setBackground(new Color(50, 50, 50));
        songList.setForeground(Color.WHITE);
        songList.setFont(new Font("Verdana", Font.PLAIN, 14));
        songList.setFixedCellHeight(30);

// Scroll pane for better usability
        songScrollPane = new JScrollPane(songList);
        songScrollPane.setPreferredSize(new Dimension(200, 400));

// 📌 ADD THE LISTENER AFTER INITIALIZATION
        songList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            @Override
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                songListValueChanged(evt);
            }
        });

// Add JList to jPanel2 instead of buttons
        jPanel2.setLayout(new BorderLayout());
        jPanel2.add(jLabel2, BorderLayout.NORTH); // "New Playlist" Label
        jPanel2.add(songScrollPane, BorderLayout.CENTER); // Add scrollable song list
        jPanel2.add(jButton5, BorderLayout.SOUTH); // Keep Open File button

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButton5)
                                                .addGap(0, 4, Short.MAX_VALUE))
                                        .addComponent(songScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jButton5)
                                        .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(songScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(18, 18, 18));

        jLabel3.setBackground(new java.awt.Color(18, 18, 18));
        jLabel3.setFont(new java.awt.Font("Verdana", 1, 21));
        jLabel3.setForeground(new java.awt.Color(29, 185, 84));
        jLabel3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jLabel3.setText("Status Console");
        jLabel3.setBorder(null);

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBorder(null);

        jTextArea1.setBackground(new java.awt.Color(18, 18, 18));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Verdana", 0, 14));
        jTextArea1.setForeground(new java.awt.Color(255, 255, 255));
        jTextArea1.setRows(5);
        jTextArea1.setText("");
        jTextArea1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextArea1.setEditable(false);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jScrollPane1)
                                        .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.CENTER))
                                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1)
                                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(102, 102, 102));
        jPanel4.setPreferredSize(new Dimension(0, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setSize(550, 659);
        setLocationRelativeTo(null);
    }


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        if (path.equals("") || names[0].equals("")) {
            return;
        }

        if (!"IDLE".equals(song.getStatus())) {
            checkArguments Arg = new checkArguments();
            if (flag == SONG_IS_PAUSED) {
                song.resume();
                jTextArea1.setText("Player Status : Resumed ");
            } else if (flag == SONG_IS_STOPPED && (Arg.isValidPlaylistType(path) || Arg.isValidDirectory(path))) {
                song.stop();
                flag = song.getCurrentSongIndex();

                if (jToggleButton1.isSelected() && !jToggleButton2.isSelected()) {
                    song.loopGUI();
                } else if (jToggleButton2.isSelected() && !jToggleButton1.isSelected()) {
                    song.randomGUI();
                } else {
                    song.orderGUI();
                }
            } else if (flag == SONG_IS_STOPPED && (Arg.isValidFileType(path))) {
                song.stop();
                flag = 0;
                song.playGUI(names[flag]);
            }
        }
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        if (flag != NO_FILES_OPENED && flag != NEW_FILE_OPENED) {
            if (!"IDLE".equals(song.getStatus())) {
                checkArguments Arg = new checkArguments();
                if (Arg.isValidPlaylistType(path) || Arg.isValidDirectory(path)) {
                    int num = (song.getCurrentSongIndex() + 1) % song.getPlayList().size();
                    song.stop();
                    flag = num;
                } else if (Arg.isValidFileType(path)) {
                    song.stop();
                }
                jTextArea1.setText("Player Status : Skipping To Next Song");
            }
        }
    }


    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        if (flag != NO_FILES_OPENED && flag != NEW_FILE_OPENED) {
            if (!"IDLE".equals(song.getStatus())) {
                if (flag != SONG_IS_STOPPED) {
                    song.pause();
                    flag = SONG_IS_PAUSED;
                    jTextArea1.setText("Player Status : Paused");
                }
            }
        }
    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        if (flag != NO_FILES_OPENED && flag != NEW_FILE_OPENED) {
            if (!"IDLE".equals(song.getStatus())) {
                song.pause();
                flag = SONG_IS_STOPPED;
                jTextArea1.setText("Player Status : Stopped");
            }
        }
    }


    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            songListModel.clear();
            clearNames();
            File file = fileChooser.getSelectedFile();
            path = file.getPath();
            song = new Song(path);
            flag = NEW_FILE_OPENED;

            if (this.song != null) {
                song.pause();
            }

            jTextArea1.setText("Player Status : New Playlist");

            checkArguments Arg = new checkArguments();
            if (Arg.isValidPlaylistType(path) || Arg.isValidDirectory(path)) {
                if (song.getPlayList().size() < 21) {
                    for (String songPath : song.getPlayList()) {
                        names[songListModel.getSize()] = songPath;
                        String songName = new File(songPath).getName();
                        songName = songName.substring(0, songName.lastIndexOf("."));

                        if (songName.length() > 15) {
                            songName = songName.substring(0, 12) + "...";
                        }

                        songListModel.addElement(songName);
                    }
                } else {
                    jTextArea1.setText("Player Status : Playlist Has Too Many Songs");
                }
            } else if (Arg.isValidFileType(path)) {
                names[0] = path;
                String songName = file.getName();
                songName = songName.substring(0, songName.lastIndexOf("."));

                if (songName.length() > 15) {
                    songName = songName.substring(0, 12) + "...";
                }

                songListModel.addElement(songName);
            }
        }
    }


    private void songListValueChanged(javax.swing.event.ListSelectionEvent evt) {
        if (!evt.getValueIsAdjusting()) {
            int selectedIndex = songList.getSelectedIndex();
            if (selectedIndex == -1) return;

            checkArguments Arg = new checkArguments();

            if (flag == SONG_IS_PAUSED) {
                song.stop();
            } else if ((flag != selectedIndex && flag != NO_FILES_OPENED) || flag == NEW_FILE_OPENED) {
                song.stop();
            }

            if (FirstTime) {
                song.loggers();
                FirstTime = false;
            }

            if (Arg.isValidFileType(path)) { // If it's a single song
                if (flag == selectedIndex) {
                    song.stop();
                }
                if (jToggleButton1.isSelected()) {
                    song.loopGUI();
                } else {
                    song.playGUI(names[selectedIndex]);
                }
            } else if (Arg.isValidPlaylistType(path) || Arg.isValidDirectory(path)) { // If it's a playlist
                if (flag == selectedIndex) {
                    song.stop();
                }
                song.setCurrentSongIndex(selectedIndex);
                flag = selectedIndex;

                if (jToggleButton1.isSelected() && !jToggleButton2.isSelected()) {
                    song.loopGUI();
                } else if (jToggleButton2.isSelected() && !jToggleButton1.isSelected()) {
                    song.randomGUI();
                } else {
                    song.orderGUI();
                }
            }
        }
    }
}
