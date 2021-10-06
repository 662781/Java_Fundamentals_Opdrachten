package nl.inholland.javafx.ui;

import javafx.scene.Node;
import javafx.stage.Stage;

public abstract class WindowSuperClass {

    protected Stage window;

    public Stage getStage() {
        return window;
    }

    public abstract Node setLayout();

}
