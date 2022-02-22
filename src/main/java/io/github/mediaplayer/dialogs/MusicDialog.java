package io.github.mediaplayer.dialogs;

import io.github.mediaplayer.Main;
import io.github.mediaplayer.bars.MusicBar;
import io.github.mediaplayer.data.Music;
import io.github.mediaplayer.utils.TimeUtil;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class MusicDialog extends Pane {

    public MusicDialog(Music music, MusicBar musicBar, int width, int height) {
        setPrefSize(width, height);
        ImageView imageView = new ImageView("music.png");
        imageView.setTranslateX(125);
        imageView.setTranslateY(20);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        Label label = new Label(music.title());
        label.setStyle("-fx-background-color: #2b2b2b; -fx-text-fill: #ffffff; -fx-font-size: 12; -fx-font-weight: bold;");
        label.setTextAlignment(TextAlignment.CENTER);
        label.setAlignment(Pos.CENTER);
        label.setPrefSize(350, 3);
        label.setTranslateY(130);
        ImageView play = new ImageView("play.png");
        play.setTranslateY(170);
        play.setTranslateX(175-45);
        play.setFitWidth(90);
        play.setFitHeight(90);
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
        trashbin.setTranslateY(250);
        trashbin.setTranslateX(300);
        trashbin.setFitWidth(30);
        trashbin.setFitHeight(30);
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
        setStyle("-fx-background-radius: 20px 20px 60px 60px; -fx-background-color: #2e2e2e;");
        getChildren().addAll(imageView, label, play, trashbin);
    }
}
