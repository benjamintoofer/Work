package sample;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by benjamintoofer on 12/2/14.
 */
public class Equation {

    private double firstNum;
    private int secondNum;
    private String holdOper = "";
    private Stack<Double> numList;
    private Stack<String> operList;

    public Equation()
    {
        numList = new Stack<Double>();
        operList = new Stack<String>();
    }

    public void add(String oper, double num)
    {
        numList.push(num);
        if(oper.equals("*") || oper.equals("/"))
        {
            holdOper = oper;

        }else{
            operList.push(oper);
        }
    }
}
