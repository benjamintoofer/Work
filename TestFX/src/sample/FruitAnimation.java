package sample;

import java.util.ArrayList;

/**
 * Created by benjamintoofer on 7/16/14.
 */
public class  FruitAnimation {



    public static void explodeFruitAnimation(Table myPane,ArrayList<GridCell> list)
    {



        for(GridCell cell:list)
        {
            if(myPane.getFruitList()[cell.cellRow][cell.cellCol].getFruit().getRadius() < (TestConstants.RADIUS * 2))
            {
                myPane.getFruitList()[cell.cellRow][cell.cellCol].getFruit().setRadius(myPane.getFruitList()[cell.cellRow][cell.cellCol].getFruit().getRadius() + .5);
            }else{
                myPane.switchAnimation = false;
                myPane.explodeAnimation = true;
            }

        }


    }


    public static void switchAnimation(Table myPane,boolean horizontalSwitch,double cellSelectedX,double cellSelectedY,double cellSwitchX,double cellSwitchY)
    {
        double speed = TestConstants.SWITCH_SPEED;

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
                myPane.setFruitInArray(tempFruit.getRowIndex(),tempFruit.getColIndex(),myPane.getCellToSwitch());
                myPane.setFruitInArray(myPane.getCellToSwitch().getRowIndex(),myPane.getCellToSwitch().getColIndex(),tempFruit);



                //Set new Grid index
                System.out.println("selected cell index: "+myPane.getSelectedCell().getRowIndex()+" "+myPane.getSelectedCell().getColIndex());
                int tempIndex = myPane.getSelectedCell().getColIndex();
                myPane.getSelectedCell().setColIndex(myPane.getCellToSwitch().getColIndex());
                myPane.getCellToSwitch().setColIndex(tempIndex);
                System.out.println("new selected cell index: "+myPane.getSelectedCell().getRowIndex()+" "+myPane.getSelectedCell().getColIndex());

                //change color value
                myPane.getTableGrid()[myPane.getSelectedCell().getRowIndex()][myPane.getSelectedCell().getColIndex()].colorNumber = myPane.getSelectedCell().getColorNum();
                myPane.getTableGrid()[myPane.getCellToSwitch().getRowIndex()][myPane.getCellToSwitch().getColIndex()].colorNumber = myPane.getCellToSwitch().getColorNum();

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
                myPane.setFruitInArray(tempFruit.getRowIndex(),tempFruit.getColIndex(),myPane.getCellToSwitch());
                myPane.setFruitInArray(myPane.getCellToSwitch().getRowIndex(),myPane.getCellToSwitch().getColIndex(),tempFruit);

                //Set new Grid index
                System.out.println("selected cell index: "+myPane.getSelectedCell().getRowIndex()+" "+myPane.getSelectedCell().getColIndex());
                int tempIndex = myPane.getSelectedCell().getRowIndex();
                myPane.getSelectedCell().setRowIndex(myPane.getCellToSwitch().getRowIndex());
                myPane.getCellToSwitch().setRowIndex(tempIndex);
                System.out.println("new selected cell index: "+myPane.getSelectedCell().getRowIndex()+" "+myPane.getSelectedCell().getColIndex());

                //change color value
                myPane.getTableGrid()[myPane.getSelectedCell().getRowIndex()][myPane.getSelectedCell().getColIndex()].colorNumber = myPane.getSelectedCell().getColorNum();
                myPane.getTableGrid()[myPane.getCellToSwitch().getRowIndex()][myPane.getCellToSwitch().getColIndex()].colorNumber = myPane.getCellToSwitch().getColorNum();

                myPane.setCellToSwitch(null);
                myPane.setSelectedCell(null);
            }


        }
    }
}
