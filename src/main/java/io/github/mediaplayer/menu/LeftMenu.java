package io.github.mediaplayer.menu;

import io.github.mediaplayer.Main;
import io.github.mediaplayer.bars.TopBar;
import io.github.mediaplayer.buttons.MediaButton;
import io.github.mediaplayer.data.Music;
import io.github.mediaplayer.managers.MusicManager;
import io.github.mediaplayer.scenes.SettingsScene;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class LeftMenu extends Pane {

    public LeftMenu(MusicMenu musicMenu) {
        MusicManager musicManager = Main.getInstance().getMusicManager();
        setStyle("-fx-background-color: #2b2b2b;");
        setPrefSize(300, 800);
        MediaButton musics = new MediaButton("Posluchaj", 200, 100, 50, 250);
        MediaButton add = new MediaButton("Dodaj", 200, 100, 50, 400);
        MediaButton settings = new MediaButton("Ustawienia", 200, 100, 50, 550);
        ImageView imageView = new ImageView("avatar.png");
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        imageView.setTranslateX(100);
        imageView.setTranslateY(20);
        Text text = new Text("Witaj, " + System.getProperty("user.name"));
        text.setTranslateY(150);
        text.setTranslateX(100 - text.getBoundsInParent().getCenterX());
        text.setStyle("-fx-font-size: 30px; -fx-fill: #2e2e2e; -fx-font-weight: bold;");
        text.setEffect(new Glow(0.75));
        Label label = new Label();
        label.setTranslateY(180);
        label.setPrefSize(300, 4);
        label.setStyle("-fx-background-color: #2e2e2e;");
        add.setOnMousePressed(event -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(null);
            if (file == null) return;
            if (!file.getName().endsWith(".mp3")) return;
            musicManager.getMusics().add(new Music(file.getName(), new Media(file.toURI().toString())));
            if (Main.getInstance().getSettingsManager().isSmallList()) musicMenu.updateSmall(); else musicMenu.update();
            try {
                Files.copy(file.toPath(), Paths.get(System.getProperty("user.home") + "\\AppData\\Roaming\\songs\\"+file.getName()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        settings.setOnMousePressed(event -> {
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(new SettingsScene(stage).getScene());
            stage.setTitle("Ustawienia");
            stage.show();
        });

        getChildren().addAll(musics, add, settings, imageView, text, label);
    }
}
