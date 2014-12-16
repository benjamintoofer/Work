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
    private String holdOper = null;
    private Stack<String> numList;
    private ArrayList<String> operList;

    public Equation()
    {
        numList = new Stack<String>();
        operList = new ArrayList<String>();
    }

    public int getNumSize()
    {
        //System.out.println(operList.size());
        return numList.size();

    }
    public  int getOperSize()
    {
        for(int i = 0; i < operList.size(); i++)
            System.out.println(operList.get(i));
        return operList.size();
    }
    public void setHoldOper(String oper)
    {
        holdOper = oper;
    }
    public String getHoldOper()
    {
        return holdOper;
    }

    public void addOper(String oper)
    {
        operList.add(oper);
    }

    public boolean isOperListEmpty()
    {
        return operList.isEmpty();
    }
    public void addNum(String num)
    {
        numList.push(num);
    }

    public String getTopOper()
    {
        if(!operList.isEmpty())
        {
            return operList.get(operList.size() - 1);
        }else{

            return "no";
        }

    }
    public void add(String oper, String num)
    {
        numList.push(num);


        operList.add(oper);

    }

    public String popNum()
    {
        if(numList.isEmpty())
        {
            System.out.println("error num list is empty");
            return null;
        }else{
            return numList.pop();
        }
    }

    public String popOperTop()
    {
        if(operList.isEmpty())
        {
            System.out.println("error oper list is empty");
            return null;
        }else{
            return operList.remove(operList.size() - 1);
        }
    }
    public String popOperBottom()
    {
        return operList.remove(0);
    }
}
