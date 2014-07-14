package sample;

import javafx.scene.Node;
import javafx.scene.shape.Circle;
/**
 * Created by benjamintoofer on 7/13/14.
 */
public class Fruit {

    private Circle fruit;
    private double gridXPos;
    private  double gridYPos;
    private int rowIndex,colIndex;

    public Fruit(Circle fruit,int row,int col)
    {
        this.fruit = fruit;
        rowIndex = row;
        colIndex = col;
        gridXPos = fruit.getCenterX();
        gridYPos = fruit.getCenterY();
    }
    public double getGridXPos()
    {
        return gridXPos;
    }
    public double getGridYPos()
    {
        return gridYPos;
    }
    public void setGridXPos(double xPos)
    {
        gridXPos =xPos;
    }
    public void setGridYPos(double yPos)
    {
        gridYPos = yPos;
    }
    public Circle getFruit()
    {
        return fruit;
    }
    public int getRowIndex()
    {
        return rowIndex;
    }
    public int getColIndex()
    {
        return colIndex;
    }
    public void setRowIndex(int row)
    {
        rowIndex = row;
    }
    public void setColIndex(int col)
    {
        colIndex = col;
    }
}
