package toofer.gfx;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import toofer.GameConstants;
import toofer.level.Level;
import toofer.level.LevelManager;
import toofer.level.Tile;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * Created by benjamintoofer on 12/13/14.
 */
public class Screen {

    private LevelManager levelManager;
    private Tile[] tileArray;

    private PixelWriter pixelWriter;
    private PixelReader pixelReader;
    private Image image;
    private WritableImage writeImage;
    private WritablePixelFormat<IntBuffer> format;
    //private int[] dataBuffer;

    public Screen(LevelManager levelManager,Tile[] tileArray)
    {
        this.levelManager = levelManager;
        this.tileArray = tileArray;
        this.writeImage = new WritableImage(GameConstants.GAME_WIDTH,GameConstants.GAME_HEIGHT);
        this.pixelWriter = writeImage.getPixelWriter();

        this.format = WritablePixelFormat.getIntArgbInstance();
        //dataBuffer = new int[GameConstants.SPRITE_WIDTH * GameConstants.SPRITE_WIDTH];
    }

    public void render(GraphicsContext gc)
    {
        Level level = levelManager.getLevel();
        this.image = tileArray[0].tileImage;

        createImageData(level);

        gc.drawImage(writeImage,0,0,GameConstants.GAME_WIDTH * 5 ,GameConstants.GAME_HEIGHT * 5);
        /*for(int i = 0; i < level.levelMap.length; i++)
        {
            int tileIndex = level.levelMap[i];
            pixelWriter.setColor();
        }*/
    }

    private void createImageData(Level level)
    {
        int width = GameConstants.SPRITE_WIDTH;

        for(int i = 0; i < level.levelMap.length; i++)
        {
            int[] dataBuffer = new int[GameConstants.SPRITE_WIDTH * GameConstants.SPRITE_WIDTH];
            int tileNum = level.levelMap[i];
            int col = i % level.getCol();
            int row = i / level.getRow();
            image = tileArray[tileNum].tileImage;
            pixelReader = image.getPixelReader();
            pixelReader.getPixels(0,0,width,width,format,dataBuffer,0,width);
            //set pixels in bulk
            pixelWriter.setPixels(col * width,row * width,width ,width,format,dataBuffer,0,width);

            //set pixels individually
        }



    }

}
