package sample;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.DepthTest;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.input.MouseEvent;
import javafx.event.Event;
import javafx.scene.input.KeyCode;


/**
 * Created by benjamintoofer on 11/19/14.
 */
public class CalculatorView extends BorderPane {

    private final int BUTTON_GAP = 10;
    private final int NUM_BUTTON_COLS = 10;
    private final int NUM_BUTTON_ROWS = 6;
    private final int CALCULATOR_BORDER_PADDING = 20;
    private int buttonWidth,buttonHeight;
    private int textFieldWidth,textFieldHeight;

    private  Button button_MC,button_MS,button_MR,button_MPlus,button_MMin,button_Del,button_CE,button_C,button_PlusMin,
                    button_Sqrt,button_0,button_1,button_2,button_3,button_4,button_5,button_6,button_7,button_8,button_9,
                    button_Recip,button_Mult,button_Div,button_Perc,button_Minus,button_Equal,button_Deci,button_Plus,button_Inv,
                    button_NatLog,button_LPar,button_RPar,button_Int,button_Sinh,button_Sin,button_Sqre,button_NFact,button_Dms,
                    button_Cosh,button_Cos,button_Power,button_ROOT,button_PI,button_Tanh,button_Tan,button_Cube,button_CubeRoot;

    private String equationStr = "",inputTxt = "0";
    private String resultStr = null;

    private String firstNumStr,secondNumStr;

    private GridPane textFieldPane;
    private GridPane numPane;
    private GridPane operPane;

    private TextField calcTextField,equationTextField;

    private int width,height;
    private int hex = 0;

    private boolean operationPressed,decimalPressed,inputEntered = false;

    public CalculatorView(int width, int height)
    {
        this.width = width;
        this.height = height;
        //this.setOnKeyPressed(new onKeyPressedHandler(this));
        this.requestFocus();

        textFieldHeight = height/5;
        textFieldWidth = width;

        buttonWidth =( width - (BUTTON_GAP * (NUM_BUTTON_COLS-1)) - (2 * CALCULATOR_BORDER_PADDING))/NUM_BUTTON_COLS;
        buttonHeight = ( height - (BUTTON_GAP * (NUM_BUTTON_ROWS-1)) - textFieldHeight - 20)/NUM_BUTTON_ROWS;

        init();

    }

    public void addCalculateHandler(EventHandler<Event> calculateHandler)
    {
        //this.addEventHandler(KeyEvent.KEY_PRESSED,calculateHandler);
        button_Equal.addEventHandler(MouseEvent.MOUSE_CLICKED,calculateHandler);
        button_Div.addEventHandler(MouseEvent.MOUSE_CLICKED,calculateHandler);
        button_Mult.addEventHandler(MouseEvent.MOUSE_CLICKED,calculateHandler);
        button_Plus.addEventHandler(MouseEvent.MOUSE_CLICKED,calculateHandler);
        button_Minus.addEventHandler(MouseEvent.MOUSE_CLICKED,calculateHandler);
        button_RPar.addEventHandler(MouseEvent.MOUSE_CLICKED, calculateHandler);
        button_LPar.addEventHandler(MouseEvent.MOUSE_CLICKED,calculateHandler);

        //Numbers
        button_0.addEventHandler(MouseEvent.MOUSE_CLICKED,calculateHandler);
        button_1.addEventHandler(MouseEvent.MOUSE_CLICKED,calculateHandler);
        button_2.addEventHandler(MouseEvent.MOUSE_CLICKED,calculateHandler);
        button_3.addEventHandler(MouseEvent.MOUSE_CLICKED,calculateHandler);
        button_4.addEventHandler(MouseEvent.MOUSE_CLICKED,calculateHandler);
        button_5.addEventHandler(MouseEvent.MOUSE_CLICKED,calculateHandler);
        button_6.addEventHandler(MouseEvent.MOUSE_CLICKED,calculateHandler);
        button_7.addEventHandler(MouseEvent.MOUSE_CLICKED,calculateHandler);
        button_8.addEventHandler(MouseEvent.MOUSE_CLICKED,calculateHandler);
        button_9.addEventHandler(MouseEvent.MOUSE_CLICKED,calculateHandler);
        button_Deci.addEventHandler(MouseEvent.MOUSE_CLICKED,calculateHandler);
    }
    public String getInputTxt()
    {
        return inputTxt;
    }

    public void setInputTxt(String text)
    {
        if(text != null)
        {
            inputTxt = text;
            if(inputTxt.contains(".0"))
            {
                inputTxt = inputTxt.substring(0,inputTxt.length() - 2);
            }
        }else{

            inputTxt = text;
            System.out.println(inputTxt);
        }

        calcTextField.setText(inputTxt);
    }

    public String getEquationStr()
    {
        return equationStr;
    }

    public void setEquationStr(String text)
    {
        equationStr = text;
        equationTextField.setText(equationStr);
    }
    public String getFirstNumStr()
    {
        return firstNumStr;
    }

    public void setFirstNumStr(String text)
    {
        firstNumStr = text;
        //this.setInputTxt(firstNumStr);
    }

    public String getSecondNumStr()
    {
        return secondNumStr;
    }

    public void setSecondNumStr(String text)
    {
        secondNumStr = text;
    }

    public boolean isOperationPressed()
    {
        return operationPressed;
    }

    public void setOperationPressed(boolean operationPressed)
    {
        this.operationPressed = operationPressed;
    }

    public void setDecimalPressed(boolean decimalPressed)
    {
        this.decimalPressed = decimalPressed;
    }

    public boolean isDecimalPressed()
    {
        return decimalPressed;
    }

    public String getResultStr()
    {
        return resultStr;
    }

    public void setResultStr(String result)
    {
        this.resultStr = result;
    }

    public void setInputEntered(boolean inputEntered)
    {
        this.inputEntered = inputEntered;
    }

    public boolean isInputEntered()
    {
        return inputEntered;
    }

    private void init()
    {
        //intialize
        textFieldPane = new GridPane();
        numPane = new GridPane();
        operPane = new GridPane();


        numPane.setId("NUM_GRID");
        operPane.setId("FUNC_GRID");

        firstNumStr = secondNumStr = null;

        //Add Panes to Calc
        this.setPadding(new Insets(CALCULATOR_BORDER_PADDING));
        this.setRight(numPane);
        this.setLeft(operPane);
        this.setTop(textFieldPane);

        /* Textfield Panel*/
        textFieldPane.setPrefHeight(textFieldHeight);
        textFieldPane.setPrefWidth(textFieldWidth);
        textFieldPane.setPadding(new Insets(0,0,10,0));

        //calcTextField.
        calcTextField = new TextField(inputTxt);
        calcTextField.setAlignment(Pos.CENTER_RIGHT);
        calcTextField.setPrefWidth(textFieldPane.getPrefWidth());
        calcTextField.setPrefHeight(textFieldPane.getPrefHeight() * 2/3);
        calcTextField.setEditable(false);

        //equation textfield
        equationTextField = new TextField(equationStr);
        equationTextField.setAlignment(Pos.CENTER_RIGHT);
        equationTextField.setPrefWidth(textFieldPane.getPrefWidth());
        equationTextField.setPrefHeight(textFieldPane.getPrefHeight() * 1/3);
        equationTextField.setEditable(false);

        textFieldPane.add(equationTextField,0,0);
        textFieldPane.add(calcTextField,0,1);

        /* Number Panel */
        numPane.setHgap(BUTTON_GAP);
        numPane.setVgap(BUTTON_GAP);
        //numPane.setPadding(new Insets(0,0,0,0));


        //Create Buttons for Number Panel
        //1st Row
        button_MC = new Button("MC");
        button_MC.setPrefWidth(buttonWidth);
        button_MC.setPrefHeight(buttonHeight);
        button_MC.setId("button_MC");
        button_MC.addEventHandler(MouseEvent.MOUSE_CLICKED, new ButtonClickHandler(this));

        button_MR = new Button("MR");
        button_MR.setPrefWidth(buttonWidth);
        button_MR.setPrefHeight(buttonHeight);
        button_MR.setId("button_MR");

        button_MS = new Button("MS");
        button_MS.setPrefWidth(buttonWidth);
        button_MS.setPrefHeight(buttonHeight);
        button_MS.setId("button_MS");

        button_MPlus = new Button("M+");
        button_MPlus.setPrefWidth(buttonWidth);
        button_MPlus.setPrefHeight(buttonHeight);
        button_MPlus.setId("button_MPlus");

        button_MMin = new Button("M-");
        button_MMin.setPrefWidth(buttonWidth);
        button_MMin.setPrefHeight(buttonHeight);
        button_MMin.setId("button_MMin");

        //2nd Row
        button_Del = new Button("<-");
        button_Del.setPrefWidth(buttonWidth);
        button_Del.setPrefHeight(buttonHeight);
        button_Del.setId("button_Del");

        button_CE = new Button("CE");
        button_CE.setPrefWidth(buttonWidth);
        button_CE.setPrefHeight(buttonHeight);
        button_CE.setId("button_CE");

        button_C = new Button("C");
        button_C.setPrefWidth(buttonWidth);
        button_C.setPrefHeight(buttonHeight);
        button_C.setId("button_C");
        button_C.addEventHandler(MouseEvent.MOUSE_CLICKED, new ButtonClickHandler(this));

        hex = 0x2213;
        button_PlusMin = new Button((char)hex+"");
        button_PlusMin.setPrefWidth(buttonWidth);
        button_PlusMin.setPrefHeight(buttonHeight);
        button_PlusMin.setId("button_PlusMin");

        hex = 0x221A;
        button_Sqrt = new Button((char)hex+"");
        button_Sqrt.setPrefWidth(buttonWidth);
        button_Sqrt.setPrefHeight(buttonHeight);
        button_Sqrt.setId("button_Sqrt");

        //3rd Row
        button_7 = new Button("7");
        button_7.setPrefWidth(buttonWidth);
        button_7.setPrefHeight(buttonHeight);
        button_7.setId("button_7");
        //button_7.addEventHandler(MouseEvent.MOUSE_CLICKED, new ButtonClickHandler(this));

        button_8 = new Button("8");
        button_8.setPrefWidth(buttonWidth);
        button_8.setPrefHeight(buttonHeight);
        button_8.setId("button_8");
        //button_8.addEventHandler(MouseEvent.MOUSE_CLICKED, new ButtonClickHandler(this));

        button_9 = new Button("9");
        button_9.setPrefWidth(buttonWidth);
        button_9.setPrefHeight(buttonHeight);
        button_9.setId("button_9");
        //button_9.addEventHandler(MouseEvent.MOUSE_CLICKED, new ButtonClickHandler(this));

        button_Div = new Button("/");
        button_Div.setPrefWidth(buttonWidth);
        button_Div.setPrefHeight(buttonHeight);
        button_Div.setId("button_Div");

        button_Perc = new Button("%");
        button_Perc.setPrefWidth(buttonWidth);
        button_Perc.setPrefHeight(buttonHeight);
        button_Perc.setId("button_Perc");

        //4th Row
        button_4 = new Button("4");
        button_4.setPrefWidth(buttonWidth);
        button_4.setPrefHeight(buttonHeight);
        button_4.setId("button_4");
        //button_4.addEventHandler(MouseEvent.MOUSE_CLICKED, new ButtonClickHandler(this));

        button_5 = new Button("5");
        button_5.setPrefWidth(buttonWidth);
        button_5.setPrefHeight(buttonHeight);
        button_5.setId("button_5");
        //button_5.addEventHandler(MouseEvent.MOUSE_CLICKED, new ButtonClickHandler(this));

        button_6 = new Button("6");
        button_6.setPrefWidth(buttonWidth);
        button_6.setPrefHeight(buttonHeight);
        button_6.setId("button_6");
        //button_6.addEventHandler(MouseEvent.MOUSE_CLICKED, new ButtonClickHandler(this));

        button_Mult = new Button("*");
        button_Mult.setPrefWidth(buttonWidth);
        button_Mult.setPrefHeight(buttonHeight);
        button_Mult.setId("button_Mult");

        button_Recip = new Button("1/x");
        button_Recip.setPrefWidth(buttonWidth);
        button_Recip.setPrefHeight(buttonHeight);
        button_Recip.setId("button_Recip");

        //5th Row
        button_1 = new Button("1");
        button_1.setPrefWidth(buttonWidth);
        button_1.setPrefHeight(buttonHeight);
        button_1.setId("button_1");
        //button_1.addEventHandler(MouseEvent.MOUSE_CLICKED, new ButtonClickHandler(this));

        button_2 = new Button("2");
        button_2.setPrefWidth(buttonWidth);
        button_2.setPrefHeight(buttonHeight);
        button_2.setId("button_2");
        //button_2.addEventHandler(MouseEvent.MOUSE_CLICKED, new ButtonClickHandler(this));

        button_3 = new Button("3");
        button_3.setPrefWidth(buttonWidth);
        button_3.setPrefHeight(buttonHeight);
        button_3.setId("button_3");
        //button_3.addEventHandler(MouseEvent.MOUSE_CLICKED, new ButtonClickHandler(this));

        button_Minus = new Button("-");
        button_Minus.setPrefWidth(buttonWidth);
        button_Minus.setPrefHeight(buttonHeight);
        button_Minus.setId("button_Minus");

        button_Equal = new Button("=");
        button_Equal.setPrefWidth(buttonWidth);
        button_Equal.setPrefHeight(2 * buttonHeight + BUTTON_GAP);
        button_Equal.setId("button_Equal");

        //6th Row
        button_0 = new Button("0");
        button_0.setPrefWidth(2 * buttonWidth + BUTTON_GAP);
        button_0.setPrefHeight(buttonHeight);
        button_0.setId("button_0");
        //button_0.addEventHandler(MouseEvent.MOUSE_CLICKED, new ButtonClickHandler(this));

        button_Deci = new Button(".");
        button_Deci.setPrefWidth(buttonWidth);
        button_Deci.setPrefHeight(buttonHeight);
        button_Deci.setId("button_Deci");
        //button_Deci.addEventHandler(MouseEvent.MOUSE_CLICKED, new ButtonClickHandler(this));

        button_Plus = new Button("+");
        button_Plus.setPrefWidth(buttonWidth);
        button_Plus.setPrefHeight(buttonHeight);
        button_Plus.setId("button_Plus");

        numPane.add(button_MC,0,0);
        numPane.add(button_MR,1,0);
        numPane.add(button_MS,2,0);
        numPane.add(button_MPlus,3,0);
        numPane.add(button_MMin,4,0);
        numPane.add(button_Del,0,1);
        numPane.add(button_CE,1,1);
        numPane.add(button_C,2,1);
        numPane.add(button_PlusMin,3,1);
        numPane.add(button_Sqrt,4,1);
        numPane.add(button_7,0,2);
        numPane.add(button_8,1,2);
        numPane.add(button_9,2,2);
        numPane.add(button_Div,3,2);
        numPane.add(button_Perc,4,2);
        numPane.add(button_4,0,3);
        numPane.add(button_5,1,3);
        numPane.add(button_6,2,3);
        numPane.add(button_Mult,3,3);
        numPane.add(button_Recip,4,3);
        numPane.add(button_1,0,4);
        numPane.add(button_2,1,4);
        numPane.add(button_3,2,4);
        numPane.add(button_Minus,3,4);
        numPane.add(button_Equal,4,4);
        numPane.setRowSpan(button_Equal, 2);
        numPane.add(button_0, 0, 5);
        numPane.setColumnSpan(button_0,2);
        numPane.add(button_Deci,2,5);
        numPane.add(button_Plus,3,5);
        //numPane.setGridLinesVisible(true);

       /* for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < 5; j++)
            {
                Button button = new Button(j+""+i);
                button.setPrefWidth(buttonWidth);
                button.setPrefHeight(buttonHeight);
                button.setMinSize(buttonWidth,buttonHeight);
                button.setMaxSize(buttonWidth,buttonHeight);

                numPane.add(button,i,j);
            }
        }*/


        /* Operation Pane */
        operPane.setHgap(BUTTON_GAP);
        operPane.setVgap(BUTTON_GAP);
        operPane.setPadding(new Insets(0,10,0,0));
        HBox radioBox = new HBox();
        radioBox.setStyle("-fx-border-width: 2px; -fx-border-color: cornflowerblue; -fx-border-radius:3 3 3 3" );
        radioBox.setSpacing(BUTTON_GAP);
        radioBox.setAlignment(Pos.CENTER);
        //radioBox.setPadding(new Insets(10));
        radioBox.setPrefWidth(buttonWidth * 5);
        radioBox.setPrefHeight(buttonHeight -4);

        ToggleGroup group = new ToggleGroup();
        RadioButton radio_Degrees = new RadioButton("Degrees");
        RadioButton radio_Radians = new RadioButton("Radians");
        RadioButton radio_Grads = new RadioButton("Grads");
        radio_Degrees.setToggleGroup(group);
        radio_Grads.setToggleGroup(group);
        radio_Radians.setToggleGroup(group);
        group.selectToggle(radio_Degrees);

        radioBox.getChildren().addAll(radio_Degrees,radio_Radians,radio_Grads);
        operPane.add(radioBox,0,0);
        operPane.setColumnSpan(radioBox,5);
        //operPane.setGridLinesVisible(true);

        //1st Row
        button_Inv = new Button("Inv");
        button_Inv.setMaxSize(buttonWidth,buttonHeight);
        button_Inv.setPrefWidth(buttonWidth);
        button_Inv.setPrefHeight(buttonHeight);
        button_Inv.setMinSize(buttonWidth,buttonHeight);
        button_Inv.setMaxSize(buttonWidth,buttonHeight);
        button_Inv.setId("button_Inv");

        button_NatLog = new Button("ln");
        button_NatLog.setMaxSize(buttonWidth,buttonHeight);
        button_NatLog.setPrefWidth(buttonWidth);
        button_NatLog.setPrefHeight(buttonHeight);
        button_NatLog.setMinSize(buttonWidth,buttonHeight);
        button_NatLog.setMaxSize(buttonWidth,buttonHeight);
        button_NatLog.setId("button_NatLog");

        button_LPar = new Button("(");
        button_LPar.setMaxSize(buttonWidth,buttonHeight);
        button_LPar.setPrefWidth(buttonWidth);
        button_LPar.setPrefHeight(buttonHeight);
        button_LPar.setMinSize(buttonWidth,buttonHeight);
        button_LPar.setMaxSize(buttonWidth,buttonHeight);
        button_LPar.setId("button_LPar");

        button_RPar = new Button(")");
        button_RPar.setMaxSize(buttonWidth,buttonHeight);
        button_RPar.setPrefWidth(buttonWidth);
        button_RPar.setPrefHeight(buttonHeight);
        button_RPar.setMinSize(buttonWidth,buttonHeight);
        button_RPar.setMaxSize(buttonWidth,buttonHeight);
        button_RPar.setId("button_RPar");

        //2nd Row
        button_Int = new Button("Int");
        button_Int.setMaxSize(buttonWidth,buttonHeight);
        button_Int.setPrefWidth(buttonWidth);
        button_Int.setPrefHeight(buttonHeight);
        button_Int.setMinSize(buttonWidth,buttonHeight);
        button_Int.setMaxSize(buttonWidth,buttonHeight);
        button_Int.setId("button_Int");

        button_Sinh = new Button("Sinh");
        button_Sinh.setMaxSize(buttonWidth,buttonHeight);
        button_Sinh.setPrefWidth(buttonWidth);
        button_Sinh.setPrefHeight(buttonHeight);
        button_Sinh.setMinSize(buttonWidth,buttonHeight);
        button_Sinh.setMaxSize(buttonWidth,buttonHeight);
        button_Sinh.setId("button_Sinh");

        button_Sin = new Button("Sin");
        button_Sin.setMaxSize(buttonWidth,buttonHeight);
        button_Sin.setPrefWidth(buttonWidth);
        button_Sin.setPrefHeight(buttonHeight);
        button_Sin.setMinSize(buttonWidth,buttonHeight);
        button_Sin.setMaxSize(buttonWidth,buttonHeight);
        button_Sin.setId("button_Sin");

        button_Sqre = new Button("^2");
        button_Sqre.setMaxSize(buttonWidth,buttonHeight);
        button_Sqre.setPrefWidth(buttonWidth);
        button_Sqre.setPrefHeight(buttonHeight);
        button_Sqre.setMinSize(buttonWidth,buttonHeight);
        button_Sqre.setMaxSize(buttonWidth,buttonHeight);
        button_Sqre.setId("button_Sqre");

        button_NFact = new Button("n!");
        button_NFact.setMaxSize(buttonWidth,buttonHeight);
        button_NFact.setPrefWidth(buttonWidth);
        button_NFact.setPrefHeight(buttonHeight);
        button_NFact.setMinSize(buttonWidth,buttonHeight);
        button_NFact.setMaxSize(buttonWidth,buttonHeight);
        button_NFact.setId("button_NFact");

        //3rd Row
        button_Dms = new Button("dms");
        button_Dms.setMaxSize(buttonWidth,buttonHeight);
        button_Dms.setPrefWidth(buttonWidth);
        button_Dms.setPrefHeight(buttonHeight);
        button_Dms.setMinSize(buttonWidth,buttonHeight);
        button_Dms.setMaxSize(buttonWidth,buttonHeight);
        button_Dms.setId("button_Dms");

        button_Cosh = new Button("Cosh");
        button_Cosh.setMaxSize(buttonWidth,buttonHeight);
        button_Cosh.setPrefWidth(buttonWidth);
        button_Cosh.setPrefHeight(buttonHeight);
        button_Cosh.setMinSize(buttonWidth,buttonHeight);
        button_Cosh.setMaxSize(buttonWidth,buttonHeight);
        button_Cosh.setId("button_Cosh");

        button_Cos = new Button("Cos");
        button_Cos.setMaxSize(buttonWidth,buttonHeight);
        button_Cos.setPrefWidth(buttonWidth);
        button_Cos.setPrefHeight(buttonHeight);
        button_Cos.setMinSize(buttonWidth,buttonHeight);
        button_Cos.setMaxSize(buttonWidth,buttonHeight);
        button_Cos.setId("button_Cos");

        button_Power = new Button("^y");
        button_Power.setMaxSize(buttonWidth,buttonHeight);
        button_Power.setPrefWidth(buttonWidth);
        button_Power.setPrefHeight(buttonHeight);
        button_Power.setMinSize(buttonWidth,buttonHeight);
        button_Power.setMaxSize(buttonWidth,buttonHeight);
        button_Power.setId("button_Power");

        hex = 0x221A;
        button_ROOT = new Button("y"+(char)hex+"X");
        button_ROOT.setMaxSize(buttonWidth,buttonHeight);
        button_ROOT.setPrefWidth(buttonWidth);
        button_ROOT.setPrefHeight(buttonHeight);
        button_ROOT.setMinSize(buttonWidth,buttonHeight);
        button_ROOT.setMaxSize(buttonWidth,buttonHeight);
        button_ROOT.setId("button_ROOT");

        //4th Row
        hex = 0x03A0;
        button_PI = new Button((char)hex+"");
        button_PI.setMaxSize(buttonWidth,buttonHeight);
        button_PI.setPrefWidth(buttonWidth);
        button_PI.setPrefHeight(buttonHeight);
        button_PI.setMinSize(buttonWidth,buttonHeight);
        button_PI.setMaxSize(buttonWidth,buttonHeight);
        button_PI.setId("button_PI");

        button_Tanh = new Button("Tanh");
        button_Tanh.setMaxSize(buttonWidth,buttonHeight);
        button_Tanh.setPrefWidth(buttonWidth);
        button_Tanh.setPrefHeight(buttonHeight);
        button_Tanh.setMinSize(buttonWidth,buttonHeight);
        button_Tanh.setMaxSize(buttonWidth,buttonHeight);
        button_Tanh.setId("button_Tanh");

        button_Tan = new Button("Tan");
        button_Tan.setMaxSize(buttonWidth,buttonHeight);
        button_Tan.setPrefWidth(buttonWidth);
        button_Tan.setPrefHeight(buttonHeight);
        button_Tan.setMinSize(buttonWidth,buttonHeight);
        button_Tan.setMaxSize(buttonWidth,buttonHeight);
        button_Tan.setId("button_Tan");

        button_Cube = new Button("^3");
        button_Cube.setMaxSize(buttonWidth,buttonHeight);
        button_Cube.setPrefWidth(buttonWidth);
        button_Cube.setPrefHeight(buttonHeight);
        button_Cube.setMinSize(buttonWidth,buttonHeight);
        button_Cube.setMaxSize(buttonWidth,buttonHeight);
        button_Cube.setId("button_Cube");

        hex = 0x221B;
        button_CubeRoot = new Button((char)hex+"X");
        button_CubeRoot.setMaxSize(buttonWidth,buttonHeight);
        button_CubeRoot.setPrefWidth(buttonWidth);
        button_CubeRoot.setPrefHeight(buttonHeight);
        button_CubeRoot.setMinSize(buttonWidth,buttonHeight);
        button_CubeRoot.setMaxSize(buttonWidth,buttonHeight);
        button_CubeRoot.setId("button_CubeRoot");

        operPane.add(button_Inv,1,1);
        operPane.add(button_NatLog,2,1);
        operPane.add(button_LPar,3,1);
        operPane.add(button_RPar,4,1);
        operPane.add(button_Int,0,2);
        operPane.add(button_Sinh,1,2);
        operPane.add(button_Sin,2,2);
        operPane.add(button_Sqre,3,2);
        operPane.add(button_NFact,4,2);
        operPane.add(button_Dms,0,3);
        operPane.add(button_Cosh,1,3);
        operPane.add(button_Cos,2,3);
        operPane.add(button_Power,3,3);
        operPane.add(button_ROOT,4,3);
        operPane.add(button_PI,0,4);
        operPane.add(button_Tanh,1,4);
        operPane.add(button_Tan,2,4);
        operPane.add(button_Cube,3,4);
        operPane.add(button_CubeRoot,4,4);


    }


}
