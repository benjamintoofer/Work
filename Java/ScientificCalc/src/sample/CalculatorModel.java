package sample;

/**
 * Created by benjamintoofer on 11/20/14.
 */
public class CalculatorModel {

    public CalculatorModel()
    {

    }

    public double calculateOperation(double firstNum,double secondNum,String oper)
    {
        double result = firstNum;
        //System.out.println(oper);
        if(oper.equals("button_Plus"))
        {
            result = firstNum + secondNum;


        }else if(oper.equals("button_Div"))
        {
            if(secondNum != 0)
            {
                result = firstNum/secondNum;
            }else{

            }

        }else if(oper.equals("button_Minus"))
        {
            result = firstNum - secondNum;

        }else if(oper.equals("button_Mult"))
        {
            result = firstNum * secondNum;

        }
        /*else if(oper.equals("button_Equal"))
        {
            result =
        }*/

        return result;

    }
    public boolean checkForMultiFirst(String oper)
    {
        if(oper.equals("button_Div") || oper.equals("button_Mult"))
        {
            return true;
        }else{

            return false;
        }
    }
    public String getOperationSymbol(String oper)
    {
        String operation = "";

        if(oper.equals("button_Plus"))
        {
            operation = "+";


        }else if(oper.equals("button_Div"))
        {
            operation = "/";

        }else if(oper.equals("button_Minus"))
        {
            operation = "-";

        }else if(oper.equals("button_Mult"))
        {
            operation = "*";

        }

        return operation;
    }
}
