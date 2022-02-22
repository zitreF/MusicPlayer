package io.github.mediaplayer.scenes;

import io.github.mediaplayer.bars.MusicBar;
import io.github.mediaplayer.menu.LeftMenu;
import io.github.mediaplayer.menu.MusicMenu;
import io.github.mediaplayer.bars.TopBar;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

public final class MainScene extends AnchorPane {

    private final MusicMenu musicMenu;

    public MainScene() {
        new Scene(this);
        this.setStyle("-fx-background-color: #404040;");
        TopBar topBar = new TopBar();
        MusicBar musicBar = new MusicBar();
        this.musicMenu = new MusicMenu(musicBar);
        LeftMenu leftMenu = new LeftMenu(musicMenu);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.getStylesheets().add("/scrollpane.css");
        scrollPane.setId("scroll-pane");
        scrollPane.setTranslateX(300);
        scrollPane.setTranslateY(50);
        scrollPane.setPrefWidth(1100);
        scrollPane.setContent(musicMenu);
        scrollPane.setStyle("-fx-background-color: transparent;");
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        getChildren().addAll(topBar, leftMenu, scrollPane, musicBar);
    }

    public MusicMenu getMusicMenu() {
        return musicMenu;
    }
}
