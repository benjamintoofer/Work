package sample;

import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.event.*;


/**
 * Created by benjamintoofer on 7/6/14.
 */
public class Table extends Pane {

    //private enum colorType{RED,BLUE,GREEN,YELLOW};

    private GridCell[][] tableGrid;
    private Circle [][] circleLocation;
    private int numRows = TestConstants.NUM_ROW;
    private int numCols = TestConstants.NUM_COL;
    private int colWidth = TestConstants.SCENE_WIDTH/numCols;
    private int rowWidth = TestConstants.SCENE_HEIGHT/numRows;
    private int tableHeight = TestConstants.SCENE_HEIGHT;
    private int tableWidth = TestConstants.SCENE_WIDTH;


    public Table()
    {
        tableGrid = new GridCell[numRows][numCols];
        circleLocation = new Circle[numRows][numCols];
        drawTable();
        assignValueToCell();
        checkIntroSetup();

        this.setOnMouseClicked(new CellClickedEvent());
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
                circleLocation[i][j] = circle;
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
                        circleLocation[i][j].setFill(colorChoice(currentCell.colorNumber));
                    }
                }
                if(currentCell.getTop() != null && currentCell.getBottom() != null)
                {
                    if(currentCell.getTop().colorNumber == currentCell.colorNumber && currentCell.getBottom().colorNumber == currentCell.colorNumber)
                    {
                        System.out.println("changed vert at "+i+" "+j);
                        currentCell.colorNumber++;
                        circleLocation[i][j].setFill(colorChoice(currentCell.colorNumber));
                    }
                }

            }
        }
    }

    private void drawTable()
    {
      //Draw Rows
      for(int i = 1;i <= numRows; i++)
      {
          Line line = new Line(10,i * colWidth,tableWidth,i * colWidth);
          line.setStroke(Color.BLUE);
          line.setStrokeWidth(1);
          this.getChildren().add(line);
      }

     //Draw Columns
      for(int i = 1; i <= numCols; i++)
      {
          Line line = new Line(i * rowWidth,10,i * rowWidth,tableHeight);
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

}
