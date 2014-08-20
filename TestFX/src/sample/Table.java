package sample;

import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.concurrent.Task;

import java.util.ArrayList;


/**
 * Created by benjamintoofer on 7/6/14.
 */
public class Table extends Pane {

    enum Direction{RIGHT,LEFT,BOTTOM,TOP};
    //private enum colorType{RED,BLUE,GREEN,YELLOW};

    private GridCell[][] tableGrid;
    private Fruit [][] fruitArray;


    private int numRows = TestConstants.NUM_ROW;
    private int numCols = TestConstants.NUM_COL;
    private int colWidth = TestConstants.SCENE_WIDTH/numCols;
    private int rowWidth = TestConstants.SCENE_HEIGHT/numRows;
    private int tableHeight = TestConstants.SCENE_HEIGHT;
    private int tableWidth = TestConstants.SCENE_WIDTH;

    private Fruit selectedCell,cellToSwitch;


    private boolean cellSelected = false;
    public  boolean switchAnimation = false;
    public  boolean explodeAnimation = false;

    private AnimationThread gameThread;


    public Table()
    {
        tableGrid = new GridCell[numRows][numCols];
        fruitArray = new Fruit[numRows][numCols];



        drawTable();
        assignValueToCell();
        checkIntroSetup();

        gameThread = new AnimationThread(this);
        gameThread.start();

        this.setOnMouseClicked(new CellClickedEvent(this));
    }

    private void assignValueToCell()
    {
        for(int i = 0; i < numRows; i++)
        {
            for(int j = 0; j < numCols; j++)
            {

                tableGrid[i][j] = new GridCell(i,j,(int)Math.round(Math.random() * 5));

                //Assign left,right,bottom,and top cells to current cell
                if(j-1 >= 0)
                {
                    tableGrid[i][j].setLeftCell(tableGrid[i][j-1]);
                }
                if(i-1 >= 0)
                {
                    tableGrid[i][j].setTopCell(tableGrid[i-1][j]);
                }
                if(i-1 >= 0)
                {
                    tableGrid[i-1][j].setBottomCell(tableGrid[i][j]);
                }
                if(j-1 >= 0)
                {
                    tableGrid[i][j-1].setRightCell(tableGrid[i][j]);
                }

                Circle circle = new Circle((j * colWidth) + (colWidth)*.5,(i * rowWidth) + (rowWidth)*.5,rowWidth/4);
                circle.setFill(colorChoice(tableGrid[i][j].colorNumber));
                circle.setStroke(Color.BLACK);
                fruitArray[i][j] = new Fruit(circle,i,j,tableGrid[i][j].colorNumber);
                this.getChildren().add(circle);


            }
        }
    }
    private void checkIntroSetup()
    {
        GridCell currentCell;


        for(int i = 0; i < numRows; i++)
        {
            for(int j = 0; j < numCols; j++)
            {
                currentCell = tableGrid[i][j];
                //Check 3 in a row from right t0 left
                if(currentCell.getLeft() != null && currentCell.getRight() != null)
                {
                    if(currentCell.getLeft().colorNumber == currentCell.colorNumber && currentCell.getRight().colorNumber == currentCell.colorNumber)
                    {
                        System.out.println("changed horz at "+i+" "+j);
                        currentCell.colorNumber++;
                        fruitArray[i][j].getFruit().setFill(colorChoice(currentCell.colorNumber));
                    }
                }
                if(currentCell.getTop() != null && currentCell.getBottom() != null)
                {
                    if(currentCell.getTop().colorNumber == currentCell.colorNumber && currentCell.getBottom().colorNumber == currentCell.colorNumber)
                    {
                        System.out.println("changed vert at "+i+" "+j);
                        currentCell.colorNumber++;
                        fruitArray[i][j].getFruit().setFill(colorChoice(currentCell.colorNumber));
                    }
                }

            }
        }
    }
    public boolean checkChainInGrid(Fruit fruit)
    {

        boolean chainHorizFound = false;
        boolean chainVertFound = false;
        GridCell cell = this.getTableGrid()[fruit.getRowIndex()][fruit.getColIndex()];
        int colorNumber = fruit.getColorNum();
        ArrayList<GridCell> vertChain = new ArrayList<GridCell>();
        ArrayList<GridCell> horizChain = new ArrayList<GridCell>();

        vertChain.add(cell);
        horizChain.add(cell);
        //System.out.println(colorNumber+" "+cell.cellRow+" "+cell.cellCol + " "+cell.colorNumber);


        checkCellIsChain(cell.getRight(),colorNumber,horizChain,Direction.RIGHT);
        checkCellIsChain(cell.getLeft(),colorNumber,horizChain,Direction.LEFT);
        checkCellIsChain(cell.getTop(),colorNumber,vertChain,Direction.TOP);
        checkCellIsChain(cell.getBottom(),colorNumber,vertChain,Direction.BOTTOM);


        //Remove Chain
        if(horizChain.size() >= 3)
        {

            FruitAnimation.explodeFruitAnimation(this,horizChain);
            if(explodeAnimation)
            {
                removeChain(horizChain);
            }
            chainHorizFound = true;

        }
        if(vertChain.size() >= 3)
        {
            FruitAnimation.explodeFruitAnimation(this,vertChain);
            if(explodeAnimation)
            {
                removeChain(vertChain);
            }
            chainVertFound = true;

        }
        if(chainHorizFound || chainVertFound)
        {
            System.out.println("chain found!");
            return true;
        }else{
            return false;
        }

    }
    private void checkCellIsChain(GridCell cell,int colorNumber,ArrayList<GridCell> list,Direction choice)
    {
       if(cell != null)
       {

           if(cell.colorNumber == colorNumber)
           {
               list.add(cell);
               //System.out.println(counter);
               GridCell nextCell = null;

               switch (choice)
               {
                   case RIGHT:
                       nextCell = cell.getRight();
                       break;
                   case LEFT:
                       nextCell = cell.getLeft();
                       break;
                   case TOP:
                       nextCell = cell.getTop();
                       break;
                   case BOTTOM:
                       nextCell = cell.getBottom();
                       break;
               }

               checkCellIsChain(nextCell,colorNumber,list,choice);

           }


       }

    }
    private void removeChain(ArrayList<GridCell> list)
    {
            for(GridCell cell:list)
            {
                this.getChildren().remove(this.getFruitList()[cell.cellRow][cell.cellCol].getFruit());

            }
        explodeAnimation = false;
    }

    private void drawTable()
    {
      //Draw Rows
      for(int i = 1;i <= numRows; i++)
      {
          Line line = new Line(0,i * colWidth,tableWidth,i * colWidth);
          line.setStroke(Color.BLUE);
          line.setStrokeWidth(1);
          this.getChildren().add(line);
      }

     //Draw Columns
      for(int i = 1; i <= numCols; i++)
      {
          Line line = new Line(i * rowWidth,0,i * rowWidth,tableHeight);
          line.setStroke(Color.BLUE);
          line.setStrokeWidth(1);
          this.getChildren().add(line);
      }

    }
    private Color colorChoice(int colorValue)
    {
        Color color = Color.RED;

        switch (colorValue)
        {
            case 0:
               color = Color.RED;
                break;
            case 1:
                color = Color.BLUE;
                break;
            case 2:
                color = Color.GREEN;
                break;
            case 3:
                color = Color.YELLOW;
                break;
            case 4:
                color = Color.ORANGE;
                break;
            case 5:
                color = Color.BLUEVIOLET;
                break;
            case 6:
                color = Color.RED;
                break;
        }
      return color;
    }

    public void setCellSelected(boolean selected)
    {
        this.cellSelected = selected;
    }
    public boolean getCellSelected()
    {
        return cellSelected;
    }
    public Fruit[][] getFruitList()
    {
        return fruitArray;
    }
    public void setFruitInArray(int row,int col,Fruit fruit)
    {
        fruitArray[row][col] = fruit;
    }
    public GridCell[][] getTableGrid()
    {
        return tableGrid;
    }
    public Fruit getSelectedCell()
    {
        return selectedCell;
    }
    public void setSelectedCell(Fruit cell)
    {
        selectedCell = cell;
    }
    public Fruit getCellToSwitch()
    {
        return cellToSwitch;
    }
    public void setCellToSwitch(Fruit cell)
    {
        cellToSwitch = cell;
    }

}