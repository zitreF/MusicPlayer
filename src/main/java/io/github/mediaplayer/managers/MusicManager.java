package io.github.mediaplayer.managers;

import io.github.mediaplayer.data.Music;
import javafx.scene.media.Media;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public final class MusicManager {

    private final Set<Music> musics;

    public MusicManager() {
        this.musics = new HashSet<>();
    }

    public void load() throws IOException {
        if (!Files.isDirectory(Paths.get(System.getProperty("user.home")+"\\AppData\\Roaming\\songs"))) Files.createDirectory(Paths.get(System.getProperty("user.home")+"\\AppData\\Roaming\\songs"));
        Files.walk(Paths.get(System.getProperty("user.home")+"\\AppData\\Roaming\\songs"))
                .filter(Files::isRegularFile)
                .filter(path -> path.toString().endsWith(".mp3"))
                .forEach(file -> {
                    musics.add(new Music(file.getFileName().toString().replace(".mp3", ""), new Media(file.toUri().toString())));
                });
    }

    public Set<Music> getMusics() {
        return musics;
    }
}
