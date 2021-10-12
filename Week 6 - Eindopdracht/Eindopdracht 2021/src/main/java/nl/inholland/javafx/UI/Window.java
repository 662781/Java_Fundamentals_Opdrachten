package nl.inholland.javafx.UI;

import javafx.scene.Node;
import javafx.stage.Stage;
import nl.inholland.javafx.Database.Database;

public abstract class Window{
    //Field
    protected Stage window;

    //Getter
    public Stage getStage() {
        return window;
    }

    //Abstract method
    protected abstract Node setLayout();
}
