package toofer.toofer.input;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import toofer.Player;

/**
 * Created by benjamintoofer on 12/16/14.
 */
public class KeyDownHandler implements EventHandler<KeyEvent>{

    private Player myPlayer;

    public KeyDownHandler(Player player)
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
