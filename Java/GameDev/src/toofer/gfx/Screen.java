package toofer.gfx;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import toofer.Game;
import toofer.GameConstants;
import toofer.Player;
import toofer.level.Level;
import toofer.level.LevelManager;
import toofer.level.Tile;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * Created by benjamintoofer on 12/13/14.
 */

/*

Place different rendering layers
1. Map layer
2. player layer
3. anything else if necessary

Dont render map layer / background layer if offset doesnt change
Try to: If Map moves. removes pixels that are off the screen and add new pixels 


Dont render player unless moves or idle animation

 */
public class Screen {

    private LevelManager levelManager;
    private Tile[] tileArray;

    private PixelWriter pixelWriter;
    private PixelReader pixelReader;
    private Image image;
    private WritableImage writeImage;
    private WritablePixelFormat<IntBuffer> format;

    private Player myPlayer;

    public Screen(LevelManager levelManager,Tile[] tileArray, Player myPlayer)
    {
        this.levelManager = levelManager;
        this.tileArray = tileArray;
        this.myPlayer = myPlayer;

        init();

        //dataBuffer = new int[GameConstants.SPRITE_WIDTH * GameConstants.SPRITE_WIDTH];
    }

    private void init()
    {
        this.writeImage = new WritableImage(GameConstants.GAME_WIDTH * GameConstants.FRAME_SCALE,GameConstants.GAME_HEIGHT * GameConstants.FRAME_SCALE);
        this.pixelWriter = writeImage.getPixelWriter();
        this.format = WritablePixelFormat.getIntArgbInstance();
    }

    public void render(GraphicsContext gc)
    {
        Level level = levelManager.getLevel();
        this.image = tileArray[0].tileImage;

        createImageData(level);
        renderPlayer();

        gc.drawImage(writeImage,0,0,writeImage.getWidth() ,writeImage.getHeight() );

    }

    private void createImageData(Level level)
    {
        int width = GameConstants.SPRITE_WIDTH;

        for(int i = 0; i < level.levelMap.length; i++)
        {
            int[] dataBuffer = new int[GameConstants.SPRITE_WIDTH * GameConstants.SPRITE_WIDTH];
            int tileNum = level.levelMap[i];
            int colMap = i % level.getCol();
            int rowMap = i / level.getCol();

            image = tileArray[tileNum].tileImage;
            pixelReader = image.getPixelReader();
            pixelReader.getPixels(0,0,width,width,format,dataBuffer,0,width);


            //set pixels individually
            for(int colorIndex = 0; colorIndex < dataBuffer.length; colorIndex++)
            {
                int col = (colMap * width )+ (colorIndex % (width));
                int row = (rowMap * width ) + (colorIndex / (width));
                //System.out.println(col+" "+row);

                for(int j = 0;j < GameConstants.GAME_SCALE; j++)
                {
                    for(int h = 0;h < GameConstants.GAME_SCALE; h++)
                    {
                        if(col * GameConstants.GAME_SCALE + h < writeImage.getWidth() && row * GameConstants.GAME_SCALE + j < writeImage.getHeight())
                        {
                            pixelWriter.setArgb( col * GameConstants.GAME_SCALE + h,row * GameConstants.GAME_SCALE + j,dataBuffer[colorIndex]);
                        }
                    }

                }
            }
        }



    }

    private void renderPlayer()
    {
        int width = GameConstants.SPRITE_WIDTH;
        int[] dataBuffer = new int[width * width * 2];
        image = myPlayer.playerImage;
        pixelReader = image.getPixelReader();
        pixelReader.getPixels(0,0,width,width * 2,format,dataBuffer,0,width);

        //pixelWriter.setPixels((int)myPlayer.getxPos(),(int)myPlayer.getyPos(),width,width*2,format,dataBuffer,0,width);

        for(int colorIndex = 0; colorIndex < dataBuffer.length; colorIndex++)
        {
            if(dataBuffer[colorIndex] != 0)
            {
                int col = (colorIndex % (width));
                int row = (colorIndex / (width ));
                //System.out.println(col+" "+row);
                for(int j = 0;j < GameConstants.GAME_SCALE; j++)
                {
                    for(int h = 0;h < GameConstants.GAME_SCALE; h++)
                    {
                        pixelWriter.setArgb( myPlayer.getxPos()+ col * GameConstants.GAME_SCALE + h,myPlayer.getyPos()+row * GameConstants.GAME_SCALE + j,dataBuffer[colorIndex]);
                    }

                }
            }

        }

    }

}
