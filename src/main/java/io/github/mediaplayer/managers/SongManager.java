package io.github.mediaplayer.managers;

import io.github.mediaplayer.data.Music;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class SongManager {

    private MediaPlayer mediaPlayer;
    private final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    private boolean isLooped;
    private double volume = 1.0;

    public SongManager(Media media) {
        this.mediaPlayer = new MediaPlayer(media);
    }

    public void playMusic(Music music) {
        if (mediaPlayer != null) mediaPlayer.stop();
        this.mediaPlayer = new MediaPlayer(music.media());
        this.mediaPlayer.setVolume(this.volume);
        this.mediaPlayer.play();
        scheduledExecutorService.schedule(() -> mediaPlayer.setCycleCount((isLooped ? MediaPlayer.INDEFINITE : 1)), 1, TimeUnit.SECONDS);
    }

    public void changeLoop() {
        this.isLooped = !isLooped;
        this.mediaPlayer.setCycleCount((isLooped ? MediaPlayer.INDEFINITE : 1));
    }

    public void setVolume(double volume) {
        this.volume = volume;
        this.mediaPlayer.setVolume(volume);
    }

    public void pause() {
        this.mediaPlayer.pause();
    }

    public void resume() {
        this.mediaPlayer.play();
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public boolean isLooped() {
        return isLooped;
    }
}
