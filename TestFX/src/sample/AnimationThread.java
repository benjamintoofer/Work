package sample;

import javafx.application.Application;
import javafx.application.Platform;

/**
 * Created by benjamintoofer on 7/9/14.
 */
public class AnimationThread extends Thread {

    private Table myPane;
    private boolean runThread;


    double cellSelectedX;
    double cellSelectedY;
    double cellSwitchX;
    double cellSwitchY;

    Fruit selected;
    Fruit toSwitch;

    boolean horizontalSwitch;



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
                    cellSelectedX = myPane.getSelectedCell().getGridXPos();
                    cellSelectedY = myPane.getSelectedCell().getGridYPos();
                    cellSwitchX = myPane.getCellToSwitch().getGridXPos();
                    cellSwitchY = myPane.getCellToSwitch().getGridYPos();

                    selected = myPane.getSelectedCell();
                    toSwitch = myPane.getCellToSwitch();

                    myPane.switchAnimation = true;

                    if(cellSelectedX == cellSwitchX)
                    {
                        horizontalSwitch = false;
                    }else{
                        horizontalSwitch = true;
                    }


                }




                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if(myPane.switchAnimation)
                        {
                            if(myPane.getSelectedCell() != null && myPane.getCellToSwitch() != null)
                            {
                                FruitAnimation.switchAnimation(myPane,horizontalSwitch,cellSelectedX,cellSelectedY,cellSwitchX,cellSwitchY);
                            }


                            boolean selectedChainFound = myPane.checkChainInGrid(selected);
                            boolean switchChainFound = myPane.checkChainInGrid(toSwitch);

                            if((!selectedChainFound || !switchChainFound) && !myPane.switchAnimation)
                            {
                                System.out.println("switch back");
                                FruitAnimation.switchAnimation(myPane,horizontalSwitch,cellSwitchX,cellSwitchY,cellSelectedX,cellSelectedY);
                            }

                        }

                    }


                });

                Thread.sleep(40);
            }

        }catch (InterruptedException ex){}

    }

}
