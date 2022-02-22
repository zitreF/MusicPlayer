package io.github.mediaplayer.bars;

import io.github.mediaplayer.Main;
import io.github.mediaplayer.managers.SongManager;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public final class MusicBar extends Pane {

    private final SongManager songManager;
    private final Slider musicSlider;
    private final Text text;
    private final ProgressBar musicProgress;

    public MusicBar() {
        this.songManager = Main.getInstance().getSongManager();
        setTranslateX(0);
        setTranslateY(700);
        setPrefSize(1400, 100);
        setStyle("-fx-background-color: #292828;");
        ImageView pause = new ImageView("pause.png");
        pause.setFitHeight(70);
        pause.setFitWidth(90);
        pause.setTranslateX(655);
        ImageView play = new ImageView("resume.png");
        play.setFitHeight(70);
        play.setFitWidth(70);
        play.setVisible(false);
        play.setEffect(new DropShadow(0.5, Color.BLACK));
        pause.setOnMousePressed(event -> {
            pause.setVisible(false);
            play.setVisible(true);
            songManager.pause();
        });
        pause.setEffect(new DropShadow(0.5, Color.BLACK));
        pause.setOnMouseEntered(event -> pause.setEffect(new Glow(0.5)));
        pause.setOnMouseExited(event -> pause.setEffect(null));
        play.setOnMousePressed(event -> {
            play.setVisible(false);
            pause.setVisible(true);
            songManager.resume();
        });
        play.setOnMouseEntered(event -> play.setEffect(new Glow(0.5)));
        play.setOnMouseExited(event -> play.setEffect(null));
        play.setTranslateX(665);
        ImageView loop = new ImageView("loop.png");
        loop.setEffect(new DropShadow(5, Color.web("#7a2027")));
        loop.setTranslateX(755);
        loop.setTranslateY(15);
        loop.setFitHeight(50);
        loop.setFitWidth(50);
        loop.setOnMousePressed(event -> {
            songManager.changeLoop();
            loop.setEffect(new DropShadow(5, (songManager.isLooped() ? Color.web("#187026") : Color.web("#7a2027"))));
        });
        this.musicSlider = new Slider();
        musicSlider.getStylesheets().add("slider.css");
        musicSlider.setId("slider");
        musicSlider.setPrefSize(750, 15);
        musicSlider.setTranslateX(325);
        musicSlider.setTranslateY(70);
        this.musicProgress = new ProgressBar(0);
        musicProgress.setId("progress-bar");
        musicProgress.getStylesheets().add("progressbar.css");
        musicProgress.setPrefSize(750, 7);
        musicProgress.setTranslateX(musicSlider.getTranslateX());
        musicProgress.setTranslateY(musicSlider.getTranslateY()+5.25);

        Slider volumeSlider = new Slider();
        volumeSlider.getStylesheets().add("slider.css");
        volumeSlider.setId("slider");
        volumeSlider.setPrefSize(100, 30);
        volumeSlider.setTranslateX(1250);
        volumeSlider.setTranslateY(35);
        volumeSlider.setMax(1.5);
        volumeSlider.setMin(0);
        volumeSlider.setValue(1);
        ProgressBar volumeProgress = new ProgressBar(0);
        volumeProgress.setId("progress-bar");
        volumeProgress.getStylesheets().add("progressbar.css");
        volumeProgress.setPrefSize(100, 10);
        volumeProgress.setTranslateX(volumeSlider.getTranslateX());
        volumeProgress.setTranslateY(volumeSlider.getTranslateY()+10);
        volumeProgress.setProgress(1/1.5);
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            songManager.setVolume(newValue.doubleValue());
            volumeProgress.setProgress(newValue.doubleValue()/1.5);
        });
        this.text = new Text("00:00:00|00:00:00");
        text.setTranslateX(10);
        text.setTranslateY(50);
        text.setStyle("-fx-fill: white; -fx-font-size: 30; -fx-font-weight: bold;");
        getChildren().addAll(text, pause, play, musicProgress, musicSlider, loop, volumeProgress, volumeSlider);
    }

    public Slider getMusicSlider() {
        return musicSlider;
    }

    public SongManager getSongManager() {
        return songManager;
    }

    public Text getText() {
        return text;
    }

    public ProgressBar getMusicProgress() {
        return musicProgress;
    }
}
