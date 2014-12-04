package sample;

import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.scene.control.*;

import java.util.ArrayList;

public class Controller {

    private CalculatorView calcView;
    private CalculatorModel calcModel;
    private String oper = "";
    private String equationStr = "";
    private double result = 0;
    private double firstNum,secondNum,tempNum = 0;
    private int paraCounter = 0;
    private Button btn = new Button();

    private ArrayList<Equation> equationList;

    public Controller(CalculatorView calcView, CalculatorModel calcModel)
    {
        this.calcView = calcView;
        this.calcModel = calcModel;

        equationList = new ArrayList<Equation>();

        this.calcView.addCalculateHandler(new CalculateHandler());
    }

    class CalculateHandler implements EventHandler<Event> {

        String newTxt = "";


        @Override
        public void handle(Event event)
        {
            Button btn = (Button)event.getSource();

            //If there are no equations start a new one
            if(equationList.isEmpty())
            {
                equationList.add(new Equation());
            }

            //check for numInput
            if(btn.getId().equals("button_0") || btn.getId().equals("button_1") || btn.getId().equals("button_2") ||
                    btn.getId().equals("button_3") || btn.getId().equals("button_4") || btn.getId().equals("button_5") ||
                    btn.getId().equals("button_6") || btn.getId().equals("button_7") || btn.getId().equals("button_8") ||
                    btn.getId().equals("button_9") || btn.getId().equals("button_Deci"))
            {
                calcView.setInputEntered(true);
                buttonPessed(btn);
                oper = null;

            }else{

                oper = btn.getId();

                //If Parenthesis is entered then start a new equation
                if(oper.equals("button_LPar"))
                {
                    equationList.add(new Equation());
                    openNewEquation();

                    //Add to Left Parenthesis text
                    newTxt = calcView.getEquationStr()+" (";
                    calcView.setEquationStr(newTxt);
                    paraCounter++;

                }else if(oper.equals("button_RPar"))//Closing the equation with Right parenthesis
                {
                    if(paraCounter > 0)
                    {
                        System.out.println("Right para");

                        //Add to Left Parenthesis text
                        newTxt = calcView.getEquationStr()+" "+calcView.getInputTxt()+" )";
                        calcView.setEquationStr(newTxt);

                        calcView.setInputEntered(true);

                        paraCounter--;
                    }

                }else if(calcView.isInputEntered())
                {


                    //Add to equation text
                    newTxt = calcView.getEquationStr()+" "+calcView.getInputTxt()+" "+calcModel.getOperationSymbol(oper);
                    calcView.setEquationStr(newTxt);

                    Equation currentEq = equationList.get(equationList.size() - 1);
                    tempNum = Double.parseDouble(calcView.getInputTxt());

                    currentEq.add(calcModel.getOperationSymbol(oper),tempNum);



                    calcView.setInputEntered(false);
                    calcView.setOperationPressed(true);
                    calcView.setDecimalPressed(false);

                }else if(!calcView.isInputEntered()){//If no num input then allow operatoin change

                    System.out.println("change oper");

                }
            }



        }
        private void openNewEquation()
        {
            System.out.println(equationList.size());
        }
        private void closeEquation()
        {

        }
        public void buttonPessed(Button btnNum)
        {
            String newTxt = "";
            Button btn = btnNum;
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
}
