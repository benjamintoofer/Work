package sample;


import java.util.Vector;

/**
 * Created by benjamintoofer on 7/7/14.
 */
public class GridCell {

    int cellRow,cellCol;
    int colorNumber;
    private GridCell rightCell,leftCell,topCell,bottomCell;


    public GridCell(int row, int col,int colorNumber)
    {
        cellRow = row;
        cellCol = col;
        this.colorNumber = colorNumber;
    }
    public int getRow()
    {
        return cellRow;
    }
    public int getCol()
    {
        return cellCol;
    }

    //Set Right,Left,Top,Bottom cells
    public void setRightCell(GridCell rightCell)
    {
        if(rightCell != null)
        {
            this.rightCell = rightCell;
        }
    }
    public void setLeftCell(GridCell leftCell)
    {
        if(leftCell != null)
        {
            this.leftCell = leftCell;
        }
    }
    public void setTopCell(GridCell topCell)
    {
        if(topCell != null)
        {
            this.topCell = topCell;
        }
    }
    public void setBottomCell(GridCell bottomCell)
    {
        if(bottomCell != null)
        {
            this.bottomCell = bottomCell;
        }
    }

    //Get Left,Right,Top,Bottom Cells
    public GridCell getRight()
    {
        return rightCell;
    }
    public GridCell getLeft()
    {
        return leftCell;
    }
    public GridCell getBottom()
    {
        return bottomCell;
    }
    public GridCell getTop()
    {
        return topCell;
    }

}
