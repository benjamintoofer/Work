package sample;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;


/**
 * Created by benjamintoofer on 7/8/14.
 */
public class CellClickedEvent implements EventHandler<MouseEvent> {

    private int colWidth = TestConstants.SCENE_WIDTH/TestConstants.NUM_COL;
    private int rowWidth = TestConstants.SCENE_HEIGHT/TestConstants.NUM_ROW;
    private int rowIndexClicked = 0;
    private int colIndexClicked = 0;
    private int rowIndexHold = 0;
    private int colIndexHold = 0;
    private Polygon box;
    private Table myPane;

    public CellClickedEvent(Table myPane)
    {
        this.myPane = myPane;

    }
    @Override
    public void handle(MouseEvent e)
    {
        rowIndexClicked = (int)Math.floor(e.getY()/rowWidth);
        colIndexClicked = (int)Math.floor(e.getX()/colWidth);

        double[] boxXPoints = new double[2];
        double[] boxYPoints = new double[2];

        boxYPoints[0] = rowIndexClicked * rowWidth;
        boxYPoints[1] = rowIndexClicked * rowWidth + (rowWidth);
        boxXPoints[0] = colIndexClicked * colWidth;
        boxXPoints[1] = colIndexClicked * colWidth + (colWidth);

        if(!myPane.getCellSelected() && myPane.getSelectedCell() == null)
        {

            rowIndexHold = rowIndexClicked;
            colIndexHold = colIndexClicked;

            box = new Polygon();
            box.getPoints().addAll(boxXPoints[0],boxYPoints[0],
                    boxXPoints[1],boxYPoints[0],
                    boxXPoints[1],boxYPoints[1],
                    boxXPoints[0],boxYPoints[1]);
            box.setFill(Color.color(0,0,0,0));
            box.setStroke(Color.RED);
            box.setStrokeWidth(2.5);
            myPane.getChildren().add(box);
            myPane.setCellSelected(true);


            //Set cell that is selected
            if(myPane.getSelectedCell() == null)
            {
                myPane.setSelectedCell(myPane.getFruitList()[rowIndexHold][colIndexHold]);
            }



        }else if((myPane.getTableGrid()[rowIndexHold][colIndexHold].getRight() == myPane.getTableGrid()[rowIndexClicked][colIndexClicked]
              || myPane.getTableGrid()[rowIndexHold][colIndexHold].getLeft() == myPane.getTableGrid()[rowIndexClicked][colIndexClicked]
              || myPane.getTableGrid()[rowIndexHold][colIndexHold].getBottom() == myPane.getTableGrid()[rowIndexClicked][colIndexClicked]
              || myPane.getTableGrid()[rowIndexHold][colIndexHold].getTop() == myPane.getTableGrid()[rowIndexClicked][colIndexClicked]) )
        {
            System.out.println("Switch!");

            //Set second cell selected
            if(myPane.getCellToSwitch() == null)
            {
                myPane.setCellToSwitch(myPane.getFruitList()[rowIndexClicked][colIndexClicked]);
                myPane.getChildren().remove(box);
                myPane.setCellSelected(false);
            }

        }else if(myPane.getCellToSwitch() == null){
            System.out.println("reset");
            myPane.setSelectedCell(null);
            myPane.setCellToSwitch(null);
            myPane.getChildren().remove(box);
            myPane.setCellSelected(false);
        }



    }
}
