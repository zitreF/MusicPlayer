package io.github.mediaplayer.menu;

import io.github.mediaplayer.Main;
import io.github.mediaplayer.bars.MusicBar;
import io.github.mediaplayer.data.Music;
import io.github.mediaplayer.dialogs.MusicDialog;
import io.github.mediaplayer.dialogs.SmallMusicDialog;
import io.github.mediaplayer.managers.MusicManager;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

public final class MusicMenu extends GridPane {

    private final MusicManager musicManager;
    private final MusicBar musicBar;

    public MusicMenu(MusicBar musicBar) {
        this.musicBar = musicBar;
        this.musicManager = Main.getInstance().getMusicManager();
        this.setStyle("-fx-background-color: #404040;");
        setPrefSize(1100, 700);
        this.setPadding(new Insets(8, 8, 8, 8));
        setVgap(5);
        setHgap(5);
        int z = 0;
        int y = 0;
        for (Music music : musicManager.getMusics()) {
            add(new MusicDialog(music, musicBar, 350, 300), z, y);
            z++;
            if (z % 3 == 0) {
                z = 0;
                y++;
            }
        }
        int height = 0;
            for (int i = 0; i < musicManager.getMusics().size(); i++) {
                if (i % 3 == 0) {
                    height += 300;
                }
        }
        setMinHeight(height+100);
    }

    public void update() {
        getChildren().clear();
        int z = 0;
        int y = 0;
        for (Music music : musicManager.getMusics()) {
            add(new MusicDialog(music, musicBar, 350, 300), z, y);
            z++;
            if (z % 3 == 0) {
                z = 0;
                y++;
            }
        }
        int height = 0;
        for (int i = 0; i < musicManager.getMusics().size(); i++) {
            if (i % 3 == 0) {
                height += 300;
            }
        }
        setMinHeight(height+100);
    }

    public void updateSmall() {
        getChildren().clear();
        int y = 1;
        for (Music music : musicManager.getMusics()) {
            add(new SmallMusicDialog(music, musicBar, 1000, 75), 0, y);
            y++;
        }
        setMinHeight(75*musicManager.getMusics().size()+250);
    }
}
