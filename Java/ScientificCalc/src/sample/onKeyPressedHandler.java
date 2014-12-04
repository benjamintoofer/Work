package sample;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

/**
 * Created by benjamintoofer on 11/21/14.
 */
public class onKeyPressedHandler implements EventHandler<KeyEvent>{

    private CalculatorView calcView;

    public onKeyPressedHandler(CalculatorView calcView)
    {
        this.calcView = calcView;
    }


    @Override
    public void handle(KeyEvent e)
    {

    }
}
