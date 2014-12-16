package sample;

import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.scene.control.*;

import java.util.ArrayList;

public class Controller {

    private CalculatorView calcView;
    private CalculatorModel calcModel;
    private String oper = "";
    private String strOperForEquat = "";
    private String equationStr = "";
    private String num = "";
    private double result = 0;
    private double firstNum,secondNum,tempNum = 0;
    private int paraCounter = 0;
    private Button btn = new Button();
    private Equation currentEq;

    private enum calcState  {NUM_STATE,OPER_STATE,FUNC_STATE,EQUAL_STATE}
    private calcState currentCalcState;

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

            currentCalcState = getCalcState(btn);

            switch (currentCalcState)
            {
                case NUM_STATE:
                    buttonPessed(btn);
                    break;
                case OPER_STATE:
                    handleOperation(btn);
                    break;
            }
            //check for numInput
            /*if(btn.getId().equals("button_0") || btn.getId().equals("button_1") || btn.getId().equals("button_2") ||
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

                    //If there are no equations start a new one
                    if(equationList.isEmpty())
                    {
                        equationList.add(new Equation());
                    }


                    Equation currentEq = equationList.get(equationList.size() - 1);
                    num = calcView.getInputTxt();

                    //Add to equation text
                    newTxt = calcView.getEquationStr()+" "+calcView.getInputTxt()+" "+calcModel.getOperationSymbol(oper);
                    calcView.setEquationStr(newTxt);


                    currentEq.add(calcModel.getOperationSymbol(oper),num);



                    calcView.setInputEntered(false);
                    calcView.setOperationPressed(true);
                    calcView.setDecimalPressed(false);

                }else if(!calcView.isInputEntered()){//If no num input then allow operatoin change

                    System.out.println("change oper");

                }
            }*/



        }
        private calcState getCalcState(Button btn)
        {
            calcState state = null;

            //Number State
            if(btn.getId().equals("button_0") || btn.getId().equals("button_1") || btn.getId().equals("button_2") ||
                    btn.getId().equals("button_3") || btn.getId().equals("button_4") || btn.getId().equals("button_5") ||
                    btn.getId().equals("button_6") || btn.getId().equals("button_7") || btn.getId().equals("button_8") ||
                    btn.getId().equals("button_9") || btn.getId().equals("button_Deci"))
            {
                state = calcState.NUM_STATE;
            }

            //Operation State
            if(btn.getId().equals("button_Div") || btn.getId().equals("button_Plus") ||
                    btn.getId().equals("button_Minus") || btn.getId().equals("button_Mult"))
            {
                state = calcState.OPER_STATE;
            }

            //Paranthesis State

            return state;
        }

        private void handleOperation(Button btn)
        {
            if(equationList.isEmpty())
            {
                equationList.add(new Equation());
            }

            if(calcView.isInputEntered())
            {
                oper = btn.getId();


                currentEq = equationList.get(equationList.size() - 1);
                num = calcView.getInputTxt();

                //System.out.println(currentEq.getNumSize()+" "+currentEq.getOperSize());
                System.out.println(currentEq.getTopOper()+" "+currentEq.getOperSize());
                //check if holding operator has nothing

                if(currentEq.getTopOper().equals("*") || currentEq.getTopOper().equals("/"))
                {

                    firstNum = Double.parseDouble(num);
                    secondNum = Double.parseDouble(currentEq.popNum());
                    strOperForEquat = calcModel.getOperationSymbol(currentEq.popOperTop());

                    newTxt = Double.toString(calcModel.calculateOperation(firstNum,secondNum,strOperForEquat));
                    System.out.println(newTxt);
                    currentEq.addNum(newTxt);

                }

                //Add to equation text
                newTxt = calcView.getEquationStr()+" "+calcView.getInputTxt()+" "+calcModel.getOperationSymbol(oper);
                calcView.setEquationStr(newTxt);


                currentEq.add(calcModel.getOperationSymbol(oper),num);



                if(currentEq.getNumSize() > 1)
                {
                    secondNum = Double.parseDouble(currentEq.popNum());//System.out.println("");
                    firstNum = Double.parseDouble(currentEq.popNum());//System.out.println(firstNum);
                    strOperForEquat = currentEq.popOperBottom();//System.out.println(strOperForEquat);
                    System.out.println(firstNum+"-----"+secondNum);
                    newTxt = Double.toString(calcModel.calculateOperation(firstNum,secondNum,strOperForEquat));
                    currentEq.addNum(newTxt);
                    calcView.setInputTxt(newTxt);
                }

                calcView.setInputEntered(false);
                calcView.setOperationPressed(true);
                calcView.setDecimalPressed(false);

            }else {//If no num input then allow operation change
                oper = btn.getId();



                currentEq.popOperTop();
                currentEq.addOper(calcModel.getOperationSymbol(oper));


                newTxt = calcView.getEquationStr().substring(0,calcView.getEquationStr().length() - 1);
                newTxt += calcModel.getOperationSymbol(oper);
                calcView.setEquationStr(newTxt);

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
