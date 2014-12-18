package toofer.toofer.input;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import toofer.Player;

/**
 * Created by benjamintoofer on 12/18/14.
 */
public class KeyUpHandler implements EventHandler<KeyEvent> {

    private Player myPlayer;

    public KeyUpHandler(Player player)
    {
        this.myPlayer = player;
    }

    @Override
    public void handle(KeyEvent e)
    {
        switch(e.getCode())
        {
            case UP:

                break;

            case DOWN:

                break;

            case RIGHT:

                break;

            case LEFT:

                break;
        }
    }
}

