package sample;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * Created by benjamintoofer on 11/20/14.
 */
public class ButtonClickHandler implements EventHandler<Event> {

    private CalculatorView calcView;

    public ButtonClickHandler(CalculatorView calcView)
    {
        this.calcView = calcView;
    }
    @Override
    public void handle(Event event)
    {
        String newTxt = "";
        Button btn = (Button)event.getSource();
        calcView.setInputEntered(true);
        //System.out.println("button ");

        if(btn.getId().equals("button_Deci"))
        {System.out.println("button Deci");
            if(!calcView.isDecimalPressed() && !calcView.isOperationPressed())
            {
                newTxt = calcView.getInputTxt() + ".";
                calcView.setInputTxt(newTxt);
                calcView.setDecimalPressed(true);
            }else if(!calcView.isDecimalPressed() && calcView.isOperationPressed())
            {
                newTxt = "0.";
                calcView.setInputTxt(newTxt);
                calcView.setDecimalPressed(true);
            }
        }
        if((calcView.getInputTxt().equals("0") || calcView.isOperationPressed()) && !calcView.isDecimalPressed())
        {
            calcView.setInputTxt("");
            calcView.setOperationPressed(false);
        }


        if(btn.getId().equals("button_1"))
        {

            newTxt = calcView.getInputTxt() + "1";
            calcView.setInputTxt(newTxt);
        }else if(btn.getId().equals("button_2"))
        {
            newTxt = calcView.getInputTxt() + "2";
            calcView.setInputTxt(newTxt);
        }else if(btn.getId().equals("button_3"))
        {
            newTxt = calcView.getInputTxt() + "3";
            calcView.setInputTxt(newTxt);
        }else if(btn.getId().equals("button_4"))
        {
            newTxt = calcView.getInputTxt() + "4";
            calcView.setInputTxt(newTxt);
        }else if(btn.getId().equals("button_5"))
        {
            newTxt = calcView.getInputTxt() + "5";
            calcView.setInputTxt(newTxt);
        }else if(btn.getId().equals("button_6"))
        {
            newTxt = calcView.getInputTxt() + "6";
            calcView.setInputTxt(newTxt);
        }else if(btn.getId().equals("button_7"))
        {
            newTxt = calcView.getInputTxt() + "7";
            calcView.setInputTxt(newTxt);
        }else if(btn.getId().equals("button_8"))
        {
            newTxt = calcView.getInputTxt() + "8";
            calcView.setInputTxt(newTxt);
        }else if(btn.getId().equals("button_9"))
        {
            newTxt = calcView.getInputTxt() + "9";
            calcView.setInputTxt(newTxt);
        }else if(btn.getId().equals("button_0"))
        {
            newTxt = calcView.getInputTxt() + "0";
            calcView.setInputTxt(newTxt);
        }else if(btn.getId().equals("button_C"))
        {
            newTxt = "0";
            calcView.setInputTxt(newTxt);
            calcView.setFirstNumStr(null);
            calcView.setSecondNumStr(null);
            calcView.setDecimalPressed(false);
        }else if(btn.getId().equals("button_Dec"))
        {
            if(!calcView.isDecimalPressed())
            {
                calcView.setInputTxt("0");
                calcView.setDecimalPressed(true);
            }
        }

    }

}
