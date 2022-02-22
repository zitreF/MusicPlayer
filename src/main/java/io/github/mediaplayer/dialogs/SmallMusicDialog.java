package io.github.mediaplayer.dialogs;

import io.github.mediaplayer.Main;
import io.github.mediaplayer.bars.MusicBar;
import io.github.mediaplayer.data.Music;
import io.github.mediaplayer.scenes.SettingsScene;
import io.github.mediaplayer.utils.TimeUtil;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class SmallMusicDialog extends Pane {

    public SmallMusicDialog(Music music, MusicBar musicBar, int width, int height) {
        setPrefSize(width, height);
        Label label = new Label(music.title());
        label.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 25; -fx-font-weight: bold;");
        label.setPrefSize(730, 3);
        label.setTranslateY(25);
        label.setTranslateX(100);
        ImageView play = new ImageView("play.png");
        play.setTranslateY(10);
        play.setTranslateX(10);
        play.setFitWidth(60);
        play.setFitHeight(60);
        play.setSmooth(true);
        play.setOnMouseEntered(event -> play.setEffect(new Glow(0.6)));
        play.setOnMouseExited(event -> play.setEffect(null));
        play.setOnMousePressed(event -> {
            Main.getInstance().getSongManager().playMusic(music);
            musicBar.getMusicSlider().setMax(musicBar.getSongManager().getMediaPlayer().getTotalDuration().toSeconds());
            musicBar.getMusicSlider().setOnMousePressed(e -> {
                Duration seekTo = Duration.seconds(musicBar.getMusicSlider().getValue());
                musicBar.getSongManager().getMediaPlayer().seek(seekTo);
            });
            musicBar.getSongManager().getMediaPlayer().currentTimeProperty().addListener(ov -> {
                double total = musicBar.getSongManager().getMediaPlayer().getTotalDuration().toSeconds();
                double current = musicBar.getSongManager().getMediaPlayer().getCurrentTime().toSeconds();
                musicBar.getMusicSlider().setMax(total);
                musicBar.getMusicSlider().setValue(current);
                musicBar.getText().setText(TimeUtil.getTimeString(current*1000) + "|" + TimeUtil.getTimeString(total*1000));
                musicBar.getMusicProgress().setProgress(current / total);

            });
        });
        ImageView trashbin = new ImageView("trashbin.png");
        trashbin.setTranslateY(10);
        trashbin.setTranslateX(920);
        trashbin.setFitWidth(50);
        trashbin.setFitHeight(50);
        trashbin.setOnMouseEntered(event -> trashbin.setEffect(new Glow(0.5)));
        trashbin.setOnMouseExited(event -> trashbin.setEffect(null));
        trashbin.setOnMousePressed(event -> {

            Main.getInstance().getMusicManager().getMusics().remove(music);

            if (Main.getInstance().getSongManager().getMediaPlayer().getMedia() == music.media()) return;

            try {
                Files.deleteIfExists(Paths.get(System.getProperty("user.home") + "\\AppData\\Roaming\\songs\\" + music.title() + ".mp3"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (Main.getInstance().getSettingsManager().isSmallList()) Main.getInstance().getMainScene().getMusicMenu().updateSmall(); else Main.getInstance().getMainScene().getMusicMenu().update();
        });
        setStyle("-fx-background-radius: 5px; -fx-background-color: #2e2e2e;");
        getChildren().addAll(label, play, trashbin);
    }

}
