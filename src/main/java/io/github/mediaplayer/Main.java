package io.github.mediaplayer;

import io.github.mediaplayer.managers.MusicManager;
import io.github.mediaplayer.managers.SettingsManager;
import io.github.mediaplayer.managers.SongManager;
import io.github.mediaplayer.menu.MusicMenu;
import io.github.mediaplayer.scenes.MainScene;
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;

public final class Main extends Application {

    private static Main instance;
    private SongManager songManager;
    private Stage stage;
    private MusicManager musicManager;
    private MainScene mainScene;
    private SettingsManager settingsManager;

    @Override
    public void start(Stage stage) throws IOException {
        Main.instance = this;
        this.stage = stage;
        this.settingsManager = new SettingsManager();
        this.musicManager = new MusicManager();
        musicManager.load();
        this.songManager = new SongManager(musicManager.getMusics().iterator().next().media());
        this.mainScene = new MainScene();
        stage.setTitle("MediaPlayer");
        stage.setWidth(1400);
        stage.setHeight(800);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setOpacity(0.99);
        stage.setScene(mainScene.getScene());
        stage.show();
    }

    public MainScene getMainScene() {
        return mainScene;
    }

    public SongManager getSongManager() {
        return songManager;
    }

    public MusicManager getMusicManager() {
        return musicManager;
    }

    public static Main getInstance() {
        return instance;
    }

    public SettingsManager getSettingsManager() {
        return settingsManager;
    }

    public Stage getStage() {
        return stage;
    }

    public static void main(String[] args) {
        launch();
    }
}