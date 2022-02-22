package io.github.mediaplayer.buttons;

import javafx.scene.control.Button;
import javafx.scene.effect.Glow;

public final class MediaButton extends Button {

    public MediaButton(String text, int width, int height, int x, int y) {
        setText(text);
        setPrefSize(width, height);
        setTranslateX(x);
        setTranslateY(y);
        setOnMouseEntered(event -> {
            this.setEffect(new Glow(0.3));
        });
        setOnMouseExited(event -> {
            this.setEffect(null);
        });
        setStyle("-fx-background-color: linear-gradient(to right, #525252, #3b3a3a); -fx-background-radius: 50px; -fx-text-fill: #8a8a8a; -fx-font-size: 30px; -fx-font-family: 'Arial'; -fx-background-insets: 0, 0, 0, 0;");
    }
}
