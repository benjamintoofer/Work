package sample;

import javafx.application.Application;
import javafx.application.Platform;

/**
 * Created by benjamintoofer on 7/9/14.
 */
public class AnimationThread extends Thread {

    private Table myPane;
    private boolean runThread;
    private double speed = TestConstants.SWITCH_SPEED;


    public AnimationThread(Table myPane)
    {
        this.myPane = myPane;
        runThread = true;
    }

    @Override
    public void run() {
        try{
            while(runThread)
            {
                if(myPane.getSelectedCell() != null && myPane.getCellToSwitch() != null)
                {
                    double cellSelectedX = myPane.getSelectedCell().getGridXPos();
                    double cellSelectedY = myPane.getSelectedCell().getGridYPos();
                    double cellSwitchX = myPane.getCellToSwitch().getGridXPos();
                    double cellSwitchY = myPane.getCellToSwitch().getGridYPos();

                    boolean horizontalSwitch;

                    if(cellSelectedX == cellSwitchX)
                    {
                        horizontalSwitch = false;
                    }else{
                        horizontalSwitch = true;
                    }

                    switchAnimation(horizontalSwitch,cellSelectedX,cellSelectedY,cellSwitchX,cellSwitchY);
                }




               /* Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        //System.out.println("fx thread");

                    }


                });*/

                Thread.sleep(40);
            }

        }catch (InterruptedException ex){}

    }
    public void switchAnimation(boolean horizontalSwitch,double cellSelectedX,double cellSelectedY,double cellSwitchX,double cellSwitchY)
    {
        if(horizontalSwitch)
        {
            if(myPane.getSelectedCell().getFruit().getCenterX() < (cellSwitchX - speed) )//Switch center with right
            {
                myPane.getSelectedCell().getFruit().setCenterX(myPane.getSelectedCell().getFruit().getCenterX() + speed);
                myPane.getCellToSwitch().getFruit().setCenterX(myPane.getCellToSwitch().getFruit().getCenterX() - speed);

            }else if(myPane.getSelectedCell().getFruit().getCenterX() > (cellSwitchX + speed))//Switch center with left
            {
                myPane.getSelectedCell().getFruit().setCenterX(myPane.getSelectedCell().getFruit().getCenterX() - speed);
                myPane.getCellToSwitch().getFruit().setCenterX(myPane.getCellToSwitch().getFruit().getCenterX() + speed);

            }else{
                //set new xPos on Grid

                double tempX = myPane.getSelectedCell().getGridXPos();
                Fruit tempFruit = myPane.getFruitList()[myPane.getSelectedCell().getRowIndex()][myPane.getSelectedCell().getColIndex()];

                myPane.getFruitList()[myPane.getSelectedCell().getRowIndex()][myPane.getSelectedCell().getColIndex()].getFruit().setCenterX(cellSwitchX);
                myPane.getFruitList()[myPane.getCellToSwitch().getRowIndex()][myPane.getCellToSwitch().getColIndex()].getFruit().setCenterX(cellSelectedX);

                //Switch Grid position
                myPane.getSelectedCell().setGridXPos(cellSwitchX);
                myPane.getCellToSwitch().setGridXPos(tempX);




                //switch positions in array
                myPane.setFruitinArray(tempFruit.getRowIndex(),tempFruit.getColIndex(),myPane.getCellToSwitch());
                myPane.setFruitinArray(myPane.getCellToSwitch().getRowIndex(),myPane.getCellToSwitch().getColIndex(),tempFruit);

                //Set new Grid index
                System.out.println("selected cell index: "+myPane.getSelectedCell().getRowIndex()+" "+myPane.getSelectedCell().getColIndex());
                int tempIndex = myPane.getSelectedCell().getColIndex();
                myPane.getSelectedCell().setColIndex(myPane.getCellToSwitch().getColIndex());
                myPane.getCellToSwitch().setColIndex(tempIndex);
                System.out.println("new selected cell index: "+myPane.getSelectedCell().getRowIndex()+" "+myPane.getSelectedCell().getColIndex());
                myPane.setCellToSwitch(null);
                myPane.setSelectedCell(null);
            }

        }else{

            if(myPane.getSelectedCell().getFruit().getCenterY() < (cellSwitchY - speed))//Switch center with right
            {
                //System.out.println(myPane.getSelectedCell().getCenterX());
                myPane.getSelectedCell().getFruit().setCenterY(myPane.getSelectedCell().getFruit().getCenterY() + speed);
                myPane.getCellToSwitch().getFruit().setCenterY(myPane.getCellToSwitch().getFruit().getCenterY() - speed);

            }else if(myPane.getSelectedCell().getFruit().getCenterY() > (cellSwitchY + speed))//Switch center with left
            {
                myPane.getSelectedCell().getFruit().setCenterY(myPane.getSelectedCell().getFruit().getCenterY() - speed);
                myPane.getCellToSwitch().getFruit().setCenterY(myPane.getCellToSwitch().getFruit().getCenterY() + speed);

            }else{
                //set new yPos on Grid

                double tempY = myPane.getSelectedCell().getGridYPos();
                Fruit tempFruit = myPane.getFruitList()[myPane.getSelectedCell().getRowIndex()][myPane.getSelectedCell().getColIndex()];



                myPane.getFruitList()[myPane.getSelectedCell().getRowIndex()][myPane.getSelectedCell().getColIndex()].getFruit().setCenterY(cellSwitchY);
                myPane.getFruitList()[myPane.getCellToSwitch().getRowIndex()][myPane.getCellToSwitch().getColIndex()].getFruit().setCenterY(cellSelectedY);

                //Switch Grid position
                myPane.getSelectedCell().setGridYPos(cellSwitchY);
                myPane.getCellToSwitch().setGridYPos(tempY);

                //switch positions in array
                myPane.setFruitinArray(tempFruit.getRowIndex(),tempFruit.getColIndex(),myPane.getCellToSwitch());
                myPane.setFruitinArray(myPane.getCellToSwitch().getRowIndex(),myPane.getCellToSwitch().getColIndex(),tempFruit);

                //Set new Grid index
                System.out.println("selected cell index: "+myPane.getSelectedCell().getRowIndex()+" "+myPane.getSelectedCell().getColIndex());
                int tempIndex = myPane.getSelectedCell().getRowIndex();
                myPane.getSelectedCell().setRowIndex(myPane.getCellToSwitch().getRowIndex());
                myPane.getCellToSwitch().setRowIndex(tempIndex);
                System.out.println("new selected cell index: "+myPane.getSelectedCell().getRowIndex()+" "+myPane.getSelectedCell().getColIndex());
                myPane.setCellToSwitch(null);
                myPane.setSelectedCell(null);
            }


        }
    }
}
