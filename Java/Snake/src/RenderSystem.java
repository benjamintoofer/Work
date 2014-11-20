import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.canvas.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.geometry.Point2D;

import static javafx.scene.input.KeyCode.*;


/**
 * Created by benjamintoofer on 11/15/14.
 */
public class RenderSystem extends Canvas {

    private int width,height;
    private Grid myGrid;
    private Snake mySnake;
    private Apple myApple;
    private MainMenu myMenu;
    private Canvas buttonLayer;

    private int moveSnakeX = 0;
    private int moveSnakeY = 0;

    private boolean hardMode = false;
    private int counter = 0;
    private boolean appleEaten = false;

    private static final int END_GAME_STATE = 2;
    private static final int GAME_STATE = 1;
    private static final int MENU_STATE = 0;
    private int currentState = 0;

    /* FPS variables */
    private int score = 0;
    private long lastTime;
    private int frameCounter;
    private int frameRate = 0;

    public RenderSystem(int width, int height)
    {
        super(width,height);
        this.width = width;
        this.height = height;



        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                if(currentState == MENU_STATE)
                {
                    System.out.println("clicked");
                    //Easy Button
                    if(mouseEvent.getX() > myMenu.easyButton.getX() && mouseEvent.getX() < myMenu.easyButton.getX() + myMenu.easyButton.getWidth()
                            && mouseEvent.getY() > myMenu.easyButton.getY() && mouseEvent.getY() < myMenu.easyButton.getY() + myMenu.easyButton.getHeight())
                    {
                        System.out.println("easy pressed");
                        currentState = GAME_STATE;
                        hardMode = false;
                    }

                    //Hard Button
                    if(mouseEvent.getX() > myMenu.hardButton.getX() && mouseEvent.getX() < myMenu.hardButton.getX() + myMenu.hardButton.getWidth()
                            && mouseEvent.getY() > myMenu.hardButton.getY() && mouseEvent.getY() < myMenu.hardButton.getY() + myMenu.hardButton.getHeight())
                    {
                        System.out.println("hard pressed");
                        currentState = GAME_STATE;
                        hardMode = true;
                    }
                }
            }
        });
        this.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent)
            {
                switch (keyEvent.getCode())
                {

                    case DIGIT1:
                        currentState = MENU_STATE;
                        break;
                    case DIGIT2:
                        currentState = GAME_STATE;
                        break;
                    case SPACE:
                        //mySnake.addSegment();
                        break;
                    case UP:
                        if(moveSnakeY == 0)
                        {
                            moveSnakeY = -1;
                            moveSnakeX = 0;
                        }
                        break;
                    case DOWN:
                        if(moveSnakeY == 0)
                        {
                            moveSnakeY = 1;
                            moveSnakeX = 0;
                        }
                        break;
                    case LEFT:
                        if(moveSnakeX == 0)
                        {
                            moveSnakeX = -1;
                            moveSnakeY = 0;
                        }
                        break;
                    case RIGHT:
                        if(moveSnakeX == 0)
                        {
                            moveSnakeX = 1;
                            moveSnakeY = 0;
                        }
                        break;
                }
            }
        });

        buttonLayer = new Canvas();

        myGrid = new Grid(width,height);
        mySnake = new Snake();
        mySnake.setSnake(myGrid.getCols()/2,myGrid.getRows()/2);
        System.out.println(myGrid.getCols()+" "+myGrid.getRows());
        myApple = new Apple(generateApplePos());
        myMenu = new MainMenu(this);
    }

    public void tick()
    {

        switch (currentState)
        {
            case MENU_STATE:
                tickMenu();
                break;
            case GAME_STATE:
                tickGame();
                break;
            case END_GAME_STATE:
                tickEndGame();
                break;
        }

        //System.out.println(mySnake.headSnake.yPos +"  "+(myGrid.getRows()-1));

        /* Check for eaten apple */
        if(mySnake.headSnake.xPos == myApple.xPos && mySnake.headSnake.yPos == myApple.yPos && hardMode)
        {
            myApple.setApplePos(generateApplePos());
            mySnake.addSegment();
            score++;
        }else if(mySnake.headSnake.xPos == myApple.xPos && mySnake.headSnake.yPos == myApple.yPos && !hardMode){
            appleEaten = true;System.out.println("here");
            if(appleEaten && counter == 1)
            {
                System.out.println("also here");
                myApple.setApplePos(generateApplePos());
                mySnake.addSegment();
                score++;
                appleEaten = false;
            }
        }

        /* Check for Boundaries */
        checkBoundaries(hardMode);



    }
    private void checkBoundaries(boolean hardMode)
    {
        if(hardMode)
        {
            if(mySnake.headSnake.xPos < 0 ||mySnake.headSnake.xPos > myGrid.getCols()-1
                    || mySnake.headSnake.yPos < 0 || mySnake.headSnake.yPos > myGrid.getRows()-1)
            {
                System.out.println("END GAME");
                currentState = END_GAME_STATE;
                reset();
            }
        }else{
            //Check along x axis
            if(mySnake.headSnake.xPos < 0)
            {
                mySnake.headSnake.xPos = myGrid.getCols()-1;

            }else if(mySnake.headSnake.xPos > myGrid.getCols()-1)
            {
                mySnake.headSnake.xPos = 0;
            }
            //Check along y axis
            if(mySnake.headSnake.yPos < 0)
            {
                mySnake.headSnake.yPos = myGrid.getRows()-1;

            }else if(mySnake.headSnake.yPos > myGrid.getRows()-1)
            {
                mySnake.headSnake.yPos = 0;
            }
        }

    }
    private void checkCollision()
    {
        for(SnakeSegment seg: mySnake.getSegmentList())
        {
            if(mySnake.headSnake.xPos == seg.xPos && mySnake.headSnake.yPos == seg.yPos && mySnake.headSnake != seg)
            {
                currentState = END_GAME_STATE;
                reset();
                break;
            }
        }
    }
    private void reset()
    {
        mySnake.resetSnake();
        mySnake.setSnake(myGrid.getCols()/2,myGrid.getRows()/2);
        moveSnakeY = moveSnakeX = score = 0;
        myApple.setApplePos(generateApplePos());
    }
    public void render(GraphicsContext gc)
    {
        this.requestFocus();
        //clear graphics
        gc.clearRect(0,0,width,height);

        //renderFPSCounter(gc);
        //System.out.println(currentState);
       switch (currentState)
       {
           case MENU_STATE:
               buttonLayer.setVisible(true);
               renderMenu(gc);
               break;
           case GAME_STATE:
               renderGame(gc);
               break;
           case END_GAME_STATE:
               renderEndGame(gc);
               break;
       }

        renderFPSCounter(gc);

    }

    private void tickGame()
    {

        if(hardMode)
        {

            SnakeSegment seg = mySnake.getSegmentList().get(mySnake.getSegmentList().size()-1);
            seg.xPos = mySnake.headSnake.xPos + moveSnakeX;
            seg.yPos = mySnake.headSnake.yPos + moveSnakeY;
            mySnake.getSegmentList().remove(seg);
            mySnake.getSegmentList().add(0, seg);
            mySnake.headSnake = mySnake.getSegmentList().get(0);
            mySnake.tailSnake = mySnake.getSegmentList().get(mySnake.getSegmentList().size()-1);

        }else{

            if(counter == 1)
            {
                SnakeSegment seg = mySnake.getSegmentList().get(mySnake.getSegmentList().size()-1);
                seg.xPos = mySnake.headSnake.xPos + moveSnakeX;
                seg.yPos = mySnake.headSnake.yPos + moveSnakeY;
                mySnake.getSegmentList().remove(seg);
                mySnake.getSegmentList().add(0, seg);
                mySnake.headSnake = mySnake.getSegmentList().get(0);
                mySnake.tailSnake = mySnake.getSegmentList().get(mySnake.getSegmentList().size()-1);

                counter = 0;
            }else{

                counter++;
            }
        }



        /* check for collision */
        checkCollision();
    }

    private void tickEndGame()
    {
        moveSnakeY = moveSnakeX = 0;
    }
    private void tickMenu()
    {
        moveSnakeY = moveSnakeX = 0;
    }
    private void renderGame(GraphicsContext gc)
    {
        gc.clearRect(0,0,width,height);
       /* gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        //Draw Columns
        for(int i = 0; i < myGrid.getCols(); i++)
        {
            gc.strokeLine(i * myGrid.PIXEL_WIDTH,0,i * myGrid.PIXEL_WIDTH,height);
        }

        //Draw Rows
        for(int i = 0; i < myGrid.getRows(); i++)
        {
            gc.strokeLine(0,i * myGrid.PIXEL_HEIGHT,width,i * myGrid.PIXEL_HEIGHT);
        }*/
        if(hardMode)
        {
            gc.setStroke(Color.BLACK);
            gc.setLineWidth(4);
            gc.strokeRect(0,0,width,height);
        }

        //Draw apple
        gc.setFill(Color.RED);
        gc.fillRect(myApple.xPos * myGrid.PIXEL_WIDTH,myApple.yPos * myGrid.PIXEL_HEIGHT,myGrid.PIXEL_WIDTH,myGrid.PIXEL_HEIGHT);


        //Draw snake
        int cushion = myGrid.PIXEL_WIDTH/10;
        gc.setFill(Color.BLUE);
        for(SnakeSegment seg:mySnake.getSegmentList())
        {
            gc.fillRect(seg.xPos*myGrid.PIXEL_WIDTH + cushion,seg.yPos*myGrid.PIXEL_HEIGHT + cushion,myGrid.PIXEL_WIDTH - (2*cushion),myGrid.PIXEL_HEIGHT- (2*cushion));
        }

    }

    private void renderMenu(GraphicsContext gc)
    {
        myMenu.drawMenu(gc);
    }
    private void renderEndGame(GraphicsContext gc)
    {
        gc.setFill(Color.BLACK);
        gc.fillText("END",width/2,height/2);
    }
    private void renderFPSCounter(GraphicsContext gc)
    {
         /* FPS Counter */
        gc.setFont(new Font(20));
        if((System.currentTimeMillis() - lastTime) >= 1000)
        {
            frameRate = (int)(frameCounter/((System.currentTimeMillis() - lastTime)/1000));
            lastTime = System.currentTimeMillis();
            frameCounter = 0;
        }else{
            frameCounter++;
        }
        gc.setFill(Color.BLUE);
        gc.fillText("Framerate:" + frameRate, 5, 25);
        gc.fillText("Score: "+score,5,45);


    }

    private  Point2D generateApplePos()
    {
        int x = (int)Math.floor(myGrid.getCols()*Math.random());
        int y = (int)Math.floor(myGrid.getRows()*Math.random());

        if(x == mySnake.headSnake.xPos && y == mySnake.headSnake.yPos)
        {
            return generateApplePos();
        }else{
            return new Point2D(x,y);
        }
    }

}
