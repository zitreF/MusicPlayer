package io.github.mediaplayer.bars;

import io.github.mediaplayer.Main;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public final class TopBar extends HBox {

    private double xOffset;
    private double yOffset;
    private final DropShadow redeffect;
    private final DropShadow greeneffect;

    public TopBar() {
        setPrefSize(1400, 50);
        setStyle("-fx-background-color: linear-gradient(to right, #2e2e2e, #363636);");
        this.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        this.setOnMouseDragged(event -> {
            Main.getInstance().getStage().setX(event.getScreenX() - xOffset);
            Main.getInstance().getStage().setY(event.getScreenY() - yOffset);
        });

        this.redeffect = new DropShadow();
        redeffect.setBlurType(BlurType.THREE_PASS_BOX);
        redeffect.setColor(Color.rgb(218, 68, 83));

        this.greeneffect = new DropShadow();
        greeneffect.setBlurType(BlurType.THREE_PASS_BOX);
        greeneffect.setRadius(15);
        greeneffect.setColor(Color.GREEN);

        registerExitButton();
        registerMinimizeButton();
    }

    private void registerExitButton() {

        ImageView exit = new ImageView("close-logo.png");

        exit.setFitHeight(32);
        exit.setFitWidth(32);
        exit.setTranslateX(1325);
        exit.setTranslateY(10);

        this.getChildren().add(exit);

        exit.setOnMouseEntered(event ->
                exit.setEffect(redeffect)
        );

        exit.setOnMouseExited(event ->
                exit.setEffect(null)
        );

        exit.setOnMousePressed(event ->
                System.exit(0)
        );
    }

    private void registerMinimizeButton() {

        ImageView minimize = new ImageView("minimize-logo.png");

        minimize.setFitHeight(37);
        minimize.setFitWidth(37);
        minimize.setTranslateX(1240);
        minimize.setTranslateY(8);

        minimize.setOnMouseEntered(event ->
                minimize.setEffect(greeneffect)
        );

        minimize.setOnMouseExited(event ->
                minimize.setEffect(null)
        );

        minimize.setOnMousePressed(event ->
                Main.getInstance().getStage().setIconified(true)
        );

        this.getChildren().add(minimize);
    }
}
