package sample;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * Created by benjamintoofer on 7/8/14.
 */
public class CellClickedEvent implements EventHandler<MouseEvent> {

    @Override
    public void handle(MouseEvent actionEvent) {
        System.out.println("Hello");
    }
}
