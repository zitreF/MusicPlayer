package io.github.mediaplayer.scenes;

import io.github.mediaplayer.Main;
import io.github.mediaplayer.menu.MusicMenu;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public final class SettingsScene extends AnchorPane {

    public SettingsScene(Stage stage) {
        new Scene(this);
        setStyle("-fx-background-color: transparent;");
        getScene().setFill(Color.TRANSPARENT);
        Label label = new Label();
        label.setPrefSize(500, 800);
        label.setStyle("-fx-background-color: #262626; -fx-background-radius: 60px; -fx-border-radius: 60px");
        ImageView trashbin = new ImageView("close-logo.png");
        trashbin.setTranslateY(40);
        trashbin.setTranslateX(440);
        trashbin.setFitWidth(30);
        trashbin.setFitHeight(30);
        trashbin.setOnMouseEntered(event -> trashbin.setEffect(new Glow(0.5)));
        trashbin.setOnMouseExited(event -> trashbin.setEffect(null));
        trashbin.setOnMousePressed(event -> stage.close());
        Label label1 = new Label();
        label1.setTranslateY(80);
        label1.setPrefSize(500, 1);
        label1.setStyle("-fx-background-color: #424242;");
        MusicMenu musicMenu = Main.getInstance().getMainScene().getMusicMenu();
        Button list = new Button("Duza lista");
        list.setTranslateX(100);
        list.setTranslateY(150);
        list.setPrefSize(300, 50);
        list.setStyle("-fx-background-color: #424242; -fx-background-radius: 60px; -fx-border-radius: 60px; -fx-text-fill: #ffffff; -fx-font-size: 20px; -fx-font-weight: bold;");
        list.setOnMouseEntered(event -> list.setEffect(new Glow(0.5)));
        list.setOnMouseExited(event -> list.setEffect(null));
        list.setOnMousePressed(event -> {
            stage.close();
            musicMenu.update();
            Main.getInstance().getSettingsManager().setSmallList(false);
        });
        Button list2 = new Button("Mala lista");
        list2.setTranslateX(100);
        list2.setTranslateY(350);
        list2.setPrefSize(300, 50);
        list2.setStyle("-fx-background-color: #424242; -fx-background-radius: 60px; -fx-border-radius: 60px; -fx-text-fill: #ffffff; -fx-font-size: 20px; -fx-font-weight: bold;");
        list2.setOnMouseEntered(event -> list2.setEffect(new Glow(0.5)));
        list2.setOnMouseExited(event -> list2.setEffect(null));
        list2.setOnMousePressed(event -> {
            stage.close();
            musicMenu.updateSmall();
            Main.getInstance().getSettingsManager().setSmallList(true);
        });
        getChildren().addAll(label, trashbin, list, list2, label1);
    }
}
